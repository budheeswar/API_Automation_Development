package udb.endpoints;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import io.restassured.response.Response;
import udb.requestURL.UserRequestURLs;

public class UserEndpoints {
	UserRequestURLs urls = new UserRequestURLs();

	public Response getAllUsers() {

		Response res = when().get(urls.getAllUsersURL);
		return res;
	}

	public Response getUserByID(int id) {
		//int id1 =4302961; 
		Response res = given().pathParam("ID", id).when().get(urls.getAllUsersURL+"/{ID}");
		return res;
	}

}
