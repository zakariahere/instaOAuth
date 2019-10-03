package com.elzakaria.instaOAuth.http.util;

import org.apache.commons.httpclient.NameValuePair;
import org.junit.Assert;
import org.junit.Test;

public class NameValuePairBuilderTest {

	NameValuePairBuilder nameValuePairBuilder = new NameValuePairBuilder();
	
	@Test
	public void testBuild() {
		nameValuePairBuilder.addValuePair("code1", "value1");
		nameValuePairBuilder.addValuePair("code2", "value2");
		
		NameValuePair[] nameValuePairs = nameValuePairBuilder.build();
		
		Assert.assertEquals(2, nameValuePairs.length);
			
	}
	
}
