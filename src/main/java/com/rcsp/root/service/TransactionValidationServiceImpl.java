package com.rcsp.root.service;

import com.rcsp.root.model.response.Result;
import com.rcsp.root.model.transaction.Transaction;
import com.rcsp.root.service.validator.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by satish on 08/08/20.
 */
@Service
@Slf4j
public class TransactionValidationServiceImpl implements TransactionValidationService {

    @Autowired
    private ValidationService validationService;

    @Override
    public Optional<Result> validate(List<Transaction> transactions) {
        return Optional.ofNullable(transactions)
                .map(transactions1 -> validationService.getDuplicateReferenceAccountNumber(transactions1))
                .map(validatedDupTrans -> validationService.getBalanceMissMatchAccountNumber(validatedDupTrans))
                .map(validatedBalanceIssueTrans -> validationService.prepareResult(validatedBalanceIssueTrans));
    }
}
