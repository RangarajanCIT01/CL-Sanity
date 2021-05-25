package Helper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;

public class StartApp2 {
	
	public AndroidDriver<MobileElement> mobile_driver;
	public AndroidDriver<MobileElement> mobile_browserdriver;
	public WebDriver web_driver;
	public Properties prop;
	
	Efficacies effic;
	Properties config2;
	Runtime runtime;
	
	public String dataSheetName;
	public String databookName;	
	
	public File rootPath;
	public File appPath;
	public File emulPath;
	public File emulAppPath;
	DesiredCapabilities capabilities;
	public DesiredCapabilities caps = DesiredCapabilities.android();
	
	// Starting the driver on device
	public AndroidDriver<MobileElement> startApp_Mobile() throws IOException, InterruptedException {
		
		//emulatorBatFile();
		Thread.sleep(5000);
		stopAppiumServer();
		Thread.sleep(5000);
		startAppiumServer();
		
		rootPath = new File(System.getProperty("user.dir"));
		appPath = new File(rootPath, "/src/main/resources/AndroidResrc/");
		File appn = new File(appPath, "cloudLibrary.apk");
		
		effic = new Efficacies();
		config2 = effic.loadPropertiesFromResources("environment.properties");
		System.out.println(config2);
		
		if (config2.getProperty("PlatformName").equalsIgnoreCase("android") &&  
				config2.getProperty("IsRealDevice").equalsIgnoreCase("no")) {
			
			capabilities = new DesiredCapabilities();
			//capabilities.setCapability("deviceName", config2.getProperty("eDeviceName").toString());
			//capabilities.setCapability("udid", config2.getProperty("eDeviceUDID").toString());
			capabilities.setCapability("orientation", config2.getProperty("DeviceOrientation").toString());
			//capabilities.setCapability("platformName", config2.getProperty("PlatformName").toString());
			//capabilities.setCapability("platformVersion", config2.getProperty("ePlatformVersion").toString());
			capabilities.setCapability("app", appn.getAbsolutePath());
			capabilities.setCapability("appPackage", config2.getProperty("PackageName").toString());
			capabilities.setCapability("appActivity", config2.getProperty("ActivityName").toString());
			capabilities.setCapability("newCommandTimeout", "60");
			
			mobile_driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4725/wd/hub"), capabilities); 
			mobile_driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					
		} else if (config2.getProperty("PlatformName").equalsIgnoreCase("android")&&  
				config2.getProperty("IsRealDevice").equalsIgnoreCase("yes")) {
			
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", config2.getProperty("RealDeviceName").toString());
			capabilities.setCapability("udid", config2.getProperty("RealDeviceUDID").toString());
			capabilities.setCapability("orientation", config2.getProperty("DeviceOrientation").toString());
			capabilities.setCapability("platformName", config2.getProperty("PlatformName").toString());
			capabilities.setCapability("platformVersion", config2.getProperty("RealPlatformVersion").toString());
			capabilities.setCapability("app", appn.getAbsolutePath());
			capabilities.setCapability("appPackage", config2.getProperty("PackageName").toString());
			capabilities.setCapability("appActivity", config2.getProperty("ActivityName").toString());
			capabilities.setCapability("newCommandTimeout", "60");
			
			mobile_driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); 
			mobile_driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					
		} 
		return mobile_driver;
	}
	
	// Starting the driver on device
	public AndroidDriver<MobileElement> startApp_BrowserMobile() throws IOException {
		
		effic = new Efficacies();
		config2 = effic.loadPropertiesFromResources("environment.properties");
		System.out.println(config2);
		
		if (config2.getProperty("PlatformName").equalsIgnoreCase("android") &&  
				config2.getProperty("IsRealDevice").equalsIgnoreCase("no")) {
			
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Android9");
			caps.setCapability(MobileCapabilityType.VERSION,"9");
			caps.setCapability("udid", "emulator-5554");
			
			mobile_browserdriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			mobile_browserdriver.get(config2.getProperty("ProdURL")); 
			mobile_browserdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
					
		} else if (config2.getProperty("PlatformName").equalsIgnoreCase("android")&&  
				config2.getProperty("IsRealDevice").equalsIgnoreCase("yes")) {
			
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Android9");
			caps.setCapability(MobileCapabilityType.VERSION,"9");
			caps.setCapability("udid", "emulator-5554");
			
			mobile_browserdriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			mobile_browserdriver.get(config2.getProperty("ProdURL")); 
			mobile_browserdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
					
		} 
		return mobile_browserdriver;
	}
	
	// Starting the driver browser
	public WebDriver startApp_Web() throws IOException
		{
		  	rootPath = new File(System.getProperty("user.dir"));
			appPath = new File(rootPath, "/src/main/resources/WebResrc/");
			File chromePath = new File(appPath, "chromedriverWeb.exe");
			
			effic = new Efficacies();
			config2 = effic.loadPropertiesFromResources("environment.properties");
			System.out.println(config2);
			
			if (config2.getProperty("Browser").equalsIgnoreCase("Chrome")) {
				
				System.setProperty("webdriver.chrome.driver",chromePath.getAbsolutePath());
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
				options.addArguments("--disable-extensions");
				options.addArguments("-–disable-notifications");
				options.addArguments("disable-infobars");
				web_driver = new ChromeDriver(options);
				web_driver.get(config2.getProperty("ProdURL"));
				web_driver.manage().window().maximize();
						
			} else if (config2.getProperty("Browser").equalsIgnoreCase("IE")) {
				
				System.setProperty("webdriver.ie.driver","");
				web_driver = new InternetExplorerDriver();
		 
				web_driver.get(config2.getProperty("ProdURL"));
				web_driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				web_driver.manage().window().maximize();
			}
			return web_driver;
		}
	
	public void serverStartDevice2() {
		 
	     // Create AppiumDriverLocalService object with specifying the port.
	     AppiumDriverLocalService service = new AppiumServiceBuilder().usingPort(4725).build();

	     // To start Appium server.
	     service.start();

	     // To End Appium server.
	     //service.stop();
		 }
	 
	public void startAppiumServer() {
		runtime = Runtime.getRuntime();
		try {
		    runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4725 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
		    Thread.sleep(5000);
		    System.out.println("Appium Server is started....");
		} catch (IOException | InterruptedException e) {
		    e.printStackTrace();
		}
	}
	
	
	public void stopAppiumServer() {
		runtime = Runtime.getRuntime();
		try {
		    runtime.exec("taskkill /F /IM node.exe");
		    runtime.exec("taskkill /F /IM cmd.exe");
		    System.out.println("Appium Server Stopped.!");
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	// Close the Web driver
	public void closeDriver_Web(){
		try {
			web_driver.quit();
			System.out.println("Closing the driver...");
        } catch (Exception e) { }
	}
	
	// Close the Android driver
	public void closeDriver_Mobile(){
		try {
			Thread.sleep(5000);
			mobile_driver.quit();
			System.out.println("Closing the driver...");
        } catch (Exception e) { }
	}
	
	// Close the device browser driver
	public void closeDriver_MobileBrowser(){
		try {
			mobile_browserdriver.quit();
			System.out.println("Closing the driver...");
        } catch (Exception e) { }
	}
	
	public void recursiveDelete(File file) {
        //to end the recursive loop
        if (!file.exists())
            return;
        
        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                recursiveDelete(f);
            }
        }
        //call delete to delete files and empty directory
        file.delete();
        System.out.println("Deleted file/folder: "+file.getAbsolutePath());
    }
	
	public void emulatorBatFile() throws IOException
	{
		Process process = Runtime.getRuntime().exec("cmd /c Android.bat", null, new File("C:\\Users\\rangarajan\\Documents\\WPT_Automation\\CloudLibrary_DC\\src\\main\\java\\Helper\\"));	
	}
	
	public void emulatorClose() throws IOException
	{
		Process process = Runtime.getRuntime().exec("cmd /c EmulatorKill.bat", null, new File("D:\\New folder (2)\\cloudlibrarysanity\\src\\main\\java\\Helper\\"));	
	}
}




