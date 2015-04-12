package com.mios.rest.basicauth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * 
 * Service:  /Users/jmroldanv/workspace/git_spring_guides/my-gs-rest-basicauth-service
 * 
 * Using default security user: user
 * Using default security password: bac373a8-1a82-4deb-97a1-05c64cef8137  (change on each service execution)
 *
 *
 */
public class MyHttpGetBasicAuth 
{
    public static void main( String[] args ) throws Exception
    {
    	String url="http://localhost:8080/greeting";
    	
    	System.out.println("200 OK");
    	System.out.println("400 Not found");
    	System.out.println("401 Unauthorized");
    	System.out.println("\n");
    	
    	CredentialsProvider provider = new BasicCredentialsProvider();
    	UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("user", "bac373a8-1a82-4deb-97a1-05c64cef8137");
    	provider.setCredentials(AuthScope.ANY, credentials);
    	HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
    	 
    	HttpResponse response = client.execute(new HttpGet(url));
    	int statusCode = response.getStatusLine().getStatusCode();
    	System.out.println(statusCode);
    	if (statusCode == HttpStatus.SC_OK)
    	{   		
    		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
	        String line = "";
	        while ((line = rd.readLine()) != null) {
	          System.out.println(line);
	        }
    	}
    }
}
