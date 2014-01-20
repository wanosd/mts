package controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller("homeController")
public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class); 

	@RequestMapping("/")
	public String showHome() {

		logger.info("Showing Home Page....");
		return "index";
	}
}
