/*

 * PLEASE DO NOT RUN THIS FILE 

   -----------------------------------
 * PLEASE RUN testng.xml FILE

*/

package TestCase;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utilities.WriteExcel;
import BaseClasses.BaseTestClass;
import BaseClasses.PageBaseClass;
import PageClasses.HealthInsuranceMenu;
import PageClasses.HomePage;

/*
 * HealthInsuranceTest class is used to collect the required data from the TestDataProvider class and pass that data to the HealthInsuranceMenu class  
 */
public class HealthInsuranceTest extends BaseTestClass {

	HomePage homepage;
	HealthInsuranceMenu healthInsuranceMenu;
	WriteExcel writeExcel = new WriteExcel();

	@Test(groups={"HealthInsurance","Regression" })
	public void HealthInsuranceMenuTest() {
		try {
			logger = report.createTest("Health Insurance Menu Test");
			PageBaseClass pageBase = new PageBaseClass(driver, logger);
			PageFactory.initElements(driver, pageBase);
			homepage = pageBase.OpenApplication();
			homepage.openProductsMenu();
			List<WebElement> list = homepage.getMenuProductsList("Health Insurance");
			waitForPageLoad();
			healthInsuranceMenu = homepage.getHealthMenuInstance();
			healthInsuranceMenu.storeMenuItems(list);
			healthInsuranceMenu.displayMenuItems();
			waitForPageLoad();
			logger.log(Status.INFO, "Navigated to Home Page");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
