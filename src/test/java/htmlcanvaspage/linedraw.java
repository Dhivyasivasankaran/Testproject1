package htmlcanvaspage;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class linedraw {

	static WebDriver driver;
    
	
	@BeforeClass
	public static void setup() throws Exception {
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://htmlcanvasstudio.com");
        Thread.sleep(1000);
        driver.manage().window().maximize();
	}
	
	
    @Test
	public void lineoperations() throws Exception {
		// TODO Auto-generated method stub
    	
    	//Locating the canvas window area by using id locator
        WebElement draw = driver.findElement(By.id("imageTemp"));

    	//Getting the size of canvas window by using getSize() method
        Dimension size = draw.getSize();
        
        //Printing the Overall Size
        System.out.println("Overall Size --"+size);
        
        //Getting the width of the canvas window by getWidth() method and divide by 4 for making as four quadrants
        int width1 = size.getWidth() / 4;
        
        //Getting the width of the canvas window by getWidth() method and divide by 4 for making as four quadrants
        int height1 = size.getHeight() / 4;
        
        //Printing the values fo width1 and height1
        System.out.println(width1+"--"+height1);
        
        //Handling NoSuchElementException using Explicit wait condition
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(draw));
        
        //Locating a line button by xpath
        driver.findElement(By.xpath("//*[@title='Draw a line']")).click();
        
        //Creating object for Actions class for handing mouse events
        Actions act = new Actions(driver);
         
        //Drawing a horizontal line
        act.moveToElement(draw,-width1, 0).clickAndHold().moveToElement(draw,width1,0).release().click().perform();
        
        //Drawing a vertical line
        act.moveToElement(draw,0, -height1).clickAndHold().moveToElement(draw,0,height1).release().click().perform();
        Thread.sleep(1000);

        //Locating a rectangle button  by xpath
        driver.findElement(By.xpath("//*[@title='Draw a rectangle']")).click();
        
        //Drawing a rectangle
        act.moveToElement(draw,-width1, height1+10).clickAndHold().moveToElement(draw,width1,height1+50).release().click().perform();
        Thread.sleep(1000);
        
        //Locating a eraser button by xpath
        driver.findElement(By.xpath("//*[@title='Use eraser']")).click();
        
        //Using eraser to erase the horizontal line drawn first
        act.moveToElement(draw,-width1, 0).clickAndHold().moveToElement(draw,width1,0).release().click().perform();
        Thread.sleep(1000);  
    }
    
    @AfterClass
    public static void tearDown() {
    	driver.quit();
    }
	}


	