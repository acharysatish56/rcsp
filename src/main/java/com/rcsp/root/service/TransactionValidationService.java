package com.rcsp.root.service;

import com.rcsp.root.model.response.Result;
import com.rcsp.root.model.transaction.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Created by satish on 08/08/20.
 */
public interface TransactionValidationService {

    /**
     * @param transactionList
     * @return
     */
    Optional<Result> validate(final List<Transaction> transactionList);
}
