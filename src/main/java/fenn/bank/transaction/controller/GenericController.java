package fenn.bank.transaction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class GenericController extends CorsUtils {
	private static final Logger LOG = LoggerFactory.getLogger(GenericController.class.toString());

	protected void crossOriginComplianceSetting(HttpServletRequest request) {
		if(CorsUtils.isCorsRequest(request)) {
			LOG.info("origin specified {} ",request.getHeader("Origin"));
		}else {
			LOG.info("request {} ",request);
		}
	}

}
