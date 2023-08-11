package udb.testcases;

import static org.testng.Assert.assertNotNull;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import udb.dataprovider.CsvDataProvider;
import udb.endpoints.UserEndpoints;

public class TestGetUserByID {
	UserEndpoints endpoints;
	String id;
	
	public TestGetUserByID() {
		super();
	}
	@BeforeSuite()
	public void beforesuite(ITestContext context) {
		
	}
	

	@Test(dataProvider = "csvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void verifyGetByID(HashMap<String,String> hashmap) {
		if(hashmap.get("TestCaseNo").equalsIgnoreCase("TC102")) {
			id = hashmap.get("Request");
			System.out.println("THE REQUEST ID is "+id);
			Response res = endpoints.getUserByID(Integer.parseInt(id));
			System.out.println("THE DETAILS ARE ");
			res.then().log().body();
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertNotNull(res);
		}
		
		
		
	}

	@BeforeTest
	public void beforeTest(ITestContext context) {
		endpoints = new UserEndpoints();
	}

	@AfterTest
	public void afterTest() {
	}

}
