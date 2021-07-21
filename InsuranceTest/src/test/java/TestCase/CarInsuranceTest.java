/*

 * PLEASE DO NOT RUN THIS FILE 

   -----------------------------------
 * PLEASE RUN testng.xml FILE

*/

package TestCase;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utilities.TestDataProvider;
import utilities.WriteExcel;
import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import PageClasses.CarInsuranceQuote;
import PageClasses.HomePage;

/*
 * CarInsuranceTest class is used to collect the required data from the TestDataProvider class and pass that data to the CarInsuranceQuote class  
 */
public class CarInsuranceTest extends BaseTestClass {

	HomePage homepage;
	CarInsuranceQuote carInsuranceQuote;
	WriteExcel writeExcel = new WriteExcel();

	@Test(dataProvider="getCarInsuranceTestData",groups={"CarInsurance","Default"})
	public void CarInsuranceQuoteTest(Hashtable<String, String> testData) {
		logger = report.createTest("Car Insurance Quote Test(ID - " + testData.get("Test ID") + ")");
		PageBaseClass pageBase = new PageBaseClass(driver, logger);
		try {

			PageFactory.initElements(driver, pageBase);
			homepage = pageBase.OpenApplication();
			homepage.openProductsMenu();
			carInsuranceQuote = homepage.getCarInsurancePage();
			carInsuranceQuote.clickProceedButton();
			waitForPageLoad();
			carInsuranceQuote.enterCityInput(testData.get("City"));
			waitForPageLoad();
			carInsuranceQuote.enterCarBrand(testData.get("Car Brand"));
			waitForPageLoad();
			carInsuranceQuote.selectFuelType(testData.get("Fuel Type"));
			waitForPageLoad();
			carInsuranceQuote.enterCarVariant(testData.get("Car Variant"));
			waitForPageLoad();
			carInsuranceQuote.SelectRegYear(testData.get("Reg Year"));
			waitForPageLoad();
			carInsuranceQuote.enterUsername(testData.get("Username"));
			carInsuranceQuote.enterEmailAndNumber(testData.get("Email"), testData.get("Number"));
			carInsuranceQuote.takeScreenShotOnPass();
			int val = carInsuranceQuote.checkEnteredValue();
			if (val == 2)
				writeExcel.writeTestCaseResult(" Email and Phone number is invalid", "Pass", "CarInsuranceTest.xlsx",
						testData.get("Test ID"));
			else if (val == 1)
				writeExcel.writeTestCaseResult("Email or Phone number is invalid", "Pass", "CarInsuranceTest.xlsx",
						testData.get("Test ID"));
			else
				writeExcel.writeTestCaseResult("Email and Phone number is valid", "Pass", "CarInsuranceTest.xlsx",
						testData.get("Test ID"));
			logger.log(Status.INFO, "Navigated to Home Page");
		} catch (Exception e) {
			writeExcel.writeTestCaseResult("Check the Screenshot", "Fail", "CarInsuranceTest.xlsx",
					testData.get("Test ID"));
			logger.log(Status.FAIL, e.getMessage());
			pageBase.takeScreenShotOnFailure();
			Assert.assertTrue(false, "Failing the Test after capturing the error!");

		}
	}

	@DataProvider
	public Object[][] getCarInsuranceTestData() {
		return TestDataProvider.getTestData("CarInsuranceTest.xlsx", "InsuranceTest", "Car Insurance Quote Test");
	}

}
