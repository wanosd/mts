package testDeleteAfter;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import dao.UserDAO;
import users.*;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml"); 

		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		
		try{
			List<User> users = userDAO.getUsers();
			//User user = userDAO.getUserByName("Chris");
		
		for (User user : users){
			System.out.println("WORKING!!!" + user);
		//	userDAO.changeUserEmailAddress("chrisobri@gmail.com", user.getName());
		}
		}catch (CannotGetJdbcConnectionException ex){
			System.out.println("Cannot get connection");
			
		}catch (DataAccessException e){
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
		
		
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
