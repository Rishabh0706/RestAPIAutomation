package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase{
	
	TestBase testBase;
	String url;
	String apiUrl;
	String actualUrl;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	@BeforeMethod
	public void setUp(){
		
		testBase = new TestBase();
		url = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		actualUrl = url + apiUrl;

	}
	
	@Test(priority=1)
	public void getAPITest() throws ClientProtocolException, IOException{
		
		restClient = new RestClient();
		httpResponse = restClient.get(actualUrl);
		
		//a. Status Code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ------> "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200");
				
		//b. Json String
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON ------> "+responseJson);
		
		//value from JSON Object
		String perPageValue = TestUtil.getValueByJPath(responseJson,"/per_page");
		System.out.println("Value of per page is --> "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		String totalValue = TestUtil.getValueByJPath(responseJson,"/total");
		System.out.println("Value of total is --> "+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//Value from JSON Array
		String lastName = TestUtil.getValueByJPath(responseJson,"/data[1]/last_name");
		String id = TestUtil.getValueByJPath(responseJson,"/data[1]/id");
		String avatar = TestUtil.getValueByJPath(responseJson,"/data[1]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson,"/data[1]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
				
		//c. All Headers
		Header[] headersArray = httpResponse.getAllHeaders();
			
		HashMap<String,String> allHeaders = new HashMap<String,String>();
			
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue());
		}
				
		System.out.println("Header Array ------> "+allHeaders);
		
		
	}
	
	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException{
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		httpResponse = restClient.get(actualUrl, headerMap);
		
		//a. Status Code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code ------> "+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status Code is not 200");
				
		//b. Json String
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON ------> "+responseJson);
		
		//value from JSON Object
		String perPageValue = TestUtil.getValueByJPath(responseJson,"/per_page");
		System.out.println("Value of per page is --> "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		String totalValue = TestUtil.getValueByJPath(responseJson,"/total");
		System.out.println("Value of total is --> "+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//Value from JSON Array
		String lastName = TestUtil.getValueByJPath(responseJson,"/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson,"/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson,"/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson,"/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
				
		//c. All Headers
		Header[] headersArray = httpResponse.getAllHeaders();
			
		HashMap<String,String> allHeaders = new HashMap<String,String>();
			
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue());
		}
				
		System.out.println("Header Array ------> "+allHeaders);
		
	}
	
	
	
	

}
