package com.elzakaria.instaOAuth.http.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NameValuePairBuilder {

	private List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
	
	
	public NameValuePairBuilder() {
		super();
	}

	/**
	 * add NameValuePairBuilder help chaining builder
	 * @param pCode code of pair
	 * @param pValue value of pair
	 * @return
	 */
	public NameValuePairBuilder addValuePair(final String pCode, final String pValue)
	{
		nameValuePairs.add(new NameValuePair(pCode, pValue));
		return this;
	}
	
	/**
	 * build final array of NameValuePair
	 * @return  NameValuePair array
	 */
	public NameValuePair[] build()
	{
		return nameValuePairs.toArray(new NameValuePair[nameValuePairs.size()]);
	}
	
}
