package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;

import com.qa.base.TestBase;

public class RestClient extends TestBase {

	//Get Method
	public CloseableHttpResponse connect (String url) throws ClientProtocolException, IOException, JSONException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget= new HttpGet(url);
		CloseableHttpResponse httpresponse=httpclient.execute(httpget);
		
		return httpresponse;
	}
	
	// To restrict access
	//Get method where in header some more details require like user id, password/ auth token  and all
	public CloseableHttpResponse connect (String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException, JSONException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget= new HttpGet(url);
		//tricky part as below
		for(HashMap.Entry<String, String> entry : headerMap.entrySet()) {
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpresponse=httpclient.execute(httpget);
		return httpresponse;
	}
	// Post Method
	public static CloseableHttpResponse Post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString)); //Post all payload
		//for headers
		for(HashMap.Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse httpresponse=httpclient.execute(httppost);
		return httpresponse;
	}
	
}
