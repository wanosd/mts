package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@RequestMapping("/loggedout")
	public String showLogOut(){
		return "loggedout";
	}
	
	@RequestMapping("/accessdenied")
	public String showDenied(){
		return "accessdenied";
	}

}
