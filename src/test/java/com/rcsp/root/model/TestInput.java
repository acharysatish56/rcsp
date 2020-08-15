package com.rcsp.root.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.rcsp.root.model.transaction.Transaction;

public class TestInput {
	
	/**
	 * 
	 * @return
	 */
	public static List<Transaction> getTransactions() {
		Transaction one = new Transaction();
		one.setAccNumber("111l");
		one.setTransNumber(101L);
		one.setStartBalance(new BigDecimal(1000));
		one.setMutation(new BigDecimal(1000));
		one.setEndBalance(new BigDecimal(2000));
		one.setDescription("DEPOSITED");

		Transaction two = new Transaction();
		two.setAccNumber("111l");
		two.setTransNumber(102L);
		two.setStartBalance(new BigDecimal(2000));
		two.setMutation(new BigDecimal(1000));
		two.setEndBalance(new BigDecimal(3000));
		two.setDescription("DEPOSITED");
		return Arrays.asList(one, two);
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Transaction> getTransactionsWithDuplicateResult() {
		Transaction one = new Transaction();
		one.setTransNumber(101L);
		one.setAccNumber("1111");
		one.setStartBalance(new BigDecimal(1000));
		one.setMutation(new BigDecimal(1000));
		one.setEndBalance(new BigDecimal(2000));
		one.setDescription("DEPOSITED");

		Transaction two = new Transaction();
		two.setTransNumber(101L);
		two.setAccNumber("1112");
		two.setStartBalance(new BigDecimal(2000));
		two.setMutation(new BigDecimal(1000));
		two.setEndBalance(new BigDecimal(3000));
		two.setDescription("DEPOSITED");
		return Arrays.asList(one, two);
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Transaction> getTransactionsWithIncorrectEndBalance() {
		Transaction one = new Transaction();
		one.setTransNumber(101L);
		one.setAccNumber("111l");
		one.setStartBalance(new BigDecimal(1000));
		one.setMutation(new BigDecimal(1000));
		one.setEndBalance(new BigDecimal(2000));
		one.setDescription("DEPOSITED");

		Transaction two = new Transaction();
		two.setTransNumber(102L);
		two.setAccNumber("111l");
		two.setStartBalance(new BigDecimal(2000));
		two.setMutation(new BigDecimal(1000));
		two.setEndBalance(new BigDecimal(4000));
		two.setDescription("DEPOSITED");
		return Arrays.asList(one, two);
	}

	/**
	 * -negative mutation test with failure scenario
	 * @return
	 */
	public static List<Transaction> getTransactionsWithIncorrectEndBalanceWithNegativeMutation() {
		Transaction one = new Transaction();
		one.setTransNumber(101L);
		one.setAccNumber("111l");
		one.setStartBalance(new BigDecimal(1000));
		one.setMutation(new BigDecimal(-1000));
		one.setEndBalance(new BigDecimal(10));
		one.setDescription("DEPOSITED");
		return Arrays.asList(one);
	}

	/**
	 * -negative mutation test with success scenario
	 * @return
	 */
	public static List<Transaction> getTransactionsWithSuccessWithNegativeMutation() {
		Transaction one = new Transaction();
		one.setAccNumber("111l");
		one.setTransNumber(101L);
		one.setStartBalance(new BigDecimal(2000));
		one.setMutation(new BigDecimal(-1000));
		one.setEndBalance(new BigDecimal(1000));
		one.setDescription("WITHDRAW");
		return Arrays.asList(one);
	}

	/**
	 * 
	 * @return
	 */
	public static List<Transaction> getTransactionsWithDuplicateReferenceIncorrectBalance() {
		Transaction one = new Transaction();
		one.setTransNumber(101L);
		one.setAccNumber("1111");
		one.setStartBalance(new BigDecimal(1000));
		one.setMutation(new BigDecimal(1000));
		one.setEndBalance(new BigDecimal(2000));
		one.setDescription("DEPOSITED");

		Transaction two = new Transaction();
		two.setTransNumber(101L);
		two.setAccNumber("1112");
		two.setStartBalance(new BigDecimal(2000));
		two.setMutation(new BigDecimal(1000));
		two.setEndBalance(new BigDecimal(4000));
		two.setDescription("DEPOSITED");
		return Arrays.asList(one, two);
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<Transaction> getTransactionsWithInternalServerError() {
		Transaction one = new Transaction();
		one.setTransNumber(null);
		one.setAccNumber("111l");
		one.setStartBalance(null);
		one.setMutation(new BigDecimal(1000));
		one.setEndBalance(new BigDecimal(2000));
		one.setDescription("DEPOSITED");
		return Arrays.asList(one);
	}
}
