package fenn.bank.transaction.external.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fenn.bank.transaction.exceptions.TransactionException;

@Service
public class AccountConnector {
	private static final Logger LOG = LoggerFactory.getLogger(AccountConnector.class.toString());
	@Autowired
	private RestTemplate restServiceCaller;
	@Value("${connect.account.url}")
	private String checkAccountUrl;

	public  boolean checkAccountExistency(String userId) throws TransactionException, URISyntaxException{
		LOG.info("checkAccountExistency in");
		String checkAccountUrlCall = checkAccountUrl.concat(userId);
		URI uri = new URI(checkAccountUrlCall);
		ResponseEntity<String> result = restServiceCaller.getForEntity(uri, String.class);
		LOG.info("checkAccountExistency out");
		return result.getBody() != null ? result.getBody().equalsIgnoreCase("OK"):false;
	} 
}
