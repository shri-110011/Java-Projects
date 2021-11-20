package com.basicsstrong.practice;

import com.basicsstrong.annotation.Column;
import com.basicsstrong.annotation.PrimaryKey;

public class TransactionHistory {
	
	@PrimaryKey
	private long transactionId;
	
	@Column
	private int accountNumber;
	
	@Column
	private String name;
	
	@Column
	private String transactionType;
	
	@Column
	private double amount;

	public TransactionHistory() {
		
	}

	public TransactionHistory(int accountNumber, String name, String transactionType, double amount) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "TransactionHistory [transactionId=" + transactionId + ", accountNumber=" + accountNumber + ", name="
				+ name + ", transactionType=" + transactionType + ", amount=" + amount + "]";
	}

}
