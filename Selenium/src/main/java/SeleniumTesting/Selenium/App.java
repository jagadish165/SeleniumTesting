package SeleniumTesting.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.setProperty("webdriver.chrome.driver", "C:\\Java\\chromedriver_win32\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();
	    System.out.println( "Hello World!" );
        driver.get("http://www.smallcase.com");
    }
    
}
