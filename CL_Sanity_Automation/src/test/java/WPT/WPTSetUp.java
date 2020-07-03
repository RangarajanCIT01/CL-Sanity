package WPT;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Helper.Efficacies;
import Helper.EmailReporter;
import Helper.ExtentReportListener;
import Helper.MailReport;
import Helper.StartApp;
import WebPages.WPTBasePage;
import WebPages.WPTSanityPage;

@Listeners(EmailReporter.class)

public class WPTSetUp {

	public Efficacies eff;
	public WPTSanityPage sanityPageWeb;
	public WPTBasePage basePageWeb;
	public WebDriver web_driver;
	public StartApp startApp;
	
	public ExtentReports extent;
    
	
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		startApp = new StartApp();
		eff = new Efficacies();
		web_driver = startApp.startApp_Web();
		sanityPageWeb = new WPTSanityPage(web_driver);
		basePageWeb = new WPTBasePage(web_driver);
		extent = new ExtentReports();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		startApp.closeDriver_Web();
	}

}
