package WebPages;

import java.awt.AWTException;
import java.awt.Robot;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WPTBasePage {
	
	public WebDriver web_driver;
	public int minWait = 15;
	public int maxWait = 30;
	WebDriverWait wait;

	public WPTBasePage(WebDriver driver) {
        this.web_driver = driver;
        this.wait = new WebDriverWait(driver, minWait);

    }
	
	// Click the Element using FluentWait
	public WebElement getElement(By element) {
		FluentWait<WebDriver> wait = new FluentWait<>(this.web_driver)
		        .pollingEvery(Duration.ofSeconds(2))
		        .withTimeout(Duration.ofSeconds(8))
		        .ignoring(StaleElementReferenceException.class)
		        .ignoring(NoSuchElementException.class)
		        .ignoring(ElementNotVisibleException.class);

		wait.until(ExpectedConditions.elementToBeClickable(element));
		return (WebElement) web_driver.findElement(element);
	}
	
	
	// Wait till the Element gets visible
    public void waitForVisibilityOf(By locator) {
   		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
	// Wait till the Element gets Clickable
    public void waitForClickabilityOf(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
	// Get the Page Title
    public String getPageTitle() {
		return web_driver.getTitle();
	}
	
	// Verify the text Id
    public void verifybyTextId(By id,String match_text){
    	
    	if(web_driver.findElement(id).getText().equals(match_text)){
    		Assert.assertTrue(true, "Text matches with"+match_text);
    	} else {
    		Assert.assertFalse(true, "Text not matches with"+match_text);
    	}
    }
    
	// Select a Link from List
    public void selectfromListId(By id,String match_text) {
		List<WebElement> type = (List<WebElement>) web_driver.findElements(id);
    	int i;
		try {
			for(i=0; i<type.size();i++)
			{
				//System.out.println(type.get(i).getText());
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
    
    public void clickAllHyperLinksByTagName(String tagName){
        int numberOfElementsFound = getNumberOfElementsFound(By.tagName(tagName));
        System.out.println(numberOfElementsFound);
        for (int pos = 0; pos < numberOfElementsFound; pos++) {
            getElementWithIndex(By.tagName(tagName), pos).click();
            web_driver.navigate().back();
        }
    }

    public int getNumberOfElementsFound(By by) {
        return web_driver.findElements(by).size();
    }

    public WebElement getElementWithIndex(By by, int pos) {
        return web_driver.findElements(by).get(pos);
    }
    
    public void selectfromDropdown(By locator, String match_text) {
    
    	List<WebElement> options = (List<WebElement>) web_driver.findElements(locator);
    	//List<WebElement> options = web_driver.findElements(By.tagName("a"));
    	System.out.println(options);
    // Get all of the options
    
    // Loop through the options and select the one that matches
    for (WebElement opt : options) {
        if (opt.getText().equals(match_text)) {
            opt.click();
        }  
    }}
    
    //Click the Element
    public void clickElement(By locator){
    	try {
    			waitForVisibilityOf(locator);
    			waitForClickabilityOf(locator);
    			web_driver.findElement(locator).click();
            } catch (Exception e)
        	{
            	Assert.assertFalse(true, e.getMessage());
        	}
    }
    
    //Click the Element with wait time
    public void clickElement(By locator, int waitForEle) throws InterruptedException{
    
    	try {
        		waitforPageLoad(waitForEle);
    			waitForVisibilityOf(locator);
    			waitForClickabilityOf(locator);
    			web_driver.findElement(locator).click();
            } catch (Exception e)
        	{
            	Assert.assertFalse(true, e.getMessage());
        	}
    }
    
    //Enter the text
    public void enterText(By locator, String value){
    	try {
    			waitforPageLoad(1);
    			waitForVisibilityOf(locator);
    			web_driver.findElement(locator).sendKeys(value);
            } catch (Exception e)
        	{
            	e.printStackTrace();
        	}
    }
    
    //Clear and enter the text
    public void clearAndEnterText(By locator, String value){
    	try {
    			waitForVisibilityOf(locator);
    			web_driver.findElement(locator).clear();
    			web_driver.findElement(locator).sendKeys(value);
            } catch (Exception e)
        	{
            	e.printStackTrace();
        	}
    }
    
    public void clear(By locator) {
    	try {
    	waitForVisibilityOf(locator);
		waitForClickabilityOf(locator);
		web_driver.findElement(locator).clear();
    	} catch (Exception e)
    	{
        	e.printStackTrace();
    	}
	}
    
    //Select the value from drop-down
    public void choosefromDropDown(By locator, int index){
    	 waitForVisibilityOf(locator);
 	   List<WebElement> list_count = (List<WebElement>) web_driver.findElements(locator);
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
    
	//Scroll to the Element
	public void scrolltoElement(By locator) {
		try {
		WebElement element = web_driver.findElement(locator);
		Actions actions = new Actions(web_driver);
		actions.moveToElement(element);
		actions.perform();
		} catch (Exception e) {
			Assert.assertFalse(true, "Unable to Scroll to Element"+e.getMessage());
		}
	}
	
	//Wait for page load
	public void waitforPageLoad(int sec) throws InterruptedException {
		Thread.sleep(1000 * sec);
	}
	
	//Locate the Element using the locator value
    public WebElement locateElement(String locator, String locValue) 
	 	{
	 		try {
	 			switch(locator) {
	 			case("id"):
	 			return web_driver.findElement(By.id(locValue));
	 			case("link"): return web_driver.findElement(By.linkText(locValue));
	 			case("xpath"):return web_driver.findElement(By.xpath(locValue));
	 			case("name"): return web_driver.findElement(By.name(locValue));
	 			case("class"): return web_driver.findElement(By.className(locValue));
	 			case("tag"):return web_driver.findElement(By.tagName(locValue));
	 			}
	 		} catch (NoSuchElementException e) {
	 			System.out.println("The element with locator "+locator+" not found.");
			} catch (WebDriverException e) {
				System.out.println("Unknown exception occured while finding "+locator+" with the value "+locValue);
			}
	 		return null;
	 	}
	     		
    //Accept the alert
	 public void acceptAlert() 
	 	{	
			String text = "";		
			Alert alert = web_driver.switchTo().alert();
			text = alert.getText();
			alert.accept();
  		}

	//Dismiss the alert 
	public void dismissAlert()
		{	
				String text = "";		
				Alert alert = web_driver.switchTo().alert();
				text = alert.getText();
				alert.dismiss();
		}
		
	//Navigate to the URL
	public void navigateTo(String url) throws Exception {
	        this.web_driver.get(url);
	    }

	//Navigate to the previous page
	public void navigateBack() throws Exception {
	        web_driver.navigate().back();
	    }
		
	//Refresh the page
	public void refreshPage() throws Exception {
	        this.web_driver.navigate().refresh();
	    }

	//Get the Current URL
	 public String getCurrentURL() throws Exception {
	        return this.web_driver.getCurrentUrl();
	    }

	 //Get the page title
	 public String getTitle() throws Exception {
	        return this.web_driver.getTitle();
	    }

	 //Maximixe the Window
	 public void maximizeWindow() throws Exception {
	        this.web_driver.manage().window().maximize();
	        
	    }

	    public Set<String> getWindowHandles() throws Exception {
	        return this.web_driver.getWindowHandles();
	    }

	    public void switchToWindow(String handle) throws Exception {
	        this.web_driver.switchTo().window(handle);
	        
	    }

	    public void sendKeysTo(WebElement elem, String inputString) throws Exception {
	        elem.sendKeys(new CharSequence[]{inputString});
	        
	    }

	    public void sendKeysTo(By byElem, String inputString) throws Exception {
	        this.web_driver.findElement(byElem).sendKeys(new CharSequence[]{inputString});
	        
	    }

	    public WebElement findTheElement(By byElem) throws Exception {
	        return this.web_driver.findElement(byElem);
	    }

	    public List<WebElement> findTheElements(By byElem) throws Exception {
	        return this.web_driver.findElements(byElem);
	    }

	    public void jsClick(WebElement element) throws Exception {
	        ((JavascriptExecutor)this.web_driver).executeScript("arguments[0].click();", new Object[]{element});
	        
	    }

	    public void jsClick(By byElem) throws Exception {
	        ((JavascriptExecutor)this.web_driver).executeScript("arguments[0].click();", new Object[]{this.findTheElement(byElem)});
	        
	    }

	    public void jsScroll(WebElement element) throws Exception {
	        ((JavascriptExecutor)this.web_driver).executeScript("arguments[0].scrollIntoView(true);", new Object[]{element});
	        
	    }

	    public void jsScroll(By byElem) throws Exception {
	        WebElement element = this.findTheElement(byElem);
	        ((JavascriptExecutor)this.web_driver).executeScript("arguments[0].scrollIntoView(true);", new Object[]{element});
	        
	    }

	   public List<String> returnInnertext(List<WebElement> elements) throws Exception {
	        return (List)elements.stream().map(WebElement::getText).collect(Collectors.toList());
	    }

	    public WebElement clickButtonWithText(String buttonText) throws Exception {
	        List<WebElement> elems = this.web_driver.findElements(By.tagName("button"));
	        WebElement element = (WebElement)elems.stream().filter((elem) -> {
	            return elem.getText().trim().equals(buttonText);
	        }).findFirst().orElseThrow(() -> {
	            return new NoSuchElementException("Button with text " + buttonText + " is not Found");
	        });
	        return element;
	    }

	    public WebElement clickInputButtonWithText(String buttonText) throws Exception {
	        List<WebElement> elems = this.web_driver.findElements(By.cssSelector("input[type='button']"));
	        WebElement element = (WebElement)elems.stream().filter((elem) -> {
	            return elem.getText().trim().equals(buttonText);
	        }).findFirst().orElseThrow(() -> {
	            return new NoSuchElementException("Button with text " + buttonText + " is not Found");
	        });
	        return element;
	    }

	    public WebElement clickButtonContainingText(String partialButtonText) throws Exception {
	        List<WebElement> elems = this.web_driver.findElements(By.tagName("button"));
	        WebElement element = (WebElement)elems.stream().filter((elem) -> {
	            return elem.getText().trim().contains(partialButtonText);
	        }).findFirst().orElseThrow(() -> {
	            return new NoSuchElementException("Button with Partial text " + partialButtonText + " is not Found");
	        });
	        return element;
	    }

	    public void mouseHover(WebElement element) throws Exception {
	        Actions actions = new Actions(this.web_driver);
	        actions.moveToElement(element).build().perform();
	        
	    }

	    public void moveToElementAndClick(WebElement element) throws Exception {
	        Actions actions = new Actions(this.web_driver);
	        actions.moveToElement(element).click().build().perform();
	        
	    }

	    public void moveToElementAndClick(By byElem) throws Exception {
	        Actions actions = new Actions(this.web_driver);
	        actions.moveToElement(this.findTheElement(byElem)).click().build().perform();
	        
	    }

	    public void clickAndSendkeys(WebElement elem, String string) throws Exception {
	        elem.click();
	        elem.sendKeys(new CharSequence[]{string});
	        
	    }

	    public void clickAndSendkeys(By byElem, String string) throws Exception {
	        this.findTheElement(byElem).click();
	        this.findTheElement(byElem).sendKeys(new CharSequence[]{string});
	        
	    }

	    public String retrieveValueJS(WebElement elem) throws Exception {
	        JavascriptExecutor js = (JavascriptExecutor)this.web_driver;
	        return js.executeScript("return arguments[0].value", new Object[]{elem}).toString();
	    }

	    public String retrieveValueJS(By byElem) throws Exception {
	        JavascriptExecutor js = (JavascriptExecutor)this.web_driver;
	        return js.executeScript("return arguments[0].value", new Object[]{this.findTheElement(byElem)}).toString();
	    }

	    public Boolean checkVisibility(WebElement element) {
	        boolean visible = false;

	        try {
	            if (element.isDisplayed()) {
	                visible = true;
	            }
	        } catch (NoSuchElementException var4) {
	            visible = false;
	        }

	        return visible;
	    }

	    public Boolean checkSelected(WebElement element) {
	        boolean selected = false;

	        try {
	            if (element.isSelected()) {
	                selected = true;
	            }
	        } catch (NoSuchElementException var4) {
	            selected = false;
	        }

	        return selected;
	    }

	    public Boolean checkEnabled(WebElement element) {
	        boolean selected = false;

	        try {
	            if (element.isEnabled()) {
	                selected = true;
	            }
	        } catch (NoSuchElementException var4) {
	            selected = false;
	        }

	        return selected;
	    }

	    public void performDragAndDrop(WebElement source, WebElement target) throws Exception {
	        Actions actions = new Actions(this.web_driver);
	        actions.dragAndDrop(source, target).build().perform();
	        
	    }
	   
		public boolean verifyEqualsText(By element, String expectedText) {
				
			String actualText = web_driver.findElement(element).getText();
			try {
			if (actualText.equals(expectedText))
				return true;
			else 
				return false;
			} catch (Exception e) { return false;}
		}
		
		public boolean verifyContainsText(By element, String expectedText) {
				
			String actualText = web_driver.findElement(element).getText();
			try {
			if (actualText.contains(expectedText))
				return true;
			else 
				return false;
			} catch (Exception e) { return false;}
		}
		
		public void pressEnter(By locator)
		{
			web_driver.findElement(locator).sendKeys(Keys.ENTER); 
		}
		
		public void mouseMove() throws AWTException {
		
			Actions actions = new Actions(web_driver);
			Robot robot = new Robot();
			robot.mouseMove(50,50);
			actions.click().build().perform();
		}
		
		// Wait for Spinner to complete
		public void waitforSpinnerToComplete(By locator) throws InterruptedException {
			try {
			
				waitForVisibilityOf(locator);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

			} catch (NoSuchElementException e) {}
		}
		
		// Wait for working... to complete
		public void waitforWorkingToComplete(By locator) throws InterruptedException {
			
			try {
				waitForVisibilityOf(locator);
		 	 	wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			} catch (NoSuchElementException e) {}

		}

}



