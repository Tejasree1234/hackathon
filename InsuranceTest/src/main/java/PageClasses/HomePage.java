package PageClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClasses.PageBaseClass;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/*
 * HomePage class is used to handle all Insurance services of PolicyBazaar home page
 */
public class HomePage extends PageBaseClass {

	public HomePage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		// TODO Auto-generated constructor stub
	}

	@FindBy(className = "ruby-menu-mega")
	public WebElement insuranceProducts;

	@FindBy(xpath = "/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[1]/ul[1]")
	public WebElement LifeInsurance;

	@FindBy(xpath = "/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[2]/ul[1]")
	public WebElement HealthInsurance;

	@FindBy(xpath = "/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[3]/ul[1]")
	public WebElement CarInsurance;

	@FindBy(xpath = "/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[4]/ul[1]")
	public WebElement OtherInsurance;

	@FindBy(xpath = "/html/body/cclink/div[4]/div[1]/div/ul/li[2]/div/div/div[3]/h3/a")
	public WebElement openCarInsurancePage;

	/*
	 * Opening products menu bar
	 */
	public void openProductsMenu() {
		Actions actions = new Actions(driver);
		actions.moveToElement(insuranceProducts).build().perform();
		logger.log(Status.PASS, "Menu Bar Opened");

	}

	/*
	 * Getting car insurance page instance
	 */
	public CarInsuranceQuote getCarInsurancePage() {

		elementClick(openCarInsurancePage);
		CarInsuranceQuote carInsuranceQuote = new CarInsuranceQuote(driver, logger);
		PageFactory.initElements(driver, carInsuranceQuote);
		return carInsuranceQuote;
	}

	/*
	 * Getting insurance list based on insurance type
	 */
	public List<WebElement> getMenuProductsList(String insuranceName) {
		if (insuranceName.equalsIgnoreCase("Life Insurance")) {
			return getInsuranceList(LifeInsurance);
		} else if (insuranceName.equalsIgnoreCase("Health Insurance")) {
			return getInsuranceList(HealthInsurance);
		} else if (insuranceName.equalsIgnoreCase("Car Insurance")) {
			return getInsuranceList(CarInsurance);
		} else if (insuranceName.equalsIgnoreCase("Other Insurance")) {
			return getInsuranceList(OtherInsurance);
		} else {
			return getInsuranceList(LifeInsurance);
		}
	}

	/*
	 * Getting insurance list
	 */
	public List<WebElement> getInsuranceList(WebElement element) {
		List<WebElement> elementList = element.findElements(By.tagName("li"));
		return elementList;

	}

	/*
	 * Getting Health insurance menu instance
	 */
	public HealthInsuranceMenu getHealthMenuInstance() {
		HealthInsuranceMenu healthInsuranceMenu = new HealthInsuranceMenu(driver, logger);
		PageFactory.initElements(driver, healthInsuranceMenu);
		return healthInsuranceMenu;
	}

	/*
	 * Click travel insurance button in menu bar
	 */
	public InsurancePlanForStudents clickTravelInsurance() {
		openProductsMenu();
		List<WebElement> InsuranceList = getMenuProductsList("Other Insurance");

		for (WebElement InsuranceName : InsuranceList) {
			if (InsuranceName.getText().equalsIgnoreCase("Travel Insurance")) {
				WebElement travelInsurance = InsuranceName.findElement(By.tagName("a"));
				travelInsurance.click();
				break;
			}
		}

		InsurancePlanForStudents insurancePlanForStudents = new InsurancePlanForStudents(driver, logger);
		PageFactory.initElements(driver, insurancePlanForStudents);
		return insurancePlanForStudents;
	}
}
