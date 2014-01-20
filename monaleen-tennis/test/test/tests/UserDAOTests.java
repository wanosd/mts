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

import users.Member;
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
		User user = new Member();
		user.setUsername("testuser@test.com");
		user.setPassword("password");
		user.setEnabled(true);
		assertTrue("User Created", userDAO.createUser(user));
		
		List<User> users = userDAO.getAllUsers();
		System.out.println("TEST123" + users.size());
		assertEquals("Number of users should be one", 1, users.size());
		assertTrue("User should exist", userDAO.exists(user.getUsername()));
	}

}
 