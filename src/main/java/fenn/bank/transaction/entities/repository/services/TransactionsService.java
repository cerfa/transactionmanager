package fenn.bank.transaction.entities.repository.services;

import fenn.bank.transaction.dto.Transaction;
import fenn.bank.transaction.dto.TransactionInfoResponse;
import fenn.bank.transaction.dto.TransactionsHistoryResponse;
import fenn.bank.transaction.entities.Transactions;
import fenn.bank.transaction.entities.repository.TransactionsRepository;
import fenn.bank.transaction.exceptions.TransactionException;
import fenn.bank.transaction.external.services.AccountConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

	public TransactionInfoResponse  transactionProcessing(Transaction transaction)throws TransactionException {
		LOG.info("transactionProcessing in");
		TransactionInfoResponse transactionInfoResponse = new TransactionInfoResponse();
		try {
			if (accountConnector.checkAccountExistence(transaction.getCustomerId())) {
				if(accountConnector.checkAccountBalanceExistence(transaction.getAccountId(),
						transaction.getAmount().toString())) {
					Transactions transactions = new Transactions();
					transactions.setAccountId(transaction.getAccountId());
					transactions.setCustomerId(transaction.getCustomerId());
					transactions.setAmount(transaction.getAmount());
					transactions.setTimeStamp(new Timestamp(System.currentTimeMillis()));
					save(transactions);
					transactionInfoResponse.setResponseMessage("transaction processed");
				} else {
					transactionInfoResponse.setResponseMessage("Not enough balance in the account");
				}
			} else {
				transactionInfoResponse.setResponseMessage("could not process transaction.");
			}
		} catch (Exception except) {
			throw new TransactionException(except.getMessage());
		}
		LOG.info("transactionProcessingDone  out");
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
			LOG.info("retreiveAllUserTransactionDone out");
			return txResponse;
		}
		else {
			LOG.info("retreiveAllUserTransaction out");
			return txResponse;
		}
	}
}
