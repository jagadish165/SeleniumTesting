package SeleniumTesting.Selenium;		

import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;	
import org.testng.annotations.AfterTest;
public class AppTest {		
	    private WebDriver driver;
		private String flg;
		private int i;		
		private ExcelOp insExcel;
		private Robot robot;
		private int j;
		@Test				
		public void testEasy() throws IOException {	
			
	        System.out.println( "Hello World" );
	        driver.get("http://www.smallcase.com");
	        driver.findElement(By.linkText("Log in with your broker")).click();
	        driver.findElement(By.xpath("//*[@id='__next']/div[4]/div[2]/div/div[1]/div[2]/div/div[1]/a[7]/div")).click();
	        String oldTab = driver.getWindowHandle();
	        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	        newTab.remove(oldTab);
	        // change focus to new tab
	        driver.switchTo().window(newTab.get(0));
	        driver.findElement(By.xpath("//*[@class = 'su-input-label su-visible su-dynamic-label']/following-sibling::input")).sendKeys("NZ7621");
	        driver.findElement(By.xpath("//*[@class = 'su-input-label su-dynamic-label']/following-sibling::input")).sendKeys("Stergang@1"); 	
	        driver.findElement(By.xpath("//*[@id='container']/div/div/div[2]/form/div[4]/button")).click();
	        
	        
	       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='PIN']")));
	        driver.findElement(By.xpath("//*[@id=\"container\"]/div/div/div[2]/form/div[2]/div/input")).sendKeys("115165");
	        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
	        driver.findElement(By.xpath("//a[contains(.,'Discover smallcases')]")).click();
	        robot.delay(500);
	        driver.findElement(By.xpath("//*[text()='All smallcases']")).click();
	        int s =0;
	        do {
	        	flg = "no";
	        	robot.keyPress(KeyEvent.VK_CONTROL);
	        	robot.keyPress(KeyEvent.VK_END);
	        	robot.keyRelease(KeyEvent.VK_END);
	        	robot.keyRelease(KeyEvent.VK_CONTROL);
	        	robot.delay(500);
	        	
	            if(driver.findElements(By.xpath("//span[contains(.,'Load more')]")).size()!=0) {
	            flg = "yes";
	            s = s++;
	            String rst = retryingFind(By.xpath("//span[contains(.,'Load more')]"),"click");
	            if(rst=="false") {
	            break;
	            }
	            }
	          } while(flg =="yes" && s<10);
	        List<WebElement> Smlcs = driver.findElements(By.xpath("//div[@class='DiscoverCard__sc-card___2YtVy']"));
	        i =0;
	       // System.out.println(Smlcs.size());
	        //for(WebElement e : Smlcs) {
	        for(int a=0;a<Smlcs.size();a++) {
	        	//System.out.println(Smlcs.get(i).getText());
	        	//j = 0;
	        	//int size = Smlcs.get(i).findElements(By.xpath(".//*[contains(@class,'DiscoverCard__name_')]")).size();
	        	//System.out.println(size);
	        	List<WebElement> Smlcs1 = driver.findElements(By.xpath("//div[@class='DiscoverCard__sc-card___2YtVy']"));
	        	String title = Smlcs1.get(a).findElement(By.xpath(".//*[contains(@class,'DiscoverCard__name_')]")).getText();
	        	String desc = Smlcs1.get(a).findElement(By.xpath(".//*[contains(@class,'DiscoverCard__descr')]")).getText();
	        	String CAGR = Smlcs1.get(a).findElement(By.xpath(".//*[text() = 'CAGR']/following-sibling::div")).getText();
//	        	insExcel.WriteExcel(i, j, title);
//	        	insExcel.WriteExcel(i, j+1, desc);
//	        	insExcel.WriteExcel(i, j+2, CAGR);
	        	robot.keyPress(KeyEvent.VK_CONTROL);
	        	robot.keyPress(KeyEvent.VK_HOME);
	        	robot.keyRelease(KeyEvent.VK_HOME);
	        	robot.keyRelease(KeyEvent.VK_CONTROL);
	        	robot.delay(500);
	        	Smlcs1.get(a).findElement(By.xpath(".//*[contains(@class,'DiscoverCard__name_')]")).click();
	        	driver.findElement(By.xpath("//*[contains(text(),'Stocks') or contains(text(),'ETF')][contains(@class,'RouteTab__tab_element')]")).click();
	        	getStocks(title,desc,CAGR);
	        	driver.navigate().back();
	        	driver.navigate().back();
	        	robot.delay(1000);
	        	i=i++;
	        	System.out.println(i+"-main");
	        	}
		}	
		@BeforeTest
		public void beforeTest() throws IOException, AWTException {	
			
			
			Runtime rt = Runtime.getRuntime();
			rt.exec("taskkill /im chromedriver.exe /f /t");
			System.setProperty("webdriver.chrome.driver", "C:\\Java\\chromedriver_win32\\chromedriver.exe");
		    driver = new ChromeDriver();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		    driver.manage().window().maximize();
		    robot = new Robot();
		    insExcel = new ExcelOp();
		    
		}		
		@AfterTest
		public void afterTest() {
			//driver.quit();			
		}	
		public String retryingFind(By by,String strtype) {
		    int attempts = 0;
		    while(attempts < 2) {
		        try {
		        	switch(strtype)
		        	{
		        	   // case statements
		        	   // values must be of same type of expression
		        	   case "click" :
		        		  driver.findElement(by).click();
		        		  return "true";
		        	      // break is optional
		        	   
		        	   case "getText" :
		        		   return driver.findElement(by).getText();
		        		   
		        	      // Statements
		        	       // break is optional
		        	   
		        	   // We can have any number of case statements
		        	   // below is default statement, used when none of the cases is true. 
		        	   // No break is needed in the default case.
		        	   default : 
		        	      // Statements
		        	}
		            
		            
		           
		        } catch(StaleElementReferenceException e) {
		        }
		        attempts++;
		    }
		    return "false";
		}
		public void getStocks(String ttl,String dsc,String CGR) throws IOException {
			
        	List<WebElement> StocksGrid = driver.findElements(By.xpath("//*[@class='mt16']/div"));
        	int d = i;
        	System.out.println(i+"-sub1");
        	j=0;
        	for(WebElement div : StocksGrid) {
        		String Sector = div.findElement(By.xpath(".//*[contains(@class,'pull-left text')]")).getText();
        		String SectWght = div.findElement(By.xpath(".//*[contains(@class,'pull-right text')]")).getText();
        		String LstRbl = driver.findElement(By.xpath("//div[contains(@class,'StocksWeights__rebalance')]/div/div[1]/div[2]")).getText();
        		String NxtRbl = driver.findElement(By.xpath("//div[contains(@class,'StocksWeights__rebalance')]/div/div[2]/div[2]")).getText();
        		String Risk = driver.findElement(By.xpath("//div[contains(@class,'label-risk')]")).getText();
        		//String LstRbl = 
        		
        		//System.out.println(Sector+"             "+SectWght);
        		List<WebElement> Stocks = div.findElements(By.xpath(".//*[contains(@class,'StocksWeights__stock-name__')]"));
        		List<WebElement> StckWgt = div.findElements(By.xpath(".//*[contains(@class,'pull-right text')]"));
        		for(int x = 0;x<Stocks.size();x++) {
        			String strStocks = Stocks.get(x).getText();
        			String strStckWgt = StckWgt.get(x).getText();
        			Actions actions = new Actions(driver);
        			
        			
        			
        				actions.moveToElement(Stocks.get(x));
            			actions.perform();	
        			
        				
        			JavascriptExecutor js = (JavascriptExecutor) driver;
        		//	js.executeScript("arguments[0].scrollIntoView();", Stocks.get(x));
        		//	js.executeScript("window.scrollTo(0,"+Stocks.get(x).getLocation().y+")");
        			js.executeScript("window.scrollBy(0,30)");
        			Stocks.get(x).click();
        			
        			String strStckprice = driver.findElement(By.xpath("//div[contains(@class,'StockWidgetContent__price_')]/h2/span[1]")).getText();
        			String strStck1Mret = driver.findElement(By.xpath("//div[contains(@class,'StockWidgetContent__returns')]/div[1]/h5[1]")).getText();
        			String strStck1Yret = driver.findElement(By.xpath("//div[contains(@class,'StockWidgetContent__returns')]/div[2]/h5[1]")).getText();
        			insExcel.WriteExcel(d, j, ttl);
            		insExcel.WriteExcel(d, j+1, dsc);
            		insExcel.WriteExcel(d, j+2, CGR);
            		insExcel.WriteExcel(d, j+3, Sector);
            		insExcel.WriteExcel(d, j+4, SectWght);
        			insExcel.WriteExcel(d, j+5, strStocks);
            		insExcel.WriteExcel(d, j+6, strStckWgt);
            		insExcel.WriteExcel(d, j+7, LstRbl);
            		insExcel.WriteExcel(d, j+8, NxtRbl);
            		insExcel.WriteExcel(d, j+9, Risk);
            		insExcel.WriteExcel(d, j+10, strStckprice);
            		insExcel.WriteExcel(d, j+11, strStck1Mret);
            		insExcel.WriteExcel(d, j+12, strStck1Yret);
            		//div.findElement(By.xpath(".//*[contains(@class,'pull-left text')]")).click();
            		robot.keyPress(KeyEvent.VK_ESCAPE);
            		d= d+1;
        			//System.out.println(strStocks+"             "+strStckWgt);
        		}
        		
        		}
        	i = d;
        	System.out.println(i+"-sub2");
		}
}	