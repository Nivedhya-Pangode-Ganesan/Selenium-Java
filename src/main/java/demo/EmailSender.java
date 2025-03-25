package demo;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailSender {

	public static void main(String[] args) {
		final String senderEmail = "nivedhyatest@gmail.com";
		final String appPassword = "mlgzlwjeunfzygke";
		final String recipientEmail = "nivedhyatest@gmail.com";

		// SMTP Server Properties
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.port", "587");

		// Create session with Authentication
		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, appPassword);
			}
		});

		session.setDebug(true);

		try {

			// Create Email Message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Test Email From QA Automation");
//			message.setText("Hello \nThis is a Test Report Email from Java Automation\n\nRegards,\nNivedhya");

			// Email Body Part
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setText("Hello \nThis is a Test Report Email from Java Automation\n\nRegards,\nNivedhya");
			
			// Attachment Part
			MimeBodyPart attachmentPart = new MimeBodyPart();
			String filePath = System.getProperty("user.dir")+"/reports/ExtentReport.html";
			System.out.println("Attachment Path is - "+filePath);
			attachmentPart.attachFile(new File(filePath));
			
			// Combine body and attachment parts
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(textPart);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);
			
			// Send Email
			Transport.send(message);
			System.out.println("Email sent successfully ***");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
