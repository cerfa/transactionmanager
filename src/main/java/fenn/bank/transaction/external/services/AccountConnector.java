package fenn.bank.transaction.external.services;

import fenn.bank.transaction.exceptions.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class AccountConnector {
	private static final Logger LOG = LoggerFactory.getLogger(AccountConnector.class.toString());
	@Autowired
	private RestTemplate restServiceCaller;
	@Value("${connect.account.url}")
	private String checkAccountUrl;

	@Value("${connect.account.balance.url}")
	private String checkAccountBalanceUrl;

	public  boolean checkAccountExistence(String userId) throws TransactionException {
		LOG.info("checkAccountExistence in");
		try {
			String checkAccountUrlCall = checkAccountUrl.concat(userId);
			URI uri = new URI(checkAccountUrlCall);
			ResponseEntity<String> result = restServiceCaller.getForEntity(uri, String.class);
			LOG.info("checkAccountExistence out");
			String responseBody = result.getBody();
			return StringUtils.hasLength(responseBody) && responseBody.equalsIgnoreCase("OK");
		} catch (Exception except){
			throw new TransactionException(except.getMessage());
		}
	}
	public  boolean checkAccountBalanceExistence(String accountId, String amount) throws TransactionException {
		LOG.info("checkAccountExistence and balance  in");
		try{
			String checkAccountUrlCall = checkAccountBalanceUrl.concat(accountId).concat("/").concat(amount);
		    URI uri = new URI(checkAccountUrlCall);
		    ResponseEntity<String> result = restServiceCaller.getForEntity(uri, String.class);
		   LOG.info("checkAccountBalance out");
		   String responseBody = result.getBody();
		   return StringUtils.hasLength(responseBody) && responseBody.equalsIgnoreCase("OK");
	    } catch (Exception except){
		   throw new TransactionException(except.getMessage());
	    }
	}
}
