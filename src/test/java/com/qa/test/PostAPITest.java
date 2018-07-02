package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

import junit.framework.Assert;

	public class PostAPITest extends TestBase {
		TestBase testbase;
		RestClient restclient;
		String URI;
		String apiurl;
		String url;
		CloseableHttpResponse httpresponse;
		
		@BeforeMethod
		public void initializeData() throws ClientProtocolException, IOException, JSONException {
			testbase =new TestBase();
			url = properties.getProperty("URL");
			apiurl = properties.getProperty("serviceurl");
			URI = url+apiurl;
			
		}
		@Test
		public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException, JSONException {
		  restclient = new RestClient();
		  //headers detail
		  HashMap<String, String> HeadersDetails = new HashMap<String, String>();
		  HeadersDetails.put("Content-Type", "application/json; charset=utf-8");
		    
		  //Jackson api to marshaling data (from java object to Json object) to create proper PayLoad.	
			ObjectMapper mapper = new ObjectMapper();
			Users users =new Users("morpheus","leader");    //Expected User Object
			//Java Object to Json file--------------------MARSHALING------------------------------
			mapper.writeValue(new File("E:\\Selenium\\WebServiceTesting\\src\\test\\java\\com\\qa\\data\\Users.json"), users);	
			//Json file to Json string.	
			String UserJSONString = mapper.writeValueAsString(users);
			System.out.println(UserJSONString);
			//Call the post method from the Rest Client
			httpresponse = RestClient.Post(URI, UserJSONString, HeadersDetails);
			//Get response code
			int StatusCode = httpresponse.getStatusLine().getStatusCode();
			Assert.assertEquals(StatusCode,TestBase.RESPONSE_CODE_201 );
			//JSon string response
			String ResponseString = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
			//convert string to Json object.
			JSONObject responseJSON = new JSONObject(ResponseString);
			System.out.println("Response JSON from Api  = "+responseJSON);
			//Validate the response has the same value sent in time of post.
			//JSon to Java Object -----------------UNMARSHALING----------------------
			Users userResponseObj = mapper.readValue(ResponseString, Users.class);   //Actual User Object
			System.out.println(userResponseObj);
			//Compare expected and actual user object.
			Assert.assertTrue(users.getName().equals(userResponseObj.getName()));
			Assert.assertTrue(users.getJOb().equals(userResponseObj.getJOb()));
			//Get id and created date
			System.out.println(userResponseObj.getId());
			System.out.println(userResponseObj.getCreatedAt());
		
		}
		
		
		
		
		
		
		
		
	}
	
		

