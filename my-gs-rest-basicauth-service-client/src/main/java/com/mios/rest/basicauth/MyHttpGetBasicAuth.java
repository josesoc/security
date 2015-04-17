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
 * Using default security password: a2ec5668-c961-47a8-89f0-5fbb43d486f2  (change on each service execution)
 *
 *
 *	GET /greeting HTTP/1.1
	Host: 127.0.0.1:6000
	Connection: Keep-Alive
	User-Agent: Apache-HttpClient/4.4.1 (Java/1.7.0_60)
	Accept-Encoding: gzip,deflate

	GET /greeting HTTP/1.1
	Host: localhost:6000
	Connection: Keep-Alive
	User-Agent: Apache-HttpClient/4.4.1 (Java/1.7.0_60)
	Accept-Encoding: gzip,deflate
	Authorization: Basic dXNlcjphMmVjNTY2OC1jOTYxLTQ3YTgtODlmMC01ZmJiNDNkNDg2ZjI=
	
	
	HTTP/1.1 401 Unauthorized
	Server: Apache-Coyote/1.1
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	WWW-Authenticate: Basic realm="Spring"
	Content-Type: application/json;charset=UTF-8
	Transfer-Encoding: chunked
	Date: Fri, 17 Apr 2015 11:13:05 GMT

	96
	{"timestamp":1429269185806,"status":401,"error":"Unauthorized","message":"Full authentication is required to access this resource","path":"/greeting"}
	0

	HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Strict-Transport-Security: max-age=31536000 ; includeSubDomains
	Content-Type: application/json;charset=UTF-8
	Transfer-Encoding: chunked
	Date: Fri, 17 Apr 2015 11:13:05 GMT

	22
	{"id":1,"content":"Hello, World!"}
	0

 *
 *
 */

public class MyHttpGetBasicAuth 
{
    public static void main( String[] args ) throws Exception
    {
    	String url="http://localhost:8080/greeting";
    	
    	//for testing only
    	//String url="http://localhost:6000/greeting";
    	
    	System.out.println("200 OK");
    	System.out.println("400 Not found");
    	System.out.println("401 Unauthorized");
    	System.out.println("\n");
    	
    	CredentialsProvider provider = new BasicCredentialsProvider();
    	UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("user", "a2ec5668-c961-47a8-89f0-5fbb43d486f2");
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
