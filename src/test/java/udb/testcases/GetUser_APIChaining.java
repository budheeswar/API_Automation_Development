package udb.testcases;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import udb.endpoints.UserEndpoints;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class GetUser_APIChaining {
	UserEndpoints endpoints;
	Integer idValue;

	@Test(priority = 1)
	public void verifyGetAllUsersAPI(ITestContext context) {
		Response res = endpoints.getAllUsers();
		JsonPath jsPath = res.jsonPath();
		idValue = jsPath.get("[1].id");
		context.setAttribute("userID", idValue);
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertNotNull(res);

	}

	@Test(dependsOnMethods = "verifyGetAllUsersAPI")
	public void verifyGetByID(ITestContext context) {
		int userid = (int) context.getAttribute("userID");
		System.out.println("THE ID is "+userid);
		Response res = endpoints.getUserByID(userid);
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertNotNull(res);

	}

	@BeforeTest
	public void beforeTest() {
		endpoints = new UserEndpoints();
	}

	@AfterTest
	public void afterTest() {
		endpoints = null;
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
