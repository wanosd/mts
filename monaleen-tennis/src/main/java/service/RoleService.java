package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import users.Role;
import dao.RoleDAO;

@Service("roleService")
public class RoleService {

	private RoleDAO roleDAO;
	
	@Autowired
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
	public void create(Role role){
		roleDAO.createRole(role);
	}
	
	public boolean exists(String name){
		return roleDAO.exists(name);
	}
	
	public void delete(Role role){
		roleDAO.delete(role);
	}
	
	public void update(Role role){
		roleDAO.update(role);
	}

	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}
	
	public List<String> getRoleNames(){
		return roleDAO.getRolesNames();
	}
	
	public int getNoBookings(String role){
		return roleDAO.noBookings(role);
	}
	
	
}
