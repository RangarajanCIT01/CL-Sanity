package Android;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Helper.ExtentTestManager;

public class SanityDeviceBrowser extends DroidBrowserSetUp {
	
	@Test(groups="DeviceBrowser", description="Login [MSS]: Go through the overall login flow.")
	public void verifyValidLogin() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("DeviceBrowser");
		Map<String, String> testData = eff.readJsonElement("SetUp_DeviceBrowser.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
			
		basePage.clickElement(sanityPageDeviceBrowser.iconClose);
		
		basePage.getElement(sanityPageDeviceBrowser.btnLogin);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.btnLogin).getText());
		expectedData.add(testData.get("ButtonLogin"));
		
		basePage.clickElement(sanityPageDeviceBrowser.btnLogin,5);		
		ExtentTestManager.getTest().info("Enter the Library UserID");
		basePage.enterText(sanityPageDeviceBrowser.txtLibID, testData.get("LibraryID"));
		basePage.clickElement(sanityPageDeviceBrowser.btnLoginPopup);
		
		basePage.getElement(sanityPageDeviceBrowser.btnAccept);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.btnAccept).getText());
		expectedData.add(testData.get("ButtonAccept"));
		
		basePage.clickElement(sanityPageDeviceBrowser.btnAccept,5);				

		basePage.getElement(sanityPageDeviceBrowser.logout);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.logout).getText());
		expectedData.add(testData.get("TextLogout"));
		
		Assert.assertEquals(expectedData, actualData);	
		ExtentTestManager.getTest().info("Verify Login is successfull - " + ExtentTestManager.getTest().getStatus());
	}
		
	@Test(groups="DeviceBrowser", description="Borrow the EBook and Verify")
	public void verify_BorrowEBook() throws Exception {
		ExtentTestManager.getTest().assignCategory("DeviceBrowser");
		Map<String, String> testData = eff.readJsonElement("SetUp_DeviceBrowser.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
		
		basePage.getElement(sanityPageDeviceBrowser.txtSearch);
		basePage.clickElement(sanityPageDeviceBrowser.txtSearch,2);
		
		ExtentTestManager.getTest().info("Verify by borrowing the EBook");
		basePage.enterText(sanityPageDeviceBrowser.txtSearch, testData.get("EBook_ISBN"));
		basePage.clickElement(sanityPageDeviceBrowser.iconSearch);
		basePage.clickElement(sanityPageDeviceBrowser.btnBorrow);
	
		basePage.getElement(sanityPageDeviceBrowser.btnRead);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.btnRead).getText());
		expectedData.add(testData.get("TextRead"));
		
		basePage.getElement(sanityPageDeviceBrowser.txtTitle);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title"));
					
		basePage.clickElement(sanityPageDeviceBrowser.btnRead);
		basePage.clickElement(sanityPageDeviceBrowser.tapEBook,10);
		basePage.clickElement(sanityPageDeviceBrowser.lnkGoto,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkChapterSelect1,4);
		basePage.clickElement(sanityPageDeviceBrowser.tapEBook,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkAppearance,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkFontSizeIncrease,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkFontSizeDecrease,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkFontDefault,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkFontNight,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkFontSepia,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkFontDefault,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkFontDouble,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkFontSingle,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkdropdownfld,4);
		basePage.waitforPageLoad(2);		
		sanityPageDeviceBrowser.selectDropDown(sanityPageDeviceBrowser.lnkdropdownfld, testData.get("FontSelect1"));
				
		basePage.getElement(sanityPageDeviceBrowser.txtPageTitle);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.txtPageTitle).getText());
		expectedData.add(testData.get("EBook_Title"));	
		
		basePage.clickElement(sanityPageDeviceBrowser.icnFontPopupClose);
		basePage.clickElement(sanityPageDeviceBrowser.lnkGoto,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkChapterSelect2,4);	
		basePage.clickElement(sanityPageDeviceBrowser.tapEBook,4);
		basePage.clickElement(sanityPageDeviceBrowser.icnClose,4);
		basePage.clickElement(sanityPageDeviceBrowser.btnReturnMyBooks,4);
		basePage.clickElement(sanityPageDeviceBrowser.btnReturnPopup,4);	
		ExtentTestManager.getTest().info("Verify the Font Setting options and return the EBook");
		
		basePage.getElement(sanityPageDeviceBrowser.menuReading);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.menuReading).getText());
		expectedData.add(testData.get("TextReading"));
		
		Assert.assertEquals(expectedData, actualData);	
		ExtentTestManager.getTest().info("Verify the EBook features are verified - " + ExtentTestManager.getTest().getStatus());
	}	
	
	@Test(groups="DeviceBrowser", description="Borrow the Audiobook and Verify")
	public void verify_BorrowAudioBook() throws Exception {
		ExtentTestManager.getTest().assignCategory("DeviceBrowser");
		Map<String, String> testData = eff.readJsonElement("SetUp_DeviceBrowser.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
			
		basePage.getElement(sanityPageDeviceBrowser.txtSearch);
		basePage.clickElement(sanityPageDeviceBrowser.txtSearch);
		basePage.enterText(sanityPageDeviceBrowser.txtSearch, testData.get("AudioBook_ISBN"));
		basePage.clickElement(sanityPageDeviceBrowser.iconSearch);

		basePage.getElement(sanityPageDeviceBrowser.txtTitle);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.txtTitle).getText());
		expectedData.add(testData.get("AudioBook_Title"));
		ExtentTestManager.getTest().info("Verify by borrowing the AudioBook");
		
		basePage.clickElement(sanityPageDeviceBrowser.btnBorrow);
		basePage.clickElement(sanityPageDeviceBrowser.btnListen,3);	
		basePage.getElement(sanityPageDeviceBrowser.btnPlay);
		basePage.clickElement(sanityPageDeviceBrowser.btnPlay,10);
		basePage.clickElement(sanityPageDeviceBrowser.lnkTrack4,5);
		basePage.clickElement(sanityPageDeviceBrowser.btnfastforward,5);
		basePage.clickElement(sanityPageDeviceBrowser.btnfastbackward,5);
		basePage.clickElement(sanityPageDeviceBrowser.btnRepeat,4);
		basePage.clickElement(sanityPageDeviceBrowser.btnrotateleft,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkTrack5,4);	
		basePage.clickElement(sanityPageDeviceBrowser.btnPause,4);
		basePage.waitforPageLoad(3);
		basePage.navigateBack();
		basePage.clickElement(sanityPageDeviceBrowser.btnReturn,6);
		basePage.clickElement(sanityPageDeviceBrowser.btnReturnPopup,6);
		
		basePage.getElement(sanityPageDeviceBrowser.btnBorrow);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.btnBorrow).getText());
		expectedData.add(testData.get("TextBorrow"));
		
		Assert.assertEquals(expectedData, actualData);	
		ExtentTestManager.getTest().info("Verify the AudioBook features are verified - " + ExtentTestManager.getTest().getStatus());
	}	
	
	@Test(groups="DeviceBrowser", description="Verify the Shelf got loaded in the Featured Page ")
	public void verifyShelf() throws Exception {
		ExtentTestManager.getTest().assignCategory("DeviceBrowser");
		Map<String, String> testData = eff.readJsonElement("SetUp_DeviceBrowser.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
			
		basePage.clickElement(sanityPageDeviceBrowser.iconHamb);
		basePage.clickElement(sanityPageDeviceBrowser.menuFeatured,5);
		ExtentTestManager.getTest().info("Navigate to Featured Page");
		
		basePage.getElement(sanityPageDeviceBrowser.txtShelf);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.txtShelf).getText());
		expectedData.add(testData.get("FeaturedShelf"));
		
		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify the Shelf name is displayed - " + ExtentTestManager.getTest().getStatus());
	}
		
	@Test(groups="DeviceBrowser", description="Verify the filter functionality for EBook and Audiobook")
	public void verifyFilter() throws Exception {
		ExtentTestManager.getTest().assignCategory("DeviceBrowser");
		Map<String, String> testData = eff.readJsonElement("SetUp_DeviceBrowser.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
			
		basePage.clickElement(sanityPageDeviceBrowser.iconHamb,5);
		basePage.clickElement(sanityPageDeviceBrowser.menuFilter,5);
		basePage.clickElement(sanityPageDeviceBrowser.lnkEBook);
		basePage.clickElement(sanityPageDeviceBrowser.icnCloseFilter);		
			
		basePage.getElement(sanityPageDeviceBrowser.txtSearch);
		basePage.enterText(sanityPageDeviceBrowser.txtSearch, testData.get("EBook_ISBN"));
		basePage.clickElement(sanityPageDeviceBrowser.iconSearch);
			
		basePage.getElement(sanityPageDeviceBrowser.txtNoItems);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.txtNoItems).getText());
		expectedData.add(testData.get("TextNoItems"));
				
		ExtentTestManager.getTest().info("Search the AudioBook");
		basePage.clearAndEnterText(sanityPageDeviceBrowser.txtSearch, testData.get("AudioBook_ISBN"));
		basePage.clickElement(sanityPageDeviceBrowser.iconSearch);
		
		basePage.getElement(sanityPageDeviceBrowser.txtTitle);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.txtTitle).getText());
		expectedData.add(testData.get("AudioBook_Title"));
		
		basePage.clickElement(sanityPageDeviceBrowser.iconHamb,4);
		basePage.clickElement(sanityPageDeviceBrowser.menuFilter,4);
		basePage.clickElement(sanityPageDeviceBrowser.lnkAudioBook);
		basePage.clickElement(sanityPageDeviceBrowser.icnCloseFilter);
				
		basePage.clearAndEnterText(sanityPageDeviceBrowser.txtSearch, testData.get("AudioBook_ISBN"));
		basePage.clickElement(sanityPageDeviceBrowser.iconSearch);

		basePage.getElement(sanityPageDeviceBrowser.txtNoItems);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.txtNoItems).getText());
		expectedData.add(testData.get("TextNoItems"));
				
		basePage.clearAndEnterText(sanityPageDeviceBrowser.txtSearch, testData.get("EBook_ISBN"));
		basePage.clickElement(sanityPageDeviceBrowser.iconSearch);
			
		ExtentTestManager.getTest().info("Search the EBook");
		
		basePage.getElement(sanityPageDeviceBrowser.txtTitle);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title"));	
			
		basePage.clickElement(sanityPageDeviceBrowser.iconHamb,5);
		basePage.clickElement(sanityPageDeviceBrowser.menuFilter,5);
		basePage.clickElement(sanityPageDeviceBrowser.lnkAudioBook);
		basePage.clickElement(sanityPageDeviceBrowser.icnCloseFilter);
		
		basePage.clickElement(sanityPageDeviceBrowser.iconHamb,5);
		basePage.clickElement(sanityPageDeviceBrowser.menuFeatured,5);
		
		basePage.getElement(sanityPageDeviceBrowser.logout);
		actualData.add(basePage.getElement(sanityPageDeviceBrowser.logout).getText());
		expectedData.add(testData.get("TextLogout"));
					
		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify the EBook and AudioBook is filtered - " + ExtentTestManager.getTest().getStatus());
	}	
		
}




