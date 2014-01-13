package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/createmembers")
	public String createMembers(Model model){
			return "createmembers";
	} 
	
	/*
	 * Method will only take Post requests
	 */
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String doRegister(Model model, Member member){
		System.out.println(member);
			return "registerSuccess";
	} 

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

}
