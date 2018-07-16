package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase{
	
	TestBase testBase;
	String url;
	String apiUrl;
	String actualUrl;
	RestClient restClient;
	
	@BeforeMethod
	public void setUp(){
		
		testBase = new TestBase();
		url = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		actualUrl = url + apiUrl;

	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException{
		
		restClient = new RestClient();
		restClient.get(actualUrl);
		
	}
	
	
	
	

}
