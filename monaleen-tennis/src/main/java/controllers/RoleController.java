package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import service.RoleService;
import users.Role;

@Controller
public class RoleController {
	
	private static Logger logger = Logger.getLogger(RoleController.class);
	
	private RoleService roleService;

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@RequestMapping("/updateRole")
	public String updateRole(HttpServletRequest request, Model model){
		Role role = roleService.getRole(request.getParameter("roleID"));
		String test = request.getParameter("booking"); logger.info("BOOKING: " + test);
		int old = role.getBookings_allowed();
	    role.setBookings_allowed(Integer.valueOf(request.getParameter("booking")));
		roleService.update(role);
		model.addAttribute("message", "Role " + role.getName() + " updated. This role has changed from " + old + " bookings to " + request.getParameter("booking") + " allowed bookings.");
		return newRole(model);
	}
	
	@RequestMapping("/deleteRole")
	public String deleteRole(HttpServletRequest request, Model model){
		Role role = roleService.getRole(request.getParameter("roleID"));
		List<String> list = roleService.defaultRoles();
		for (String s : list){
			if (s.equals(role.getName())){
				model.addAttribute("message", "The Role [" + s +"] is a default role needed by the system. It cannot be deleted.");
				return newRole(model);
			}		
		}
		roleService.delete(role);
		model.addAttribute("message", "The Role [" + role.getName() + "] has been successfully deleted.");
		return newRole(model);
	}
	
	@RequestMapping("/createNewRole")
	public String newRole(Model model) {
		roleService.defaultRoles();
		model.addAttribute("role", new Role());
		model.addAttribute("existing", roleService.getRoles());
		return "createNewRole";
	}
	
	@RequestMapping("/saveNewRole")
	public String saveNewRole(Model model, @ModelAttribute("role") Role role,
			BindingResult result) {
		if (roleService.exists(role.getName())) {
			result.rejectValue("name", "Duplicate Key",
					"This role exists already");
			return newRole(model);
		} else {
			roleService.create(role);
			return newRole(model);
		}
	}
}
