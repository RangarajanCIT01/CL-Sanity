package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class WPTSanityPage extends WPTBasePage {

	public WPTSanityPage(WebDriver web_driver) {
		super(web_driver);
	
	}

	/** WELCOME **/
	public By btnLogin = By.xpath("/html/body/header/div[1]/div[1]/div/div[1]/div[2]/div[2]/div/div[1]/button");
	public By txtLibID = By.xpath("//input[@ng-model='LoginService.User.UserId']");
	public By btnLoginPopup = By.xpath("//button[@ng-show='accept']");
	public By btnAccept = By.xpath("//button[@ng-show='secondButton']");
	public By menuFeatured = By.xpath("//span[@id='Featured']");
	public By txtSearch = By.xpath("/html/body/header/div[1]/div[1]/div/div[3]/div[2]/div/input");
	public By filterSuggestionsforLib = By.xpath("//span[text()='Suggestions for library']");	
	public By btnBorrow = By.xpath("//button[text()='Borrow']");
	public By btnSuggest = By.xpath("//button[text()='Suggest to Library']");
	public By btnRemoveSuggest = By.xpath("//button[text()='Remove Suggestion']");
	public By btnDeleteHistory = By.xpath("//button[text()='Delete History']");	
	public By btnDeleteHistoryPopup = By.xpath("//button[@ng-show='accept']");
	public By txtClearHistory = By.xpath("//div[text()='There is no history of borrowed books to show.']");	
	
	public By btnRead = By.xpath("//button[text()='Read']");
	public By btnListen = By.xpath("//button[@class='button-blue']");
	public By btnReturn = By.xpath("//button[text()='Return']");
	public By btnReturnMyBooks = By.xpath("/html/body/main/div/div[2]/main/div/div/div/div[2]/div/div/div/div[1]/div[1]/button");
	public By btnReturnPopup = By.xpath("/html/body/div[3]/div/div/div/main/div/div[1]/button");		
	public By txtTitle = By.xpath("//div[@class='item-title']");
	public By menuBrowse = By.xpath("//span[text()='Browse']");
	public By btnfastforward = By.xpath("//i[@class='fa fa-fast-forward']");
	public By btnfastbackward = By.xpath("//i[@class='fa fa-fast-backward']");
	public By btnrotateleft = By.xpath("//div[@class='btn btn-large seek-back']");
	public By btnPlay = By.xpath("//i[@class='fa fa-play']");
	public By btnRepeat = By.xpath("//div[@class='btn btn-large seek-forward']");
	public By btnPause = By.xpath("//i[@class='fa fa-pause']");
	public By lnkTrack1 = By.xpath("//span[text()='Part 1 Chapter 1']");
	public By txtShelf = By.xpath("/html/body/main/div/div/div/div/div/div[1]/div/div/div[1]/div/span[1]/span[1]");
	public By lnkAppearance = By.xpath("//span[@title='Appearance']");
	public By lnkFontSizeIncrease = By.xpath("//button[@class='btn btn-narrow font-plus']");
	public By lnkFontSizeDecrease = By.xpath("//button[@class='btn btn-narrow font-minus']");
	public By lnkFontDefault = By.xpath("//button[text()='Default']");
	public By lnkFontNight = By.xpath("//button[text()='Night']");
	public By lnkFontSepia = By.xpath("//button[text()='Sepia']");
	public By lnkFontSingle = By.xpath("//button[text()='Single']");
	public By lnkFontDouble = By.xpath("//button[text()='Double']");
	public By lnkdropdownfld = By.xpath("//select[@class='ng-pristine ng-untouched ng-valid ng-valid-required']");
	public By txtPageTitle = By.xpath("/html/body/main/div/div[1]/div[4]/header/div/div/div/div[2]/div");
	public By lnkTOC = By.xpath("//span[@id='goto']");
	public By lnkBookmarks = By.xpath("//span[@id='Bookmarks']");
	public By btnDeleteBk = By.xpath("//span[@class='theme-clickable bookmark-icon-delete']");
	public By icnRefresh = By.xpath("//i[@class='fa fa-refresh']");
	public By lnkGoto = By.xpath("//span[text()='Goto']");
	public By txtPageno = By.xpath("//input[@type='number']");
	public By btnGo = By.xpath("//button[@type='button']");	
	public By btnHold = By.xpath("//button[text()='Place Hold']");
	public By btnRemoveHold = By.xpath("//button[text()='Remove Hold']");
	public By btnPlaceHoldPopup = By.xpath("//*[@id='quickmodals']/div/div/div/main/div/div[2]/button[1]");
	public By btnCancel = By.xpath("//button[@translate='ACTIONBTN_CANCEL']");
	
	
	//public By chapter = By.xpath("//li[@class='nav-elem']");
	public By lnkChapterSelect1 = By.xpath("//a[text()='Automatic First Down']");
	public By lnkChapterSelect2 = By.xpath("//a[text()='Cover']");
	public By lnkChapter1 = By.xpath("//a[text()='Balanced Line']");
	public By lnkChapter2 = By.xpath("//a[text()='Blindsided']");
	public By lnkBookmark = By.xpath("/html/body/main/div/div[1]/div[4]/header/div/div/div/div[3]/div/span/span");
	
	public By icnClose = By.xpath("//span[@id='close']");
	public By icnFontPopupClose = By.xpath("/html/body/main/div/div[1]/div[4]/main/div/span/span");
	public By menuFilter = By.xpath("//span[text()='Filters']");	
	public By lnkEBook = By.xpath("//span[text()='eBooks']");
	public By lnkAudioBook = By.xpath("//span[text()='Audiobooks']");
	public By txtNoItems = By.xpath("//span[text()='No Items']");
	public By tapEBook = By.xpath("//main[@id='content']");
	public By btnLogout = By.xpath("/html/body/header/div[1]/div[1]/div/div[1]/div[2]/div[2]/div/div[2]/span/button");
	public By btnLogoutpopup = By.xpath("//button[@translate='MENU_LOGOUT']");
	public By menuMyBooks = By.xpath("//span[@translate='MENU_MYBOOKS']");
	
	public By icnBookmark = By.xpath("//i[@class='fa fa-bookmark']");
	public By lnkHistory = By.xpath("//span[text()='History']");
	public By btnBorrowHistory = By.xpath("//*[@id='content']/div/div[2]/div/div/div[1]/div/main/div/div[4]/div[2]/div/div/div/div[1]/button");
	
	public By icnFlag = By.xpath("//i[@title='flag/unflag Book of Interest.']");
	public By imgCover = By.xpath("//img[@class='item-img']");
	public By icnBDClose = By.xpath("//div[text()='X']");
	public By icnFlagTab = By.xpath("//div[@id='button-BOI']");
	public By menuAbout = By.className("about-popup");
	public By lblVersion = By.className("appVersion");
	public By spinner = By.className("spinner");
	public By working = By.className("user-action-button");
	
	public By ChapterID = By.xpath("//*[@id='epubreader-toc']/li");	
		
		
	public void selectDropDown(By locator, String value) {
		
		Select optSelect = new Select(web_driver.findElement(locator));
		optSelect.selectByVisibleText(value);
	}
	
	public String getAppVersion() {
		
		clickElement(menuAbout);
		return getElement(lblVersion).getText();
	}


}
