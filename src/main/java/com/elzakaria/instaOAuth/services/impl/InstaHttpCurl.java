package com.elzakaria.instaOAuth.services.impl;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elzakaria.instaOAuth.http.util.NameValuePairBuilder;
import com.elzakaria.instaOAuth.services.IInstaHttpCurl;

@Service
public class InstaHttpCurl implements IInstaHttpCurl {

	private static final Logger LOGGER = Logger.getLogger(InstaHttpCurl.class);
	
	private static final String ENDPOINT_AUTHORIZE = "https://api.instagram.com/oauth/access_token";

	@Autowired
	private NameValuePairBuilder nvpBuilder;

	@Override
	public String curlForToken(final String pCode) throws Exception {

		HttpClient httpClient = new HttpClient();

		PostMethod postMethod = new PostMethod(ENDPOINT_AUTHORIZE);

		// Provide custom retry handler is necessary
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(2, false));

		nvpBuilder.addValuePair("client_id", "93126b0c5bb548de9e681319c9d89d99");
		nvpBuilder.addValuePair("client_secret", "8bd58d203d0d4575a104a3f5d73cf977");
		nvpBuilder.addValuePair("grant_type", "authorization_code");
		nvpBuilder.addValuePair("redirect_uri", "http://localhost:9999/instaOAuth/redirect_uri");
		nvpBuilder.addValuePair("code", pCode);

		postMethod.setRequestBody(nvpBuilder.build());

		String messageResponse;
		try {
			final int statusCode = httpClient.executeMethod(postMethod);
			messageResponse = null;
			if (HttpStatus.SC_OK == (statusCode)) {

				// Read the response body.
				byte[] responseBody = postMethod.getResponseBody();

				// Deal with the response.
				// Use caution: ensure correct character encoding and is not binary data
				messageResponse = new String(responseBody);

				LOGGER.info("Having the response from instagram " + messageResponse);

			} 
		} finally {
			postMethod.releaseConnection();
		}
		return messageResponse;
	}
}
