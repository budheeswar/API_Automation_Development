package udb.suites;

import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import udb.dataprovider.CsvDataProvider;
import udb.report.ReportGenerator;
import udb.testcases.TestGetUsers;
import udb.util.CommonUtils;

public class GETUserSuite {
	TestGetUsers testGetUser=null;
	ReportGenerator reportGenerator = null;
	CommonUtils utils = null;
	
	@Test(dataProvider = "csvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void testGetUser(HashMap<String,String> hashmap) {
		try {
			reportGenerator.setUpReportGenerator(hashmap.get("TestCaseNo"),"testGetUser");
			
		
		if(hashmap.get("TestCaseNo").equalsIgnoreCase("TC101")) {
			testGetUser.verifyGetAllUsersAPI(hashmap, reportGenerator);
			reportGenerator.logMessage("Test case "+hashmap.get("TestCaseNo")+" success", Status.PASS);
		}
		if(hashmap.get("TestCaseNo").equalsIgnoreCase("TC102")) {
			testGetUser.verifyGetByID(hashmap,reportGenerator);
			reportGenerator.logMessage("Test case "+hashmap.get("TestCaseNo")+" success", Status.PASS);

		}
		} catch (Exception e) {
			
			reportGenerator.logMessage("Error occured "+e.toString(), Status.FAIL);
			e.printStackTrace();
		}
	}
	@BeforeMethod
	public void before_method() {
		testGetUser = new TestGetUsers();
		reportGenerator = new ReportGenerator();
		utils = new CommonUtils();
		
	}
	@AfterMethod
	public void after_method() {
		reportGenerator.endReport();
		//upload result to testrail 
		
	}

	@BeforeTest
	public void beforeTest() {
		
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
		
	}

	@AfterSuite
	public void afterSuite() {
	}

}
