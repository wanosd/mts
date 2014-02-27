package email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;

public class Email implements I_Message {


	private static Logger logger = Logger.getLogger(Email.class);

	private static MimeMessage message;

	private static MimeMessageHelper helper;

	public Email(JavaMailSender mailSender) {
		if (mailSender == null){
			logger.info("MAIL SENDER NULL");
		}
		message = mailSender.createMimeMessage();
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean send(JavaMailSender mailSender) {
		logger.info("About to send");
		mailSender.send(message);
		logger.info("Sent");
		return true;

	}

	public void set(String from, String to, String subject, String text,
			File file) {
		logger.info("MIME Message created");

		logger.info("MIME Helper Okay");
		try {
			helper.setFrom(from);

			logger.info("Set From");
			helper.setTo(to);
			logger.info("Set To");
			helper.setSubject(subject);
			logger.info("Set Sub");
			helper.setText(text);

			if (file != null) {
				helper.addAttachment(file.getName(), file);
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Set File");
		logger.info("Finished putting message together");
	}
}