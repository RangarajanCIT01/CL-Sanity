package AndroidPages;

import java.net.MalformedURLException;
import java.util.Map;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DroidSanityPage extends DroidBasePage {
	
	public DroidSanityPage(AndroidDriver<MobileElement> driver) 
	{
        super(driver);
    }
	
	/** WELCOME **/
	public By btnLogin = By.xpath("//android.widget.Button[@text='NEXT']");
	public By txtLibId = By.xpath("//android.widget.EditText[@text='Library Card ID']");
	public By txtCardId = By.xpath("//android.widget.EditText[@resource-id ='com.txtr.android.mmm:id/edit_library_card_id']");
			
	/** SKIP **/
	public By btnSkip = By.xpath("//android.widget.Button[@text='SKIP']");
	public By lnkOk = By.xpath("//android.widget.Button[@text='OK']");
	
	/** INVALID **/
	public By lblLoginFail = By.xpath("//android.widget.TextView[@text='Login Failed']");
	public By lblLoginDesc = By.xpath("//android.widget.TextView[@text='Invalid Id or Password']");
	public By btnOk = By.xpath("//android.widget.Button[@text='OK']");
	public By btnBack = By.xpath("//android.widget.ImageButton[@index=0]");
	public By btnAccept = By.xpath("//android.widget.Button[@text='ACCEPT']");	
	public By txtPatronName = By.xpath("//android.widget.EditText[@text='Library Card ID']");
	public By txtWelcome = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_welcome_message']");
	public By txtLibrary = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_library_name_front']");
	public By lstEnvironment = By.xpath("//android.widget.TextView[@text='Select Environment']");
	
	/****** MYBOOKS *******/
	public By menuSearch = By.xpath("//android.widget.TextView[@text='Search']");
	public By lnkFeatured = By.xpath("//android.widget.TextView[@text='FEATURED']");
	public By lblSrchKeywd = By.xpath("//android.widget.TextView[@text='Enter Search Keyword(s)']");
	public By lblEditTxt = By.xpath("//android.widget.EditText[@text='Enter Search Keyword(s)']");
	public By txtBookDetails = By.xpath("//android.widget.TextView[@text='Book Details']");
	public By imgBookCover = By.id("com.txtr.android.mmm:id/img_book_cover");
	public By btnBorrow = By.xpath("//android.widget.Button[@text='Borrow']");
	public By lblTitle = By.id("com.txtr.android.mmm:id/txt_book_title"); 
	public By btnImgBack = By.xpath("//android.widget.ImageView[@resource-id='com.txtr.android.mmm:id/img_back']");
	public By txtError = By.xpath("//android.widget.TextView[@text='Error']");
	public By btnReturn = By.xpath("//android.widget.Button[@text='Return']");
	public By searchBackbtn = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");
	public By menuMyBooks = By.xpath("//android.widget.TextView[@text='My Books']");
	public By lnkMyBooks = By.xpath("//android.widget.Button[@text='Read']");
	public By txtReturnBook = By.xpath("//android.widget.TextView[@text='Return Book']");
	public By txtReturn = By.xpath("//android.widget.TextView[@text='Return Book']");
	public By icnFilter = By.xpath("//android.widget.ImageView[@resource-id='com.txtr.android.mmm:id/action_filter_browse']");
	public By tglAllLibTitles = By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.txtr.android.mmm:id/recycler_view_filters']/android.view.ViewGroup[6]/android.widget.Switch");
	public By linkApply = By.xpath("//android.widget.TextView[@text='APPLY']");
	public By lnkAll = By.xpath("//android.widget.TextView[@text='ALL']");
	public By lblSubTitle  	= By.id("com.txtr.android.mmm:id/txt_book_subtitle");
	public By btnLogout = By.xpath("//android.widget.Button[@text='LOGOUT']");
	public By lnkSave = By.xpath("//android.widget.TextView[@text='SAVE']");
	public By menuAccount = By.xpath("//android.widget.TextView[@text='Account']");
	public By lnkFavorites = By.xpath("//android.widget.TextView[@text='FAVORITES']");
	public By lnkHistory = By.xpath("//android.widget.TextView[@text='HISTORY']");
	public By lnkCurrent = By.xpath("//android.widget.TextView[@text='CURRENT']");
	public By lnkHolds = By.xpath("//android.widget.TextView[@text='HOLDS']");
	public By lnkSaved = By.xpath("//android.widget.TextView[@text='SAVED']");	
	public By lnkAbout = By.xpath("//android.widget.TextView[@text='About']");
	public By txtNotifications = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_notifications_label']");
	public By btnListen = By.xpath("//android.widget.Button[@text='Listen']");
	public By txtDownloading = By.xpath("//android.widget.TextView[@text='Downloading…']");
	public By txtDownloadPaused = By.xpath("//android.widget.TextView[@text='Download paused']");
	public By btnDownload = By.xpath("//android.widget.ImageButton[@resource-id='com.txtr.android.mmm:id/audioPlayerDownloadIB']");
	public By imgAudioBookCover = By.xpath("//android.widget.ImageView[@resource-id='com.txtr.android.mmm:id/audioPlayerCoverArtIV']");
	public By lnkTracks = By.xpath("//android.widget.TextView[@text='TRACKS']");
	public By lnkCover = By.xpath("//android.widget.TextView[@text='COVER']");
	public By btnPlay = By.xpath("//android.widget.ImageButton[@resource-id='com.txtr.android.mmm:id/audioPlayerPlayPauseIB']");
	public By lnkPlaySample = By.xpath("//android.widget.TextView[@text='Play Sample']");
	public By lnkPauseSample = By.xpath("//android.widget.TextView[@text='Pause Sample']");
	public By lblNarrator = By.id("com.txtr.android.mmm:id/txt_book_narrator");
	public By btnRead = By.xpath("//android.widget.Button[@text='Read']");
	public By tapEbooks = By.className("android.widget.ImageView");
	public By lblHistory = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_no_receipts_label']");
	public By lblHolds = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_no_books_label']");
	public By lblSaved = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_no_books_label']");
	public By shakeDevice = By.className("androidx.recyclerview.widget.RecyclerView");
	public By txtDownloadInProgress = By.xpath("//android.widget.LinearLayout/android.widget.TextView[@text='Please wait while download is in progress…']");
	public By dotsEbook = By.xpath("//android.widget.FrameLayout/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.ImageView");
	public By lnkTableOfContents = By.xpath("//android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView[@resource-id='com.txtr.android.mmm:id/title']");
	public By lnkChapter = By.xpath("//android.widget.LinearLayout/android.widget.ListView[@resource-id='com.txtr.android.mmm:id/lstNavItems']/android.widget.LinearLayout[7]/android.widget.TextView[@resource-id='com.txtr.android.mmm:id/cellTextView']");
	public By lnkEbookBookMark = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/toggle_bookmark']");
	public By imgBookMarked = By.xpath("//android.widget.ImageView[@resource-id='com.txtr.android.mmm:id/page_bookmark_image_view']");
	public By lnkEBookChapter = By.xpath("//android.widget.LinearLayout/android.widget.ListView[@resource-id='com.txtr.android.mmm:id/lstNavItems']/android.widget.LinearLayout/android.widget.TextView[@resource-id='com.txtr.android.mmm:id/cellTextView']");
	 
	public By btnMyBooksRead = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[4]/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.Button[@text='Read']");
	public By btnlistenMyBooks = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.HorizontalScrollView[@resource-id='com.txtr.android.mmm:id/horizontalScrollView2']/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.Button[@text='Listen']");
	public By btnReadMyBooks = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.Button[@text='Read']");
	
	/******** SEARCH *********/
	
	public By tglEbook = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.Switch[@resource-id='com.txtr.android.mmm:id/switch_row']");
	public By tglAudioBook = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]/android.widget.Switch[@resource-id='com.txtr.android.mmm:id/switch_row']");
	public By txtNoBooks = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_no_book_results']");
	public By tap = By.xpath("//android.widget.TextView[@index='0']");
	public By lnkApply = By.xpath("//android.widget.TextView[@text='APPLY']");
	public By tglEnglish = By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[9]/android.widget.Switch[@resource-id='com.txtr.android.mmm:id/switch_row']");
	public By icnResultFilter = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/action_filter']");
	public By lblAuthor = By.id("com.txtr.android.mmm:id/txt_book_author");
	public By lblInfo = By.id("com.txtr.android.mmm:id/txt_book_info");
	public By lblISBN = By.id("com.txtr.android.mmm:id/txt_book_isbn");
	public By lblFormat = By.id("com.txtr.android.mmm:id/txt_book_format");
	public By lnkAdvancedSearch = By.xpath("//android.widget.TextView[@text='Advanced Search']");	
	public By txtAdvancedSearch = By.xpath("//android.widget.TextView[@text='Advanced Search']");
	public By txtfldTitle = By.xpath("//android.widget.EditText[@resource-id ='com.txtr.android.mmm:id/edit_title']");
	public By txtfldAuthor = By.xpath("//android.widget.EditText[@resource-id ='com.txtr.android.mmm:id/edit_author']");
	public By txtfldSeries = By.xpath("//android.widget.EditText[@resource-id ='com.txtr.android.mmm:id/edit_series']");
	public By txtfldISBN = By.xpath("//android.widget.EditText[@resource-id ='com.txtr.android.mmm:id/edit_isbn']");
	public By btnSearch = By.xpath("//android.widget.Button[@text='SEARCH']");
	public By btnClearAll = By.xpath("//android.widget.Button[@text='CLEAR ALL']");	
	public By lnkFeedBack = By.xpath("//android.widget.TextView[@text='Having trouble with this title?']");
	public By txtFormat = By.xpath("//android.widget.TextView[@text='Format']");
	public By txtAvailability = By.xpath("//android.widget.TextView[@text='Availability']");
	public By txtLanguage = By.xpath("//android.widget.TextView[@text='Language']");
	public By btnLetsGo = By.xpath("//android.widget.Button[@text='com.txtr.android.mmm:id/btn_lets_go']");
	
		/*** EBOOK AND AUDIOBOOK ****/
	
	public By lnkBookMark = By.xpath("//android.widget.ImageButton[@resource-id='com.txtr.android.mmm:id/audioPlayerBookmarkButton']");
	public By lnkPreviousChapter = By.xpath("//android.widget.ImageButton[@resource-id='com.txtr.android.mmm:id/audioPlayerPreviousChapterIB']");
	public By lnkPlayBack = By.xpath("//android.widget.ImageButton[@resource-id='com.txtr.android.mmm:id/audioPlayerBack15IB']");
	public By lnkPlayForward = By.xpath("//android.widget.ImageButton[@resource-id='com.txtr.android.mmm:id/audioPlayerForward15IB']");
	public By lnkNextChapter = By.xpath("//android.widget.ImageButton[@resource-id='com.txtr.android.mmm:id/audioPlayerNextChapterIB']");
	public By btnCreateBookMark = By.xpath("//android.widget.Button[@text='Create Bookmark']");
	public By btnBookMarkSave = By.xpath("//android.widget.Button[@text='Save']");
	public By txtBookMarkName = By.xpath("//android.widget.EditText[@text='Type notes here']");
	public By txtBookMarkVerify = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/bookmarkRowNoteTV']");
	public By btnBookMarkDelete = By.xpath("//android.widget.Button[@text='Delete']");
	public By lnkCreatedBookMark = By.xpath("//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[@text='Chapter']");
	public By lblCurrentNoBooks = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_no_books_label']");
	
	/*** Browser***/
	public By fldAddress = By.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/search_box_text']");
	

	/** SEARCH **/
	public By lnkFeedback = By.id("com.txtr.android.mmm:id/txt_book_feedback");
	public By txtNothing = By.xpath("//android.widget.TextView[@text='Nothing currently checked out. Time to borrow some books!']");
	public By txtAuthorName = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_book_author']");
	
	/** WELCOME **/
	public By txtTap = By.xpath("//android.widget.TextView[@text='Tap the screen to continue.']");
	public By lstLanguage = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/btn_choose_language']");
	public By tapMain = By.className("android.widget.TextView");
	public By lstCountry = By.xpath("//android.widget.TextView[@text='Select Country']");
	public By lstState = By.xpath("//android.widget.TextView[@text='Select State/Region']");
	public By lstLibrary = By.xpath("//android.widget.TextView[@text='Select Library']");
	public By btnNext = By.xpath("//android.widget.Button[@text='NEXT']");
	public By txtCountryAlert = By.xpath("//android.widget.TextView[@text='You must choose country first!']");
	public By txtLibAlert = By.xpath("//android.widget.TextView[@text='You must choose country and state first!']");
		
	/*** Shelf ***/
	public By txtShelfName = By.xpath("//android.widget.TextView[@resource-id='com.txtr.android.mmm:id/txt_shelf_title']");
	
	
	public DroidSanityPage selectEnvironment(Map<String, String> testData) throws MalformedURLException, InterruptedException {
		
		clickElement(lstLanguage);
		scrollElement(testData.get("Language"));
		
		clickElement(tapMain);
						
//		clickElement(lstEnvironment);
//		scrollElement(testData.get("Environment"));
		
		clickElement(lstCountry);
		scrollElement(testData.get("Country"));
				
		clickElement(lstState);
		scrollElement(testData.get("State"));
		
		clickElement(lstLibrary);
		scrollElement(testData.get("Library"));
			
		clickElement(btnNext);
			
		System.out.println("Welcome Page fields got selected");
		
		return new DroidSanityPage(driver);
	}
	
	public boolean verifyContainingText(By element, String expectedText) {
			
		String actualText = driver.findElement(element).getText();
		try {
		if (actualText.equals(expectedText))
			return true;
		else 
			return false;
		} catch (Exception e) { return false;}
	}
	
	public DroidSanityPage invalidUserID(By locator, String userName) {
		
		enterText(locator, userName);
		clickElement(btnLogin);
		return new DroidSanityPage(driver);
	}
	
	public DroidSanityPage validUserID(String validUserName) {
		
		enterText(txtCardId, validUserName);
		clickElement(btnLogin);
		return new DroidSanityPage(driver);
	}
	
	public DroidSanityPage slider() throws InterruptedException {
		
		waitForVisibilityOf(btnSkip);
		String text = driver.findElement(btnSkip).getText();
		if(text.contains("SKIP")) {
			clickElement(btnSkip);
			waitforPageLoad(3);
			clickElement(lnkOk);
			System.out.println("Home page loaded");
		} else {
			System.out.println("Home page loaded");
		}
		return new DroidSanityPage(driver);
	}
	
	public boolean verifyEqualsText(By element, String expectedText) {
			
		String actualText = driver.findElement(element).getText();
		try {
		if (actualText.equals(expectedText))
			return true;
		else 
			return false;
		} catch (Exception e) { return false;}
	}
	
	public boolean verifyContainsText(By element, String expectedText) {
			
		String actualText = driver.findElement(element).getText();
		try {
		if (actualText.contains(expectedText))
			return true;
		else 
			return false;
		} catch (Exception e) { return false;}
	}
	
	public void backToFeaturedPage() {
		try {
			clickElement(btnBack);
			waitforPageLoad(2);
			clickElement(btnBack);
			waitforPageLoad(2);
			clickElement(btnImgBack);
			waitforPageLoad(2);
		} catch (InterruptedException e) {
			e.getMessage();
		}
	
	}
	
}
