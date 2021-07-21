package PageClasses;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;

/*
 * HealthInsuranceMenu class is used to get the various types of Insurance available under the health insurance menu
 */

public class HealthInsuranceMenu extends PageBaseClass {

	public HealthInsuranceMenu(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
	}

	List<String> menuList = new ArrayList<String>();

	/*
	 * Storing health insurance menu items
	 */
	public void storeMenuItems(List<WebElement> list) {
		logger.log(Status.INFO, "Storing Health Insurance Items");
		for (WebElement webElement : list) {
			this.menuList.add(webElement.getText());
		}
		logger.log(Status.PASS, "Health Insurance Items Stored");
	}

	/*
	 * Printing health insurance menu items
	 */
	public void displayMenuItems() {
		logger.log(Status.INFO, "Displaying Health Insurance Items");
		System.out.println();
		System.out.println("Health Insurance Items");
		System.out.println();
		for (String element : menuList) {

			System.out.println(element);

		}
		logger.log(Status.PASS, "Health Insurance Items Displayed");
	}

}
