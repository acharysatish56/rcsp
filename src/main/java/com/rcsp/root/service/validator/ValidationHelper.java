package com.rcsp.root.service.validator;

import com.rcsp.root.model.transaction.Transaction;

import java.util.function.Predicate;

/**
 * Created by satish on 10/08/20.
 */
public class ValidationHelper {

    /**
     *
     */
    public static Predicate<Transaction> isAddingBalanceMatching =
            (transaction) -> transaction.getStartBalance().add(transaction.getMutation()).compareTo(transaction.getEndBalance()) == 0;

    /**
     *
     */
    public static Predicate<Transaction> isMinusBalanceMatching =
            (transaction) -> transaction.getStartBalance().subtract(transaction.getMutation()).compareTo(transaction.getEndBalance()) == 0;

    /**
     *
     */
    public static Predicate<Transaction> isConflictInBalance = transaction -> isAddingBalanceMatching.or(isMinusBalanceMatching).negate().test(transaction);
}
