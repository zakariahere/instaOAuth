package com.elzakaria.instaOAuth.services.impl;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.elzakaria.instaOAuth.http.util.GSONConverter;
import com.elzakaria.instaOAuth.http.util.NameValuePairBuilder;
import com.elzakaria.instaOAuth.services.IInstaHttpCurl;

@Service
public class InstaHttpCurl implements IInstaHttpCurl {

	private static final Logger LOGGER = Logger.getLogger(InstaHttpCurl.class);
	
	private static final String ACCESS_TOKEN_PARAM_NAME = "access_token";
	
	private static final String ENDPOINT_AUTHORIZE = "https://api.instagram.com/oauth/access_token";
	private static final String ENDPOINT_USERS_SELF = "https://api.instagram.com/v1/users/self/";
	private static final String ENDPOINT_MEDIA_RECENT_SELF = "https://api.instagram.com/v1/users/self/media/recent/";

	@Autowired
	private NameValuePairBuilder nvpBuilder;

	@Value("${prop_client_id}")
	private String clientId;
	
	@Value("${prop_client_secret}")
	private String ClienSecret;
	
	@Value("${prop_grant_type}")
	private String grantType;
	
	@Value("${prop_redirect_uri}")
	private String redirectURI;
	
	
	@Override
	public String curlForToken(final String pCode) throws Exception {

		final HttpClient httpClient = new HttpClient();

		final PostMethod postMethod = new PostMethod(ENDPOINT_AUTHORIZE);

		// Provide custom retry handler is necessary
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(2, false));

		nvpBuilder.addValuePair("client_id", clientId);
		nvpBuilder.addValuePair("client_secret", ClienSecret);
		nvpBuilder.addValuePair("grant_type", grantType);
		nvpBuilder.addValuePair("redirect_uri", redirectURI);
		nvpBuilder.addValuePair("code", pCode);

		//prepare curl post
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
				messageResponse = GSONConverter.getTokenFromResponse(messageResponse);
			} 
		} finally {
			//close connection
			postMethod.releaseConnection();
		}
		
		
		return messageResponse;
	}


	

	@Override
	public String getUsersSelf(String pAccessToken) throws Exception {
		final HttpClient httpClient = new HttpClient();
		final GetMethod getMethod = new GetMethod(ENDPOINT_USERS_SELF);

		nvpBuilder.addValuePair(ACCESS_TOKEN_PARAM_NAME, pAccessToken);

		getMethod.setQueryString(nvpBuilder.build());
		
		// Provide custom retry handler is necessary
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler(2, false));

		String messageResponse;
		try {
			final int statusCode = httpClient.executeMethod(getMethod);
			messageResponse = null;
			if (HttpStatus.SC_OK == (statusCode)) {

				// Read the response body.
				byte[] responseBody = getMethod.getResponseBody();

				// Deal with the response.
				// Use caution: ensure correct character encoding and is not binary data
				messageResponse = new String(responseBody);

				LOGGER.info("Having the response from instagram " + messageResponse);

			} 
		} finally {
			//close connection
			getMethod.releaseConnection();
		}
		return messageResponse;
	}
	



	@Override
	public String getRecentMedia(String pAccessToken) throws Exception {
		final HttpClient httpClient = new HttpClient();
		final GetMethod getMethod = new GetMethod(ENDPOINT_MEDIA_RECENT_SELF);

		nvpBuilder.addValuePair(ACCESS_TOKEN_PARAM_NAME, pAccessToken);

		getMethod.setQueryString(nvpBuilder.build());
		
		// Provide custom retry handler is necessary
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler(2, false));

		String messageResponse;
		try {
			final int statusCode = httpClient.executeMethod(getMethod);
			messageResponse = null;
			if (HttpStatus.SC_OK == (statusCode)) {

				// Read the response body.
				byte[] responseBody = getMethod.getResponseBody();

				// Deal with the response.
				// Use caution: ensure correct character encoding and is not binary data
				messageResponse = new String(responseBody);

				LOGGER.info("Having the response from instagram " + messageResponse);

			} 
		} finally {
			//close connection
			getMethod.releaseConnection();
		}
		return messageResponse;
	}
	
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}


	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	/**
	 * @return the clienSecret
	 */
	public String getClienSecret() {
		return ClienSecret;
	}


	/**
	 * @param clienSecret the clienSecret to set
	 */
	public void setClienSecret(String clienSecret) {
		ClienSecret = clienSecret;
	}


	/**
	 * @return the grantType
	 */
	public String getGrantType() {
		return grantType;
	}


	/**
	 * @param grantType the grantType to set
	 */
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}


	/**
	 * @return the redirectURI
	 */
	public String getRedirectURI() {
		return redirectURI;
	}


	/**
	 * @param redirectURI the redirectURI to set
	 */
	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}




	/**
	 * @return the nvpBuilder
	 */
	public NameValuePairBuilder getNvpBuilder() {
		return nvpBuilder;
	}




	/**
	 * @param nvpBuilder the nvpBuilder to set
	 */
	public void setNvpBuilder(NameValuePairBuilder nvpBuilder) {
		this.nvpBuilder = nvpBuilder;
	}

	
}
