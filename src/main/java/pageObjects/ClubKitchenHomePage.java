package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClubKitchenHomePage {
	// This is the homa page pf Club Kitchen

	
	public WebDriver driver;
	WebDriverWait wait;

	public ClubKitchenHomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='banner--content center right']/a")
	private WebElement mamacitaLogo;
	
	@FindBy(how = How.XPATH, using = "//button[@class='agree-cookie']")
	private WebElement agreeCookies;

	
	// Click on Mamacita icon on Home PAge so that Mamacita restaurant page is openend.
	public void clickMamacitaLogoOnHomePAge() {
		wait.until(ExpectedConditions.visibilityOf(mamacitaLogo));
		wait.until(ExpectedConditions.elementToBeClickable(mamacitaLogo));
		mamacitaLogo.click();
	}
	
	//Accept cookies
	public void clickAgreeCookies() {
		wait.until(ExpectedConditions.visibilityOf(agreeCookies));
		wait.until(ExpectedConditions.elementToBeClickable(agreeCookies));
		agreeCookies.click();
	}

}
