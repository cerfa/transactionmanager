package fenn.bank.transaction.dto;

public class TransactionInfoResponse {
 private String responseMessage;

public TransactionInfoResponse() {
}

public TransactionInfoResponse(String responseMessage) {
	this.responseMessage = responseMessage;
}

public String getResponseMessage() {
	return responseMessage;
}

public void setResponseMessage(String responseMessage) {
	this.responseMessage = responseMessage;
} 
 
}
