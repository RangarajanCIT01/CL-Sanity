package AndroidPages;

import org.openqa.selenium.By;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class DroidBrowserSanityPage extends DroidBasePage{
	
	public DroidBrowserSanityPage(AndroidDriver<MobileElement> driver) 
	{
        super(driver);
    }
	
	public By iconHamb = By.xpath("//span[@rotate-class-rotated='fa fa-arrow-right']");	
	public By iconClose = By.xpath("//div[@ng-click='close($event)']");
	public By txtLogID = By.xpath("//input[@type='text']");
	public By btnLoginPopup = By.xpath("//button[@ng-show='accept']");
	public By btnAcceptPopup = By.xpath("//button[@class='button-blue']");
	public By btnLogout = By.xpath("//button[text()='Log out']");
	public By icnSearch = By.xpath("//i[@class='fa fa-search']");
	public By btnBorrow = By.xpath("//button[text()='Borrow']");
	public By btnRead = By.xpath("//button[text()='Read']");
	public By btnListen = By.xpath("//button[@class='button-blue']");
	public By btnReturn = By.xpath("//button[text()='Return']");
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
	public By btnLogin = By.xpath("/html/body/header/div[1]/div[2]/div/div/div[1]/span[2]/div/div[1]/button");
	public By txtLibID = By.xpath("//input[@ng-model='LoginService.User.UserId']");
	public By btnAccept = By.xpath("//button[@ng-show='secondButton']");
	//public By menuFeatured = By.xpath("//span[@id='Featured']");
	public By menuFeatured = By.xpath("/html/body/header/div[2]/div[1]/div/main/div/div/div[1]/div[1]/div/ul/li[1]/div/span");
	public By menuReading = By.xpath("//span[text()='Reading']");
	public By logout = By.xpath("/html/body/header/div[1]/div[2]/div/div/div[1]/span[2]/div/div[2]/span/button");	
	public By txtSearch = By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/div/input");	
	public By btnReturnMyBooks = By.xpath("/html/body/main/div/div[2]/main/div/div/div/div[2]/div/div/div/div[1]/div[1]/button");
	public By btnReturnPopup = By.xpath("/html/body/div[3]/div/div/div/main/div/div[1]/button");		
	public By txtTitle = By.xpath("//div[@class='item-title']");
	public By menuBrowse = By.xpath("//span[text()='Browse']");
	public By btnfastforward = By.xpath("//i[@class='fa fa-fast-forward']");
	public By btnfastbackward = By.xpath("//i[@class='fa fa-fast-backward']");
	public By btnrotateleft = By.xpath("//div[@class='btn btn-large seek-back']");
	//public By btnPlay = By.xpath("//i[@class='fa fa-play']");
	public By btnPlay = By.xpath("/html/body/main/div/div/div/main/div[3]/div[2]/div[2]/div/div/div[1]/div[3]/i");
	public By btnRepeat = By.xpath("//div[@class='btn btn-large seek-forward']");
	public By btnPause = By.xpath("//i[@class='fa fa-pause']");
	public By lnkTrack5 = By.xpath("//span[text()='Track 5']");
	public By lnkTrack4 = By.xpath("//span[text()='Track 4']");
	public By listeniframe = By.xpath("//div[@class='flexbox-container-row flexbox-left-center-right-items flexbox-flex-wrap']/html/body/main/div/div/div/main/div[3]/div[2]");
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
	public By lnkGoto = By.xpath("//span[@id='goto']");
	public By lnkChapterSelect1 = By.xpath("//a[text()='Automatic First Down']");
	public By lnkChapterSelect2 = By.xpath("//a[text()='Cover']");
	public By icnClose = By.xpath("//span[@id='close']");
	public By icnFontPopupClose = By.xpath("/html/body/main/div/div[1]/div[4]/main/div/span/span");	
	public By menuFilter = By.xpath("/html/body/header/div[2]/div[1]/div/main/div/div/div[1]/div[2]/ul/li[1]/div/div/div/span");
	public By lnkEBook = By.xpath("//span[text()='eBooks']");
	public By icnCloseFilter = By.xpath("//div[@ng-click='onReject($event)']");
	public By lnkAudioBook = By.xpath("//span[text()='Audiobooks']");
	public By txtNoItems = By.xpath("//span[text()='No Items']");
	public By tapEBook = By.xpath("//main[@id='content']");
	public By iconSearch = By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/div/i");	
}





