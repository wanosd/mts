package controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.UserService;
import users.User;

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
	public String doRegister(Model model, @Valid User member,
			BindingResult result) {

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
			userService.create(member);

			return "registerSuccess";
		}
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
