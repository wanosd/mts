package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.UserService;
import users.*;


@Controller
public class MembersController {
	
	private UserService userService;
	
	@RequestMapping("/members")
	public String showMembers(Model model){
		
		List<User> userList = userService.getCurrentMembers();
		model.addAttribute("users", userList);
		
		return "members";
	}
	
	@RequestMapping("/admin")
	public String showAdmin() {

		return "admin";
	}
	
	@RequestMapping("/approveMembers")
	public String showPendingMembers(Model model) {

		List<User> toApprove = userService.getPendingMembers();
		model.addAttribute("toApprove", toApprove);
		return "approveMembers";
	}
	
	
	@RequestMapping("/createmembers")
	public String createMembers(Model model){
		model.addAttribute("member", new Member());	
		return "createmembers";
	} 
	
	/*
	 * Method will only take Post requests
	 * Registers user to database
	 */
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String doRegister(Model model, @Valid Member member, BindingResult result){
		
			if (result.hasErrors()){
				return "createmembers";
			}	
			
			if (userService.exists(member.getUsername())){
				result.rejectValue("username", "Duplicate Key", "This email address has already been used");
				return "createmembers"; 
			}
			
			else
				{
					userService.create(member);

					return "registerSuccess";			
				}
			} 

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
