package Helper;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
public class EmailReporter implements IReporter{
	static String suite_name = "";
	static String file_name = "";
	static String propertyFilePath= "./PropertyFiles/config.properties";
	static Properties prop = new Properties();
	static BufferedReader reader;
	
	public static String failedXMLTest;
	public static String failedXMLClass;
	
	public static List<String> failedXMLMethodName = new ArrayList<String>();
	public static Map<String,List<String>> failedXMLMethods=new HashMap<String, List<String>>();
	
    public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1, String outputDirectory) {
    	try {
			reader =new BufferedReader(new FileReader(propertyFilePath));
			prop.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}  	
		
    	for (ISuite iSuite : arg1) {
        	Map<String,ISuiteResult> results = iSuite.getResults();
        	suite_name = iSuite.getName();
        	Set<String> keys = results.keySet();
        			for (String key : keys) {
        			ITestContext context = results.get(key).getTestContext();
        			file_name = context.getName();
        			
        			System.out.println("Suite Name->"+context.getName()
        					+"\n::Report output Ditectory->"+context.getOutputDirectory()
        					+"\n::Suite Name->"+ context.getSuite().getName()
        					+"\n::Start Date Time for execution->"+context.getStartDate()
        					+"\n::End Date Time for execution->"+context.getEndDate());
        			
        			IResultMap resultMapPassed = context.getPassedTests();
        			Collection<ITestNGMethod> passedMethods = resultMapPassed.getAllMethods();
        			System.out.println("\n\n========PASSED TEST CASE========");
        			for (ITestNGMethod iTestNGMethod : passedMethods) {
        				System.out.println("--------"+iTestNGMethod.getMethodName()+"--------"
        						+"\nDescription->"+iTestNGMethod.getDescription()
        						+"\nPriority->"+iTestNGMethod.getPriority()
        						+"\n:Date->"+new Date(iTestNGMethod.getDate()));
        			}
        			
        			IResultMap resultMap = context.getFailedTests();
        			Collection<ITestNGMethod> failedMethods = resultMap.getAllMethods();
        			System.out.println("\n\n=========FAILED TEST CASE=========");
        			for (ITestNGMethod iTestNGMethod : failedMethods) {
        				System.out.println("--------"+iTestNGMethod.getXmlTest().getName()+"-----");
        				failedXMLTest=iTestNGMethod.getXmlTest().getName();
        				System.out.println("Test Name: "+failedXMLTest);
        				
        				System.out.println("--------"+iTestNGMethod.getTestClass()+"-----");
        				failedXMLClass=iTestNGMethod.getTestClass().getName();
        				System.out.println("class Name: "+failedXMLClass);
        				
        				System.out.println("--------"+iTestNGMethod.getMethodName()+"--------");
        				String methodName=iTestNGMethod.getMethodName();
        				failedXMLMethodName.add(methodName);
        				
        				System.out.println("----Description----"+iTestNGMethod.getDescription()+"--------"
        						+"\nPriority->"+iTestNGMethod.getPriority()
        						+"\n:Date->"+new Date(iTestNGMethod.getDate()));
        			}
        			
        			failedXMLMethods.put(failedXMLClass,failedXMLMethodName);
//        			runTestNGTest(failedXMLMethods);
        		}
        }
    	
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	String[] emailTo = prop.getProperty("emailTo").split(",");
		
//    	if(prop.getProperty("emailNotification").equalsIgnoreCase("Yes"))
    	try {
			sendMail(prop.getProperty("emailFrom"),prop.getProperty("emailUserPassword"),emailTo,"CloudLibrary Sanity Check report");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    public static String readfile(String filePath){    
    	String strLine = "";
    	try {
    		
            FileInputStream fstream = new FileInputStream(filePath);
            
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null)   {
            	strLine += line;
            }
            in.close();
            
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    	System.out.println(strLine);
    	return strLine;
    }
    
    
    /**
	 * Check File exists or not
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static boolean checkFileExists(String fileName) {
		boolean checkCond = false;
//		String file = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + fileName;
		
		Instant start = Instant.now();
		do {
			Instant now = Instant.now();
			if (Duration.between(start, now).toMillis() >= 25) {
				break;
			}
			if (new File(fileName).exists()) {
				checkCond = true;
				break;
			} 
			
		} while(checkCond==false);
		System.out.println("File Exists.." + fileName);
		return checkCond;
	}
	
  
	public static void sendMail(String from, String pass, String[] to, String subject) throws InterruptedException {
		
//		checkFileExists("emailable-report.html");

//		String file = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "emailable-report.html";
		String body = readfile("./test-output/"+suite_name+"/"+file_name+".html");
//		String body = readfile(file);
        	
    	Properties props = System.getProperties();
        String host = "MBSSupercop.cit.congruentindia.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
   
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        BodyPart MessageAttachment= new MimeBodyPart();
//        MessageAttachment = new MimeBodyPart();
       
        try {
        	String file = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "AutomationTestReport.html";
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            
            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setContent(body, "text/html");
            
    		checkFileExists(file);
            
            DataSource srcEx=new FileDataSource(file);
            MessageAttachment.setDataHandler(new DataHandler(srcEx));
            MessageAttachment.setFileName(file);
                        
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
//            multipart.addBodyPart(MessageAttachment);
          
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

	
	public void runTestNGTest(Map<String,List<String>> failedMethods) {
		
		System.out.println("--------Failure Retry---------");
		 
	     TestNG myTestNG = new TestNG();
	     List<String> methodNames;
		 String className;
	    
	     XmlSuite mySuite = new XmlSuite();
	     mySuite.setName("ReRun Suite");
	   
	     XmlTest myTest = new XmlTest(mySuite);
	     myTest.setName("FailedTest");
	    
	     List<XmlClass> myClasses = new ArrayList<XmlClass> ();
	     List<XmlInclude> myMethods= new ArrayList<XmlInclude>();

	     for (Map.Entry<String, List<String>> entry : failedMethods.entrySet()) {
	    	 className=entry.getKey();
	    	 methodNames=entry.getValue();
	    	 
	    	 for (String method : methodNames) {
	    		 myMethods.add(new XmlInclude(method));
	    	 }
	    	 
	    	 XmlClass c1 = new XmlClass(className);
	    	 c1.setIncludedMethods(myMethods);
	    	 myClasses.add(c1);
	     }
	     
	     //Assign that to the XmlTest Object created earlier.
	     myTest.setXmlClasses(myClasses);

	    //Create a list of XmlTests and add the Xmltest you created earlier to it.
	     List<XmlTest> myTests = new ArrayList<XmlTest>();
	     myTests.add(myTest);

	    //add the list of tests to your Suite.
	     mySuite.setTests(myTests);

	    //Add the suite to the list of suites.
	     List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
	     mySuites.add(mySuite);

	    //Set the list of Suites to the testNG object you created earlier.
	     myTestNG.setXmlSuites(mySuites);
	     
	    //invoke run() - this will run your class.
	     myTestNG.run();
	}
	
	public static File getLatestReport()
	{
	    File directory = new File("./test-output");
	    File[] files = directory.listFiles(File::isFile);
	    long lastModifiedTime = Long.MIN_VALUE;
	    File chosenFile = null;

	    if (files != null)
	    {
	        for (File file : files)
	        {
	            if (file.lastModified() > lastModifiedTime && file.getName().startsWith("AutomationTestReport"))
	            {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }

	    return chosenFile;
	}
 	
}
