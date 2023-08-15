package udb.suites;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import udb.dataprovider.CsvDataProvider;
import udb.endpoints.UserEndpoints;
import udb.report.ReportGenerator;
import udb.util.CommonUtils;

public class GetUser_APIChainingSuite {
	UserEndpoints endpoints;
	Integer idValue;
	ReportGenerator reportGenerator=null;
	CommonUtils utils=null;

	@Test(priority = 1,dataProvider = "csvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void verifyGetIdFromUsersList(ITestContext context, HashMap<String,String> hashmap) {
		try {
			reportGenerator.setUpReportGenerator(hashmap.get("TestCaseNo"), "verifyAPIChaining");
			reportGenerator.logMessage("Calling GetAllUsers API ", Status.INFO);
			Response res = endpoints.getAllUsers();
			Assert.assertNotNull(res);
			Assert.assertEquals(res.getStatusCode(), 200);
			reportGenerator.logMessage("Got Response Code 200", Status.PASS);
			JsonPath jsPath = res.jsonPath();
			idValue = jsPath.get("[1].id");
			context.setAttribute("userID", idValue);
			reportGenerator.logMessage("Passing ID "+idValue+" as Input to Another API", Status.PASS);
			
			
			
		}catch(Exception ex) {
			reportGenerator.logMessage("Exception occured for method verifyGetIdFromUsersList", Status.FAIL);
			System.out.println("EXCEPTIOn OCCURED");
		}
		

	}

	@Test(dependsOnMethods = "verifyGetIdFromUsersList")
	public void verifyGetByIDChaining(ITestContext context) {
		try {
			int userid = (int) context.getAttribute("userID");
			System.out.println("THE ID is "+userid);
			reportGenerator.logMessage("Fetching Data by ID taken from API_Response", Status.INFO);
			Response res = endpoints.getUserByID(12389);
			if(res.getStatusCode() == 404) {
				throw new Exception();
			}
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertNotNull(res);
			res.then().log().body();
			
			
			reportGenerator.logMessage("Data Fetch Successfully by ID "+userid, Status.PASS);
			

		}catch(Exception ex) {
			reportGenerator.logMessage("Exception occured while Fetching DATA in method verifyGetByIDChaining", Status.FAIL);
		}
		
	}

	@BeforeTest
	public void beforeTest() {
		endpoints = new UserEndpoints();
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
		reportGenerator = new ReportGenerator();
		utils = new CommonUtils();
	}

	@AfterSuite
	public void afterSuite() {
		reportGenerator.endReport();
	}

}
