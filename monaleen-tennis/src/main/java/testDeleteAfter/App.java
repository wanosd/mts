package testDeleteAfter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import users.*;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("beans.xml"); 

		User user = (Member) context.getBean("member");
		user.toString();
	}

}
