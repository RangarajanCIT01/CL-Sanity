package Android;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import AndroidPages.DroidBasePage;
import AndroidPages.DroidSanityPage;
import Helper.Efficacies;
import Helper.StartApp;
import Helper.StartApp2;
import Helper.StartApp3;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class DroidSetUp2 {
	public Efficacies eff;
	public DroidSanityPage sanityPageDevice;
	public DroidBasePage basePage;
	public AndroidDriver<MobileElement> driver;
	public StartApp3 startApp;
	
	@Parameters({"platformVersion","deviceName"})
	@BeforeClass(alwaysRun = true)
    public void setUp(String platformVersion, String deviceName) throws Exception {
		
		startApp = new StartApp3();
		eff = new Efficacies();
		driver = startApp.startApp_Mobile(platformVersion,deviceName);
		sanityPageDevice = new DroidSanityPage(driver);
		basePage = new DroidBasePage(driver);
		
		Map<String, String> loginData = eff.readJsonElement("SetUp_Device.json", "baseData");
		System.out.println(loginData);
		
		sanityPageDevice.selectEnvironment(loginData);
		basePage.clickElement(sanityPageDevice.btnAccept);
    }

	//@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		startApp.closeDriver_Mobile();
		
	}
}