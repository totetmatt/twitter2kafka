package org.totetmatt.twitter2kafka.configuration;


import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Component
@Configuration
@ConfigurationProperties( prefix = "twitter" )
public class TwitterConfiguration {
	@NotNull
	private String OAuthConsumerKey;
	@NotNull
	private String OAuthConsumerSecret;
	@NotNull
	private String OAuthAccessToken;
	@NotNull
	private String OAuthAccessTokenSecret;

    public String getOAuthConsumerKey() {
		return OAuthConsumerKey;
	}
	public void setOAuthConsumerKey(String oAuthConsumerKey) {
		OAuthConsumerKey = oAuthConsumerKey;
	}
	public String getOAuthConsumerSecret() {
		return OAuthConsumerSecret;
	}
	public void setOAuthConsumerSecret(String oAuthConsumerSecret) {
		OAuthConsumerSecret = oAuthConsumerSecret;
	}
	public String getOAuthAccessToken() {
		return OAuthAccessToken;
	}
	public void setOAuthAccessToken(String oAuthAccessToken) {
		OAuthAccessToken = oAuthAccessToken;
	}
	public String getOAuthAccessTokenSecret() {
		return OAuthAccessTokenSecret;
	}
	public void setOAuthAccessTokenSecret(String oAuthAccessTokenSecret) {
		OAuthAccessTokenSecret = oAuthAccessTokenSecret;
	}
	
	
	
}
