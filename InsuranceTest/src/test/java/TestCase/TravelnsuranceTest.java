/*

 * PLEASE DO NOT RUN THIS FILE 

   -----------------------------------
 * PLEASE RUN testng.xml FILE

 */

package TestCase;

import java.util.Hashtable;

/*
 * TravelInsuranceTest class is used to collect the required data from the TestDataProvider class and pass that data to the InsurancePlanForStudents class  
 */

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utilities.TestDataProvider;
import utilities.WriteExcel;
import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import PageClasses.HomePage;
import PageClasses.InsurancePlanForStudents;

public class TravelnsuranceTest extends BaseTestClass {

	HomePage homepage;
	InsurancePlanForStudents insurancePlanForStudents;
	WriteExcel writeExcel = new WriteExcel();

	@Test(dataProvider = "getInsurancePlanTestData", groups = { "InsurancePlan","Smoke" })
	public void InsurancePlanTest(Hashtable<String, String> testData) {
		String[] ageList = testData.get("Age").split(",");
		logger = report.createTest("Insurance Plan Test(ID - " + testData.get("Test ID") + ")");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		try {

			PageFactory.initElements(driver, pageBase);
			homepage = pageBase.OpenApplication();
			insurancePlanForStudents = homepage.clickTravelInsurance();
			//insurancePlanForStudents.clickStudentButton();
			insurancePlanForStudents.enterDestination(testData.get("Destination"));
			waitLoad(1);
			insurancePlanForStudents.clickNextButton();
			waitForPageLoad();
			insurancePlanForStudents.enterStudentAge(ageList);
			insurancePlanForStudents.clickNextButton();
			insurancePlanForStudents.enterStartDate(testData.get("Start Date")); // dd/mm/yyyy
			insurancePlanForStudents.enterEndDate(testData.get("End Date")); // dd/mm/yyyy
			insurancePlanForStudents.clickNextButton();
			waitForPageLoad();
//			insurancePlanForStudents.enterFullname(testData.get("Name Prefix"), testData.get("Name"));
			insurancePlanForStudents.enterMobleNumber(testData.get("Number"));
			insurancePlanForStudents.clickNextButton();
//			insurancePlanForStudents.enterEmail(testData.get("Email"));
//			insurancePlanForStudents.clickFreeQuotesButton();
//			waitForPageLoad();
			insurancePlanForStudents.sortPrice();
			insurancePlanForStudents.getInsurancePlan();
			logger.log(Status.INFO, "Navigated to Home Page");
			writeExcel.writeTestCaseResult("Displayed three lowest international travel insurance plan", "Pass",
					"StudentsInsurancePlanTest.xlsx", testData.get("Test ID"));
		} catch (Exception e) {
			writeExcel.writeTestCaseResult("Check the Screenshot", "Fail", "StudentsInsurancePlanTest.xlsx",
					testData.get("Test ID"));
			logger.log(Status.FAIL, e.getMessage());
			pageBase.takeScreenShotOnFailure();
			Assert.assertTrue(false, "Failing the Test after capturing the error!");
		}
	}

	@DataProvider
	public Object[][] getInsurancePlanTestData() {
		return TestDataProvider.getTestData("StudentsInsurancePlanTest.xlsx", "InsuranceTest", "Insurance Plan Test");
	}

}
