package PageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import BaseClasses.PageBaseClass;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/*
 * CarInsuranceQuote class is used to automate the car insurance web page and Screenshot the error message 
 */
public class CarInsuranceQuote extends PageBaseClass {

	public CarInsuranceQuote(WebDriver driver, ExtentTest logger) {
		super(driver, logger);

	}

	// Using Page factory framework to store the webElement to a specific variable
	@FindBy(xpath = "//*[@id=\frmCar\']/div[1]/div/div/div[5]/a[1]")
	public WebElement proceedWithoutCarNumber;

	@FindBy(xpath = "//*[@id='searchInput']/div/input")
	public WebElement cityInputBox;

	@FindBy(xpath = "//*[@id='react-autowhatever-1']")
	public WebElement cityInputBoxAutoSuggest;

	@FindBy(xpath = "//*[@id='searchInput']/div/input")
	public WebElement carBrandInputBox;

	@FindBy(xpath = "//*[@id='react-autowhatever-1']")
	public WebElement carBrandAutoSuggest;

	@FindBy(xpath = "//*[@id='dvFuelType']/ul/div")
	public WebElement fuelType;

	@FindBy(xpath = "//*[@id='searchInput']/div/input")
	public WebElement carModelInputBox;

	@FindBy(xpath = "//*[@id='react-autowhatever-1']")
	public WebElement carModelAutoSuggest;

	@FindBy(xpath = "//*[@id='searchInput']/div/input")
	public WebElement carVariantBox;

	@FindBy(xpath = "//*[@id='react-autowhatever-1']")
	public WebElement carVariantAutoSuggest;

	@FindBy(xpath = "//*[@id='dvRegYear']/ul/div")
	public WebElement regYear;

	@FindBy(id = "name")
	public WebElement name;

	@FindBy(id = "email")
	public WebElement email;

	@FindBy(id = "mobileNo")
	public WebElement mobileNo;

	@FindBy(xpath = "//*[@id='variantScroll']")
	public WebElement selectbydiv;

	public By checkInvalid = By.xpath("//*[@class='msg-error show']");

	/*
	 * To check entered value valid ro not
	 */
	public int checkEnteredValue() {
		List<WebElement> list = driver.findElements(checkInvalid);
		if (list.size() == 2) {
			return 2; // Email and Phone number is invalid
		} else if (list.size() == 1) {
			return 1; // Email or Phone number is invalid
		} else {
			return 0; // Email and Phone number is valid
		}
	}

	/*
	 * Clicking proceed without car number button
	 */
	public void clickProceedButton() {
		elementClick(proceedWithoutCarNumber);
	}

	/*
	 * Clear input box
	 */
	public void clearInputfield(WebElement element) {
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
	}

	/*
	 * Selecting element
	 */
	public boolean selectByName(String value, WebElement element) {
		if (element.findElement(By.xpath("//*[contains(text(),'" + value + "')]")).isEnabled()) {
			element.findElement(By.xpath("//*[contains(text(),'" + value + "')]")).click();
			logger.log(Status.PASS, value + ": Element Clicked Successfully");
			return true;
		}
		return false;
	}

	/*
	 * Entering city name in input box
	 */
	public void enterCityInput(String cityName) {
		try {
			boolean flag = false;
			String city = cityName.toUpperCase();
			clearInputfield(cityInputBox);
			cityInputBox.click();
			waitforElement(By.xpath("//*[@id='react-autowhatever-1']"));
			flag = selectByName(city, cityInputBoxAutoSuggest);
			if (!flag) {
				reportFail("Enter valid city name");
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/*
	 * Entering car brand name in input box
	 */
	public void enterCarBrand(String carName) {
		try {
			boolean flag = false;
			String car = carName.toUpperCase();
			clearInputfield(carBrandInputBox);
			carBrandInputBox.click();
			waitforElement(By.xpath("//*[@id='react-autowhatever-1']"));
			flag = selectByName(car, carBrandAutoSuggest);
			if (!flag) {
				reportFail("Enter valid car brand");
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/*
	 * Selecting car fuel type
	 */
	public void selectFuelType(String fueltype) {
		String fuel = fueltype.substring(0, 1).toUpperCase() + fueltype.substring(1);
		selectByName(fuel, fuelType);
	}

	/*
	 * Entering car model name in input box
	 */
	public void enterCarModel(String carModel) {
		try {
			boolean flag = false;
			String model = carModel.toUpperCase();
			clearInputfield(carModelInputBox);
			carModelInputBox.click();
			waitforElement(By.xpath("//*[@id='react-autowhatever-1']"));
			flag = selectByName(model, carModelAutoSuggest);
			if (!flag) {
				reportFail("Enter valid car model");
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/*
	 * Entering car variant name in input box
	 */
	public void enterCarVariant(String carVariant) {
		boolean flag = false;
		try {
			flag = selectByName(carVariant, selectbydiv);
			if (!flag) {
				reportFail("Enter valid city variant");
			}
		} catch (Exception e) {

			reportFail(e.getMessage());
		}

	}

	/*
	 * Selecting car registration year
	 */
	public void SelectRegYear(String year) {
		try {
			boolean flag = false;
			flag = selectByName(year, regYear);
			if (!flag) {
				reportFail("Enter valid year");
			}
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/*
	 * Entering user full name
	 */
	public void enterUsername(String username) {
		enterText(name, username);
	}

	/*
	 * Entering email and phone number in input box
	 */
	public void enterEmailAndNumber(String emailID, String number) {
		enterText(email, emailID);
		email.sendKeys(Keys.ENTER);
		enterText(mobileNo, number);
		mobileNo.sendKeys(Keys.ENTER);

	}

	/*
	 * Taking error screenshot
	 */
	public void takeScreenShot() {
		takeScreenShotOnPass();
		logger.log(Status.PASS, "Error message captured");
	}
}
