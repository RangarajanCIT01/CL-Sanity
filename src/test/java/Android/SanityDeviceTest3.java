package Android;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Helper.ExtentTestManager;


public class SanityDeviceTest3 extends DroidSetUp2 {
	
	@Test(groups="Android", description="Validate Authentication by entering Invalid Credentials")
	public void verifyInvalidUser() throws Exception {
		ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			ExtentTestManager.getTest().info("Enter invalid credentials");
			basePage.enterText(sanityPageDevice.txtLibId, testData.get("WrongPatronID"));
			basePage.clickElement(sanityPageDevice.btnLogin);
			
			actualData.add(basePage.getElement(sanityPageDevice.lblLoginFail).getText());
			expectedData.add(testData.get("MsgTitle"));
			
			actualData.add(basePage.getElement(sanityPageDevice.lblLoginDesc).getText());
			expectedData.add(testData.get("MsgDesc"));
			
			basePage.clickElement(sanityPageDevice.btnOk);
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.btnAccept);
			ExtentTestManager.getTest().info("Verify Login success using Invalid Credentials - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="Android", description="TC_17012 Login [MSS]: Go through the overall login flow.")
	public void verifyLogin() throws Exception {
		ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.enterText(sanityPageDevice.txtLibId, testData.get("PatronID"));
			ExtentTestManager.getTest().info("Enter valid credentials");
			basePage.clickElement(sanityPageDevice.btnLogin);
			sanityPageDevice.slider();		
						
			actualData.add(sanityPageDevice.verifyContainsText(sanityPageDevice.txtWelcome, testData.get("MsgWelcome")));
			expectedData.add(true);
			 
//			actualData.add(basePage.getElement(sanityPageDevice.txtLibrary).getText());
//			expectedData.add(testData.get("LibraryName"));
			
			Assert.assertEquals(expectedData, actualData);
			ExtentTestManager.getTest().info("Verify Login is successfull - " + ExtentTestManager.getTest().getStatus());
	}
		
		@Test(groups="Android", description="Filter the AudioBooks")
		public void verify_AudioBookFilter() throws Exception {	
			ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
										
			basePage.clickElement(sanityPageDevice.menuSearch);			
			basePage.clickElement(sanityPageDevice.icnFilter);
			basePage.clickElement(sanityPageDevice.tglEbook);
			basePage.clickElement(sanityPageDevice.tglAudioBook);
			basePage.clickElement(sanityPageDevice.lnkApply);
				
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);
			ExtentTestManager.getTest().info("Search the AudioBook after applying the filter");
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("AudioBook_ISBN"));
			basePage.search();
				
			basePage.getElement(sanityPageDevice.lblTitle);
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("AudioBook_Title"));
				
			basePage.clickElement(sanityPageDevice.imgBookCover);			
			basePage.scrollToElement(testData.get("AudioBook_Format"));
						
			actualData.add(basePage.getElement(sanityPageDevice.lblFormat).getText());
			expectedData.add(testData.get("AudioBook_Format"));
				
			basePage.waitforPageLoad(4);
			basePage.navigateBackTwice();
			basePage.clickElement(sanityPageDevice.btnImgBack);
						
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("EBook_ISBN_1"));
			basePage.waitforPageLoad(3);			
			basePage.search();
						
			basePage.getElement(sanityPageDevice.txtNoBooks);
			actualData.add(basePage.getElement(sanityPageDevice.txtNoBooks).getText());
			expectedData.add(testData.get("NoBooksError"));
			basePage.waitforPageLoad(3);
			basePage.navigateBackTwice();
			basePage.clickElement(sanityPageDevice.icnFilter);
			basePage.clickElement(sanityPageDevice.tglAudioBook);
			basePage.clickElement(sanityPageDevice.lnkApply);
				
			basePage.clickElement(sanityPageDevice.menuHome);
						
			basePage.getElement(sanityPageDevice.txtNotifications);
			actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
			expectedData.add(testData.get("TextNotificationsHome"));
						
			Assert.assertEquals(expectedData, actualData);
			ExtentTestManager.getTest().info("Verify the AudioBook is filtered - " + ExtentTestManager.getTest().getStatus());
		}
}


