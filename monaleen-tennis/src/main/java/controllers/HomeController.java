package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller("homeController")
public class HomeController {

	@RequestMapping("/")
	public String showHome() {

		return "index";
	}
	
	@RequestMapping("/admin")
	public String showAdmin() {

		return "admin";
	}
}
