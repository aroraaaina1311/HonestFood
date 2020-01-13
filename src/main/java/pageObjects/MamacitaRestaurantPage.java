package pageObjects;

import java.util.List;

import org.apache.poi.poifs.crypt.dsig.KeyInfoKeySelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MamacitaRestaurantPage {
	// This is Mamacita Restaurant's page

	public WebDriver driver;
	WebDriverWait wait;

	public MamacitaRestaurantPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}

	//address textbox
	@FindBy(how = How.XPATH, using = "//input[@id='address-input']")
	private WebElement addressTextBoxInPopup;

	//save address button
	@FindBy(how = How.XPATH, using = "//input[@class='btn--honest blattgold--form-banner-submit']")
	private WebElement zumMenuButtonOnAddressPopUp;

	//options available in MAmacita Menu	
	@FindBy(how = How.XPATH, using = "//ul[@class='navigation--list container js--menu-scroller--list']/li")
	private List<WebElement> mamacitaMenuList;

	//first item available in every option
	@FindBy(how = How.XPATH, using = "//div[@class='product--box box--image'][1]/div/div[2]")
	private List<WebElement> firstItem;

	//Extra popup OR Add to Cart Popup
	@FindBy(how = How.XPATH, using = "//div[@class='content']")
	private WebElement addToCartPopup;

	@FindBy(how = How.XPATH, using = "//button[@id='topup-modal--close']")
	private WebElement addToCartButtonOnExtrasPopup;

	public WebElement getAddressTextBoxPopUp() {
		wait.until(ExpectedConditions.elementToBeClickable(addressTextBoxInPopup));
		return addressTextBoxInPopup;
	}

	//click on Save addrese
	public void clickOnZumMenuButtonOnAddressPopUp() {
		wait.until(ExpectedConditions.visibilityOf(zumMenuButtonOnAddressPopUp));
		wait.until(ExpectedConditions.elementToBeClickable(zumMenuButtonOnAddressPopUp));
		zumMenuButtonOnAddressPopUp.click();
	}

	// adding 1 dish from each option available
	public void addEachDishFromMamacitaMenu() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElements(mamacitaMenuList));

		int max = mamacitaMenuList.size();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
		for (int i = 0; i < max - 1; i++) {

			wait.until(ExpectedConditions.visibilityOfAllElements(mamacitaMenuList));
			wait.until(ExpectedConditions.visibilityOf(mamacitaMenuList.get(i)));
			wait.until(ExpectedConditions.elementToBeClickable(mamacitaMenuList.get(i)));

			mamacitaMenuList.get(i).click();
			wait.until(ExpectedConditions.visibilityOf(firstItem.get(1)));
			wait.until(ExpectedConditions.elementToBeClickable(firstItem.get(i)));
			firstItem.get(i).click();
			addToCartPopup.click();
			js.executeScript("arguments[0].scrollTop = arguments[1];", addToCartPopup, 300);
			addToCartButtonOnExtrasPopup.click();
		}

		Thread.sleep(2000);
		mamacitaMenuList.get(max - 1).click(); // adding Desserts to cart
		firstItem.get(max).click();

	}
}
