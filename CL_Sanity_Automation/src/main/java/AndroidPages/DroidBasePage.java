package AndroidPages;


import java.time.Duration;
import java.util.List;
import com.google.common.collect.ImmutableMap;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DroidBasePage {
	
	public AndroidDriver<MobileElement> driver;
	
	public By menuHome = By.xpath("//android.widget.TextView[@text='Home']");
	public By lnkAbout = By.xpath("//android.widget.TextView[@text='About']");
	public By lnkHelpAndSupport = By.xpath("//android.widget.TextView[@text='Help and Support']");
	public By lnkAnonymous = By.xpath("//android.widget.TextView[@text='Anonymous Usage Stat']");
	public By btnBack = By.xpath("//android.widget.ImageButton[@index=0]");
	public By btnLogout = By.xpath("//android.widget.Button[@text='LOGOUT']");	
	public By txtNotifications = By.xpath("//android.widget.TextView[@text='Notifications']");
	
	public int minWait = 15;
	public int maxWait = 30;
	WebDriverWait wait;

	public DroidBasePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, minWait);
    }
	
	public void clickElementUsingF(By locator) {
		FluentWait<AndroidDriver<MobileElement>> wait = new FluentWait<>(this.driver)
		        .pollingEvery(Duration.ofSeconds(2))
		        .withTimeout(Duration.ofSeconds(20))
		        .ignoring(StaleElementReferenceException.class)
		        .ignoring(NoSuchElementException.class)
		        .ignoring(ElementNotVisibleException.class);

		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}


    public void waitForVisibilityOf(By locator) {
   		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    
    public void waitForClickabilityOf(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public void scrolluiautomator(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    public String getPageTitle() {
		return driver.getTitle();
	}
	

	public MobileElement getElement(By element) throws Exception {
		FluentWait<AndroidDriver<MobileElement>> wait = new FluentWait<>(this.driver)
		        .pollingEvery(Duration.ofSeconds(2))
		        .withTimeout(Duration.ofSeconds(20))
		        .ignoring(StaleElementReferenceException.class)
		        .ignoring(NoSuchElementException.class)
		        .ignoring(ElementNotVisibleException.class);

		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		
		return (MobileElement) driver.findElement(element);
	}
   
    public void verifybyTextId(By id,String match_text){
    	
    	if(driver.findElement(id).getText().equals(match_text)){
    		Assert.assertTrue(true, "Text matches with"+match_text);
    	} else {
    		Assert.assertFalse(true, "Text not matches with"+match_text);
    	}
    }
    
    public void selectfromListId(By id,String match_text) {
		List<MobileElement> type = (List<MobileElement>) driver.findElements(id);
    	int i;
		try {
			for(i=0; i<type.size();i++)
			{
				System.out.println(type.get(i).getText());
				if(type.get(i).getText().equals(match_text)){
					type.get(i).click();
					Assert.assertTrue(true, "Clicked "+type.get(i).getText());
					break;
				}
			}
		} catch (Exception e) {
			Assert.assertFalse(true, e.getMessage());
		}
    }
    

    public void scrollElement(String value)
    {
    	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+value+"\"))").click();
    }
    

    public void scrollToElement(String value)
    {
    	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+value+"\"))");
    }
    

    public void clickElement(By locator){
    	try {
    			waitForVisibilityOf(locator);
    			waitForClickabilityOf(locator);
    			driver.findElement(locator).click();
            } catch (Exception e)
        	{
            	Assert.assertFalse(true, e.getMessage());
        	}
    }
    

    public void clickElement(By locator, int waitForEle) throws InterruptedException{
    
    	try {
        		waitforPageLoad(waitForEle);
    			waitForVisibilityOf(locator);
    			waitForClickabilityOf(locator);
    			driver.findElement(locator).click();
            } catch (Exception e)
        	{
            	Assert.assertFalse(true, e.getMessage());
        	}
    }
    
    public void enterText(By locator, String value){
    	try {
    			waitforPageLoad(1);
    			waitForVisibilityOf(locator);
    			driver.findElement(locator).sendKeys(value);
            } catch (Exception e)
        	{
            	e.printStackTrace();
        	}
    }
    
    public void clearAndEnterText(By locator, String value){
    	try {
    			waitForVisibilityOf(locator);
    			driver.findElement(locator).clear();
    			driver.findElement(locator).sendKeys(value);
            } catch (Exception e)
        	{
            	e.printStackTrace();
        	}
    }
    
    public void clear(By locator) {
    	try {
    	waitForVisibilityOf(locator);
		waitForClickabilityOf(locator);
    	driver.findElement(locator).clear();
    	} catch (Exception e)
    	{
        	e.printStackTrace();
    	}
	}
    
    public void choosefromDropDown(By locator, int index){
    	 waitForVisibilityOf(locator);
 	   List<MobileElement> list_count = (List<MobileElement>) driver.findElements(locator);
 	   if(!list_count.isEmpty()){
 		   try {
 		   System.out.println("Clicking " + list_count.get(index).getText() +" Size is: "+list_count.size());
 		   list_count.get(index).click();
 		   } catch (Exception e) {
 			  Assert.assertFalse(true, e.getMessage());
 		   }
 	   } else {
 		  Assert.assertFalse(true, "Could not find the DropDown List");
 	   }
    }
	
	public void scrolltoElement(By locator) {
		try {
		WebElement element = driver.findElement(locator);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		} catch (Exception e) {
			Assert.assertFalse(true, "Unable to Scroll to Element"+e.getMessage());
		}
	}
	
	public void waitforPageLoad(int sec) throws InterruptedException {
		Thread.sleep(1000 * sec);
	}
	
	public void setOrientationToLANDSCAPE() throws InterruptedException {
		waitforPageLoad(1);
		driver.rotate(ScreenOrientation.LANDSCAPE);
	}
	
	
	public void setOrientationToPORTRAIT() throws InterruptedException {
		waitforPageLoad(1);
		driver.rotate(ScreenOrientation.PORTRAIT);
	}
	
	public MobileElement generateElement(String text) throws Exception {
		String element = "//android.widget.TextView[@text='"+text+"']";
	    return (MobileElement) driver.findElement(By.xpath(element));
	}
	
	public void swipeDownAnonymous(int pixelsToSwipe) {

		  try {
		   Point value = null;
		   value = driver.findElement(lnkAnonymous).getLocation();
		   int x = value.x;
		   int y = value.y;
		   int y1 = value.y+pixelsToSwipe;

		   swipe(x,y1,x,y);
		   
		  } catch(Exception e) {
		   System.out.println(e.getMessage());
		  }
	}
	
	public void swipeDownHelp(int pixelsToSwipe) {

		  try {
		   Point value = null;
		   value = driver.findElement(lnkHelpAndSupport).getLocation();
		   int x = value.x;
		   int y = value.y;
		   int y1 = value.y+pixelsToSwipe;

		   swipe(x,y1,x,y);
		   
		  } catch(Exception e) {
		   System.out.println(e.getMessage());
		  }
	}
	
	public void swipeDownAbout(int pixelsToSwipe) {

		  try {
		   Point value = null;
		   value = driver.findElement(lnkAbout).getLocation();
		   int x = value.x;
		   int y = value.y;
		   int y1 = value.y+pixelsToSwipe;

		   swipe(x,y1,x,y);
		   
		  } catch(Exception e) {
		   System.out.println(e.getMessage());
		  }
	}
	public void swipe(int fromX,int fromY,int toX,int toY) {
		  
		  TouchAction action = new TouchAction(driver);
		  action.press(PointOption.point(fromX,fromY))
		  .waitAction(new WaitOptions().withDuration(Duration.ofMillis(2000)))
		  .moveTo(PointOption.point(toX, toY))
		  .release()
		  .perform();
	 }
	
	
	public void scrollDown() {
	    //if pressX was zero it didn't work for me
	    int pressX = driver.manage().window().getSize().width / 2;
	    // 4/5 of the screen as the bottom finger-press point
	    int bottomY = driver.manage().window().getSize().height * 4/5;
	    // just non zero point, as it didn't scroll to zero normally
	    int topY = driver.manage().window().getSize().height / 8;
	    //scroll with TouchAction by itself
	    scroll(pressX, bottomY, pressX, topY);
	}

	public void scroll(int fromX, int fromY, int toX, int toY) {
	    TouchAction touchAction = new TouchAction(driver);
	    touchAction.longPress(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();
	}
	
	    public void sendKeyBoardKey(By locator){
	    	try {
	    			waitForVisibilityOf(locator);
	    			driver.findElement(locator).sendKeys(Keys.chord(Keys.CONTROL + "m"));
	            } catch (Exception e)
	        	{
	            	e.printStackTrace();
	        	}
	    }
	 
	 public void buttonBack()
	 {
		 clickElement(btnBack);
		 clickElement(btnBack);
		 clickElement(btnBack);
	 }
	 
	 public void navigateBackTwice() throws InterruptedException
		{
			driver.navigate().back();
			waitforPageLoad(1);
			driver.navigate().back();
			waitforPageLoad(1);
		}
	 
	 public void navigateBack() 
	 {
		 driver.navigate().back();
	 }	
	 
	 public void allow() throws InterruptedException
	 {
		 waitforPageLoad(1);
		 driver.findElement(MobileBy.id("com.android.packageinstaller:id/permission_allow_button")).click();
	 }
	 
	 public void search() throws InterruptedException
	 {
		 waitforPageLoad(1);
		 driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
	 }
	 
	 public void logout()
	 {
		clickElement(btnLogout);
		clickElement(btnLogout);
	 }
	 
	 public void pressEnter(By locator) {
		driver.findElement(locator).sendKeys(Keys.ENTER); 
	 }
	 
	 public void selectDropDown(By locator, String value) {
		Select optSelect =new Select(driver.findElement(locator));
		optSelect.selectByVisibleText(value);
	 }
}
