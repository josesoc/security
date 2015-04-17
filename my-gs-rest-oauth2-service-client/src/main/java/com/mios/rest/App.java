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
	
	/**
	 * HTTP Request a Token: 
	 * 
	 *    $ curl -X POST -vu clientapp:123456 http://localhost:6000/oauth/token 
	 *           -H "Accept: application/json" 
	 *           -d "password=12345678&username=pepe&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
	 * 
			POST /oauth/token HTTP/1.1
			Authorization: Basic Y2xpZW50YXBwOjEyMzQ1Ng==
			User-Agent: curl/7.33.0
			Host: 127.0.0.1:6000
			Accept: application/json
			Content-Length: 111
			Content-Type: application/x-www-form-urlencoded

			password=12345678&username=pepe&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp
			
			
			HTTP/1.1 200 OK
			Server: Apache-Coyote/1.1
			X-Content-Type-Options: nosniff
			X-XSS-Protection: 1; mode=block
			Cache-Control: no-cache, no-store, max-age=0, must-revalidate
			Pragma: no-cache
			Expires: 0
			X-Frame-Options: DENY
			X-Application-Context: application:hsql:9090
			Cache-Control: no-store
			Pragma: no-cache
			Content-Type: application/json;charset=UTF-8
			Transfer-Encoding: chunked
			Date: Fri, 17 Apr 2015 09:59:30 GMT

			cc
			{
  				"access_token" : "9f653980-3b2a-4bb6-aa87-afd4ad2c4114",
  				"token_type" : "bearer",
  				"refresh_token" : "63fd2425-1ceb-4947-9f9e-2b5a41b02b33",
  				"expires_in" : 43199,
  			"scope" : "read write"
			}
			0		
		

	 * @throws Exception
	 */
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
