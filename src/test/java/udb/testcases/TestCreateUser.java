package udb.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import udb.endpoints.UserEndpoints;
import udb.model.User;
import udb.report.ReportGenerator;
import udb.util.CommonUtils;

public class TestCreateUser {

	UserEndpoints endpoints;
	List<HashMap<String, String>> list;
	User user;
	ReportGenerator reportGenerator = null;

	public void verifyEmailValidation(HashMap<String, String> hashmap, ReportGenerator reportGenerator) {
		try {
			this.reportGenerator = reportGenerator;
			// Get the Payload
			list = this.getTheUserPayload(hashmap);
			HashMap<String, String> payload = new HashMap<>();
			// Set the Payload
			for (HashMap<String, String> h : list) {
				payload.putAll(h);
			}
			user = new User();
			user.setName(payload.get("name"));
			user.setGender(payload.get("gender"));
			user.setEmail(payload.get("email"));
			user.setStatus(payload.get("status"));

			reportGenerator.logMessage("The POST Request " + hashmap.get("Request"), Status.INFO);
			endpoints = new UserEndpoints();
			reportGenerator.logMessage("Hitting POST URL for Email Validation", Status.INFO);
			Response response = endpoints.createUser(user);
			System.out.println("THE PAYLOAD IS " + hashmap.toString());
			System.out.println("The RESPONSE for verifyEmailValidation ");
			System.out.println(response.then().log().body());
			Assert.assertNotNull(response);

			Assert.assertEquals(response.jsonPath().getString("[0].message"), hashmap.get("Expected Message"));
			reportGenerator.logMessage("Response Message received -->" + response.jsonPath().getString("[0].message"),
					Status.INFO);
			reportGenerator.logMessage("Response Success --> Email Validation Working", Status.PASS);

		} catch (Exception ex) {
			reportGenerator.logMessage("verifyEmailValidation fails " + ex.getMessage(), Status.FAIL);
		}
	}

	public void verifyCreateUserWithValidRequest(HashMap<String, String> hashmap, ReportGenerator reportGenerator) {
		try {
			// Get the Payload
			list = this.getTheUserPayload(hashmap);
			reportGenerator.logMessage("the user Request " + hashmap.get("Request"), Status.INFO);
			HashMap<String, String> payload = new HashMap<>();
			// Set the Payload
			for (HashMap<String, String> h : list) {
				payload.putAll(h);
			}
			user = new User();
			user.setName(payload.get("name"));
			user.setGender(payload.get("gender"));
			user.setEmail(payload.get("email"));
			user.setStatus(payload.get("status"));

			endpoints = new UserEndpoints();
			reportGenerator.logMessage("Hitting Create User URL ", Status.INFO);

			Response response = endpoints.createUser(user);
			Assert.assertNotNull(response);

			System.out.println("The PAYLOAD IS " + hashmap.toString());
			System.out.println("The RESPONSE for verifyCreateUserWithValidRequest");
			System.out.println(response.then().log().body());
			System.out.println(response.jsonPath().getString("[0].message"));
			if (response.jsonPath().getString("[0].message") !=null) {
				reportGenerator.logMessage("Name or Email already used ", Status.FAIL);
				throw new Exception();
			}
			Assert.assertTrue(response.jsonPath().get("id") != null, "Field 'ID' is Present");
			reportGenerator.logMessage(
					"The User Creation API got Success and created ID " + response.jsonPath().get("id"), Status.PASS);
			System.out.println("The Generated ID " + response.jsonPath().get("id"));

		} catch (Exception ex) {
			System.out.println("verifyCreateUserWithValidRequest fails " + ex.getMessage());
			reportGenerator.logMessage("verifyCreateUserWithValidRequest method fails ", Status.FAIL);
		}
	}

	private List<HashMap<String, String>> getTheUserPayload(HashMap<String, String> hashmap) {
		if (hashmap.get("TestCaseNo").equalsIgnoreCase("TC103")) {
			list = new ArrayList<>();
			String reqObj = hashmap.get("Request");
			String[] stArr = reqObj.split(",");
			List<String> listpair = new ArrayList<>();
			for (String s : stArr) {
				listpair.add(s);
				System.out.println(s);
			}
			for (String s1 : listpair) {
				String[] keyValuePairs = s1.replaceAll("\"", "").split(":");
				HashMap<String, String> keyvalue = new HashMap<>();
				keyvalue.put(keyValuePairs[0], keyValuePairs[1]);
				list.add(keyvalue);
			}
		}
		return list;

	}

}
