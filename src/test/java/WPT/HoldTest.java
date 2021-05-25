package WPT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Helper.DataInputProvider;
import Helper.Efficacies;
import Helper.ExtentTestManager;
import Helper.StartApp;
import WebPages.WPTBasePage;
import WebPages.WPTSanityPage;

public class HoldTest  {
	
	public DataInputProvider data = new DataInputProvider();
	
	public Efficacies eff;
	public WPTSanityPage sanityPageWeb;
	public WPTBasePage basePageWeb;
	public WebDriver web_driver;
	public StartApp startApp;
    
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		startApp = new StartApp();
		eff = new Efficacies();
		web_driver = startApp.startApp_Web();
		sanityPageWeb = new WPTSanityPage(web_driver);
		basePageWeb = new WPTBasePage(web_driver);

		//Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");		
	}

	@Test(dataProvider = "Authentication")
	public void verify_HoldTest(String username) throws Exception {
		
		System.out.println("Logged in as:" + username);	
		basePageWeb.getElement(sanityPageWeb.btnLogin);
	
		basePageWeb.clickElement(sanityPageWeb.btnLogin);
		basePageWeb.enterText(sanityPageWeb.txtLibID, username);
		basePageWeb.clickElement(sanityPageWeb.btnLoginPopup);

		basePageWeb.getElement(sanityPageWeb.btnAccept);
		
		basePageWeb.clickElement(sanityPageWeb.btnAccept);
		
		Thread.sleep(1000);
		basePageWeb.clear(sanityPageWeb.txtSearch);
		Thread.sleep(1000);
		basePageWeb.enterText(sanityPageWeb.txtSearch, "9781101022610");
		Thread.sleep(1000);
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
		
		Thread.sleep(1000);
		basePageWeb.waitForVisibilityOf(sanityPageWeb.btnHold);
		basePageWeb.clickElement(sanityPageWeb.btnHold);
		
		try {
			basePageWeb.getElement(sanityPageWeb.btnCancel);
			basePageWeb.clickElement(sanityPageWeb.btnPlaceHoldPopup);	
		}catch (Exception e)
		{
			
		}
		basePageWeb.waitForVisibilityOf(sanityPageWeb.btnRemoveHold);
		System.out.println("Title '9781101022610' Hold Succesfully for the User:" + username);
		
		//Thread.sleep(5);
		basePageWeb.clear(sanityPageWeb.txtSearch);
		Thread.sleep(1000);
		basePageWeb.enterText(sanityPageWeb.txtSearch, "9781415962695");
		
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
		
		Thread.sleep(1000);
		basePageWeb.waitForVisibilityOf(sanityPageWeb.btnHold);
		basePageWeb.clickElement(sanityPageWeb.btnHold);
		
		try {
			basePageWeb.getElement(sanityPageWeb.btnCancel);
			basePageWeb.clickElement(sanityPageWeb.btnPlaceHoldPopup);	
		}catch (Exception e)
		{
			
		}
				
		Thread.sleep(1000);
		basePageWeb.waitForVisibilityOf(sanityPageWeb.btnRemoveHold);
		System.out.println("Title '9781415962695' Hold Succesfully for the User:" + username);
		
		basePageWeb.clickElement(sanityPageWeb.menuMyBooks);
		
		Thread.sleep(1000);
		basePageWeb.clickElement(sanityPageWeb.btnLogout);
		Thread.sleep(1000);
		basePageWeb.clickElement(sanityPageWeb.btnLogoutpopup);	
			
		System.out.println("Logged out Username > " + username );
		Thread.sleep(5);
		
	}	
	
	@DataProvider(name = "Authentication")
	 public Object[][] credentials() {
	 
	        //Object[][] obj = data.getSheet("D:\\CloudLibraryReg\\MobileApps\\src\\main\\resources\\AndroidResrc\\HoldTest.csv", "Hold");
			//Object[][] obj = data.getSheet("HoldTest", "Hold");
			Object[][] obj = data.getSheet("Sheet1");
	        return obj;
	 
	  }
}
