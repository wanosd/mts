package email;

import java.io.File;

import org.springframework.mail.javamail.JavaMailSender;

public interface I_Message {

	public boolean send(JavaMailSender mailSender);
	
	public void set(String from, String to, String subject, String text, File file) ;
}
