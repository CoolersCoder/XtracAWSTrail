package com.fmr.xtrac.feature.helper;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudtrail.AWSCloudTrailAsync;
import com.amazonaws.services.cloudtrail.AWSCloudTrailAsyncClientBuilder;
import com.fmr.xtrac.feature.utils.Constants;
import com.fmr.xtrac.integrations.cloudtrail.exceptions.ProxyException;

/**
 * @author a605213
 * @version Oct 26, 2017 2:31:44 PM
 * 
 */
public class AWSConfig {

	private static AWSCloudTrailAsync awsCloudTrailAsync = null;

	private AWSConfig(){}
	
	public static AWSCloudTrailAsync getAWSCloudTrailAsyncInstance() {
		if (awsCloudTrailAsync == null) {
			return awsCloudTrailAsync = new AWSConfig().getTrailClient();
		} else {
			return awsCloudTrailAsync;
		}
	}

	public AWSCloudTrailAsync getTrailClient() {
		AWSCloudTrailAsync awsTrailClient = AWSCloudTrailAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1).withClientConfiguration(
				clientProxyConfig("http://http.proxy.fmr.com", "8000")).build();

		return awsTrailClient;
	}

	/**
	 * Set proxy
	 * 
	 * @param proxyHost
	 * @param proxyPort
	 * @return ClientConfiguration
	 */
	private ClientConfiguration clientProxyConfig(String proxyHost, String proxyPort) {
		ClientConfiguration clientConfiguration = null;
		try {
			clientConfiguration = new ClientConfiguration();
			int port = Integer.parseInt(proxyHost);
			clientConfiguration.setProxyHost(proxyHost);
			clientConfiguration.setProxyPort(port);
		} catch (Exception e) {
			new ProxyException(e.getMessage());
		}
		return clientConfiguration;
	}

}
