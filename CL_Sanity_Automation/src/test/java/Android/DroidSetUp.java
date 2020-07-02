package Android;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import AndroidPages.DroidBasePage;
import AndroidPages.DroidSanityPage;
import Helper.Efficacies;
import Helper.EmailReporter;
import Helper.StartApp;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

@Listeners(EmailReporter.class)
public class DroidSetUp {
	public Efficacies eff;
	public DroidSanityPage sanityPageDevice;
	public DroidBasePage basePage;
	public AndroidDriver<MobileElement> driver;
	public StartApp startApp;
	
	@BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
		
		startApp = new StartApp();
		eff = new Efficacies();
		driver = startApp.startApp_Mobile();
		sanityPageDevice = new DroidSanityPage(driver);
		basePage = new DroidBasePage(driver);
		
		Map<String, String> loginData = eff.readJsonElement("SetUp_Device.json", "baseData");
		System.out.println(loginData);
		
		sanityPageDevice.selectEnvironment(loginData);
		basePage.clickElement(sanityPageDevice.btnAccept);
    }

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		startApp.closeDriver_Mobile();
	}
}