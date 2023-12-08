package fenn.bank.transaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import fenn.bank.transaction.dto.Transaction;
import fenn.bank.transaction.dto.TransactionInfoResponse;
import fenn.bank.transaction.dto.TransactionsHistoryResponse;
import fenn.bank.transaction.entities.repository.services.TransactionsService;


@RestController
@RequestMapping(value="/bankTransaction")
public class TansactionInfoController extends GenericController{
	private static final Logger LOG = LoggerFactory.getLogger(TansactionInfoController.class.toString());
	
	@Autowired
	private TransactionsService transactionService;
	
	@PostMapping(value="/process",produces = "application/json")
	public ResponseEntity<TransactionInfoResponse> processTransaction(@RequestBody Transaction transaction) throws Exception{
		LOG.info("****** processTransaction in ******");
		return ResponseEntity
				.ok()
				.body(transactionService.transactionProcessing(transaction));
	}
	
	@GetMapping(value="/transactions/{userId}",produces = "application/json")
	public ResponseEntity<TransactionsHistoryResponse> retrieveUserTransactions(@PathVariable("userId") @NotNull String userId) throws Exception{
		LOG.info("****** retrieveAccountData in ******");
		return ResponseEntity
				.ok()
				.body(transactionService.retreiveAllUserTransaction(userId));
	}
}
