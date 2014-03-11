package controllers;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("homeController")
public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class); 
	
	@RequestMapping("/")
	public String showHome() {
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Showing Home Page...." + currentUser);
		return "index";
	}
	
	
}
