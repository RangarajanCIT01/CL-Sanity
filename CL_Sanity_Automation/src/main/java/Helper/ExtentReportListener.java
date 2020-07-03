package Helper;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

public class ExtentReportListener implements IReporter, ITestListener, Serializable {
 public String OUTPUT_FOLDER;
 public String FILE_NAME;
 public ExtentReports extent;
 public String testNgFile;
 public String browser;
 public String description;
 public static String filename;
 static String propertyFilePath= "./PropertyFiles/config.properties";
 Properties prop = new Properties();
 
 public ExtentHtmlReporter htmlReporter;
 public ExtentTest logger;
 
 EmailReporter emailReport = new EmailReporter();
 
 public ExtentReportListener() throws IOException {
     this.OUTPUT_FOLDER = "TEST-OUTPUT" + File.separator;
     this.FILE_NAME = "AutomationTestReport";
     this.browser = null;
     
	 prop = new Efficacies().loadPropertiesFromResources("config.properties");

	 File directory = new File(this.OUTPUT_FOLDER);
	 recursiveDelete(directory);
     
 }

 public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
	 this.init();
     Iterator var4 = suites.iterator();

     while(var4.hasNext()) {
         ISuite suite = (ISuite)var4.next();
         Map<String, ISuiteResult> result = suite.getResults();
         Iterator var7 = result.values().iterator();

         while(var7.hasNext()) {
             ISuiteResult r = (ISuiteResult)var7.next();
             ITestContext context = r.getTestContext();
             this.buildTestNodes(context.getFailedTests(), Status.FAIL);
             this.buildTestNodes(context.getSkippedTests(), Status.SKIP);
             this.buildTestNodes(context.getPassedTests(), Status.PASS);
         }
     }

     var4 = Reporter.getOutput().iterator();

     while(var4.hasNext()) {
         String s = (String)var4.next();
         this.extent.setTestRunnerOutput(s);
     }

   
     this.extent.flush();
//     new File(this.OUTPUT_FOLDER + this.FILE_NAME + ".html");
 }

 private void init() {
     File directory = new File(this.OUTPUT_FOLDER);
     if (!directory.exists()) {
         directory.mkdir();
     }
       
     ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(this.OUTPUT_FOLDER + this.FILE_NAME + ".html");
     htmlReporter.config().setDocumentTitle("CloudLibrary"); 
	 htmlReporter.config().setReportName("CloudLibrary Automation Report");
     htmlReporter.config().setTheme(Theme.DARK);
     htmlReporter.config().enableTimeline(false);
     
     this.extent = new ExtentReports();
     this.extent.attachReporter(new ExtentReporter[]{htmlReporter});
     this.extent.setReportUsesManualConfiguration(true);

      this.extent.setSystemInfo("Environment", prop.getProperty("Environment"));
	  this.extent.setSystemInfo("Browser", prop.getProperty("Browser"));
	  this.extent.setSystemInfo("WPT_AppVer", prop.getProperty("WPT_AppVer"));
	  this.extent.setSystemInfo("Android_AppVer", prop.getProperty("Android_AppVer"));
	  this.extent.setSystemInfo("Android_OS_Ver", prop.getProperty("Android_OS_Ver"));
	  this.extent.setSystemInfo("WPT_AppVer", prop.getProperty("WPT_AppVer"));
 }

 private void buildTestNodes(IResultMap tests, Status status) {
     ExceptionFormatter exceptionFormatter = new ExceptionFormatter();
     if (tests.size() > 0) {
         Iterator var5 = tests.getAllResults().iterator();

         while(var5.hasNext()) {
             ITestResult result = (ITestResult)var5.next();
             ExtentTest test = this.extent.createTest(result.getMethod().getMethodName()).assignCategory(new String[]
            		 { result.getTestClass().getName() + " --> " + result.getMethod().getMethodName()});
             if (result.getMethod().getDescription() != null) {
                 test.getModel().setDescription(result.getMethod().getDescription());
             }

             test.getModel().setStartTime(this.getTime(result.getStartMillis()));
             test.getModel().setEndTime(this.getTime(result.getEndMillis()));
             String[] var7 = result.getMethod().getGroups();
             int i = var7.length;

             for(int var9 = 0; var9 < i; ++var9) {
                 String group = var7[var9];
                 test.assignCategory(new String[]{group});
             }

             if (result.getThrowable() == null) {
                 if (result.getStatus() == 3 && result.getTestContext().getFailedConfigurations().size() > 0) {
                     IResultMap iresultmap = result.getTestContext().getFailedConfigurations();
                     test.log(status, "<br>Failure is in the method --> " + result.getTestClass().getName() + "." + ((ITestNGMethod)iresultmap.getAllMethods().iterator().next()).getMethodName() + "<br>" + iresultmap.toString());
                 }

                 if (result.getStatus() == 1) {
                     test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                 }
             } else if (result.getStatus() != 3) {
                 if (result.getStatus() == 2) {
                     test.log(status, exceptionFormatter.toHtml(exceptionFormatter.format(result.getThrowable())));
                 }
             } else if (result.getTestContext().getFailedConfigurations().size() > 0) {
                 Boolean applicationIssue = false;

                 try {
                     if (result.getThrowable().getMessage().contains("Application Issue")) {
                         applicationIssue = true;
                     }
                 } catch (Exception var11) {
                     applicationIssue = false;
                 }

                 if (applicationIssue) {
                     test.log(Status.ERROR, "Retrying Failure! <br> " + exceptionFormatter.toHtml(exceptionFormatter.format(result.getThrowable())));
                 } else {
                     IResultMap iresultmap = result.getTestContext().getFailedConfigurations();
                     test.log(status, "<br>Failure is in the method --> " + result.getTestClass().getName() + "." + ((ITestNGMethod)iresultmap.getAllMethods().iterator().next()).getMethodName() + "<br>" + exceptionFormatter.toHtml(exceptionFormatter.format(((ITestResult)iresultmap.getAllResults().iterator().next()).getThrowable())));
                 }
             } else if (result.getThrowable().getMessage().contains("Application Issue")) {
                 test.log(Status.ERROR, "Retrying Failure! <br> " + exceptionFormatter.toHtml(exceptionFormatter.format(result.getThrowable())));
             } else if (result.getThrowable().getMessage().contains("depends on not successfully finished methods")) {
                 String methodsDependedOn = "";

                 for(i = 0; i < result.getMethod().getMethodsDependedUpon().length; ++i) {
                     methodsDependedOn = methodsDependedOn + result.getMethod().getMethodsDependedUpon()[i] + "<br>";
                 }

                 test.log(status, "Method Dependency Failure! <br>Possible test failures are: <br>" + methodsDependedOn + "<br>" + exceptionFormatter.toHtml(exceptionFormatter.format(result.getThrowable())));
             }

             test.getModel().setStartTime(this.getTime(result.getStartMillis()));
             test.getModel().setEndTime(this.getTime(result.getEndMillis()));
         }
     }

 }

 public String getTestClassName(String testName) {
     String[] reqTestClassname = testName.split("\\.");
     int i = reqTestClassname.length - 1;
     return reqTestClassname[i];
 }

 private Date getTime(long millis) {
     Calendar calendar = Calendar.getInstance();
     calendar.setTimeInMillis(millis);
     return calendar.getTime();
 }

 public void onTestStart(ITestResult result) {
	 
 }

 public void onTestSuccess(ITestResult result) {
 }

 public void onTestFailure(ITestResult result) {
     try {
         if (result.getThrowable() != null && result.getThrowable().getMessage().contains("Exception:") && result.getThrowable().getMessage().contains("Detailed Trace:")) {
             String error = result.getThrowable().getMessage().substring(0, result.getThrowable().getMessage().indexOf("Detailed Trace:"));
             String name = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
             Reporter.log("<span id =\"" + name + "\">" + error + "</span><br/>");
         }
     } catch (NullPointerException var4) {
     }

 }

 public void onTestSkipped(ITestResult result) {
 }

 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
 }

 public void onStart(ITestContext context) {
 }

 public void onFinish(ITestContext context) {
	  

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


}
