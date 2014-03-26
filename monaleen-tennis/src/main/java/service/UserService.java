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
	
	@Secured({"ROLE_ADMIN", "ROLE_MEMBER", "ROLE_COMMITTEE", "ROLE_WARNING", "ROLE_SUSPEND"})
	public List<User> getCurrentMembers(){
		return usersDAO.getUsers();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public List<User> getPendingMembers(){
		return usersDAO.getPendingUsers();
	}

	public void create(User user) {
		usersDAO.createUser(user);
	}

	public boolean exists(String username) {
		return usersDAO.exists(username);
	}
	
	public User getUserByUsername(String username){
		return usersDAO.getUserByUserName(username);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void enableUser(String username){
		usersDAO.enableMember(username);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void disableUser(String username){
		usersDAO.disableMember(username);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_MEMBER", "ROLE_COMMITTEE", "ROLE_WARNING", "ROLE_SUSPEND"})
	public void editProfile(User formUser, String username){
		usersDAO.changeUserDetails(formUser, username);
	}
	
	public String emailToName(String email){
		return getUserByUsername(email).getName();
	}
	
	public List<User> getAdmins(){
		return usersDAO.getAdmins();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void createGrade(String name){
		usersDAO.createGrade(name);
	}

	public List<User> getCommittee() {
		return usersDAO.getCommittee();
	}
	
	public String getNameFromEmail(String email){
		return getUserByUsername(email).getName();
	}

	public User getUserByID(int id) {
		return usersDAO.getUserByID(id);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public User getUserByName(String username){
		return usersDAO.getUserByName(username);
	}
	
	public void updateUser(User user){
		usersDAO.update(user);
	}

	public void delete(User user) {
		usersDAO.delete(user);
		
	}
	
	public void createJDBC(User user){
		usersDAO.createUserJDBC(user);
	}
	
	public void enableUserJDBC(User user){
		usersDAO.enableUserJDBC(true, user.getUsername());
	}
	
	public void deleteUserJDBC(User user){
		usersDAO.deleteUserJDBC(user);
	}

	public List<User> getAllMembersJDBC() {
		return usersDAO.getAllUsersJDBC();
	}
	

	

	
}
