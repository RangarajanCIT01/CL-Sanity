package WPT;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import Helper.Efficacies;
import Helper.EmailReporter;
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
	
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		startApp = new StartApp();
		eff = new Efficacies();
		web_driver = startApp.startApp_Web();
		sanityPageWeb = new WPTSanityPage(web_driver);
		basePageWeb = new WPTBasePage(web_driver);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		startApp.closeDriver_Web();
	}

}
