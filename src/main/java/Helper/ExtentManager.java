package Helper;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    private static String reportFileName = "Test-Automaton-Report"+".html";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "TestReport";
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;
  
 
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
 
    //Create an extent report instance
    public static ExtentReports createInstance() {
        String fileName = getReportPath(reportFilepath);
        

               
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
//      Initialize report
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        // Set environment details
        try {
			Properties prop = new Efficacies().loadPropertiesFromResources("config.properties");
			extent.setSystemInfo("Server", prop.getProperty("Environment"));
			extent.setSystemInfo("Browser", prop.getProperty("Browser"));
			extent.setSystemInfo("WPT Build ", prop.getProperty("WPT_AppVer"));
			extent.setSystemInfo("Android Build", prop.getProperty("Android_AppVer"));
			extent.setSystemInfo("Android OS", prop.getProperty("Android_OS_Ver"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("CloudLibrary Automation");
        htmlReporter.config().setReportName("CloudLibrary Automation");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		 
        return extent;
    }
     
    //Create the report path
    private static String getReportPath (String path) {
    	File testDirectory = new File(path);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
		return reportFileLocation;
    }

 
}
