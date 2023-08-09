package udb.endpoints;
import static io.restassured.RestAssured.when;

import io.restassured.response.Response;
import udb.requestURL.UserRequestURLs;
public class UserEndpoints {
	UserRequestURLs urls = new UserRequestURLs();
	public Response getAllUsers() {
		
		Response res = when().get(urls.getAllUsersURL);
		return res;
	}

}
