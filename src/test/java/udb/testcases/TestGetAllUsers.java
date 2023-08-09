package udb.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import udb.endpoints.UserEndpoints;

public class TestGetAllUsers {
	UserEndpoints endpoints;
  @Test
  public void verifyGetAllUsersAPI() {
	  Response res = endpoints.getAllUsers();
	  res.then().log().body();
  }
  @BeforeTest
  public void beforeTest() {
	  endpoints = new UserEndpoints();  
  }

  @AfterTest
  public void afterTest() {
  }

}
