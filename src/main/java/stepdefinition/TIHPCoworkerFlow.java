package stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TIHPCoworkerFlow extends TestBase{

	public TIHPCoworkerFlow() throws IOException {
		super();
		
	}

	@Given("^Access IHP Planner for coworker validation$")
	public void access_IHP_Planner_for_coworker_validation(){
		TestBase.initialization();
		driver.switchTo().frame("MainFrame");
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.id("Agreement_CheckBox")));
		driver.findElement(By.id("Agreement_CheckBox")).click();
		driver.findElement(By.xpath("//a[@id='button_AgreeButton']")).click();
	}

	@When("^Click on login link on hoomepage$")
	public void click_on_login_link_on_hoomepage(){
		driver.switchTo().frame("DashboardFrame");
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//span[@id='Header_LoginButton']"))).click();
	}

	@When("^Enter coworker username and Password and click on login button$")
	public void enter_coworker_username_and_Password_and_click_on_login_button(){
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.id("username")))
				.sendKeys(prop.getProperty("coworkerusername"));
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(
				prop.getProperty("password"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@When("^Click on Open Validation link$")
	public void click_on_Open_Validation_link() throws InterruptedException{
		Thread.sleep(10000);
		System.out.println(driver.getCurrentUrl());
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("DashboardFrame");
		WebElement userProfile = driver
				.findElement(By.id("HeaderLoggedInText"));
		if (userProfile.isDisplayed() || userProfile.isEnabled()) {
			System.out.println("element displayed");
			userProfile.click();
			driver.findElement(By.xpath("//div[@id='header_loggedUserWrapper']/ul/li[3]")).click();
		}
	}

	@Then("^Enter Email Id and click on Get Design button$")
	public void enter_Email_Id_and_click_on_Get_Design_button() throws InterruptedException{
	   Thread.sleep(5000);
	   driver.switchTo().frame("VPUIOpenValidation");
	   WebElement UserIDTextbox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='inputEmail']")));
	   UserIDTextbox.sendKeys(prop.getProperty("customerid"));
	   UserIDTextbox.sendKeys(Keys.ENTER);
	   Thread.sleep(5000);
	   
	   
	   
		
	}

	@Then("^Verify that designs are displayed in the drop down$")
	public void verify_that_designs_are_displayed_in_the_drop_down(){
	driver.findElement(By.xpath("//button[@data-id='DesignNameList']")).click();
	List<WebElement> designs=driver.findElements(By.xpath("//ul[@role='listbox']"));
	System.out.println(designs);
	if(!designs.isEmpty()){
	 for(WebElement designname:designs)
	 {
		 System.out.println(designname);
	 }
	}else
	{
		System.out.println("There is no designs for validation");
	}
		
		
	}

	@When("^select any design and click on start validation button$")
	public void select_any_design_and_click_on_start_validation_button() throws InterruptedException{
		driver.findElement(By.xpath("//ul[@role='listbox']/li/a")).click();
		driver.findElement(By.xpath("//button[@id='nextbutton']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("startValidationButton"))).click();
		Thread.sleep(10000);
	}

	@Then("^Design area should be displayed and verify the login$")
	public void design_area_should_be_displayed_and_verify_the_login(){
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		driver.switchTo().frame("HostRoomFrame");
		WebElement planningarea=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//canvas[@id='canvas']")));
		if(planningarea.isDisplayed())
		{
		System.out.println("Planning area is opened");
		}else
		{
			System.out.println("Planning area is not opened");
		}
		driver.switchTo().defaultContent();
	}

	@When("^Modify the design and save the design$")
	public void modify_the_design_and_save_the_design() throws Throwable {
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		Thread.sleep(30000);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//input[@id='MainControl_Search_SearchAllProducts']")))
				.sendKeys("chair");
		driver.findElement(By.xpath("//div[@id='General_SearchButton']"))
				.click();
		for (int i = 1; i <= 1; i++) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='PagingItemSelector_ItemsContainer']/div[2]"))).click();
				
			} catch (Exception e) {
				WebElement Cabinet = driver
						.findElement(By
								.xpath("//div[@class='PagingItemSelector_ItemsContainer']/div[2]"));
				wait.until(ExpectedConditions.visibilityOf(Cabinet));
				Cabinet.click();
			}
		}
		
		Thread.sleep(10000);
		System.out.println("Save the design");
		driver.findElement(By.xpath("//span[@id='header_button_save']"))
				.click();
		Thread.sleep(5000);
	
	}

	@When("^Click on Finish Validation button$")
	public void click_on_Finish_Validation_button(){
		 WebElement FinishValidationbutton= driver.findElement(By.xpath("//a[@class='button_type_a']"));
   	     js.executeScript("arguments[0].scrollIntoView(true);", FinishValidationbutton);
   	     FinishValidationbutton.click();
   	     
	 	
	}	
	@Then("^verify that Design is Validated$")
	public void verify_that_Design_is_Validated() throws InterruptedException{
		Thread.sleep(10000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		driver.switchTo().frame("PlannerFrame");
		WebElement lockdesignimage = driver.findElement(By
				.xpath("//img[@id='lock_img']"));

		if (lockdesignimage.isDisplayed()||lockdesignimage.isEnabled()) {
			System.out.println("Design is Validated by coworker");
		} else {
			System.out.println("Design is not Validated");
		}
	}

}
