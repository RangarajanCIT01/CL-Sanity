package Helper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.getInstance();
	static Properties prop;
	
    static String bodyHeader = "<p>Hi Team,</p><body>Please find the Automation execution for the Cloudlibrary Sanity tests :</body>";	 
	static String bodyFooter = "<p>Thanks!</p><img src=\"https://www.bibliotheca.com/wp-content/uploads/2018/11/G-LBLO1017_2017_bibliotheca_long-term_logo_3000px-e1576140191624-180x44.png\" alt=\"logo\" width=\"250\" height=\"50\">";
	
	public static String emailBody() throws Exception {
		
		prop = new Efficacies().loadPropertiesFromResources("config.properties");
		
		String a = "<center><head>\r\n" + 
				"<style>\r\n" + 
				"table, th, td {\r\n" + 
				"  border: 1px solid black;\r\n" + 
				"}\r\n" + 
				"</style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"<table style=\"background-color:#86C5EE;\">\r\n" + 
				"  <tr>\r\n" + 
				"  <th colspan=\"2\"><b>Environment Details</b></th>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td><b>Server : </b></td>\r\n" + 
				"    <td>PROD</td>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td><b>Browser : </b></td>\r\n" + 
				"    <td>Chrome</td>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td><b>Android OS : </b></td>\r\n" + 
				"    <td>9.0</td>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td><b>Android Build : </b></td>\r\n" + 
				"    <td>5.1.0.9</td>\r\n" + 
				"  </tr>\r\n" + 
				"  <tr>\r\n" + 
				"    <td><b>WPT Build : </b></td>\r\n" + 
				"    <td>3.9.20.1</td>\r\n" + 
				"  </tr>\r\n" + 
				"</table>\r\n" + 
				"\r\n" + 
				"</body></center>";
	
		return  a;
	}

	public static synchronized ExtentTest getTest() {
		
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}
	
	public static synchronized void endTest() throws Exception {
		extent.flush();
		System.out.println("On Generate Report");
		
		prop = new Efficacies().loadPropertiesFromResources("config.properties");
		String[] to = prop.getProperty("emailTo").split(";");
      	
		sendMail(prop.getProperty("emailFrom"), prop.getProperty("emailUserPassword"), to, prop.getProperty("heading"));
	}

	public static synchronized ExtentTest startTest(String testName) {
		 File directory = new File(System.getProperty("user.dir")+ "/test-output");
		 recursiveDelete(directory);
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
	
	public static String readfile(String filePath){    
		String strLine = "";
		try{
	        FileInputStream fstream = new FileInputStream(filePath);
	        
	        DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        String line;
	        while ((line = br.readLine()) != null)   {
	        	strLine += line;
	        }
	        in.close();
	        
	    }catch (Exception e){//Catch exception if any
	        System.err.println("Error: " + e.getMessage());
	    }
		return strLine;
	}

	/**
	 * 
	 * @param from
	 * @param pass
	 * @param to
	 * @param subject
	 * @throws Exception
	 */
	private static void sendMail(String from, String pass, String[] to, String subject) throws Exception {
		
		emailBody();
		
		checkFileExists("WPT Sanity Check.html");

		String body = readfile(System.getProperty("user.dir")+"\\test-output\\CloudLibrary Sanity Suite\\WPT Sanity Check.html");
		Properties props = System.getProperties();
	    String host = "smtp.gmail.com";
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.user", from);
	    props.put("mail.smtp.password", pass);
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);
	    BodyPart MessageAttachment= new MimeBodyPart();
	    MessageAttachment = new MimeBodyPart();
	   
	    try {
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
	        Multipart multipart = new MimeMultipart();
	        	        
			DataSource srcEx=new FileDataSource("./TestReport/Test-Automaton-Report.html");
			MessageAttachment.setDataHandler(new DataHandler(srcEx));
			MessageAttachment.setFileName("SanityCheck.html");
			multipart.addBodyPart(MessageAttachment);
			
			 BodyPart msgBodyReport = new MimeBodyPart(); 
			 msgBodyReport.setContent(bodyHeader + emailBody() + body + bodyFooter, "text/html"); 
			 multipart.addBodyPart(msgBodyReport);
			 		        
	        message.setContent(multipart);

	        Transport transport = session.getTransport("smtp");
	        transport.connect(host, 587, from, pass);
	        transport.sendMessage(message, message.getAllRecipients());
	        System.out.println("Email Sent!");
	        transport.close();
	        
	    }
	    catch (AddressException ae) {
	        ae.printStackTrace();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();
	    }	
	}
	
	/**
	 * Delete test-output folder before start
	 * @param file
	 */
	
	public static void recursiveDelete(File file) {
	     // to end the recursive loop
	     if (!file.exists())
	         return;
	     
	     // if directory, go inside and call recursively
	     if (file.isDirectory()) {
	         for (File f : file.listFiles()) {
	             // call recursively
	             recursiveDelete(f);
	         }
	     }
	     
	     // delete files and empty directory
	     file.delete();
	     System.out.println("Deleted file/folder: "+file.getAbsolutePath());
	 }
	
	 /**
	 * Check File exists or not
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	
	public static boolean checkFileExists(String fileName) {
		boolean checkCond = false;
		String file = System.getProperty("user.dir") + File.separator + "test-output" + File.separator 
				+ "CloudLibrary Sanity Suite" + File.separator + fileName;
		
		Instant start = Instant.now();
		do {
			Instant now = Instant.now();
			if (Duration.between(start, now).toMillis() >= 15) {
				break;
			}
			
			if (new File(file).exists()) {
				checkCond = true;
				break;
			} 
			
		} while(checkCond==false);
		System.out.println("File Exists.." + file);
		return checkCond;
	}
}





