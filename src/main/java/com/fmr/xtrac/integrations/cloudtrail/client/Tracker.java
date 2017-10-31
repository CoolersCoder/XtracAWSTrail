package com.fmr.xtrac.integrations.cloudtrail.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.cloudtrail.AWSCloudTrailAsync;
import com.amazonaws.services.cloudtrail.AWSCloudTrailAsyncClientBuilder;
import com.amazonaws.services.cloudtrail.model.DescribeTrailsRequest;
import com.amazonaws.services.cloudtrail.model.DescribeTrailsResult;
import com.amazonaws.services.cloudtrail.model.LookupAttribute;
import com.amazonaws.services.cloudtrail.model.LookupAttributeKey;
import com.amazonaws.services.cloudtrail.model.LookupEventsRequest;
import com.amazonaws.services.cloudtrail.model.LookupEventsResult;
import com.fmr.xtrac.feature.helper.AWSConfig;

/** 
* @author a605213 
* @version Oct 26, 2017 2:29:53 PM 
* 
*/
public class Tracker {
	private static void proxy(){
		  System.setProperty("http.proxyHost", "http.proxy.fmr.com");
		  System.setProperty("http.proxyPort", "8000");
		  
		  System.setProperty("https.proxyHost", "http.proxy.fmr.com");
		  System.setProperty("https.proxyPort", "8000");
	}
	
	public static void main(String[] args) {
		proxy();
		AWSCloudTrailAsync client = AWSConfig.getAWSCloudTrailAsyncInstance();
		 
		
		DescribeTrailsRequest describeTrailsRequest = new DescribeTrailsRequest();

		//set filter value
		LookupEventsRequest lookupEventsRequest = new LookupEventsRequest();
		
		
		//set filter Attribute
		LookupAttribute lookupAttribute = new LookupAttribute();
		lookupAttribute.setAttributeKey(LookupAttributeKey.Username);
		lookupAttribute.setAttributeValue("ruihu");
	 
		
		//set up lookup Evenet
		List<LookupAttribute> asList = Arrays.asList(lookupAttribute);		
		lookupEventsRequest.setLookupAttributes(asList);
	 
		System.out.println();
	    LookupEventsResult lookupEvents = client.lookupEvents(lookupEventsRequest);
	    lookupEvents.getEvents().stream().forEach(e->
		{
			String cloudTrailEvent = e.getCloudTrailEvent();
			System.out.println(cloudTrailEvent);
		});
	
	}	
}
