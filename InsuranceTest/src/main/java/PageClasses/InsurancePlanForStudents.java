package PageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import BaseClasses.PageBaseClass;
/*
 * InsurancePlanForStudents class is used to find the three lowest international travel insurance plan 
 * Display with amount and insurance provider comapny 
 */

public class InsurancePlanForStudents extends PageBaseClass {

	public InsurancePlanForStudents(WebDriver driver, ExtentTest logger) {
		super(driver, logger);

	}

//	@FindBy(xpath = "//*[@id='topForm']/section/div[2]/article/ul/li[3]/a")
//	public WebElement studentBtn;

	@FindBy(id = "destination-autocomplete")
	public WebElement destinationTextBox;
	
	@FindBy(xpath = "//*[@id=\'topForm\']/section/div[2]/div/div[2]/button")
	public WebElement nextBtn;
	
	@FindBy(xpath = "//*[@id=\'secondStep\']/a")
	public WebElement Addstudent;
	
	@FindBy(xpath = "//*[@id='enddate']")
	public WebElement tripEndDateBox;

	@FindBy(xpath = "//*[@id='startdate']")
	public WebElement tripStartDateBox;

	@FindBy(xpath = "//*[@id='navigatorType']/body/div[8]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]")
	public WebElement tripStartMonth;

	@FindBy(xpath = "//*[@id='navigatorType']/body/div[8]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]")
	public WebElement tripStartYear;

	@FindBy(xpath = "//*[@id='navigatorType']/body/div[9]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]")
	public WebElement tripEndMonth;

	@FindBy(xpath = "//*[@id='navigatorType']/body/div[9]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]")
	public WebElement tripEndYear;

//	@FindBy(id = "proceedStepOne")
//	public WebElement proceedBtn;

//	@FindBy(id = "travelgender")
//	public WebElement prefixElement;
//
//	@FindBy(id = "travelname")
//	public WebElement fullnameElement;
//
//	@FindBy(id = "travelCountry")
//	public WebElement numberPrefix;

	@FindBy(name = "travelmobile")
	public WebElement moblieElement;

//	@FindBy(name = "travelemail")
//	public WebElement emailElement;
//
//	@FindBy(xpath = "//*[@id='topForm']/section/div[2]/div[2]/div[1]/div[2]/div/a[2]")
//	public WebElement quotesBtn;

	@FindBy(className = "sort_select")
	public WebElement sortlowest;

	/*
	 * Clicking student tab
	 */
//	public void clickStudentButton() {
//		elementClick(studentBtn);
//	}

	/*
	 * Entering student destination in form
	 */
	public void enterDestination(String destination) {
		enterText(destinationTextBox, destination);
		destinationTextBox.sendKeys(Keys.ENTER);
		//waitLoad(1);
	}
	
	public void clickNextButton() {
	
			driver.findElement(By.xpath("//body/div[@id='policybazaar']/section[1]/div[1]/div[3]/section[1]/div[2]/div[1]/div[2]/button[1]")).click();
		
	}

	/*
	 * Entering student age in form
	 */
	public void enterStudentAge(String[] ageList) {
		for (int i = 0; i < ageList.length; i++) {
			WebElement element = driver.findElement(By.id("travellerAge" + (i + 1)));
			enterText(element, ageList[i]);
			element.sendKeys(Keys.ENTER);
			if(i!=ageList.length-1)
			{
			elementClick(Addstudent);
			}
		}
	}
	
	
	

	/*
	 * Entering trip start date in form
	 */
	public void enterStartDate(String date) throws Exception {
		selectDate(date, tripStartMonth, tripStartYear, tripStartDateBox, true);
	}

	/*
	 * Entering trip end date in form
	 */
	public void enterEndDate(String date) throws Exception {
		selectDate(date, tripEndMonth, tripEndYear, tripEndDateBox, false);
	}

	/*
	 * Clicking next proceed button
	 */
//	public void clickProceedButton() {
//		proceedBtn.click();
//	}

	/*
	 * Entering full name in form
	 */
//	public void enterFullname(String prefix, String name) {
//		SelectElementInList(prefixElement, prefix);
//		fullnameElement.clear();
//		enterText(fullnameElement, name);
//	}

	/*
	 * Entering mobile number in form
	 */
	public void enterMobleNumber(String number) {
		moblieElement.clear();
		enterText(moblieElement, number);
	}

	/*
	 * Entering email address in form
	 */
//	public void enterEmail(String email) {
//		emailElement.clear();
//		enterText(emailElement, email);
//	}
//
//	/*
//	 * Clicking get free quotes button
//	 */
//	public void clickFreeQuotesButton() {
//		elementClick(quotesBtn);
//	}

	/*
	 * Sorting price from low to high
	 */
	public void sortPrice() {
		SelectElementInList(sortlowest, "Price: Low to High");
	}

	/*
	 * Getting insurance details
	 */
	public void getInsurancePlan() {
		try {
			System.out.println("Three lowest international travel insurance plan");
			logger.log(Status.INFO, "Displaying three lowest international travel insurance plan");
			List<WebElement> listamount = driver
					.findElements(By.xpath("//*[@class='col-md-3 cta desktop']/div/button"));
			List<WebElement> listprovider = driver.findElements(By.xpath("//*[@class='desktop leftLogo']/div"));
			System.out.println();
			for (int i = 0; i < listamount.size() && i < 3; i++) {
				System.out.println(listprovider.get(i).getAttribute("class").replace("Logo ", "") + " : "
						+ listamount.get(i).getText().replace("₹ ", ""));
			}
			System.out.println();
			logger.log(Status.PASS, "Displayed three lowest international travel insurance plan");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
}
