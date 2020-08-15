package com.rcsp.root.service.validator;

import com.rcsp.root.common.ResponseType;
import com.rcsp.root.model.TestInput;
import com.rcsp.root.model.response.ErrorRecord;
import com.rcsp.root.model.response.Result;
import com.rcsp.root.model.transaction.Transaction;
import javaslang.Tuple;
import javaslang.Tuple2;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Description;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;


    @SuppressWarnings("deprecation")
    @Test
    @Description("Get Duplicate Reference Account Number withDuplicateNumber")
    public void getDuplicateReferenceAccountNumberTestWithDuplicateNumber() {
        Tuple2<List<Transaction>, Set<ErrorRecord>> duplicateAccNo = validationService.getDuplicateReferenceAccountNumber(TestInput.getTransactionsWithDuplicateResult());
        Assert.assertEquals(duplicateAccNo._2.size(), 2);
    }

    @SuppressWarnings("deprecation")
    @Test
    @Description("Get Duplicate Reference Account Number with No DuplicateNumber")
    public void getDuplicateReferenceAccountNumberTestWithNoDuplicateNumber() {
        Tuple2<List<Transaction>, Set<ErrorRecord>> duplicateAccNo = validationService.getDuplicateReferenceAccountNumber(TestInput.getTransactions());
        Assert.assertEquals(duplicateAccNo._2.size(), 0);
    }

    @SuppressWarnings("deprecation")
    @Test
    @Description("Get Duplicate Reference Account Number withDuplicateNumber")
    public void getBalanceMissMatchAccountNumberTestWithExistCase() {
        Tuple2<Set<ErrorRecord>, Set<ErrorRecord>> balanceMissMatchAccNo = validationService.getBalanceMissMatchAccountNumber(Tuple.of(TestInput.getTransactionsWithIncorrectEndBalance(), null));
        Assert.assertEquals(balanceMissMatchAccNo._2.size(), 1);
    }

    @SuppressWarnings("deprecation")
    @Test
    @Description("Get Duplicate Reference Account Number with No DuplicateNumber")
    public void getBalanceMissMatchAccountNumberTestWithNotExistingCase() {
        Tuple2<Set<ErrorRecord>, Set<ErrorRecord>> balanceMissMatchAccNo = validationService.getBalanceMissMatchAccountNumber(Tuple.of(TestInput.getTransactions(), null));
        Assert.assertEquals(balanceMissMatchAccNo._2.size(), 0);
    }

    @SuppressWarnings("deprecation")
    @Test
    @Description("Prepare Result With Duplicate Reference Account No")
    public void prepareResultTestWithDuplicateReference() {
        Set<ErrorRecord> dupRefAccNo = new HashSet<>();
        Set<ErrorRecord> balMissMatchAccNo = new HashSet<>();
        dupRefAccNo.add(new ErrorRecord(10101l, "10101"));
        Result result = validationService.prepareResult(Tuple.of(dupRefAccNo, balMissMatchAccNo));
        Assert.assertEquals(result.getResult(), ResponseType.DUPLICATE_REFERENCE);
    }

    @SuppressWarnings("deprecation")
    @Test
    @Description("Prepare Result With Incorrect End Balance")
    public void prepareResultTestWithIncorrectEndBalance() {
        Set<ErrorRecord> dupRefAccNo = new HashSet<>();
        Set<ErrorRecord> balMissMatchAccNo = new HashSet<>();
        balMissMatchAccNo.add(new ErrorRecord(10101l, "10101"));
        Result result = validationService.prepareResult(Tuple.of(dupRefAccNo, balMissMatchAccNo));
        Assert.assertEquals(result.getResult(), ResponseType.INCORRECT_END_BALANCE);
    }

    @SuppressWarnings("deprecation")
    @Test
    @Description("Prepare Result With Duplicate Reference and Incorrect End Balance")
    public void prepareResultTestWithDuplicateReferenceIncorrectEndBalance() {
        Set<ErrorRecord> dupRefAccNo = new HashSet<>();
        Set<ErrorRecord> balMissMatchAccNo = new HashSet<>();
        dupRefAccNo.add(new ErrorRecord(10101l, "10101"));
        balMissMatchAccNo.add(new ErrorRecord(101011l, "10101"));
        Result result = validationService.prepareResult(Tuple.of(dupRefAccNo, balMissMatchAccNo));
        Assert.assertEquals(result.getResult(), ResponseType.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE);
    }

    @SuppressWarnings("deprecation")
    @Test
    @Description("Prepare Result With Success Result")
    public void prepareResultTestWithSuccessResult() {
        Set<ErrorRecord> dupRefAccNo = new HashSet<>();
        Set<ErrorRecord> balMissMatchAccNo = new HashSet<>();
        Result result = validationService.prepareResult(Tuple.of(dupRefAccNo, balMissMatchAccNo));
        Assert.assertEquals(result.getResult(), ResponseType.SUCCESSFUL);
    }
}
