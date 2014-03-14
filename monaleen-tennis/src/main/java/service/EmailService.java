package service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import email.I_Message;

@Service("emailService")
public class EmailService {

	public boolean sendMessage(I_Message message, JavaMailSender mailSender){
		return message.send(mailSender);
	}
	
	public boolean sendMessageFile(I_Message message, JavaMailSender mailSender){
		return message.send(mailSender);
	}
	
	
	
	

}
