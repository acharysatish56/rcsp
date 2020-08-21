package com.rcsp.root.service.validator;

import com.google.common.collect.ImmutableMap;
import com.rcsp.root.common.ResponseType;
import com.rcsp.root.model.response.ErrorRecord;
import com.rcsp.root.model.response.Result;
import com.rcsp.root.model.transaction.Transaction;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
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
    public static Predicate<Transaction> isConflictInBalance = transaction -> isAddingBalanceMatching.negate().test(transaction);

    /**
     *
     */
    public static ImmutableMap<ResponseType, BiFunction<Set<ErrorRecord>, Set<ErrorRecord>, Result>> RESULT =
            ImmutableMap.<ResponseType, BiFunction<Set<ErrorRecord>, Set<ErrorRecord>, Result>>builder()
                    .put(ResponseType.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE, (first, second) -> {
                        first.addAll(second);
                        return new Result(ResponseType.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE, first);
                    })
                    .put(ResponseType.DUPLICATE_REFERENCE, (first, second) -> new Result(ResponseType.DUPLICATE_REFERENCE, first))
                    .put(ResponseType.INCORRECT_END_BALANCE, (first, second) -> new Result(ResponseType.INCORRECT_END_BALANCE, second))
                    .put(ResponseType.SUCCESSFUL, (first, second) -> new Result(ResponseType.SUCCESSFUL, Collections.emptySet()))
                    .build();

    /**
     *
     */
    public static ImmutableMap<ResponseType, BiPredicate<Set<ErrorRecord>, Set<ErrorRecord>>> RESULT_FILTER =
            ImmutableMap.<ResponseType, BiPredicate<Set<ErrorRecord>, Set<ErrorRecord>>>builder()
                    .put(ResponseType.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE, (first, second) -> !first.isEmpty() && !second.isEmpty())
                    .put(ResponseType.DUPLICATE_REFERENCE, (first, second) -> !first.isEmpty() && second.isEmpty())
                    .put(ResponseType.INCORRECT_END_BALANCE, (first, second) -> first.isEmpty() && !second.isEmpty())
                    .put(ResponseType.SUCCESSFUL, (first, second) -> first.isEmpty() && second.isEmpty())
                    .build();
}
