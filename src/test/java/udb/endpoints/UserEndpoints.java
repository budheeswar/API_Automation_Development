package udb.endpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.Properties;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import udb.model.User;
import udb.util.CommonUtils;

public class UserEndpoints {
    CommonUtils utils = new CommonUtils();
    Properties config = utils.getConfigFile();
  
	public Response getAllUsers() {
		Response res = when().get(config.getProperty("getAllUsersURL"));
		return res;
	}

	public Response getUserByID(int id) {
		Response res = given().pathParam("ID", id).when().get(config.getProperty("getAllUsersURL") + "/{ID}");
		return res;
	}

	public Response createUser(User user) {
		Response res = given()
				.header("Authorization", "Bearer "+config.getProperty("token"))
				.contentType("application/json; charset=utf-8").accept(ContentType.JSON).body(user).when()
				.post(config.getProperty("postUserURL"));
		return res;
	}

}
