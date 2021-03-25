package stepdefinition;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
				.sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(
				prop.getProperty("password"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	// private By userProfileLogo = By.id("HeaderLoggedInText");
	@Then("^Verify that user is logged into planner$")
	public void verify_that_user_is_logged_into_planner() throws Throwable {
		Thread.sleep(10000);
		System.out.println(driver.getCurrentUrl());
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("DashboardFrame");
		WebElement userProfile = driver
				.findElement(By.id("HeaderLoggedInText"));
		if (userProfile.isDisplayed() || userProfile.isEnabled()) {
			System.out.println("element displayed");
			userProfile.click();
			Thread.sleep(500);
			WebElement loggedinuser = driver.findElement(By
					.xpath("//div[@id='HeaderLoggedInText']/span"));
			System.out.println(loggedinuser.getText());
			driver.switchTo().defaultContent();
		}

	}

	@Then("^add few cabinets to Planner and save the design$")
	public void add_few_cabinets_to_Planner_and_save_the_design()
			throws Throwable {
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("DashboardFrame");
		driver.switchTo().frame("FromScratchIframe");
		driver.findElement(
				By.xpath("//span[@id='CreateFromScratch_StartButton']"))
				.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		Thread.sleep(30000);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//input[@id='MainControl_Search_SearchAllProducts']")))
				.sendKeys("sink");
		driver.findElement(By.xpath("//div[@id='General_SearchButton']"))
				.click();
		int length = Integer.parseInt(prop
				.getProperty("mincabinetsforvalidation"));
		for (int i = 1; i <= length; i++) {
			try {
				WebElement Cabinet = driver
						.findElement(By
								.xpath("//div[@class='PagingItemSelector_ItemsContainer']/div[2]"));
				wait.until(ExpectedConditions.visibilityOf(Cabinet));
				Cabinet.click();
			} catch (Exception e) {
				WebElement Cabinet = driver
						.findElement(By
								.xpath("//div[@class='PagingItemSelector_ItemsContainer']/div[2]"));
				wait.until(ExpectedConditions.visibilityOf(Cabinet));
				Cabinet.click();
			}
		}
		Thread.sleep(20000);
		System.out.println("Save the design");
		driver.findElement(By.xpath("//span[@id='header_button_save']"))
				.click();
		driver.switchTo().frame("VPUIFrameSaveAS");
		String savetext = driver.findElement(
				By.xpath("//span[@id='SaveDesignFrame_Title']")).getText();
		System.out.println(savetext);
		int designSrNo = TestBase.RandomNumber();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//input[@id='save_as_name']"))).sendKeys(
				"Test-ignore " + designSrNo);
		driver.findElement(By.id("button_save_as")).click();
		driver.findElement(By.xpath("//img[@id='button_close']")).click();
		driver.switchTo().defaultContent();
	}

	@Then("^Click on Proceed button and opt for Send validation option$")
	public void click_on_Proceed_button_and_opt_for_Send_validation_option()
			throws Throwable {
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		WebElement Proceedbutton=driver.findElement(By.id("ecommerce_button"));
		js.executeScript("arguments[0].scrollIntoView(true);", Proceedbutton);
		if (Proceedbutton.isDisplayed() || Proceedbutton.isEnabled()) {
			System.out.println("Proceed button is displayed");
			Proceedbutton.click();
		}else
		{
			System.out.println("Proceed button is not displayed");
		}
		Thread.sleep(5000);
		driver.switchTo().frame("VPUIEcommerceFull");
		driver.findElement(By.xpath("//span[@id='General_SubmitButton']"))
				.click();
		driver.switchTo().defaultContent();
		

	}

	@Then("^Verify that Design is sent for Validation$")
	public void verify_that_Design_is_sent_for_Validation() throws Throwable {
		Thread.sleep(5000);
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		driver.switchTo().frame("VPUIValidateConfirmation");
		WebElement senttovalidateimg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("img_validate")));
		if(senttovalidateimg.isDisplayed())
		{
			System.out.println("Design is sent for validation successfully");
			driver.findElement(By.xpath("//img[@id='button_close']")).click();
			Thread.sleep(5000);
			driver.switchTo().frame("MainFrame");
			driver.switchTo().frame("PlannerFrame");
			WebElement lockdesignimage = driver.findElement(By
					.xpath("//img[@id='lock_img']"));

			if (lockdesignimage.isDisplayed()||lockdesignimage.isEnabled()) {
				System.out.println("Design is in Validation and it is locked");
			} else {
				System.out.println("Design is not locked");
			}
		}else
		{
			System.out.println("Design is not sent for validation");
		}
		
	}

	@Then("^add any cabinets to Planner and save the design$")
	public void add_any_cabinets_to_Planner_and_save_the_design()
			throws Throwable {
		
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("DashboardFrame");
		driver.switchTo().frame("FromScratchIframe");
		driver.findElement(
				By.xpath("//span[@id='CreateFromScratch_StartButton']"))
				.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		Thread.sleep(30000);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//input[@id='MainControl_Search_SearchAllProducts']")))
				.sendKeys("chair");
		driver.findElement(By.xpath("//div[@id='General_SearchButton']"))
				.click();
		WebElement Cabinet = driver
				.findElement(By
						.xpath("//div[@class='PagingItemSelector_ItemsContainer']/div[2]"));
		wait.until(ExpectedConditions.visibilityOf(Cabinet));
		Cabinet.click();
		Thread.sleep(10000);
		System.out.println("Save the design");
		driver.findElement(By.xpath("//span[@id='header_button_save']"))
				.click();
		driver.switchTo().frame("VPUIFrameSaveAS");
		String savetext = driver.findElement(
				By.xpath("//span[@id='SaveDesignFrame_Title']")).getText();
		System.out.println(savetext);
		int designSrNo = TestBase.RandomNumber();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//input[@id='save_as_name']"))).sendKeys(
				"Test-ignore " + designSrNo);
		driver.findElement(By.id("button_save_as")).click();
		driver.findElement(By.xpath("//img[@id='button_close']")).click();
		driver.switchTo().defaultContent();
	
	}

	@Then("^Click on Proceed button and opt for Send to cart option$")
	public void click_on_Proceed_button_and_opt_for_Send_to_cart_option() throws InterruptedException {
		Thread.sleep(5000);
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		WebElement Proceedbutton=driver.findElement(By.id("ecommerce_button"));
		js.executeScript("arguments[0].scrollIntoView(true);", Proceedbutton);
		if (Proceedbutton.isDisplayed() || Proceedbutton.isEnabled()) {
			System.out.println("Proceed button is displayed");
			Proceedbutton.click();
		}else
		{
			System.out.println("Proceed button is not displayed");
		}
		Thread.sleep(5000);
		driver.switchTo().frame("VPUIEcommerceFull");
		driver.findElement(By.xpath("//div[@id='buy_option']")).click();
		driver.findElement(By.xpath("//span[@id='General_SubmitButton']"))
				.click();
		driver.switchTo().defaultContent();

	}

	@Then("^Verify that Design is sent to Direct Basket$")
	public void verify_that_Design_is_sent_to_Direct_Basket() {
		driver.findElement(By.xpath("//span[@id='AddToCart_Complete_Header']"))
				.getText().equalsIgnoreCase("Thank you");
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
			}

		}

		List<WebElement> prices = driver
				.findElements(By
						.xpath("//div[@class='productlist']/div/div/div[2]/div[2]/div/div"));
		for (WebElement price : prices) {
			System.out.println(price.getText());
		}
	}

	@Then("^add single cabinet to Planner and save the design$")
	public void add_any_cabinet_to_Planner_and_save_the_design()
			throws Throwable {
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("DashboardFrame");
		driver.switchTo().frame("FromScratchIframe");
		driver.findElement(
				By.xpath("//span[@id='CreateFromScratch_StartButton']"))
				.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		Thread.sleep(30000);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//input[@id='MainControl_Search_SearchAllProducts']")))
				.sendKeys("sink");
		driver.findElement(By.xpath("//div[@id='General_SearchButton']"))
				.click();
		WebElement Cabinet = driver
				.findElement(By
						.xpath("//div[@class='PagingItemSelector_ItemsContainer']/div[1]"));
		wait.until(ExpectedConditions.visibilityOf(Cabinet));
		for(int i=0; i<=2;i++){
		try
		{
			Cabinet.click();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		}
		Thread.sleep(20000);
		System.out.println("Save the design");
		driver.findElement(By.xpath("//span[@id='header_button_save']"))
				.click();
		driver.switchTo().frame("VPUIFrameSaveAS");
		String savetext = driver.findElement(
				By.xpath("//span[@id='SaveDesignFrame_Title']")).getText();
		System.out.println(savetext);
		int designSrNo = TestBase.RandomNumber();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//input[@id='save_as_name']"))).sendKeys(
				"Test-ignore " + designSrNo);
		driver.findElement(By.id("button_save_as")).click();
		driver.findElement(By.xpath("//img[@id='button_close']")).click();
		driver.switchTo().defaultContent();


	}

	@Then("^Click on Proceed button and Verify that Send Validation option is disabled with proper warning message$")
	public void verify_that_Send_Validation_is_disabled_with_proper_warning_message()
			throws Throwable {
		Thread.sleep(5000);
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		WebElement Proceedbutton=driver.findElement(By.id("ecommerce_button"));
		js.executeScript("arguments[0].scrollIntoView(true);", Proceedbutton);
		if (Proceedbutton.isDisplayed() || Proceedbutton.isEnabled()) {
			System.out.println("Proceed button is displayed");
			Proceedbutton.click();
		}else
		{
			System.out.println("Proceed button is not displayed");
		}
		Thread.sleep(5000);
		driver.switchTo().frame("VPUIEcommerceFull");
		WebElement ValidationOption=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("validate_option")));
		if(!ValidationOption.isEnabled())
		{
			System.out.println("Send design for validation option is Disabled--Test Passed");
		}
	}

}
