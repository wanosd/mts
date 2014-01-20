package test.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "file:src/main/java/beans/dao-context.xml",
		"file:src/main/java/beans/security-context.xml",
		"classpath:test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOTests {
	
	@Test
	public void testCreateUser(){
		assertEquals("Dummy Test",1,1);
	}

}
