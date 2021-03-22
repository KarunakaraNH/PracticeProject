package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	public static WebDriver driver;
    public static Properties prop;
    public static JavascriptExecutor js;
    public static Actions action;
    public static WebDriverWait wait;
  
  //  SoftAssert a=new SoftAssert();
    

    public TestBase() throws IOException
    {
        prop=new Properties();
        try {
        	FileInputStream ip = new FileInputStream("./src/main/java/configuration/Config.properties");
           System.out.println("Karan");
        	prop.load(ip);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void initialization(){
    	
        String browserName = prop.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "./BrowserDrivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("FF")){
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\karunh\\IHP2 Automation(TIHP)\\IHP2Automation\\BrowserDrivers");
            //driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        js = (JavascriptExecutor)driver;
        action=new Actions(driver);
       
        
    }
    public static int RandomNumber()
    {
    	Random r=new Random();
    	return r.nextInt(1000);
    	
    }
    	

}
