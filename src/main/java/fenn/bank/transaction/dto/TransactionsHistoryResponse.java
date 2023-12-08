package fenn.bank.transaction.dto;

import java.util.List;

public class TransactionsHistoryResponse {
 private List<Transaction> transactionList;

public TransactionsHistoryResponse() {
}

public List<Transaction> getTransactionList() {
	return transactionList;
}

public void setTransactionList(List<Transaction> transactionList) {
	this.transactionList = transactionList;
}

}
