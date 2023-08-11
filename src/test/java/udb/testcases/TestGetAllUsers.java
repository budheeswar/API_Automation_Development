package udb.testcases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import udb.endpoints.UserEndpoints;

public class TestGetAllUsers {
	UserEndpoints endpoints;
	Integer idValue;
	
	public TestGetAllUsers() {
		super();
	}
	@BeforeSuite()
	public void beforesuite(ITestContext context) {
	}

	@Test
	public void verifyGetAllUsersAPI(ITestContext context) {
		Response res = endpoints.getAllUsers();
		System.out.println("THE ALL USERS ARE LISTED BELOW");
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertNotNull(res);
		
	}

	@BeforeTest
	public void beforeTest(ITestContext context) {
		endpoints = new UserEndpoints();
	}

	@AfterTest
	public void afterTest(ITestContext context) {
		
	}

}
