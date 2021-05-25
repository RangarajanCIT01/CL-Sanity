package WPT;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import Helper.Efficacies;
import Helper.ExtentTestManager;
import Helper.StartApp;
import WebPages.WPTBasePage;
import WebPages.WPTSanityPage;

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

		Map<String, String> testData = eff.readJsonElement("SetUp_Web.json", "baseData");

		basePageWeb.getElement(sanityPageWeb.btnLogin);

		basePageWeb.clickElement(sanityPageWeb.btnLogin);
		basePageWeb.enterText(sanityPageWeb.txtLibID, testData.get("LibraryID"));
		basePageWeb.clickElement(sanityPageWeb.btnLoginPopup);

		basePageWeb.getElement(sanityPageWeb.btnAccept);

		basePageWeb.clickElement(sanityPageWeb.btnAccept);
	}

	
	  @AfterClass(alwaysRun = true) public void tearDown() throws Exception { //
	  ExtentTestManager.getTest().info("Close the web driver...");
	  startApp.closeDriver_Web(); }
	 

}
