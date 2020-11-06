package WPT;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import Helper.ExtentTestManager;

public class SanityTests extends WPTSetUp {
	
	@Test(groups = "WPT", description = "Validate Authentication by entering Valid Credentials")
	public void verify_ValidUser() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
		
		ExtentTestManager.getTest().info("Wait for spinner to Complete..");
		basePageWeb.waitforSpinnerToComplete(sanityPageWeb.spinner);
		basePageWeb.getElement(sanityPageWeb.menuFeatured);
		
		actualData.add(basePageWeb.getElement(sanityPageWeb.menuFeatured).getText());
		expectedData.add(testData.get("TextFeatured"));

		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify Login is successfull - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups = "WPT", description = "Verify the Bookmark in EBook")
	public void verify_EBookBookmark() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
	
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
//		basePageWeb.waitforSpinnerToComplete(sanityPageWeb.spinner);
		basePageWeb.clickElement(sanityPageWeb.btnBorrow);
	
		ExtentTestManager.getTest().info("Verify by borrowing the EBook");
		basePageWeb.getElement(sanityPageWeb.btnRead);
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnRead).getText());
		expectedData.add(testData.get("TextRead"));
		
		basePageWeb.getElement(sanityPageWeb.txtTitle);
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title"));
					
		basePageWeb.clickElement(sanityPageWeb.btnRead);
		basePageWeb.waitforPageLoad(7);
//		basePageWeb.waitforWorkingToComplete(sanityPageWeb.working);
		
		basePageWeb.clickElement(sanityPageWeb.tapEBook,4);
		basePageWeb.clickElement(sanityPageWeb.lnkTOC,4);
			
		basePageWeb.clickElement(sanityPageWeb.lnkChapter1,4);
		basePageWeb.clickElement(sanityPageWeb.tapEBook,4);
		
		ExtentTestManager.getTest().info("Bookmark a page in the eBook");
		basePageWeb.clickElement(sanityPageWeb.lnkBookmark,4);
		basePageWeb.clickElement(sanityPageWeb.icnClose,4);
		basePageWeb.clickElement(sanityPageWeb.btnLogout,4);
		basePageWeb.clickElement(sanityPageWeb.btnLogoutpopup,4);

		basePageWeb.clickElement(sanityPageWeb.btnLogin,5);
		basePageWeb.enterText(sanityPageWeb.txtLibID, testData.get("LibraryID"));
		basePageWeb.clickElement(sanityPageWeb.btnLoginPopup,4);

		basePageWeb.clickElement(sanityPageWeb.menuMyBooks,3);
		basePageWeb.clickElement(sanityPageWeb.btnRead,4);
		
		ExtentTestManager.getTest().info("Verify the selected bookmark is displayed");
		//basePageWeb.clickElement(sanityPageWeb.tapEBook,4);
		basePageWeb.waitforPageLoad(3);
		basePageWeb.getElement(sanityPageWeb.icnBookmark);
		actualData.add(basePageWeb.getElement(sanityPageWeb.icnBookmark).isDisplayed());
		expectedData.add(true);		
		
		basePageWeb.clickElement(sanityPageWeb.tapEBook,4);
		basePageWeb.clickElement(sanityPageWeb.lnkBookmark,4);
		
		Assert.assertEquals(expectedData, actualData);
	}
	
	@Test(groups = "WPT", description = "Verify the Bookmark in EBook")
	public void verify_EBookBookmarkDelete() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
	
		basePageWeb.clickElement(sanityPageWeb.lnkTOC);
		basePageWeb.clickElement(sanityPageWeb.lnkChapter2);
		basePageWeb.clickElement(sanityPageWeb.tapEBook,4);
		ExtentTestManager.getTest().info("Navigate to a Chapter");
		
		basePageWeb.clickElement(sanityPageWeb.lnkBookmark,4);
		basePageWeb.clickElement(sanityPageWeb.lnkTOC,2);
		ExtentTestManager.getTest().info("Bookmark a Page");
		
		basePageWeb.clickElement(sanityPageWeb.lnkBookmarks,2);
		basePageWeb.clickElement(sanityPageWeb.btnDeleteBk,2);
		ExtentTestManager.getTest().info("Delete the bookmark");
		
		ExtentTestManager.getTest().info("Verify the bookmark is deleted in Bookmarks section");
		
		actualData.add(basePageWeb.getElement(sanityPageWeb.icnRefresh).isDisplayed());
		expectedData.add(true);		
			
		Assert.assertEquals(expectedData, actualData);
	}
	
	@Test(groups = "WPT", description = "Verify the Bookmark in EBook")
	public void verify_EBookGotoPage() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
	
		ExtentTestManager.getTest().info("Enter the page number");
		basePageWeb.clickElement(sanityPageWeb.lnkGoto,2);
		basePageWeb.clearAndEnterText(sanityPageWeb.txtPageno, testData.get("GotoPageNo"));
		
		basePageWeb.clickElement(sanityPageWeb.btnGo,2);
		basePageWeb.clickElement(sanityPageWeb.tapEBook,2);		
		Assert.assertEquals(expectedData, actualData);
	}
	
	@Test(groups = "WPT", description = "Borrow an EBook and verify the functionalities")
	public void borrow_EBook() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
	
//		basePageWeb.clear(sanityPageWeb.txtSearch);
//		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN"));
//		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
//		basePageWeb.waitforSpinnerToComplete(sanityPageWeb.spinner);
//		basePageWeb.clickElement(sanityPageWeb.btnBorrow);
//	
//		ExtentTestManager.getTest().info("Verify by borrowing the EBook");
//		basePageWeb.getElement(sanityPageWeb.btnRead);
//		actualData.add(basePageWeb.getElement(sanityPageWeb.btnRead).getText());
//		expectedData.add(testData.get("TextRead"));
//		
//		basePageWeb.getElement(sanityPageWeb.txtTitle);
//		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
//		expectedData.add(testData.get("EBook_Title"));
//					
//		basePageWeb.clickElement(sanityPageWeb.btnRead);
//		basePageWeb.waitforPageLoad(7);
//		basePageWeb.waitforWorkingToComplete(sanityPageWeb.working);
		
//		basePageWeb.clickElement(sanityPageWeb.tapEBook,4);
		basePageWeb.clickElement(sanityPageWeb.lnkTOC,4);
		basePageWeb.clickElement(sanityPageWeb.lnkChapterSelect1,4);
		basePageWeb.waitforPageLoad(4);
		basePageWeb.clickElement(sanityPageWeb.tapEBook,4);
		basePageWeb.clickElement(sanityPageWeb.lnkAppearance,4);
		basePageWeb.clickElement(sanityPageWeb.lnkFontSizeIncrease,4);
		basePageWeb.clickElement(sanityPageWeb.lnkFontSizeDecrease,4);
		basePageWeb.clickElement(sanityPageWeb.lnkFontDefault,4);
		basePageWeb.clickElement(sanityPageWeb.lnkFontNight,4);
		basePageWeb.clickElement(sanityPageWeb.lnkFontSepia,4);
		basePageWeb.clickElement(sanityPageWeb.lnkFontDefault,4);
		basePageWeb.clickElement(sanityPageWeb.lnkFontDouble,4);
		basePageWeb.clickElement(sanityPageWeb.lnkFontSingle,4);
		basePageWeb.clickElement(sanityPageWeb.lnkdropdownfld,4);
		basePageWeb.waitforPageLoad(2);		
		sanityPageWeb.selectDropDown(sanityPageWeb.lnkdropdownfld, testData.get("FontSelect1"));
				
		basePageWeb.getElement(sanityPageWeb.txtPageTitle);
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtPageTitle).getText());
		expectedData.add(testData.get("EBook_Title"));	
		
		basePageWeb.getElement(sanityPageWeb.icnFontPopupClose);
		basePageWeb.clickElement(sanityPageWeb.icnFontPopupClose,4);
		basePageWeb.getElement(sanityPageWeb.lnkTOC);
		basePageWeb.clickElement(sanityPageWeb.lnkTOC,4);
		
		basePageWeb.clickElement(sanityPageWeb.lnkChapterSelect2,4);
		basePageWeb.getElement(sanityPageWeb.tapEBook);
		basePageWeb.clickElement(sanityPageWeb.tapEBook,6);
		basePageWeb.getElement(sanityPageWeb.icnClose);
		basePageWeb.clickElement(sanityPageWeb.icnClose,6);
		
		basePageWeb.clickElement(sanityPageWeb.btnReturnMyBooks,4);
		basePageWeb.clickElement(sanityPageWeb.btnReturnPopup,4);
		ExtentTestManager.getTest().info("Verify the Font Setting options and return the EBook");
		
		basePageWeb.clickElement(sanityPageWeb.menuFeatured);
		
		basePageWeb.getElement(sanityPageWeb.menuFeatured);
		actualData.add(basePageWeb.getElement(sanityPageWeb.menuFeatured).getText());
		expectedData.add(testData.get("TextFeatured"));	
		
		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify the EBook features are verified - " + ExtentTestManager.getTest().getStatus());
	}

	@Test(groups="WPT", description="Borrow an Audiobook and verify the functionalities")
	public void borrow_AudioBook() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.clickElement(sanityPageWeb.menuFeatured);
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("AudioBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
		basePageWeb.clickElement(sanityPageWeb.btnBorrow);
//		basePageWeb.waitforWorkingToComplete(sanityPageWeb.working);
		ExtentTestManager.getTest().info("Verify by borrowing the AudioBook");
		
		basePageWeb.getElement(sanityPageWeb.btnListen);
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnListen).getText());
		expectedData.add(testData.get("TextListen"));

		basePageWeb.getElement(sanityPageWeb.txtTitle);
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("AudioBook_Title"));

		basePageWeb.clickElement(sanityPageWeb.btnListen, 5);

		basePageWeb.waitforPageLoad(15);
		basePageWeb.clickElement(sanityPageWeb.btnPause, 3);
		basePageWeb.clickElement(sanityPageWeb.btnPlay, 3);
		basePageWeb.clickElement(sanityPageWeb.btnfastforward, 3);
		basePageWeb.clickElement(sanityPageWeb.btnfastbackward, 3);
		basePageWeb.clickElement(sanityPageWeb.btnRepeat, 4);
		basePageWeb.clickElement(sanityPageWeb.btnrotateleft, 4);
		basePageWeb.clickElement(sanityPageWeb.btnPause, 3);
		basePageWeb.clickElement(sanityPageWeb.lnkTrack1, 4);

		basePageWeb.navigateBack();
		basePageWeb.clickElement(sanityPageWeb.btnReturn);
		basePageWeb.clickElement(sanityPageWeb.btnReturnPopup);
		ExtentTestManager.getTest().info("Verify the Audio Player Setting options and return the AudioBook");
		
//		basePageWeb.getElement(sanityPageWeb.btnBorrow);
//		actualData.add(basePageWeb.getElement(sanityPageWeb.btnBorrow).getText());
//		expectedData.add(testData.get("TextBorrow"));

		basePageWeb.clickElement(sanityPageWeb.menuFeatured);

		basePageWeb.getElement(sanityPageWeb.menuFeatured);
		actualData.add(basePageWeb.getElement(sanityPageWeb.menuFeatured).getText());
		expectedData.add(testData.get("TextFeatured"));

		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify the AudioBook features are verified - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="WPT", description="Search and verify the details of Ebook")
	public void search_EBook() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		ExtentTestManager.getTest().info("Click Browse button.");
		basePageWeb.clickElement(sanityPageWeb.menuBrowse);
		
		ExtentTestManager.getTest().info("Enter ISBN in search - "+testData.get("EBook_ISBN"));
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title"));

		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify searched title is displayed - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="WPT", description="Search and verify the details of Audiobook")
	public void Switch_Emulator() throws Exception {
		
		startApp.emulatorClose();
		startApp.emulatorBatFile();
	}

	@Test(groups="WPT", description="Search and verify the details of Audiobook")
	public void search_AudioBook() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		ExtentTestManager.getTest().info("Click Browse button.");
		basePageWeb.clickElement(sanityPageWeb.menuBrowse);
		
		ExtentTestManager.getTest().info("Enter ISBN in search - "+testData.get("AudioBook_ISBN"));
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("AudioBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("AudioBook_Title"));

		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify searched title is displayed - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="WPT", description="Search and verify the details of Suggested eBooks and Audiobooks")
	public void verify_Suggest() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
		
		basePageWeb.clickElement(sanityPageWeb.menuBrowse);
		basePageWeb.clickElement(sanityPageWeb.menuFilter);		
		basePageWeb.clickElement(sanityPageWeb.filterSuggestionsforLib);		
		
		ExtentTestManager.getTest().info("Enter ISBN in search - "+testData.get("Suggest_EBookISBN"));
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("Suggest_EBookISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
		ExtentTestManager.getTest().info("Search the EBook Suggestion Title");
		
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("Suggest_EBookTitle"));

		actualData.add(basePageWeb.getElement(sanityPageWeb.btnSuggest).getText());
		expectedData.add(testData.get("ButtonSuggest"));
		
		basePageWeb.clickElement(sanityPageWeb.btnSuggest,3);	
		
		ExtentTestManager.getTest().info("Verify the Button status as 'Remove Suggestion'");
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnRemoveSuggest).getText());
		expectedData.add(testData.get("TextRemoveSuggest"));
		
		basePageWeb.clickElement(sanityPageWeb.btnRemoveSuggest,3);	
		
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnSuggest).getText());
		expectedData.add(testData.get("ButtonSuggest"));
		
		ExtentTestManager.getTest().info("Search the AudioBook Suggestion Title");
		ExtentTestManager.getTest().info("Enter ISBN in search - "+testData.get("Suggest_AudioBookISBN"));
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.clearAndEnterText(sanityPageWeb.txtSearch, testData.get("Suggest_AudioBookISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		actualData.add(basePageWeb.getElement(sanityPageWeb.btnSuggest).getText());
		expectedData.add(testData.get("ButtonSuggest"));
		
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("Suggest_AudioBookTitle"));
		
		basePageWeb.clickElement(sanityPageWeb.btnSuggest,3);	
		
		ExtentTestManager.getTest().info("Verify the Button status as 'Remove Suggestion'");
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnRemoveSuggest).getText());
		expectedData.add(testData.get("TextRemoveSuggest"));
		
		basePageWeb.clickElement(sanityPageWeb.btnRemoveSuggest,3);	
		
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnSuggest).getText());
		expectedData.add(testData.get("ButtonSuggest"));
		
		basePageWeb.clickElement(sanityPageWeb.menuFeatured,3);
		
		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify suggesting the EBook and Audiobook - " + ExtentTestManager.getTest().getStatus());
	}	
	
	@Test(groups="WPT", description="Verify the Shelf got loaded in the Featured Page")
	public void verify_Shelf() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		ExtentTestManager.getTest().info("Navigate to Featured Page");
		basePageWeb.clickElement(sanityPageWeb.menuFeatured);

		basePageWeb.getElement(sanityPageWeb.txtShelf);
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtShelf).getText());
		expectedData.add(testData.get("FeaturedShelf"));
		
		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify the Shelf name is displayed - " + ExtentTestManager.getTest().getStatus());
	}

	@Test(groups="WPT", description="Verify the Filter functionality for EBook and Audiobook")
	public void verify_Filter() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.clickElement(sanityPageWeb.menuFeatured);
		basePageWeb.clickElement(sanityPageWeb.menuFilter);
		basePageWeb.clickElement(sanityPageWeb.lnkEBook);
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		basePageWeb.getElement(sanityPageWeb.txtNoItems);
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtNoItems).getText());
		expectedData.add(testData.get("TextNoItems"));

		ExtentTestManager.getTest().info("Search the AudioBook");
		basePageWeb.clearAndEnterText(sanityPageWeb.txtSearch, testData.get("AudioBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("AudioBook_Title"));
		
//		basePageWeb.getElement(sanityPageWeb.btnBorrow);
//		actualData.add(basePageWeb.getElement(sanityPageWeb.btnBorrow).getText());
//		expectedData.add(testData.get("TextBorrow"));

		basePageWeb.clickElement(sanityPageWeb.menuFilter);
		basePageWeb.clickElement(sanityPageWeb.lnkAudioBook);

		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.clearAndEnterText(sanityPageWeb.txtSearch, testData.get("AudioBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		basePageWeb.getElement(sanityPageWeb.txtNoItems);
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtNoItems).getText());
		expectedData.add(testData.get("TextNoItems"));

		ExtentTestManager.getTest().info("Search the EBook");
		basePageWeb.clearAndEnterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title"));
		
		basePageWeb.clickElement(sanityPageWeb.menuFilter);
		basePageWeb.clickElement(sanityPageWeb.lnkAudioBook);
		basePageWeb.clickElement(sanityPageWeb.txtSearch);
		
//		basePageWeb.getElement(sanityPageWeb.btnBorrow);
//		actualData.add(basePageWeb.getElement(sanityPageWeb.btnBorrow).getText());
//		expectedData.add(testData.get("TextBorrow"));

		basePageWeb.clickElement(sanityPageWeb.menuFeatured);

		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify the EBook and AudioBook is filtered - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="WPT", description="Verify borrowing the titles from the History section")
	public void verify_BorrowHistory() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.clickElement(sanityPageWeb.menuFeatured);
		ExtentTestManager.getTest().info("Navigate to Featured Page");
		
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN2"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
		ExtentTestManager.getTest().info("Search and borrow a title");

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title2"));
		
		basePageWeb.clickElement(sanityPageWeb.btnBorrow,3);
		
//		basePageWeb.getElement(sanityPageWeb.btnRead);
//		actualData.add(basePageWeb.getElement(sanityPageWeb.btnRead).getText());
//		expectedData.add(testData.get("TextRead"));
		
		basePageWeb.clickElement(sanityPageWeb.btnReturn,2);
		basePageWeb.clickElement(sanityPageWeb.btnReturnPopup,2);
		ExtentTestManager.getTest().info("Return the title from the Search section");
		
		basePageWeb.clickElement(sanityPageWeb.menuMyBooks,3);
		
		basePageWeb.clickElement(sanityPageWeb.lnkHistory,2);
		ExtentTestManager.getTest().info("Navigate to History tab and borrow the same title");
		basePageWeb.clickElement(sanityPageWeb.btnBorrowHistory,3);
		
		basePageWeb.getElement(sanityPageWeb.btnRead);
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnRead).getText());
		expectedData.add(testData.get("TextRead"));
		
		basePageWeb.clickElement(sanityPageWeb.btnReturn,2);
		basePageWeb.clickElement(sanityPageWeb.btnReturnPopup,2);
		
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title2"));

		basePageWeb.clickElement(sanityPageWeb.menuFeatured,2);

		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify the titles are getting borrowed from History section - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="WPT", description="Verify borrowing the titles from the History section")
	public void verify_ClearHistory() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.clickElement(sanityPageWeb.menuMyBooks,2);
		basePageWeb.clickElement(sanityPageWeb.lnkHistory,2);
		ExtentTestManager.getTest().info("Navigate to MyBooks Page");
		
		basePageWeb.clickElement(sanityPageWeb.btnDeleteHistory);
		basePageWeb.clickElement(sanityPageWeb.btnDeleteHistoryPopup);
		
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtClearHistory).getText());
		expectedData.add(testData.get("ClearHistory"));
		
		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify that allthe titles are getting removed from History section - " + ExtentTestManager.getTest().getStatus());
	}
	
	@Test(groups="WPT", description="Verify borrowing the titles from the History section")
	public void verify_flaggedTitle() throws Exception {
		
		ExtentTestManager.getTest().assignCategory("WPT");
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.clickElement(sanityPageWeb.menuMyBooks);
		ExtentTestManager.getTest().info("Navigate to MyBooks Page");
		
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN2"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
		ExtentTestManager.getTest().info("Search a title");

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title2"));
		
		basePageWeb.clickElement(sanityPageWeb.imgCover,3);
		basePageWeb.clickElement(sanityPageWeb.icnFlag,3);
		basePageWeb.clickElement(sanityPageWeb.icnBDClose,3);
		ExtentTestManager.getTest().info("Click on the Flag link in the Book Details page");
		
		basePageWeb.clickElement(sanityPageWeb.menuMyBooks);
		basePageWeb.clickElement(sanityPageWeb.icnFlagTab);
		
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title2"));
		
		basePageWeb.clickElement(sanityPageWeb.imgCover,3);
		basePageWeb.clickElement(sanityPageWeb.icnFlag,3);
		basePageWeb.clickElement(sanityPageWeb.icnBDClose,3);
		
		Assert.assertEquals(expectedData, actualData);
		ExtentTestManager.getTest().info("Verify the titles are getting borrowed from History section - " + ExtentTestManager.getTest().getStatus());
		
	}
}
