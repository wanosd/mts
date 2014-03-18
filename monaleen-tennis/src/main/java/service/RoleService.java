package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void create(Role role){
		roleDAO.createRole(role);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public boolean exists(String name){
		return roleDAO.exists(name);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void delete(Role role){
		roleDAO.delete(role);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
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
 
	public Role getRole(String id) {
		return roleDAO.getRole(id);
	}
	
	public List<String> defaultRoles(){
		return roleDAO.defaultRoles();
	}
	
	
}
