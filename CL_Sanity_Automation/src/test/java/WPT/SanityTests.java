package WPT;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SanityTests extends WPTSetUp {

	@Test(groups = "WPT", description = "Validate Authentication by entering Valid Credentials")
	public void verify_ValidUser() throws Exception {
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.getElement(sanityPageWeb.btnLogin);
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnLogin).getText());
		expectedData.add(testData.get("ButtonLogin"));

		basePageWeb.clickElement(sanityPageWeb.btnLogin);
		basePageWeb.enterText(sanityPageWeb.txtLibID, testData.get("LibraryID"));
		basePageWeb.clickElement(sanityPageWeb.btnLoginPopup);

		basePageWeb.getElement(sanityPageWeb.btnAccept);
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnAccept).getText());
		expectedData.add(testData.get("ButtonAccept"));
		basePageWeb.clickElement(sanityPageWeb.btnAccept);

		basePageWeb.waitforSpinnerToComplete(sanityPageWeb.spinner);
		basePageWeb.getElement(sanityPageWeb.menuFeatured);
		actualData.add(basePageWeb.getElement(sanityPageWeb.menuFeatured).getText());
		expectedData.add(testData.get("TextFeatured"));

		Assert.assertEquals(expectedData, actualData);
	}
	
	@Test(groups = "WPT", description = "Borrow an EBook and verify the functionalities")
	public void borrow_EBook() throws Exception {
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();
	
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
//		basePageWeb.waitforSpinnerToComplete(sanityPageWeb.spinner);
		basePageWeb.clickElement(sanityPageWeb.btnBorrow);
	
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
		basePageWeb.clickElement(sanityPageWeb.lnkGoto,4);
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
		
		basePageWeb.clickElement(sanityPageWeb.icnFontPopupClose);
		basePageWeb.clickElement(sanityPageWeb.lnkGoto,4);
		basePageWeb.clickElement(sanityPageWeb.lnkChapterSelect2,4);	
		basePageWeb.clickElement(sanityPageWeb.tapEBook,4);
		basePageWeb.clickElement(sanityPageWeb.icnClose,4);
		basePageWeb.clickElement(sanityPageWeb.btnReturnMyBooks,4);
		basePageWeb.clickElement(sanityPageWeb.btnReturnPopup,4);
		basePageWeb.clickElement(sanityPageWeb.menuFeatured);
		
		basePageWeb.getElement(sanityPageWeb.menuFeatured);
		actualData.add(basePageWeb.getElement(sanityPageWeb.menuFeatured).getText());
		expectedData.add(testData.get("TextFeatured"));	
		
		Assert.assertEquals(expectedData, actualData);
	}

	@Test(groups="WPT", description="Borrow an Audiobook and verify the functionalities")
	public void borrow_AudioBook() throws Exception {
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.clickElement(sanityPageWeb.menuFeatured);
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("AudioBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);
		basePageWeb.clickElement(sanityPageWeb.btnBorrow);
//		basePageWeb.waitforWorkingToComplete(sanityPageWeb.working);

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

		basePageWeb.getElement(sanityPageWeb.btnBorrow);
		actualData.add(basePageWeb.getElement(sanityPageWeb.btnBorrow).getText());
		expectedData.add(testData.get("TextBorrow"));

		basePageWeb.clickElement(sanityPageWeb.menuFeatured);

		basePageWeb.getElement(sanityPageWeb.menuFeatured);
		actualData.add(basePageWeb.getElement(sanityPageWeb.menuFeatured).getText());
		expectedData.add(testData.get("TextFeatured"));

		Assert.assertEquals(expectedData, actualData);
	}
	
	@Test(groups="WPT", description="Search and verify the details of Ebook")
	public void search_EBook() throws Exception {
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.clickElement(sanityPageWeb.menuBrowse);
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		actualData.add(basePageWeb.getElement(sanityPageWeb.btnBorrow).getText());
		expectedData.add(testData.get("TextBorrow"));

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title"));

		Assert.assertEquals(expectedData, actualData);
	}

	@Test(groups="WPT", description="Search and verify the details of Audiobook")
	public void search_AudioBook() throws Exception {
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.clickElement(sanityPageWeb.menuBrowse);
		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.enterText(sanityPageWeb.txtSearch, testData.get("AudioBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		actualData.add(basePageWeb.getElement(sanityPageWeb.btnBorrow).getText());
		expectedData.add(testData.get("TextBorrow"));

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("AudioBook_Title"));

		Assert.assertEquals(expectedData, actualData);
	}
	
	@Test(groups="WPT", description="Verify the Shelf got loaded in the Featured Page")
	public void verify_Shelf() throws Exception {
		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");
		ArrayList<Object> actualData = new ArrayList<>();
		ArrayList<Object> expectedData = new ArrayList<>();

		basePageWeb.clickElement(sanityPageWeb.menuFeatured);

		basePageWeb.getElement(sanityPageWeb.txtShelf);
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtShelf).getText());
		expectedData.add(testData.get("FeaturedShelf"));
		
		Assert.assertEquals(expectedData, actualData);
	}

	@Test(groups="WPT", description="Verify the Filter functionality for EBook and Audiobook")
	public void verify_Filter() throws Exception {
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

		basePageWeb.clearAndEnterText(sanityPageWeb.txtSearch, testData.get("AudioBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		actualData.add(basePageWeb.getElement(sanityPageWeb.btnBorrow).getText());
		expectedData.add(testData.get("TextBorrow"));

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("AudioBook_Title"));

		basePageWeb.clickElement(sanityPageWeb.menuFilter);
		basePageWeb.clickElement(sanityPageWeb.lnkAudioBook);

		basePageWeb.clear(sanityPageWeb.txtSearch);
		basePageWeb.clearAndEnterText(sanityPageWeb.txtSearch, testData.get("AudioBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		basePageWeb.getElement(sanityPageWeb.txtNoItems);
		actualData.add(basePageWeb.getElement(sanityPageWeb.txtNoItems).getText());
		expectedData.add(testData.get("TextNoItems"));

		basePageWeb.clearAndEnterText(sanityPageWeb.txtSearch, testData.get("EBook_ISBN"));
		basePageWeb.pressEnter(sanityPageWeb.txtSearch);

		actualData.add(basePageWeb.getElement(sanityPageWeb.btnBorrow).getText());
		expectedData.add(testData.get("TextBorrow"));

		actualData.add(basePageWeb.getElement(sanityPageWeb.txtTitle).getText());
		expectedData.add(testData.get("EBook_Title"));

		basePageWeb.clickElement(sanityPageWeb.menuFeatured);

		Assert.assertEquals(expectedData, actualData);
	}
}
