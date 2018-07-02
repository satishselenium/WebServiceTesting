package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;


public class GetAPITest extends TestBase {
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
  public void getTest() throws ClientProtocolException, IOException, JSONException {
	  restclient = new RestClient();
	  httpresponse = restclient.connect(URI);
	  
		//To get the status code
		int statuscode= httpresponse.getStatusLine().getStatusCode();
		System.out.println("Status code = "+statuscode);
		//Assert
		Assert.assertEquals(statuscode, RESPONSE_CODE_200,"Response code is not = "+RESPONSE_CODE_200);

		//get response
		String responsestring = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		System.out.println("Response  = "+responsestring);
		
		//convert string to Json object.
		JSONObject responseJSON = new JSONObject(responsestring);
		System.out.println("Response JSON from Api  = "+responseJSON);
		
		
		//Get particular value from responseJson using Jpath (Like xpath in selenium)
		//Call method to extract Jpath value from the responseJson
		String perpage = TestUtil.getValueByJPath(responseJSON,"/per_page");
		System.out.println("Perpage = "+perpage);
		//Assert
		Assert.assertEquals(perpage, "3","perpage value is not = "+3);
		
		String Total = TestUtil.getValueByJPath(responseJSON,"/total");
		System.out.println("total = "+Total);
		//Assert
		Assert.assertEquals(Total, "12","Total is not = "+12);
		
		//Extract JSON array from Response Json --Array value start with []
		String data = TestUtil.getValueByJPath(responseJSON,"/data");
		System.out.println(data);
		
		String LastNameFroFirstData = TestUtil.getValueByJPath(responseJSON,"/data[0]/last_name");
		System.out.println(LastNameFroFirstData);
		
		Header[] headersArray = httpresponse.getAllHeaders();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		for(Header header:headersArray) {
			headerMap.put(header.getName(), header.getValue());
		}
		System.out.println("headers array--->"+headerMap);
  }
  
  //Get method with header
  @Test
  public void getTestwithHeaders() throws ClientProtocolException, IOException, JSONException {
	  restclient = new RestClient();
	  
	  //Pass all the geader details in a hashmap
	  HashMap<String, String> HeadersDetails = new HashMap<String, String>();
	  HeadersDetails.put("Content-Type", "application/json; charset=utf-8");
//	  HeadersDetails.put("Content-Type", "application/json; charset=utf-8");
//	  HeadersDetails.put("Content-Type", "application/json; charset=utf-8");
	  
	  httpresponse = restclient.connect(URI,HeadersDetails);
	  
		//To get the status code
		int statuscode= httpresponse.getStatusLine().getStatusCode();
		System.out.println("Status code = "+statuscode);
		//Assert
		Assert.assertEquals(statuscode, RESPONSE_CODE_200,"Response code is not = "+RESPONSE_CODE_200);

		//get response
		String responsestring = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		System.out.println("Response  = "+responsestring);
		
		//convert string to Json object.
		JSONObject responseJSON = new JSONObject(responsestring);
		System.out.println("Response JSON from Api  = "+responseJSON);
		
		
		//Get particular value from responseJson using Jpath (Like xpath in selenium)
		//Call method to extract Jpath value from the responseJson
		String perpage = TestUtil.getValueByJPath(responseJSON,"/per_page");
		System.out.println("Perpage = "+perpage);
		//Assert
		Assert.assertEquals(perpage, "3","perpage value is not = "+3);
		
		String Total = TestUtil.getValueByJPath(responseJSON,"/total");
		System.out.println("total = "+Total);
		//Assert
		Assert.assertEquals(Total, "12","Total is not = "+12);
		
		//Extract JSON array from Response Json --Array value start with []
		String data = TestUtil.getValueByJPath(responseJSON,"/data");
		System.out.println(data);
		
		String LastNameFroFirstData = TestUtil.getValueByJPath(responseJSON,"/data[0]/last_name");
		System.out.println(LastNameFroFirstData);
		
		Header[] headersArray = httpresponse.getAllHeaders();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		for(Header header:headersArray) {
			headerMap.put(header.getName(), header.getValue());
		}
		System.out.println("headers array--->"+headerMap);
  }


  
  
  
  
  
  
  
}


//{
//    "page": 2,
//    "per_page": 3,
//    "total": 12,
//    "total_pages": 4,
//    "data": [
//        {
//            "id": 4,
//            "first_name": "Eve",
//            "last_name": "Holt",
//            "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg"
//        },
//        {
//            "id": 5,
//            "first_name": "Charles",
//            "last_name": "Morris",
//            "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/stephenmoon/128.jpg"
//        },
//        {
//            "id": 6,
//            "first_name": "Tracey",
//            "last_name": "Ramos",
//            "avatar": "https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg"
//        }
//    ]

