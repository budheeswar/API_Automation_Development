package udb.report;

import java.io.File;
import java.util.Properties;

import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import udb.util.CommonUtils;

public class ReportGenerator {
	CommonUtils util = null;;
	Properties config = null;

	private ExtentHtmlReporter htmlReporter = null;
	private ExtentReports extent = null;
	private ExtentTest test = null;
	private static final String BASE_REPORT_DIR = System.getProperty("user.dir");
	private String reportPath = null;

	public void setUpReportGenerator(String testCaseNumber, String testMethodName) {
		// location of the extent report
		reportPath = BASE_REPORT_DIR + File.separator + "test-output" + File.separator + "reportgenerator"
				+ File.separator + testCaseNumber;
		String htmlFilePath = reportPath +"."+testMethodName + "_"
				+ CommonUtils.getFormatedDate("MMddyyyyhh_mm_ss") + ".html";
		System.out.println(htmlFilePath);
		htmlReporter = new ExtentHtmlReporter(htmlFilePath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("API Automation Report"); // Title of Report
		htmlReporter.config().setReportName("API Automation Report"); // Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);// Default Theme of Report

		// General information related to application
		extent.setSystemInfo("Application Name", "RestAssured API Testing");
		extent.setSystemInfo("Author", "R Buddeeswar");
		test = extent.createTest(testMethodName);

	}

	public void logMessage(String message, Status logLevel) {
		Status level = Status.INFO;
		if (logLevel != null)
			level = logLevel;
		test.log(level, message);
		Reporter.log(message); // routing the message to testng reporter
	}

	public void endReport() {
		try {
			extent.flush();
		} catch (Exception e) {
			Reporter.log("Exception occured while pushing the test execution report due to " + e.getMessage());
		} finally {
			test = null;
			extent = null;
			htmlReporter = null;
		}
	}
}
