package fenn.bank.transaction.entities.repository.services;

import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fenn.bank.transaction.dto.Transaction;
import fenn.bank.transaction.dto.TransactionInfoResponse;
import fenn.bank.transaction.dto.TransactionsHistoryResponse;
import fenn.bank.transaction.entities.Transactions;
import fenn.bank.transaction.entities.repository.TransactionsRepository;
import fenn.bank.transaction.exceptions.TransactionException;
import fenn.bank.transaction.external.services.AccountConnector;

@Service
public class TransactionsService {
	private static final Logger LOG = LoggerFactory.getLogger(TransactionsService.class.toString());

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	private TransactionsRepository transactionRepository;

	@Autowired
	private AccountConnector accountConnector;

	// Save Account entity in the h2 database.
	public void save(final Transactions customerAccount) {
		transactionRepository.save(customerAccount);
	}

	public TransactionInfoResponse  transactionProcessing(Transaction transaction)throws TransactionException,URISyntaxException{
		LOG.info("transactionProcessing in");
		TransactionInfoResponse transactionInfoResponse = new  TransactionInfoResponse();
		if(accountConnector.checkAccountExistency(transaction.getCustomerId())){
			Transactions transactions =  new Transactions();
			transactions.setCustomerId(transaction.getCustomerId());
			transactions.setAmount(transaction.getAmount());
			transactions.setTimeStamp(new Timestamp(System.currentTimeMillis()));
			save(transactions);
			transactionInfoResponse.setResponseMessage("transaction processed");
		}else {
			transactionInfoResponse.setResponseMessage("could not process transaction.");
		}
		LOG.info("transactionProcessing out");
		return transactionInfoResponse;
	}


	public TransactionsHistoryResponse retreiveAllUserTransaction(String userId){
		LOG.info("retreiveAllUserTransaction out");
		TransactionsHistoryResponse txResponse = new TransactionsHistoryResponse();
		txResponse.setTransactionList( new ArrayList<>());
		List<Transactions> userAccCollection = transactionRepository.findAllByCustomerId(userId);
		if(!CollectionUtils.isEmpty(userAccCollection)) {
			Transaction transaction;
			for(Transactions item : userAccCollection) {
				transaction= new Transaction();
				BeanUtils.copyProperties(item, transaction);
				transaction.setTransactionId(item.getId());
				txResponse.getTransactionList().add(transaction);
			}
			LOG.info("retreiveAllUserTransaction out");
			return txResponse;
		}
		else {
			LOG.info("retreiveAllUserTransaction out");
			return txResponse;
		}
	}
}
