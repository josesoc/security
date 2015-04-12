package com.mios.rest;

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
 * 
 * Using default security user: user
 * Using default security password: f04cf9d8-01e2-41f5-94fe-561de1c2578a
 *
 */
public class MyHttpGetv2 
{
    public static void main( String[] args ) throws Exception
    {
    	String url="http://192.168.1.106:8080/greeting";

    	HttpClient client = HttpClientBuilder.create().build();
    	 
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
