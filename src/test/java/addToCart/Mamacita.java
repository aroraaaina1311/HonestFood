package addToCart;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Cart;
import pageObjects.ClubKitchenHomePage;
import pageObjects.MamacitaRestaurantPage;
import usefulResources.base;

public class Mamacita extends base{
	public static Logger log = LogManager.getLogger(Mamacita.class.getName());
	SoftAssert softAssert = new SoftAssert();



	@BeforeMethod
	public void initializeDriver() throws IOException {
		driver = initialiseDriver();
		log.info("Driver initialised");
		driver.get(prop.getProperty("URL"));
		log.info("Navigated to the URL of Club Kitchen");
	}
	
	@Test
	public void addMamacitaProductsToCart() throws InterruptedException {
		ClubKitchenHomePage homepage = new ClubKitchenHomePage(driver);
		MamacitaRestaurantPage mamacitaPage = new MamacitaRestaurantPage(driver);
		Cart cart = new Cart(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		homepage.clickAgreeCookies(); // Clicks on OK button that asks to accept cookies
		js.executeScript("window.scrollBy(0,325)");
		homepage.clickMamacitaLogoOnHomePAge();
		
		//For entering address in the address textbox 
		WebElement addressTextbox = mamacitaPage.getAddressTextBoxPopUp();
		addressTextbox.sendKeys(prop.getProperty("address"));
		mamacitaPage.clickOnZumMenuButtonOnAddressPopUp();
		log.info("added address in the address textbox");
		
		//From each option of menu item, adding 1 dish to the cart 
		mamacitaPage.addEachDishFromMamacitaMenu();
		
		//counting the no. of type of items added in the cart
		int noOfTypeOfProductsInCart = cart.getNumberOfTypeOfItemsInCart();
		if (noOfTypeOfProductsInCart == 5)
		{
			Assert.assertTrue(true);
		}
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	
}
