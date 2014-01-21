package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
	
	@Secured({"ROLE_ADMIN", "ROLE_MEMBER"})
	public List<User> getCurrentMembers(){
		return usersDAO.getUsers();
	}
	
	@Secured("ROLE_ADMIN")
	public List<User> getPendingMembers(){
		return usersDAO.getPendingUsers();
	}

	public void create(Member member) {
		usersDAO.createUser(member);
	}

	public boolean exists(String username) {
		return usersDAO.exists(username);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_MEMBER"})
	public User getUserByUsername(String username){
		return usersDAO.getUserByUserName(username);
	}
	

	

	
}
