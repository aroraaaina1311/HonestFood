package pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cart {

	// This class deals with the cart elements

	public WebDriver driver;
	WebDriverWait wait;

	public Cart(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}

	//Number of type of items added in cart
	@FindBy(how = How.XPATH, using = "//div[@class='item--container items']/div")
	private List<WebElement> ItemsInCart;

	//Getting Number of type of items added in cart
	public int getNumberOfTypeOfItemsInCart() {
		int count = ItemsInCart.size();
		return count;

	}

}
