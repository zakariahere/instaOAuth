package com.elzakaria.instaOAuth.http.util;

import org.junit.Assert;
import org.junit.Test;

import com.elzakaria.instaOAuth.beans.json.oauth.OAuthJSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONConverterTest {

	private static final String RESPONSE_OAUTH = "{\r\n" + 
			"		    \"access_token\": \"fb2e77d.47a0479900504cb3ab4a1f626d174d2d\",\r\n" + 
			"		    \"user\": {\r\n" + 
			"		        \"id\": \"1574083\",\r\n" + 
			"		        \"username\": \"snoopdogg\",\r\n" + 
			"		        \"full_name\": \"Snoop Dogg\",\r\n" + 
			"		        \"profile_picture\": \"...\"\r\n" + 
			"		    }\r\n" + 
			"		}";

	@Test
	public void testGetTokenFromResponse() {
		Gson gson = (new GsonBuilder()).create();
		OAuthJSON oAuthJSON = gson.fromJson(RESPONSE_OAUTH, OAuthJSON.class);
		Assert.assertEquals("fb2e77d.47a0479900504cb3ab4a1f626d174d2d", oAuthJSON.getAccessToken());
	}
}
