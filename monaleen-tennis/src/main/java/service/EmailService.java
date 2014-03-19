package service;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import controllers.MembersController;
import users.User;
import email.I_Message;

@Service("emailService")
public class EmailService {

	private static Logger logger = Logger.getLogger(EmailService.class);
	
	public boolean sendMessage(I_Message message, JavaMailSender mailSender, String from, String to, String subject, String text){
		message.set(mailSender, from, to, subject, text, null);
		return message.send(mailSender);
	}
	
	public boolean sendMessageFile(I_Message message, JavaMailSender mailSender,String from, String to, String subject, String text, File file){
		message.set(mailSender, from , to, subject, text, file);
		return message.send(mailSender);
	}
	
	public boolean sendClubMessage(List<User> users, I_Message message, JavaMailSender mailSender, String subject, String text){
		boolean allSent = true;
		logger.info("Send Club Message Users: " + users);
		if (message == null){
			logger.info("Message is null");
		}
		
		if (mailSender == null){
			logger.info("MailSender is null");
		}
		
		logger.info("Subject is " + subject + ". Text is " + text);
		
		for (int i = 0; i < users.size(); i ++){
			message.set(mailSender, "admin@monaleengaatennisclub.ie", users.get(i).getUsername(), subject, text, null);
			allSent = message.send(mailSender);
			}
		return allSent;
	}
	
}