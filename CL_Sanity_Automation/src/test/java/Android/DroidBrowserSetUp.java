package Android;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import AndroidPages.DroidBasePage;
import AndroidPages.DroidBrowserSanityPage;
import Helper.Efficacies;
import Helper.EmailReporter;
import Helper.StartApp;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@Listeners(EmailReporter.class)
public class DroidBrowserSetUp {
	public Efficacies eff;
	public DroidBrowserSanityPage sanityPageDeviceBrowser;
	public DroidBasePage basePage;
	public AndroidDriver<MobileElement> driver;
	public StartApp startApp;
	public String OUTPUT_FOLDER;
			
	@BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
		
		startApp = new StartApp();
		eff = new Efficacies();
		driver = startApp.startApp_BrowserMobile();
		sanityPageDeviceBrowser = new DroidBrowserSanityPage(driver);
		basePage = new DroidBasePage(driver);
		
		Map<String, String> loginData = eff.readJsonElement("SetUp_DeviceBrowser.json", "baseData");

    }

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		startApp.closeDriver_Mobile();
	}
}