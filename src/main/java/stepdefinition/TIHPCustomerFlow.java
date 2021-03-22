package stepdefinition;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TIHPCustomerFlow extends TestBase {
	public WebDriverWait wait;

	public TIHPCustomerFlow() throws IOException {
		super();

	}

	@Given("^Access IHP Planner$")
	public void access_IHP_Planner() {
		TestBase.initialization();
		driver.switchTo().frame("MainFrame");
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("Agreement_CheckBox")));
		driver.findElement(By.id("Agreement_CheckBox")).click();
		driver.findElement(By.xpath("//a[@id='button_AgreeButton']")).click();

	}

	@Given("^Click on login link$")
	public void click_on_login_link() throws Throwable {

		driver.switchTo().frame("DashboardFrame");
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//span[@id='Header_LoginButton']"))).click();

	}

	@When("^Enter username and Password and click on login button$")
	public void enter_username_and_Password_and_click_on_login_button()
			throws Throwable {
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("username")))
				.sendKeys(prop.getProperty("coworkerusername"));
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(
				prop.getProperty("password"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("when");
		
		
		
		
	}

	// private By userProfileLogo = By.id("HeaderLoggedInText");
	@Then("^Verify that user is logged into planner$")
	public void verify_that_user_is_logged_into_planner() throws Throwable {
				System.out.println("Then");
				Thread.sleep(5000);
               // driver.switchTo().frame("MainFrame");
				System.out.println(driver.getCurrentUrl());
				WebElement frame1=driver.findElement(By.id("DashboardFrame"));
                driver.switchTo().frame(frame1);
                WebElement userProfile = driver.findElement(By.id("HeaderLoggedInText"));
                if(userProfile.isDisplayed()||userProfile.isEnabled())
                {
                	System.out.println("element displayed");
                }
	}

	@Then("^add few cabinets to Planner and save the design$")
	public void add_few_cabinets_to_Planner_and_save_the_design()
			throws Throwable {
		driver.findElement(
				By.xpath("//button[@id='onetrust-accept-btn-handler']"))
				.click();
		WebElement searchproduct = driver.findElement(By
				.xpath("//input[@id='MainControl_Search_SearchAllProducts']"));
		searchproduct.sendKeys("sink");
		searchproduct.click();
		int length = Integer.parseInt(prop
				.getProperty("mincabinetsforvalidation"));
		for (int i = 1; i >= length; i++) {

			driver.findElement(
					By.xpath("//div[@class='PagingItemSelector_ItemsContainer']/div[1]"))
					.click();
		}

		// driver.findElement(By.xpath("//div[@class='PagingItemSelector_ItemsContainer']/div[2]")).click();
		driver.findElement(By.xpath("//span[@id='header_button_save']"))
				.click();
		driver.switchTo().frame(driver.findElement(By.id("frame_container")));
		int designSrNo = TestBase.RandomNumber();
		driver.findElement(By.id("save_as_name")).sendKeys(
				"TestDesign" + designSrNo);
		driver.findElement(By.id("button_save_as")).click();
		driver.findElement(By.xpath("//img[@id='button_close']")).click();
	}

	@Then("^Click on Proceed button and opt for Send validation option$")
	public void click_on_Proceed_button_and_opt_for_Send_validation_option()
			throws Throwable {
		driver.findElement(By.xpath("//a[@id='ecommerce_button']")).click();
		// driver.findElement(By.id("validate_option")).click();
		driver.findElement(By.xpath("//span[@id='General_SubmitButton']"))
				.click();

	}

	@Then("^Verify that Design is sent for Validation$")
	public void verify_that_Design_is_sent_for_Validation() throws Throwable {
		driver.findElement(By.xpath("//span[@id='Checkout_Complete_Header1']"))
				.getText().equalsIgnoreCase("Thank you");
		driver.findElement(By.xpath("//img[@id='button_close']")).click();
		WebElement ImageFile = driver.findElement(By
				.xpath("//img[@id='lock_img']"));

		Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver)
				.executeScript(
						"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
						ImageFile);
		if (!ImagePresent) {
			System.out.println("Image not displayed.");
		} else {
			System.out.println("Image displayed.");
		}
	}

	/*
	 * @Then("^Click on Proceed button and opt for Send to cart option$") public
	 * void click_on_Proceed_button_and_opt_for_Send_to_cart_option() {
	 * 
	 * driver.findElement(By.xpath("//a[@id='ecommerce_button']")).click();
	 * driver.findElement(By.xpath("//div[@id='buy_option']")).click();
	 * driver.findElement
	 * (By.xpath("//span[@id='General_SubmitButton']")).click();
	 * //span[@id='AddToCart_Complete_Header']
	 * 
	 * }
	 * 
	 * @Then("^Verify that Design is sent to Direct Basket$") public void
	 * verify_that_Design_is_sent_to_Direct_Basket() {
	 * driver.findElement(By.xpath
	 * ("//span[@id='AddToCart_Complete_Header']")).getText
	 * ().equalsIgnoreCase("Thank you"); String parentWindow =
	 * driver.getWindowHandle(); Set<String> handles =
	 * driver.getWindowHandles(); for(String windowHandle : handles){
	 * if(!windowHandle.equals(parentWindow)){
	 * driver.switchTo().window(windowHandle); }
	 * 
	 * }
	 * 
	 * List<WebElement> prices=driver.findElements(By.xpath(
	 * "//div[@class='productlist']/div/div/div[2]/div[2]/div/div"));
	 * for(WebElement price:prices) { System.out.println(price.getText());
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
}
