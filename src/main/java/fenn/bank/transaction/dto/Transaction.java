package fenn.bank.transaction.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
	private String customerId;
	private Integer transactionId;
	private BigDecimal amount;
	private Timestamp timeStamp;	
	
	public Transaction() {

	}
	public Transaction(String customerId, BigDecimal amount) {
		this.customerId = customerId;
		this.amount = amount;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	
}
