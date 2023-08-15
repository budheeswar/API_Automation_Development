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
import udb.testcases.TestCreateUser;
import udb.util.CommonUtils;

public class POSTUserSuite {

	TestCreateUser postUser;

	ReportGenerator reportGenerator = null;
	CommonUtils utils = null;

	@Test(dataProvider = "csvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void testCreateUser(HashMap<String, String> hashmap) {
		try {
			reportGenerator.setUpReportGenerator(hashmap.get("TestCaseNo"), "testCreateUser");

			if (hashmap.get("TestCaseNo").equalsIgnoreCase("TC103")) {
				postUser.verifyCreateUserWithValidRequest(hashmap, reportGenerator);
			}
			if (hashmap.get("TestCaseNo").equalsIgnoreCase("TC104")) {
				postUser.verifyEmailValidation(hashmap, reportGenerator);
			}
		} catch (Exception e) {

			reportGenerator.logMessage("Error occured while creating user" + e.toString(), Status.FAIL);
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void before_method() {
		reportGenerator = new ReportGenerator();
		utils = new CommonUtils();

	}

	@AfterMethod
	public void after_method() {
		reportGenerator.endReport();
		// upload result to testrail

	}

	@BeforeTest
	public void beforeTest() {
		postUser = new TestCreateUser();
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
