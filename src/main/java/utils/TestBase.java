package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
	public static WebDriver driver;
    public static Properties prop;
    public static JavascriptExecutor js;
    public static Actions action;
    public static WebDriverWait wait;
    public static Dimension d;
  
  //  SoftAssert a=new SoftAssert();
    

    public TestBase() throws IOException
    {
       prop=new Properties();
        try {
        	FileInputStream ip = new FileInputStream("./src/main/java/configuration/Config.properties");
          
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
            System.setProperty("webdriver.gecko.driver", "./BrowserDrivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
        //TestBase.resizeBrowser();
        //driver.manage().deleteAllCookies();
//        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        js = (JavascriptExecutor)driver;
        /*String zoomInJS="document.body.style.zoom='80%'";
    	js.executeScript(zoomInJS);*/
        action=new Actions(driver);
       
        
    }
    public static int RandomNumber()
    {
    	Random r=new Random();
    	return r.nextInt(1000);
    	
    }
    public static void resizeBrowser() {
        /*d = new Dimension(800,480);
        driver.manage().window().setSize(d);
    	for(int i=0; i<3; i++){
			driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
		}*/
    	/*String zoomInJS="document.bodystyle.zoom='50%'";
    	js.executeAsyncScript(zoomInJS);
		return zoomInJS;*/
        
    }
    	

}
