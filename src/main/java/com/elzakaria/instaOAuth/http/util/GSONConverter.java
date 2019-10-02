package com.elzakaria.instaOAuth.http.util;

import com.elzakaria.instaOAuth.beans.json.oauth.OAuthJSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONConverter {

	
	/**
	 * The answer from instagram is as follows
		 * {
		    "access_token": "fb2e77d.47a0479900504cb3ab4a1f626d174d2d",
		    "user": {
		        "id": "1574083",
		        "username": "snoopdogg",
		        "full_name": "Snoop Dogg",
		        "profile_picture": "..."
		    }
		}
	 * this method retrieve solely the access token using GSON
	 * @return access token
	 */
	public static final String getTokenFromResponse(final String pResponseInstagram) {
		Gson gson = (new GsonBuilder()).create();
		OAuthJSON oAuthJSON = gson.fromJson(pResponseInstagram, OAuthJSON.class);
		return oAuthJSON.getAccessToken();		
	}
}
