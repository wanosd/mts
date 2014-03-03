package controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.RoleService;
import service.UserService;

@Controller("siteController")
public class SiteController {

	private static Logger logger = Logger.getLogger(SiteController.class);
	
	private UserService userService;
	
	private RoleService roleService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@RequestMapping("/contactus")
	public String contactUs(Model model){
		model.addAttribute("admins", userService.getAdmins());
		model.addAttribute("committee", userService.getCommittee());
		return "contactus";
	}
	
	
}
