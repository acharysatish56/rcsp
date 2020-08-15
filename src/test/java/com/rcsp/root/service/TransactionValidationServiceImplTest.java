package com.rcsp.root.service;

import com.rcsp.root.common.ResponseType;
import com.rcsp.root.model.TestInput;
import com.rcsp.root.model.response.ErrorRecord;
import com.rcsp.root.model.response.Result;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionValidationServiceImplTest {

    @Autowired
    TransactionValidationServiceImpl transactionValidationService;

    @Test
    public void testValidateWithSuccessScenario() {
        Optional<Result> result = transactionValidationService.validate(TestInput.getTransactions());
        assertEquals(result.get().getResult(), ResponseType.SUCCESSFUL);
    }

    @Test
    @Description("test result with duplicate reference number scenario")
    public void validateWithDuplicateReferenceNumberScenario() {
        Set<ErrorRecord> expectedList = new HashSet<>();
        expectedList.add(new ErrorRecord(101l, "1112"));
        expectedList.add(new ErrorRecord(101l, "1111"));
        Optional<Result> result = transactionValidationService.validate(TestInput.getTransactionsWithDuplicateResult());
        assertEquals(result.get().getResult(), ResponseType.DUPLICATE_REFERENCE);
        Assertions.assertArrayEquals(result.get().getErrorRecords().toArray(), expectedList.toArray(),  "Array Equal Test ");
    }

    @Test
    @Description("test result with incorrect end balance scenario")
    public void validateWithWithIncorrectEndBalanceScenario() {
        Set<ErrorRecord> expectedList = new HashSet<>();
        expectedList.add(new ErrorRecord(102l, "111l"));
        Optional<Result> result = transactionValidationService.validate(TestInput.getTransactionsWithIncorrectEndBalance());
        assertEquals(result.get().getResult(), ResponseType.INCORRECT_END_BALANCE);
        Assertions.assertArrayEquals(result.get().getErrorRecords().toArray(), expectedList.toArray(),  "Array Equal Test ");
    }

    @Test
    @Description("test result with incorrect end balance scenario")
    public void validateWithWithIncorrectEndBalanceScenarioWithNegativeMutation() {
        Set<ErrorRecord> expectedList = new HashSet<>();
        expectedList.add(new ErrorRecord(101l, "111l"));
        Optional<Result> result = transactionValidationService.validate(TestInput.getTransactionsWithIncorrectEndBalanceWithNegativeMutation());
        assertEquals(result.get().getResult(), ResponseType.INCORRECT_END_BALANCE);
        Assertions.assertArrayEquals(result.get().getErrorRecords().toArray(), expectedList.toArray(),  "Array Equal Test ");
    }
    @Test
    @Description("test result with incorrect end balance scenario")
    public void validateWithWithIncorrectEndBalanceAndDuplicateReferenceNumberScenario() {
        Set<ErrorRecord> expectedList = new HashSet<>();
        expectedList.add(new ErrorRecord(101l, "1112"));
        expectedList.add(new ErrorRecord(101l, "1111"));
        Optional<Result> result = transactionValidationService.validate(TestInput.getTransactionsWithDuplicateReferenceIncorrectBalance());
        assertEquals(result.get().getResult(), ResponseType.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE);
        Assertions.assertArrayEquals(result.get().getErrorRecords().toArray(), expectedList.toArray(),  "Array Equal Test ");
    }
}
