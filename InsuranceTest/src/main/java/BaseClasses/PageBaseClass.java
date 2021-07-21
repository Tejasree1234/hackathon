package BaseClasses;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.Dateutil;
import PageClasses.HomePage;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
/*
 * PageBaseClass has some basic features which is used for Automation
 */

public class PageBaseClass extends BaseTestClass {

	public ExtentTest logger;

	public PageBaseClass(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	/*
	 * Opening the webSite
	 */
	public HomePage OpenApplication() throws IOException {
		Properties prop = new Properties();
		InputStream readFile = null;
		readFile = new FileInputStream("config.properties");
		prop.load(readFile);
		String url = (String) prop.get("URL");
		logger.log(Status.INFO, "Opening the WebSite");
		driver.get(url);
		logger.log(Status.PASS, "Successfully Opened the https: " + url);
		HomePage homepage = new HomePage(driver, logger);
		PageFactory.initElements(driver, homepage);
		return homepage;
	}

	/*
	 * Close browser
	 */
	public void tearDown() {
		driver.close();

	}

	/*
	 * Quit browser
	 */
	public void quitBrowser() {
		driver.quit();

	}

	/*
	 * Entering test in input box
	 */
	public void enterText(WebElement element, String data) {
		try {
			element.sendKeys(data);
			reportPass(data + " - Entered successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/*
	 * Wait for element
	 */
	public void waitforElement(By loctor) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.presenceOfElementLocated(loctor));
	}

	/*
	 * Clicking element
	 */
	public void elementClick(WebElement element) {

		try {
			String Elemnttext = element.getText();
			element.click();
			reportPass(Elemnttext + " : Element Clicked Successfully");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/*
	 * To verify element is Display or not
	 */
	public boolean isElementPresent(WebElement element) {
		try {
			if (element.isDisplayed()) {
				reportPass(element.getText() + " : Element is Displayed");
				return true;
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}

	/*
	 * Selecting drop down value
	 */
	public void SelectElementInList(WebElement element, String Value) {
		boolean flag = false;
		Select select = new Select(element);
		List<WebElement> list = select.getOptions();

		for (WebElement listItem : list) {

			if (listItem.getText().equals(Value)) {
				if (listItem.isEnabled()) {
					flag = true;
					select.selectByVisibleText(Value);
					logger.log(Status.INFO, "Selected the Defined Value : " + Value);
					break;
				} else {
					flag = true;
					reportFail("Value not enabled : " + Value);
				}
			}
		}
		if (flag == false) {
			reportFail("Value not displayed : " + Value);
		}

	}

	/*
	 * getting page title
	 */
	public void getTitle(String expectedTitle) {

		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/*
	 * getting all elements of drop down
	 */
	public List<WebElement> getAllElementsOfDropDown(WebElement webElement) {
		try {
			Select select = new Select(webElement);
			List<WebElement> allElements = select.getOptions();
			return allElements;
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return null;
	}

	/*
	 * Failure report
	 */
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.assertTrue(false, "Failing the Test after capturing the error!");

		report.flush();
	}

	/*
	 * Test pass report
	 */
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/*
	 * Taking screenshot
	 */
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "\\ScreenShot\\" + Dateutil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\ScreenShot\\" + Dateutil.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Taking error screenshot
	 */
	public void takeScreenShotOnPass() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "\\ScreenShot\\" + Dateutil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\ScreenShot\\" + Dateutil.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Selecting date
	 */
	public void selectDate(String date, WebElement monthElement,

			WebElement yearElement, WebElement TextBox, boolean isStartDate) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

		Date expectedDate = dateFormat.parse(date);

		String day = new SimpleDateFormat("dd").format(expectedDate);
		String month = new SimpleDateFormat("MMM").format(expectedDate);
		String year = new SimpleDateFormat("yyyy").format(expectedDate);
		TextBox.click();
		SelectElementInList(yearElement, year);
		SelectElementInList(monthElement, month);
		if (isStartDate)
		{
			List<WebElement> list1 = driver.findElements(By.xpath("//td[text()=" + day + "]"));
		for (WebElement webElement : list1) {
			if (webElement.getText().equals(day) && webElement.isDisplayed() && webElement.isEnabled()) {
				webElement.click();
				break;
			}
		}
		      	}
		else {
			List<WebElement> list = driver.findElements(By.xpath("//td[text()=" + day + "]"));
			for (WebElement webElement : list) {
				if (webElement.getText().equals(day) && webElement.isDisplayed() && webElement.isEnabled()) {
					webElement.click();
					break;
				}
			}
		}

	}

}
