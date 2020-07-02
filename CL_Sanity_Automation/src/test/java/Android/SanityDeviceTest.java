package Android;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SanityDeviceTest extends DroidSetUp {
	
	@Test(groups="Android", description="Validate Authentication by entering Invalid Credentials")
	public void verifyInvalidUser() throws Exception {
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.enterText(sanityPageDevice.txtLibId, testData.get("WrongPatronID"));
			basePage.clickElement(sanityPageDevice.btnLogin);
			
			actualData.add(basePage.getElement(sanityPageDevice.lblLoginFail).getText());
			expectedData.add(testData.get("MsgTitle"));
			
			actualData.add(basePage.getElement(sanityPageDevice.lblLoginDesc).getText());
			expectedData.add(testData.get("MsgDesc"));
			
			basePage.clickElement(sanityPageDevice.btnOk);
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.btnAccept);
	}
	
	@Test(groups="Android", description="TC_17012 Login [MSS]: Go through the overall login flow.")
	public void verifyLogin() throws Exception {
		
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.enterText(sanityPageDevice.txtLibId, testData.get("PatronID"));
			basePage.clickElement(sanityPageDevice.btnLogin);
			sanityPageDevice.slider();		
						
			actualData.add(sanityPageDevice.verifyContainsText(sanityPageDevice.txtWelcome, testData.get("MsgWelcome")));
			expectedData.add(true);
			 
			actualData.add(basePage.getElement(sanityPageDevice.txtLibrary).getText());
			expectedData.add(testData.get("LibraryName"));
			
			Assert.assertEquals(expectedData, actualData);
	}
	
	@Test(groups="Android", description="Borrow the EBook and Verify")
	public void verify_BorrowEBook() throws Exception {
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
			
			basePage.getElement(sanityPageDevice.btnBorrow);
			actualData.add(basePage.getElement(sanityPageDevice.btnBorrow).getText());
			expectedData.add(testData.get("ButtonBorrow"));
			
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.btnImgBack);
			
			Assert.assertEquals(expectedData, actualData);
	}
	
	@Test(groups="Android", description="Borrow the AudioBook and Verify")
	public void verify_BorrowAudioBook() throws Exception {
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
			
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);
			
			basePage.getElement(sanityPageDevice.lnkAdvancedSearch);
			actualData.add(basePage.getElement(sanityPageDevice.lnkAdvancedSearch).getText());
			expectedData.add(testData.get("TextAdvancedSearch"));
			
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
			
//			basePage.clickElement(sanityPageDevice.imgBookCover);
//			
//			basePage.getElement(sanityPageDevice.imgBookCover);
//			actualData.add(basePage.getElement(sanityPageDevice.imgBookCover).isDisplayed());
//			expectedData.add(true);
			
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("AudioBook_Title"));
			
			actualData.add(basePage.getElement(sanityPageDevice.lblSubTitle).getText());
			expectedData.add(testData.get("AudioBook_SubTitle"));
			
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
			basePage.navigateBack();
			
			basePage.getElement(sanityPageDevice.btnBorrow);
			actualData.add(basePage.getElement(sanityPageDevice.btnBorrow).getText());
			expectedData.add(testData.get("ButtonBorrow"));
			
			basePage.clickElement(sanityPageDevice.btnBack);
			basePage.clickElement(sanityPageDevice.btnImgBack);
			basePage.clickElement(sanityPageDevice.menuHome,3);
			
			basePage.getElement(sanityPageDevice.txtNotifications);
			actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
			expectedData.add(testData.get("TextNotificationsHome"));
			
			Assert.assertEquals(expectedData, actualData);	
	}
		
	@Test(groups="Android", description="Filter the eBooks")
	public void verify_eBookFilter() throws Exception {	
		Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
									
		basePage.clickElement(sanityPageDevice.menuSearch);			
		basePage.clickElement(sanityPageDevice.icnFilter);
		basePage.clickElement(sanityPageDevice.tglEbook);
		basePage.clickElement(sanityPageDevice.lnkApply);
		basePage.clickElement(sanityPageDevice.lblSrchKeywd);
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
		}
			
		@Test(groups="Android", description="Filter the AudioBooks")
		public void verify_AudioBookFilter() throws Exception {	
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
										
			basePage.clickElement(sanityPageDevice.menuSearch);			
			basePage.clickElement(sanityPageDevice.icnFilter);
			basePage.clickElement(sanityPageDevice.tglEbook);
			basePage.clickElement(sanityPageDevice.tglAudioBook);
			basePage.clickElement(sanityPageDevice.lnkApply);
				
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("AudioBook_ISBN"));
				
			basePage.search();
				
			basePage.getElement(sanityPageDevice.lblTitle);
			actualData.add(basePage.getElement(sanityPageDevice.lblTitle).getText());
			expectedData.add(testData.get("AudioBook_Title"));
				
			basePage.clickElement(sanityPageDevice.imgBookCover);			
			basePage.scrollToElement(testData.get("AudioBook_Format"));
						
			actualData.add(basePage.getElement(sanityPageDevice.lblFormat).getText());
			expectedData.add(testData.get("AudioBook_Format"));
				
			basePage.navigateBackTwice();
			basePage.clickElement(sanityPageDevice.btnImgBack);
						
			basePage.clickElement(sanityPageDevice.lblSrchKeywd);
			basePage.enterText(sanityPageDevice.lblEditTxt, testData.get("EBook_ISBN_1"));
						
			basePage.search();
						
			basePage.getElement(sanityPageDevice.txtNoBooks);
			actualData.add(basePage.getElement(sanityPageDevice.txtNoBooks).getText());
			expectedData.add(testData.get("NoBooksError"));
			
			basePage.navigateBackTwice();
			basePage.clickElement(sanityPageDevice.icnFilter);
			basePage.clickElement(sanityPageDevice.tglAudioBook);
			basePage.clickElement(sanityPageDevice.lnkApply);
				
			basePage.clickElement(sanityPageDevice.menuHome);
						
			basePage.getElement(sanityPageDevice.txtNotifications);
			actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
			expectedData.add(testData.get("TextNotificationsHome"));
						
			Assert.assertEquals(expectedData, actualData);
		}
		
		@Test(groups="Android", description="Verify the Shelf Name")
		public void verifyShelfName() throws Exception {	
			Map<String, String> testData = eff.readJsonElement("SetUp_Device.json", "baseData");
			ArrayList<Object> actualData = new ArrayList<>();
			ArrayList<Object> expectedData = new ArrayList<>();
										
			basePage.clickElement(sanityPageDevice.menuSearch);			
			
			basePage.getElement(sanityPageDevice.txtShelfName);
			actualData.add(basePage.getElement(sanityPageDevice.txtShelfName).getText());
			expectedData.add(testData.get("FeaturedShelf"));
				
			basePage.clickElement(sanityPageDevice.menuHome);
						
			basePage.getElement(sanityPageDevice.txtNotifications);
			actualData.add(basePage.getElement(sanityPageDevice.txtNotifications).getText());
			expectedData.add(testData.get("TextNotificationsHome"));
						
			Assert.assertEquals(expectedData, actualData);
		}	
}


