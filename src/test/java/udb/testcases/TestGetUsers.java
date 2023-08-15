package udb.testcases;

import java.util.HashMap;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import udb.endpoints.UserEndpoints;
import udb.report.ReportGenerator;

public class TestGetUsers {
	UserEndpoints endpoints;
	Integer idValue;
	ReportGenerator reportGenerator = null;

	public void verifyGetAllUsersAPI(HashMap<String, String> hashmap, ReportGenerator reportGenerator) {
		endpoints = new UserEndpoints();
		this.reportGenerator = reportGenerator;
		Response res = endpoints.getAllUsers();
		Assert.assertNotNull(res);
		reportGenerator.logMessage("Get All Users API Response working", Status.PASS);
		System.out.println("THE ALL USERS ARE LISTED BELOW");
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		reportGenerator.logMessage("Status Code is"+res.getStatusCode(), Status.PASS);
		

	}

	public void verifyGetByID(HashMap<String, String> hashmap, ReportGenerator reportGenerator) {
		endpoints = new UserEndpoints();
		String id = hashmap.get("Request");
		System.out.println("THE REQUEST ID is " + id);
		Response res = endpoints.getUserByID(Integer.parseInt(id));
		Assert.assertNotNull(res);
		reportGenerator.logMessage("Get By ID Response Taken", Status.PASS);
		System.out.println("THE DETAILS ARE ");
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		reportGenerator.logMessage("The Response 200 came for ID"+id, Status.PASS);
		
	}

}
