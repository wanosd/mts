package testDeleteAfter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import users.*;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml"); 

		User member = (Member) context.getBean("member");
		member.setName("Chris");
		System.out.println(member.toString());
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
