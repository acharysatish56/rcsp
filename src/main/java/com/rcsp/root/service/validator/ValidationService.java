package com.rcsp.root.service.validator;

import com.rcsp.root.common.ResponseType;
import com.rcsp.root.model.response.ErrorRecord;
import com.rcsp.root.model.response.Result;
import com.rcsp.root.model.transaction.Transaction;
import javaslang.Tuple;
import javaslang.Tuple2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ValidationService {

    /**
     * NOTE: added both records if it has duplicate
     *
     * @param transactions
     * @return
     */
    public Tuple2<List<Transaction>, Set<ErrorRecord>> getDuplicateReferenceAccountNumber(List<Transaction> transactions) {
        log.info("checking duplicate reference number");
        Set<ErrorRecord> errorRecordList = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getTransNumber))
                .entrySet()
                .stream()
                .filter(refMap -> refMap.getValue().size() >= 2)
                .flatMap(longListEntry -> longListEntry.getValue().stream())
                .map(transaction -> new ErrorRecord(transaction.getTransNumber(), transaction.getAccNumber()))
                .collect(Collectors.toSet());
        return Tuple.of(transactions, errorRecordList);
    }

    /**
     * @param tuple2
     * @return
     */
    public Tuple2<Set<ErrorRecord>, Set<ErrorRecord>> getBalanceMissMatchAccountNumber(Tuple2<List<Transaction>, Set<ErrorRecord>> tuple2) {
        log.info("checking balance miss match issue");
        Set<ErrorRecord> balanceConflictAccNumbers = tuple2._1.stream()
                .filter(ValidationHelper.isConflictInBalance)
                .map(transaction -> new ErrorRecord(transaction.getTransNumber(), transaction.getAccNumber()))
                .collect(Collectors.toSet());
        return Tuple.of(tuple2._2, balanceConflictAccNumbers);
    }

    /**
     * @param tuple2
     * @return
     */
    public Result prepareResult(Tuple2<Set<ErrorRecord>, Set<ErrorRecord>> tuple2) {
        log.info("preparing result");
        if (!tuple2._1().isEmpty() && !tuple2._2().isEmpty()) {
            //DUPLICATE_REFERENCE_INCORRECT_END_BALANCE
            tuple2._1.addAll(tuple2._2);
            return new Result(ResponseType.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE, tuple2._1);
        } else if (!tuple2._1().isEmpty() && tuple2._2().isEmpty()) {
            //DUPLICATE_REFERENCE
            return new Result(ResponseType.DUPLICATE_REFERENCE, tuple2._1);
        } else if (tuple2._1().isEmpty() && !tuple2._2().isEmpty()) {
            //INCORRECT_END_BALANCE
            return new Result(ResponseType.INCORRECT_END_BALANCE, tuple2._2);
        } else {
            return new Result(ResponseType.SUCCESSFUL, Collections.emptySet());
        }
    }
}
