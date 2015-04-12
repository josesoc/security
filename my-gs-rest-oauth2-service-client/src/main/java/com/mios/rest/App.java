package com.mios.rest;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;

/**
 * token: befaa589-745f-42b0-acad-c5eba34886b3
 * http://localhost:8080/api/v1/portfolios
 */
public class App {
	private static final String accessToken = "befaa589-745f-42b0-acad-c5eba34886b3";
	
	public static void main(String[] args) throws Exception {
		getToken();
		//post();
		//get();
	}
	
	public static void getToken() throws Exception {
		String tokenLocationURL="http://localhost:8080/oauth/token";
		String username="pepe";
		String password="12345678"; 
		String clientId="clientapp";
		String clientSecret="123456"; 
		
		String contentType = "application/json";
		
		OAuthClientRequest tokenRequest = OAuthClientRequest
                .tokenLocation(tokenLocationURL)
                .setGrantType(GrantType.PASSWORD)
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setUsername(username)
                .setPassword(password)
                .setScope("read write")
                .buildQueryMessage();
		
		tokenRequest.setHeader(OAuth.HeaderType.CONTENT_TYPE, contentType);
		
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthResourceResponse resourceResponse = oAuthClient.resource(tokenRequest, 
																	  OAuth.HttpMethod.POST,
																	  OAuthResourceResponse.class);

		System.out.println(resourceResponse.getResponseCode());
		System.out.println(resourceResponse.getBody());
	}

	public static void get() throws Exception {
		OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
				"http://localhost:8080/api/v1/portfolios").setAccessToken(accessToken).buildQueryMessage();

		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthResourceResponse resourceResponse = oAuthClient.resource(
				bearerClientRequest, OAuth.HttpMethod.GET,
				OAuthResourceResponse.class);

		System.out.println(resourceResponse.getResponseCode());
		System.out.println(resourceResponse.getBody());
	}

	public static void post() throws Exception {
		String body = "{\"name\":\"USA\", \"user\":{\"id\":\"1\"}} ";
		String contentType = "application/json";

		OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
				"http://localhost:8080/api/v1/portfolios").setAccessToken(accessToken).buildQueryMessage();

		// bearerClientRequest.setHeader(OAuth.HeaderType.CONTENT_TYPE,
		// "multipart/form-data");
		bearerClientRequest.setHeader(OAuth.HeaderType.CONTENT_TYPE,
				contentType);
		bearerClientRequest.setBody(body);

		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthResourceResponse resourceResponse = oAuthClient.resource(
				bearerClientRequest, OAuth.HttpMethod.POST,
				OAuthResourceResponse.class);

		System.out.println(resourceResponse.getResponseCode());
		System.out.println(resourceResponse.getBody());
	}
}
