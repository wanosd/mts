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
public class UserDAOTests {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");
	}
	
	@Test
	public void testCreateUser(){
		User user = new User();
		user.setUsername("testuser@test.com");
		user.setName("test");
		user.setPassword("password");
		user.setGender("M");
		user.setMember_type("Senior");
		user.setGrade("Beginner");
		user.setAd_line1("12345");
		user.setAd_line2("12345");
		user.setAd_city("12345");
		user.setAd_county("12345");
		user.setContact_num("123456789");
		user.setEm_con_name("Mike");
		user.setEm_con_num("12345");
		user.setEnabled(true);
		assertTrue("User Created", userDAO.createUser(user));
		List<User> users = userDAO.getAllUsers();
		assertEquals("Number of users should be one", 1, users.size());
	}
	

}
 