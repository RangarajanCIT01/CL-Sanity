package Helper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

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

public class StartApp {
	
	public AndroidDriver<MobileElement> mobile_driver;
	public AndroidDriver<MobileElement> mobile_browserdriver;
	public WebDriver web_driver;
	public Properties prop;
	
	Efficacies effic;
	Properties config;
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
	public AndroidDriver<MobileElement> startApp_Mobile() throws IOException {
		
		emualtorBatFile();
		startAppiumServer();
		
		rootPath = new File(System.getProperty("user.dir"));
		appPath = new File(rootPath, "/src/main/resources/AndroidResrc/");
		File appn = new File(appPath, "cloudLibrary.apk");
		
		effic = new Efficacies();
		config = effic.loadPropertiesFromResources("environment.properties");
		System.out.println(config);
		
		if (config.getProperty("PlatformName").equalsIgnoreCase("android") &&  
				config.getProperty("IsRealDevice").equalsIgnoreCase("no")) {
			
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", config.getProperty("eDeviceName").toString());
			capabilities.setCapability("udid", config.getProperty("eDeviceUDID").toString());
			capabilities.setCapability("orientation", config.getProperty("DeviceOrientation").toString());
			capabilities.setCapability("platformName", config.getProperty("PlatformName").toString());
			capabilities.setCapability("platformVersion", config.getProperty("ePlatformVersion").toString());
			capabilities.setCapability("app", appn.getAbsolutePath());
			capabilities.setCapability("appPackage", config.getProperty("PackageName").toString());
			capabilities.setCapability("appActivity", config.getProperty("ActivityName").toString());
			capabilities.setCapability("newCommandTimeout", "60");
			
			mobile_driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); 
			mobile_driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					
		} else if (config.getProperty("PlatformName").equalsIgnoreCase("android")&&  
				config.getProperty("IsRealDevice").equalsIgnoreCase("yes")) {
			
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", config.getProperty("RealDeviceName").toString());
			capabilities.setCapability("udid", config.getProperty("RealDeviceUDID").toString());
			capabilities.setCapability("orientation", config.getProperty("DeviceOrientation").toString());
			capabilities.setCapability("platformName", config.getProperty("PlatformName").toString());
			capabilities.setCapability("platformVersion", config.getProperty("RealPlatformVersion").toString());
			capabilities.setCapability("app", appn.getAbsolutePath());
			capabilities.setCapability("appPackage", config.getProperty("PackageName").toString());
			capabilities.setCapability("appActivity", config.getProperty("ActivityName").toString());
			capabilities.setCapability("newCommandTimeout", "60");
			
			mobile_driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); 
			mobile_driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					
		} 
		return mobile_driver;
	}
	
	// Starting the driver on device
	public AndroidDriver<MobileElement> startApp_BrowserMobile() throws IOException {
		
		effic = new Efficacies();
		config = effic.loadPropertiesFromResources("environment.properties");
		System.out.println(config);
		
		if (config.getProperty("PlatformName").equalsIgnoreCase("android") &&  
				config.getProperty("IsRealDevice").equalsIgnoreCase("no")) {
			
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Emulator-Nexus6");
			caps.setCapability(MobileCapabilityType.VERSION,"9");
			caps.setCapability("udid", "emulator-5554");
			
			mobile_browserdriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			mobile_browserdriver.get(config.getProperty("ProdURL")); 
			mobile_browserdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
					
		} else if (config.getProperty("PlatformName").equalsIgnoreCase("android")&&  
				config.getProperty("IsRealDevice").equalsIgnoreCase("yes")) {
			
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Emulator-Nexus6");
			caps.setCapability(MobileCapabilityType.VERSION,"9");
			caps.setCapability("udid", "emulator-5554");
			
			mobile_browserdriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			mobile_browserdriver.get(config.getProperty("ProdURL")); 
			mobile_browserdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
					
		} 
		return mobile_browserdriver;
	}
	
	// Starting the driver browser
	 public WebDriver startApp_Web() throws IOException
		{
		  	rootPath = new File(System.getProperty("user.dir"));
			appPath = new File(rootPath, "/src/main/resources/WebResrc/");
			File chromePath = new File(appPath, "chromedriver.exe");
			
			effic = new Efficacies();
			config = effic.loadPropertiesFromResources("environment.properties");
			System.out.println(config);
			
			if (config.getProperty("Browser").equalsIgnoreCase("Chrome")) {
				
				System.setProperty("webdriver.chrome.driver",chromePath.getAbsolutePath());
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
				options.addArguments("--disable-extensions");
				options.addArguments("-–disable-notifications");
				options.addArguments("disable-infobars");
				web_driver = new ChromeDriver(options);
				web_driver.get(config.getProperty("ProdURL"));
				web_driver.manage().window().maximize();
						
			} else if (config.getProperty("Browser").equalsIgnoreCase("IE")) {
				
				System.setProperty("webdriver.ie.driver","");
				web_driver = new InternetExplorerDriver();
		 
				web_driver.get(config.getProperty("ProdURL"));
				web_driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				web_driver.manage().window().maximize();
			}
			return web_driver;
		}
	
	
	public void startAppiumServer() {
		runtime = Runtime.getRuntime();
		try {
		    runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
		    Thread.sleep(10000);
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
	
	public void emualtorBatFile() throws IOException
	{
		
		Process process = Runtime.getRuntime().exec("cmd /c Android.bat", null, new File("C:\\Users\\rangarajan\\Documents\\WPT_Automation\\CloudLibrary_DC\\src\\main\\java\\Helper\\"));
		
	}

}
