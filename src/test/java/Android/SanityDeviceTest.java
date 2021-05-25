package Android;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Helper.ExtentTestManager;


public class SanityDeviceTest extends DroidSetUp {
	
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
	
	@Test(groups="Android", description="Borrow the EBook and Verify")
	public void verify_BorrowEBook() throws Exception {
		ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.clickElement(sanityPageDevice.menuSearch);
			
			basePage.getElement(sanityPageDevice.lnkFeatured);
			actualData.add(basePage.getElement(sanityPageDevice.lnkFeatured).getText());
			expectedData.add(testData.get("TextFeatured"));
			
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);
			
			basePage.getElement(sanityPageDevice.lnkAdvancedSearch);
			actualData.add(basePage.getElement(sanityPageDevice.lnkAdvancedSearch).getText());
			expectedData.add(testData.get("TextAdvancedSearch"));
			
			ExtentTestManager.getTest().info("Verify by borrowing the EBook");
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("EBook_ISBN_1"));
			basePage.search();
		    
			basePage.getElement(sanityPageDevice.lblTitle);
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("EBook_Title_1"));
			
			actualData.add(basePage.getElement(sanityPageDevice.lblAuthor).getText());
			expectedData.add(testData.get("EBook_Author_1"));
			
			actualData.add(basePage.getElement(sanityPageDevice.imgBookCover).isDisplayed());
			expectedData.add(true);		
			
			actualData.add(basePage.getElement(sanityPageDevice.btnBorrow).getText());
			expectedData.add(testData.get("ButtonBorrow"));
		    
		    basePage.clickElement(sanityPageDevice.btnBorrow);
		     
			actualData.add(basePage.getElement(sanityPageDevice.btnRead).getText());
			expectedData.add(testData.get("ButtonRead"));
		    
			actualData.add(basePage.getElement(sanityPageDevice.btnReturn).getText());
			expectedData.add(testData.get("ButtonReturn"));
		  
//		    basePage.clickElement(sanityPageDevice.btnRead);
//		    basePage.allow();
//		    basePage.getElement(sanityPageDevice.tapEbooks);
//		    basePage.clickElement(sanityPageDevice.tapEbooks);			
//			basePage.clickElement(sanityPageDevice.dotsEbook);
//			basePage.clickElement(sanityPageDevice.lnkTableOfContents);
//			basePage.clickElement(sanityPageDevice.lnkChapter);
//			basePage.getElement(sanityPageDevice.tapEbooks);
//		    basePage.clickElement(sanityPageDevice.tapEbooks);	
//			basePage.navigateBack();
//			
//			actualData.add(basePage.getElement(sanityPageDevice.btnRead).getText());
//			expectedData.add(testData.get("ButtonRead"));
//			    
//			actualData.add(basePage.getElement(sanityPageDevice.btnReturn).getText());
//			expectedData.add(testData.get("ButtonReturn"));
		    
			basePage.scrolltoElement(sanityPageDevice.btnReturn);
			basePage.clickElement(sanityPageDevice.btnReturn);
			basePage.clickElement(sanityPageDevice.btnOk);
			
			basePage.getElement(sanityPageDevice.lblTitle);
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("EBook_Title_1"));
			
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.btnImgBack);
			
			Assert.assertEquals(expectedData, actualData);
			ExtentTestManager.getTest().info("Verify the EBook features are verified - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="Android", description="Borrow the AudioBook and Verify")
	public void verify_BorrowAudioBook() throws Exception {
		ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);
			
			basePage.getElement(sanityPageDevice.lnkAdvancedSearch);
			actualData.add(basePage.getElement(sanityPageDevice.lnkAdvancedSearch).getText());
			expectedData.add(testData.get("TextAdvancedSearch"));
			
			ExtentTestManager.getTest().info("Verify by borrowing the AudioBook");
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("AudioBook_ISBN"));
			basePage.search();
			
			basePage.getElement(sanityPageDevice.lblTitle);
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("AudioBook_Title"));
			
			actualData.add(basePage.getElement(sanityPageDevice.lblAuthor).getText());
			expectedData.add(testData.get("AudioBook_Author"));
			
			actualData.add(basePage.getElement(sanityPageDevice.imgBookCover).isDisplayed());
			expectedData.add(true);		
			
			actualData.add(basePage.getElement(sanityPageDevice.btnBorrow).getText());
			expectedData.add(testData.get("ButtonBorrow"));
			
			basePage.clickElement(sanityPageDevice.btnBorrow);
			
			basePage.getElement(sanityPageDevice.btnListen);
			actualData.add(basePage.getElement(sanityPageDevice.btnListen).getText());
			expectedData.add(testData.get("ButtonListen"));
			
			actualData.add(basePage.getElement(sanityPageDevice.btnReturn).getText());
			expectedData.add(testData.get("ButtonReturn"));
			
//			basePage.clickElement(sanityPageDevice.imgBookCover,3);
//			
//			basePage.getElement(sanityPageDevice.imgBookCover);
//			actualData.add(basePage.getElement(sanityPageDevice.imgBookCover).isDisplayed());
//			expectedData.add(true);
			
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("AudioBook_Title"));
			
//			actualData.add(basePage.getElement(sanityPageDevice.lblSubTitle).getText());
//			expectedData.add(testData.get("AudioBook_SubTitle"));
			
//			basePage.clickElement(sanityPageDevice.btnListen);
//						
//			// Wait until the Audiobook gets downloaded
//			basePage.waitforPageLoad(10);
//			
//			basePage.getElement(sanityPageDevice.imgAudioBookCover);
//			actualData.add(basePage.getElement(sanityPageDevice.imgAudioBookCover).isDisplayed());
//			expectedData.add(true);
//			
//			basePage.clickElement(sanityPageDevice.btnPlay);
//			
//			// Wait until the Audio gets played for 5 seconds
//			basePage.waitforPageLoad(3);
//			basePage.clickElement(sanityPageDevice.btnPlay);
//			
//			basePage.getElement(sanityPageDevice.lnkPreviousChapter);
//			actualData.add(basePage.getElement(sanityPageDevice.lnkPreviousChapter).isDisplayed());
//			expectedData.add(true);	
//			
//			actualData.add(basePage.getElement(sanityPageDevice.lnkPlayBack).isDisplayed());
//			expectedData.add(true);	
//			
//			actualData.add(basePage.getElement(sanityPageDevice.lnkPlayForward).isDisplayed());
//			expectedData.add(true);	
//			
//			actualData.add(basePage.getElement(sanityPageDevice.lnkNextChapter).isDisplayed());
//			expectedData.add(true);	
//			
//			basePage.clickElement(sanityPageDevice.btnPlay);				
//			basePage.waitforPageLoad(3);
//			basePage.clickElement(sanityPageDevice.btnPlay);
//
//			basePage.navigateBackTwice();
			
			basePage.clickElement(sanityPageDevice.btnReturn);
			basePage.clickElement(sanityPageDevice.btnOk);
			
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("AudioBook_Title"));
			
			basePage.clickElement(sanityPageDevice.btnBack,3);
			basePage.clickElement(sanityPageDevice.btnImgBack);
			basePage.clickElement(sanityPageDevice.menuHome,3);
			
			basePage.getElement(sanityPageDevice.txtNotifications);
			actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
			expectedData.add(testData.get("TextNotificationsHome"));
			
			Assert.assertEquals(expectedData, actualData);
			ExtentTestManager.getTest().info("Verify the AudioBook features are verified - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="Android", description="Borrow the PDF and Verify")
	public void verify_BorrowPDF() throws Exception {
		ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.clickElement(sanityPageDevice.menuSearch);
			
			basePage.getElement(sanityPageDevice.lnkFeatured);
			actualData.add(basePage.getElement(sanityPageDevice.lnkFeatured).getText());
			expectedData.add(testData.get("TextFeatured"));
			
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);
			
			basePage.getElement(sanityPageDevice.lnkAdvancedSearch);
			actualData.add(basePage.getElement(sanityPageDevice.lnkAdvancedSearch).getText());
			expectedData.add(testData.get("TextAdvancedSearch"));
			
			ExtentTestManager.getTest().info("Verify by borrowing the PDF");
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("PDF_ISBN"));
			basePage.search();
		    
			basePage.getElement(sanityPageDevice.lblTitle);
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("PDF_Title"));
			
			actualData.add(basePage.getElement(sanityPageDevice.lblAuthor).getText());
			expectedData.add(testData.get("PDF_Author"));
			
			actualData.add(basePage.getElement(sanityPageDevice.imgBookCover).isDisplayed());
			expectedData.add(true);		
			
			actualData.add(basePage.getElement(sanityPageDevice.btnBorrow).getText());
			expectedData.add(testData.get("ButtonBorrow"));
		    
		    basePage.clickElement(sanityPageDevice.btnBorrow);
		     
			actualData.add(basePage.getElement(sanityPageDevice.btnRead).getText());
			expectedData.add(testData.get("ButtonRead"));
		    
			actualData.add(basePage.getElement(sanityPageDevice.btnReturn).getText());
			expectedData.add(testData.get("ButtonReturn"));
		  
			basePage.scrolltoElement(sanityPageDevice.btnReturn);
			basePage.clickElement(sanityPageDevice.btnReturn);
			basePage.clickElement(sanityPageDevice.btnOk);
			
			basePage.getElement(sanityPageDevice.lblTitle);
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("PDF_Title"));
			
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.btnImgBack);
			
			Assert.assertEquals(expectedData, actualData);
			ExtentTestManager.getTest().info("Verify the PDF features are verified - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="Android", description="Borrow the PDF and Verify")
	public void verify_Fab() throws Exception {
		ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.clickElement(sanityPageDevice.menuSearch);
			
			basePage.getElement(sanityPageDevice.lnkFeatured);
			actualData.add(basePage.getElement(sanityPageDevice.lnkFeatured).getText());
			expectedData.add(testData.get("TextFeatured"));
			
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);
			
			basePage.getElement(sanityPageDevice.lnkAdvancedSearch);
			actualData.add(basePage.getElement(sanityPageDevice.lnkAdvancedSearch).getText());
			expectedData.add(testData.get("TextAdvancedSearch"));
			
			ExtentTestManager.getTest().info("Verify by borrowing the PDF");
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("EBook_ISBN_1"));
			basePage.search();
		    
			basePage.getElement(sanityPageDevice.lblTitle);
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("EBook_Title_1"));
			
			actualData.add(basePage.getElement(sanityPageDevice.lblAuthor).getText());
			expectedData.add(testData.get("EBook_Author_1"));
			
			actualData.add(basePage.getElement(sanityPageDevice.imgBookCover).isDisplayed());
			expectedData.add(true);		
			
			actualData.add(basePage.getElement(sanityPageDevice.btnBorrow).getText());
			expectedData.add(testData.get("ButtonBorrow"));
		    
		    basePage.clickElement(sanityPageDevice.btnBorrow);
		    basePage.clickElement(sanityPageDevice.btnRead);
		     
			actualData.add(basePage.getElement(sanityPageDevice.btnRead).getText());
			expectedData.add(testData.get("ButtonRead"));
			
			basePage.clickElement(sanityPageDevice.lnkTOC);
			basePage.clickElement(sanityPageDevice.lnkBalancedLine);
			basePage.clickElement(sanityPageDevice.tapEBook);
			basePage.clickElement(sanityPageDevice.lnkBack);
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.btnImgBack);
			
			basePage.clickElement(sanityPageDevice.btnFab);
		
			actualData.add(basePage.getElement(sanityPageDevice.lnkBalancedLine).getText());
			expectedData.add(testData.get("TextBalancedLine"));
			
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.menuMyBooks);
		  
			basePage.scrolltoElement(sanityPageDevice.btnReturn);
			basePage.clickElement(sanityPageDevice.btnReturn);
			basePage.clickElement(sanityPageDevice.btnOk);
			
			Assert.assertEquals(expectedData, actualData);
			ExtentTestManager.getTest().info("Verify the Fab icon functionality is working - " + ExtentTestManager.getTest().getStatus());
	}
		
	@Test(groups="Android", description="Filter the eBooks")
	public void verify_eBookFilter() throws Exception {	
		ExtentTestManager.getTest().assignCategory("Android");
		Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
									
		basePage.clickElement(sanityPageDevice.menuSearch);			
		basePage.clickElement(sanityPageDevice.icnFilter);
		basePage.clickElement(sanityPageDevice.tglEbook);
		basePage.clickElement(sanityPageDevice.lnkApply);
		basePage.clickElement(sanityPageDevice.lblSrchKeywd);
		
		ExtentTestManager.getTest().info("Search the EBook after applying the filter");
		basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("EBook_ISBN_1"));
		basePage.search();
		
		basePage.getElement(sanityPageDevice.lblTitle);
		actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
		expectedData.add(testData.get("EBook_Title_1"));
					
		basePage.clickElement(sanityPageDevice.imgBookCover);
		basePage.scrollToElement(testData.get("EBookFormat"));
				
		basePage.getElement(sanityPageDevice.lblFormat);
		actualData.add(basePage.getElement(sanityPageDevice.lblFormat).getText());
		expectedData.add(testData.get("EBookFormat"));
					
		basePage.clickElement(sanityPageDevice.btnBack);
		basePage.clickElement(sanityPageDevice.btnBack);
		basePage.clickElement(sanityPageDevice.btnImgBack);
					
		basePage.clickElement(sanityPageDevice.lblSrchKeywd);
		
		basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("AudioBook_ISBN"));
		basePage.search();
			
		basePage.getElement(sanityPageDevice.txtNoBooks);
		actualData.add(basePage.getElement(sanityPageDevice.txtNoBooks).getText());
		expectedData.add(testData.get("NoBooksError"));
			
		basePage.clickElement(sanityPageDevice.btnBack);
		basePage.clickElement(sanityPageDevice.btnImgBack);
		basePage.clickElement(sanityPageDevice.menuHome);
					
		basePage.getElement(sanityPageDevice.txtNotifications);
		actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
		expectedData.add(testData.get("TextNotificationsHome"));
					
		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify the EBook is filtered - " + ExtentTestManager.getTest().getStatus());
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
		
		@Test(groups="Android", description="Search a title in the All tab after applying Language filter")
		public void verify_FavoritesFilter() throws Exception {
			
			ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json","baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.clickElement(sanityPageDevice.menuSearch);	
			basePage.clickElement(sanityPageDevice.lnkFavorites);
			basePage.clickElement(sanityPageDevice.icnFilter);
			
			basePage.getElement(sanityPageDevice.txtFormat);
			actualData.add(basePage.getElement(sanityPageDevice.txtFormat).getText());
			expectedData.add(testData.get("TextFormat"));
			
			actualData.add(basePage.getElement(sanityPageDevice.txtAvailability).getText());
			expectedData.add(testData.get("TextAvailability"));
			
			actualData.add(basePage.getElement(sanityPageDevice.txtLanguage).getText());
			expectedData.add(testData.get("TextLanguage"));
			
			basePage.clickElement(sanityPageDevice.tglAllLibTitles);		
			basePage.clickElement(sanityPageDevice.lnkApply);
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);	
			
			actualData.add(basePage.getElement(sanityPageDevice.lnkAdvancedSearch).getText());
			expectedData.add(testData.get("TextAdvancedSearch"));
			
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("EBook_Title_1"));
			basePage.search();
				
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("EBook_Title_1"));
			
			actualData.add(basePage.getElement(sanityPageDevice.lblAuthor).getText());
			expectedData.add(testData.get("EBook_Author_1"));
			
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.btnImgBack);
			basePage.clickElement(sanityPageDevice.icnFilter);
			basePage.clickElement(sanityPageDevice.tglAllLibTitles);
			basePage.clickElement(sanityPageDevice.lnkApply);		
			basePage.clickElement(sanityPageDevice.menuHome);
			
			basePage.getElement(sanityPageDevice.txtNotifications);
			actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
			expectedData.add(testData.get("TextNotificationsHome"));
		
			Assert.assertEquals(expectedData, actualData);	
		}
		
		@Test(groups="Android", description="Search a title in the All tab after applying Language filter")
		public void verify_AllLanguageFilter() throws Exception {
			
			ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json","baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.clickElement(sanityPageDevice.menuSearch);	
			basePage.clickElement(sanityPageDevice.lnkAll);
			basePage.clickElement(sanityPageDevice.icnFilter);
			
			basePage.getElement(sanityPageDevice.txtFormat);
			actualData.add(basePage.getElement(sanityPageDevice.txtFormat).getText());
			expectedData.add(testData.get("TextFormat"));
			
			actualData.add(basePage.getElement(sanityPageDevice.txtAvailability).getText());
			expectedData.add(testData.get("TextAvailability"));
			
			actualData.add(basePage.getElement(sanityPageDevice.txtLanguage).getText());
			expectedData.add(testData.get("TextLanguage"));
				
			basePage.clickElement(sanityPageDevice.tglEnglish);
			basePage.clickElement(sanityPageDevice.lnkApply);
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);	
			
			actualData.add(basePage.getElement(sanityPageDevice.lnkAdvancedSearch).getText());
			expectedData.add(testData.get("TextAdvancedSearch"));
			
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("EBook_Title_1"));
			basePage.search();
				
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("EBook_Title_1"));
			
			actualData.add(basePage.getElement(sanityPageDevice.lblAuthor).getText());
			expectedData.add(testData.get("EBook_Author_1"));
			
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.btnImgBack);
			basePage.clickElement(sanityPageDevice.icnFilter);
			basePage.clickElement(sanityPageDevice.tglEnglish);
			basePage.clickElement(sanityPageDevice.lnkApply);		
			basePage.clickElement(sanityPageDevice.menuHome);
			
			basePage.getElement(sanityPageDevice.txtNotifications);
			actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
			expectedData.add(testData.get("TextNotificationsHome"));
		
			Assert.assertEquals(expectedData, actualData);	
		}
		
		@Test(groups="Android", description="Verify the Shelf Name")
		public void verifyShelfName() throws Exception {
			ExtentTestManager.getTest().assignCategory("Android");
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
										
			basePage.clickElement(sanityPageDevice.menuSearch);	
			basePage.clickElement(sanityPageDevice.lnkFeatured);	
			
			ExtentTestManager.getTest().info("Navigate to Featured Page");
			basePage.getElement(sanityPageDevice.txtShelfName);
			actualData.add(basePage.getElement(sanityPageDevice.txtShelfName).getText());
			expectedData.add(testData.get("FeaturedShelf"));
				
			basePage.clickElement(sanityPageDevice.menuHome);
						
			basePage.getElement(sanityPageDevice.txtNotifications);
			actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
			expectedData.add(testData.get("TextNotificationsHome"));
						
			Assert.assertEquals(expectedData, actualData);
			ExtentTestManager.getTest().info("Verify the Shelf name is displayed - " + ExtentTestManager.getTest().getStatus());
		}	
				
		@Test(groups="Reaktor",description="Borrow and Unsave a title in the Saved section")
		public void verify_SaveTitle() throws Exception {
			
				Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
				ArrayList<Object> actualData = new ArrayList<>();
				ArrayList<Object> expectedData = new ArrayList<>();
				
				basePage.clickElement(sanityPageDevice.menuSearch);			
				basePage.clickElement(sanityPageDevice.lblSrchKeywd);
				
				ExtentTestManager.getTest().info("Search the EBook after applying the filter");
				basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("EBook_ISBN_1"));
				basePage.search();
				
				basePage.getElement(sanityPageDevice.lblTitle);
				actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
				expectedData.add(testData.get("EBook_Title_1"));
							
				basePage.clickElement(sanityPageDevice.imgBookCover);
				basePage.scrolltoElement(sanityPageDevice.btnSaveforLater);
							
				basePage.clickElement(sanityPageDevice.btnSaveforLater);		
				sanityPageDevice.backToFeaturedPage();
				
				basePage.clickElement(sanityPageDevice.menuMyBooks);		
				basePage.clickElement(sanityPageDevice.lnkSaved);			
				
				basePage.waitforPageLoad(3);
				basePage.scrollToElement(testData.get("EBook_Author_1"));
				basePage.clickElement(sanityPageDevice.btnUnsave,3);
				basePage.clickElement(sanityPageDevice.btnOk);
				actualData.add(basePage.getElement(sanityPageDevice.btnUnsave).getText());
				expectedData.add(testData.get("ButtonUnSave"));
				
				basePage.clickElement(sanityPageDevice.menuHome);
					
				basePage.getElement(sanityPageDevice.txtNotifications);
				actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
				expectedData.add(testData.get("TextNotificationsHome"));
				
				Assert.assertEquals(expectedData, actualData);					
		}
		
		@Test(groups="Reaktor", description="Remove all the books from History")
		public void verify_HistoryClear() throws Exception {
			
				Map<String, String> loginData = eff.readJsonElement("SetUp.json", "baseData");
				ArrayList<Object> actualData = new ArrayList<>();
				ArrayList<Object> expectedData = new ArrayList<>();
				
				basePage.clickElement(sanityPageDevice.menuMyBooks);
				basePage.clickElement(sanityPageDevice.lnkHistory);
				
				if(driver.findElement(sanityPageDevice.lnkClearAll).isDisplayed())
				{
					basePage.clickElement(sanityPageDevice.lnkClearAll);
					actualData.add(basePage.getElement(sanityPageDevice.txtWarning).getText());
					expectedData.add(loginData.get("TextWarning"));
					
					basePage.clickElement(sanityPageDevice.btnOk);
					
//					actualData.add(basePage.getElement(sanityPageDevice.lblNoHistory).getText());
//					expectedData.add(loginData.get("TextNoHistory"));
				} else
				{
					System.out.println("Clear All button is not available");
				}
						
				basePage.clickElement(sanityPageDevice.menuHome);			
				
				basePage.getElement(sanityPageDevice.txtNotifications);
				actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
				expectedData.add(loginData.get("TextNotificationsHome"));
				
				Assert.assertEquals(expectedData, actualData);					
		}	
		
		@Test(groups="Android", description="Verify by adding the second user")
		public void verify_AddSecondUser() throws Exception {
			
				ExtentTestManager.getTest().assignCategory("Android");
				Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
				ArrayList<Object> actualData = new ArrayList<>();
				ArrayList<Object> expectedData = new ArrayList<>();
				
				basePage.clickElement(sanityPageDevice.menuAccount);
				basePage.clickElement(sanityPageDevice.lnkViewCards);
				basePage.clickElement(sanityPageDevice.txtAddNew);
				sanityPageDevice.selectEnvironment(testData);
				basePage.clickElement(sanityPageDevice.btnAccept);
				sanityPageDevice.validUserID(testData.get("PatronIDSecondUser"));
				sanityPageDevice.slider();	
				
				actualData.add(sanityPageDevice.verifyContainsText(sanityPageDevice.txtWelcome, testData.get("MsgWelcome")));
				expectedData.add(true);
				
				Assert.assertEquals(expectedData, actualData);
				ExtentTestManager.getTest().info("Verify Login is successfull - " + ExtentTestManager.getTest().getStatus());
		}
		
		
}


