package Helper;


import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class MailReport implements IReporter{
	
	public void mailReporter() throws MessagingException {
	
	final String username = "automationcloudlibrary@gmail.com"; //change to your Gmail username
    final String password = "Cloudlibrary@2020"; //change to your Gmail password
    final String from = "automationcloudlibrary@gmail.com"; //change to from email address
    final String to = "rangarajan@congruentindia.com"; //change to to email address
    final String subject = "Test Email from Hello Selenium"; //change to your subject
    final String msg = "Test Email from Hello Selenium to learn the automation of email message sending using Java Mail API from Gmail."; //change to your message
 
    Properties props = System.getProperties();
    String host = "MBSSupercop.cit.congruentindia.com";
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", password);
    props.put("mail.smtp.port", "25");
    props.put("mail.smtp.auth", "true");
    
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
 
    
 
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
//        //below code only requires if your want cc email address
//        message.setRecipients(Message.RecipientType.CC,
//                InternetAddress.parse(cc));
//        //below code only requires if your want bcc email address
//        message.setRecipients(Message.RecipientType.BCC,
//                InternetAddress.parse(bcc));
        message.setSubject(subject);
        message.setText(msg);
 
        MimeBodyPart messageBodyPart = new MimeBodyPart();
 
        Multipart multipart = new MimeMultipart();
 
        messageBodyPart = new MimeBodyPart();
        String file1 = "D:\\GitHub Repo\\CloudLibrary_Sanity\\MobileApps\\test-output\\"; //change to your attachment filepath
        String fileName1 = "AutomationTestReport.html"; //change to your attachment filename
        DataSource source1 = new FileDataSource(file1);
        messageBodyPart.setDataHandler(new DataHandler(source1));
        messageBodyPart.setFileName(fileName1);
        multipart.addBodyPart(messageBodyPart);
 
        message.setContent(multipart);
 
        System.out.println("Sending");
 
        Transport.send(message);
 
        System.out.println("Done");
 
    }
    
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// TODO Auto-generated method stub
		
	}
	
  }
