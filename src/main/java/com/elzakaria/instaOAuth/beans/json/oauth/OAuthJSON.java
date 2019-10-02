package com.elzakaria.instaOAuth.beans.json.oauth;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class OAuthJSON implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204722428639629749L;

	@SerializedName(value = "access_token")
	private String accessToken;

	private User user;
	
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	//TODO put in junit.
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();
		OAuthJSON jsonRepresentation = gson.fromJson("{\r\n" + 
				"    \"access_token\": \"fb2e77d.47a0479900504cb3ab4a1f626d174d2d\",\r\n" + 
				"    \"user\": {\r\n" + 
				"        \"id\": \"1574083\",\r\n" + 
				"        \"username\": \"snoopdogg\",\r\n" + 
				"        \"full_name\": \"Snoop Dogg\",\r\n" + 
				"        \"profile_picture\": \"...\"\r\n" + 
				"    }\r\n" + 
				"}", OAuthJSON.class);
		
		System.out.println(jsonRepresentation);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OAuthJSON [accessToken=" + accessToken + ", user=" + user + "]";
	}

}
