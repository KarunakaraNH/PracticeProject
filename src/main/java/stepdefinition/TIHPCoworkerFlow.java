package stepdefinition;

import java.io.IOException;

import org.openqa.selenium.By;
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
/*		TestBase.initialization();
		driver.switchTo().frame("MainFrame");
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Agreement_CheckBox")));
		driver.findElement(By.id("Agreement_CheckBox")).click();
		driver.findElement(By.xpath("//a[@id='button_AgreeButton']")).click();*/
	}

	@When("^Click on login link on hoomepage$")
	public void click_on_login_link_on_hoomepage(){
		/*driver.switchTo().frame("DashboardFrame");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='Header_LoginButton']"))).click();
	*/}

	@When("^Enter coworker username and Password and click on login button$")
	public void enter_coworker_username_and_Password_and_click_on_login_button(){
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//button[@type='submit']")).click();*/
	}

	@Then("^Verify that coworker is logged into planner$")
	public void verify_that_coworker_is_logged_into_planner(){
		/*String Expectedtext=prop.getProperty("username");
		WebElement profilesection=driver.findElement(By.xpath("//div[@id='HeaderLoggedInText']/span"));
		System.out.println(profilesection.getText());*/
	}

	@When("^Click on Open Validation link$")
	public void click_on_Open_Validation_link(){
	  
	}

	@Then("^Enter Email Id and click on Get Design button$")
	public void enter_Email_Id_and_click_on_Get_Design_button(){
	   
	}

	@Then("^Verify that designs are displayed in the drop down$")
	public void verify_that_designs_are_displayed_in_the_drop_down(){
	 
	}

	@When("^select any design and click on start validation button$")
	public void select_any_design_and_click_on_start_validation_button(){
	
	}

	@Then("^Design area should be displayed and verify the login$")
	public void design_area_should_be_displayed_and_verify_the_login(){
	
	}

	@When("^Modify the design and save the design$")
	public void modify_the_design_and_save_the_design() throws Throwable {
	  
	
	}

	@When("^Click on Finish Validation button$")
	public void click_on_Finish_Validation_button(){
	  
		
	}	
	@Then("^verify that Design is Validated$")
	public void verify_that_Design_is_Validated(){
	   
	}

}
