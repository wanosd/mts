package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.UserService;
import users.FormValidationGroup;
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
	
	@RequestMapping("/membership")
	public String showMembership(){
		return "membership";
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
	
	@RequestMapping("/approveFinalize")
	public String approveMembers(Model model, HttpServletRequest request) {
		logger.info("Moving to approveFinalize and back to approveMembers");
		userService.enableUser(request.getParameter("username"));
		List<User> toApprove = userService.getPendingMembers();
		model.addAttribute("toApprove", toApprove);
		return "approveMembers";
	} 

	@RequestMapping("/blockMembers")
	public String showActiveMembers(Model model) {
		logger.info("Showing Pending Members Page....");
		List<User> toBlock = userService.getCurrentMembers();
		toBlock = removeLoggedIn(toBlock);
		model.addAttribute("toBlock", toBlock);
		return "blockMembers";
	}
	
	@RequestMapping("/blockFinalize")
	public String blockMembers(Model model, HttpServletRequest request) {
		logger.info("Moving to blockFinalize and back to blockMembers");
		userService.disableUser(request.getParameter("username"));
		List<User> toBlock = userService.getCurrentMembers();
		toBlock = removeLoggedIn(toBlock);
		model.addAttribute("toBlock", toBlock);
		return "blockMembers";
	} 
	
	
	@RequestMapping("/createmembers")
	public String createMembers(Model model) {
		logger.info("Showing Create Members Page....");
		model.addAttribute("member", new User());
		return "createmembers";
	}

	/*
	 * Method will only take Post requests Registers user to database
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(
			Model model,
			@Validated(FormValidationGroup.class) @ModelAttribute("member") User member,
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
			try {
				member.setAuthority("ROLE_MEMBER");
				userService.create(member);
				return "registerSuccess";
			} catch (Exception e) {
				logger.info("ERROR!!!!!!!!!!!" + e.getClass());
				return "error";
			}

		}
	}

	@RequestMapping("/profile")
	public String editProfile(Model model) {
		logger.info("Showing Edit Profile Page.....");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userService.getUserByUsername(auth.getName());
		model.addAttribute("member", user);
		return "profile";
	}

	@RequestMapping(value = "/profileUpdated", method = RequestMethod.POST)
	public String doEditProfile(
			Model model,
			@ModelAttribute("member") User member,
			BindingResult result) {
		logger.info("Showing Profile Updated.....");
		if (result.hasErrors()) {
			logger.info("Errors found in BindingResult object....");
			return "profile";
		} 
		
		if (userService.exists(member.getUsername())) {
			result.rejectValue("username", "Duplicate Key",
					"This email address has already been used");
			return "profile";
		}
		
		else {
			try {
				logger.info("About to  update user");
				userService.editProfile(member);
				logger.info("User Updated");
				return "profileUpdated";
			} catch (Exception e) {
				logger.info("Database update failed. Cause: " + e.getClass());
				return "error";
			}
		}
	}
	
	@RequestMapping("/createGrade")
	public String createGrade(){
		return "createGrade";
	}
	
	@RequestMapping("/saveGrade")
	public String saveGrade(Model model, HttpServletRequest request){
		userService.createGrade(request.getParameter("grade"));
		return "grades";
	}
	
	public List<User> removeLoggedIn(List<User> users){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User temp = null;
		for (int i = 0; i < users.size(); i++){
			if (users.get(i).getUsername().equals(username)){
				temp = users.get(i);
			}
		}
		
		if (temp != null){
			users.remove(temp);
			return users;
		}else{
			return users;
		}
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
