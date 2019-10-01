package com.elzakaria.instaOAuth.services;


/**
 * 
 * @author Zakaria BOUAZZA
 *
 */
public interface IInstaHttpCurl {
	
	/**
	 * Proceed to post to retrieve an access token from the code received in the redirect_uri <br>
	 * <code>
	 * curl -F 'client_id=CLIENT_ID' <br>
	    -F 'client_secret=CLIENT_SECRET'  <br>
	    -F 'grant_type=authorization_code' <br>
	    -F 'redirect_uri=AUTHORIZATION_REDIRECT_URI'  <br>
	    -F 'code=CODE'  <br>
	    https://api.instagram.com/oauth/access_token
	 * </code>
	 * @return access token
	 */
	String curlForToken(String pCode)  throws Exception;
	
	
	/**
	 * Get information about the owner of the access token
	 * @param pAccessToken
	 * @return json content
	 * @throws Exception 
	 * GET  https://api.instagram.com/users/self
	 */
	String getUsersSelf(String pAccessToken) throws Exception;

}
