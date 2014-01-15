package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import users.*;
import dao.UserDAO;

@Service("userService")
public class UserService {
	
	private UserDAO usersDAO;
	
	@Autowired
	public void setUsersDAO(UserDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	
	public List<User> getCurrentMembers(){
		return usersDAO.getUsers();
	}

	public void create(Member member) {
		usersDAO.createUser(member);
		
	}

	public void throwTestException() {
		usersDAO.getUserByName("tommy");
		
	}

	public boolean exists(String username) {
		return usersDAO.exists(username);
	}

	

	
}
