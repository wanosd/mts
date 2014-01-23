package controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.UserService;
import users.*;


@Controller
public class MembersController {

	private UserService userService;
	
	private static Logger logger = Logger.getLogger(MembersController.class);

	@RequestMapping("/members")
	public String showMembers(Model model) {
		logger.info("Showing Members Page....");
		List<User> userList = userService.getCurrentMembers();
		model.addAttribute("users", userList);
		return "members";
	}
	
	@RequestMapping("/approveFinalize")
	public String approveMembers(Model model, HttpServletRequest request) {
		logger.info("Moving to approveFinalize and back to approveMembers");
		userService.enableUser(request.getParameter("username"));
		List<User> toApprove = userService.getPendingMembers();
		model.addAttribute("toApprove", toApprove);
		return "approveMembers";
	}
	
	@RequestMapping("/registerSuccess")
	public String showRegSuccess() {
		logger.info("Showing Reg Success Page....");
		return "registerSuccess";
	}

	

	@RequestMapping("/admin")
	public String showAdmin() {
		logger.info("Showing Admin Page....");
		userService.exists("test");
		return "admin";
	}

	@RequestMapping("/approveMembers")
	public String showPendingMembers(Model model) {

		logger.info("Showing Pending Members Page....");
		List<User> toApprove = userService.getPendingMembers();
		model.addAttribute("toApprove", toApprove);
		return "approveMembers";
	}

	@RequestMapping("/createmembers")
	public String createMembers(Model model) {
		logger.info("Showing Create Members Page....");
		model.addAttribute("member", new User());
		return "createmembers";
	}

	@RequestMapping("/editdetails")
	public String editDetails(Model model, Principal p) {
		
		logger.info("Showing Edit Details Page....");
		if (p != null) {
			String username = p.getName();
			User user = userService.getUserByUsername(username);
			model.addAttribute("member", user);
			return "editdetails";
		} else {
			return "/";
		}

	}

	/*
	 * Method will only take Post requests Registers user to database
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(Model model, @Validated(FormValidationGroup.class) @ModelAttribute("member") User member, BindingResult result) {

		logger.info("Showing Registration Page....");
		
		if (result.hasErrors()) {
			return "createmembers";
		}

		if (userService.exists(member.getUsername())) { 
			result.rejectValue("username", "Duplicate Key",
					"This email address has already been used");
			return "createmembers";
		}

		else {
			try{
				member.setAuthority("ROLE_MEMBER");
				userService.create(member);
				return "registerSuccess";
			}catch (Exception e){
				System.out.println("ERROR!!!!!!!!!!!" + e.getClass());
				return "error";
			}
			
		}
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
