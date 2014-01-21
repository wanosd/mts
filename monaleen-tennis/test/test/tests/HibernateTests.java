package test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import users.User;
import dao.UserDAO;


@ActiveProfiles("dev")
@ContextConfiguration(locations = { "file:src/main/java/beans/dao-context.xml",
		"file:src/main/java/beans/security-context.xml",
		"classpath:test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class HibernateTests {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private DataSource dataSource;
	
	private User user1 = new User("chris@email.com", "Chris", "password", "M", "Student", "Beginner", "123 Fake St", "123 Fake St", "Faketown", "Fakecity", "0857040183", "Michelle", "085123", true, "ROLE_ADMIN");
	private User user2 = new User("michelle@email.com", "Michelle", "1password", "F", "Junior", "Beginner", "23 Fake St", "23 Fake St", "Faketown", "Fakecity", "0857040183", "Chris", "085123", false, "ROLE_MEMBER");
	private User user3 = new User("frank@email.com", "Frank", "2password", "M", "Senior", "Graded", "1 Fake St", "1 Fake St", "Faketown", "Fakecity", "0857040183", "Michelle", "085123", true, "ROLE_MEMBER");
	private User user4 = new User("tom@email.com", "Tom", "3password", "M", "Senior", "Intermediate", "12 Fake St", "12 Fake St", "Faketown", "Fakecity", "0857040183", "Michelle", "085123", true, "ROLE_MEMBER");

	
	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(dataSource); // need to replace
		jdbc.execute("delete from users"); //need to replace
		jdbc.execute("delete from authorities"); // need to replace
	}
	
	@Test
	public void testCreateRetrieve(){
		userDAO.createUser(user1);
		
		List<User> users1 = userDAO.getAllUsers();
		assertEquals("One user should have been created and retrieved", 1, users1.size());
		assertEquals("Inserted user should match retrieved", user1, users1.get(0));
		
		userDAO.createUser(user2);
		userDAO.createUser(user3);
		userDAO.createUser(user4);
		
		List<User> users2 = userDAO.getAllUsers();
		assertEquals("Number of users should be four", 4, users2.size());
	}
	
	

}
 