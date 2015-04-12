package com.mios.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * stock-portfolio
 * 
 * http://localhost:8080/api/v1/roles
 * {"name":"ROLE_USER"}
 * 
 * @author jmroldanv
 *
 */
public class MyHttpPost  {

	public static void main(String[] args) throws Exception {		
        String url="http://localhost:8080/api/v1/roles"; 
        
        StringEntity input = new StringEntity("{\"name\":\"ROLE_USER\"}");
        input.setContentType("application/json");
        
        HttpPost post = new HttpPost(url);
        post.setEntity(input);
        
//        JSONObject json = new JSONObject();
//        json.put("name1", "value1");
//        json.put("name2", "value2");
//        StringEntity se = new StringEntity( json.toString());
        
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
	}
}
