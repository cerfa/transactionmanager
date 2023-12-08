package fenn.bank.transaction.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity annotation specifies that the class is mapped to a database table.
@Entity
@Table(name="TRANSCATIONS",schema = "DBACCT")
public class Transactions {

	// @Id annotation specifies the primary key of an entity.
	// @GeneratedValue provides the generation strategy specification for the primary key values.
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	private String customerId;
	private BigDecimal amount;
	private Timestamp timeStamp;


	public Transactions() {
	}
 

	public Transactions(String customerId, BigDecimal amount, Timestamp timeStamp) {
		this.customerId = customerId;
		this.amount = amount;
		this.timeStamp = timeStamp;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
}