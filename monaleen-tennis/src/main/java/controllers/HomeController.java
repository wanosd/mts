package controllers;

import news.News;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.NewsService;


@Controller("homeController")
public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class); 
	
	private NewsService newsService;
	
	@RequestMapping("/")
	public String showHome(Model model) {
		logger.info("Showing Home Page....");
		News news = newsService.getLatestStory();
		model.addAttribute("newsHeader", news.getSummary());
		model.addAttribute("newsContent", news.getContent());
		return "index";
	}

	@RequestMapping("/error")
	public String showError(Model model){
		model.addAttribute("message", "An error has occured. Please try again");
		return "error";
	}
	
	@RequestMapping("/denied")
	public String showDeny(Model model){
		model.addAttribute("message", "The page you are looking for either does not exist, or you do not have access. Please contact an administrator if you believe this is an error.");
		return "error";
	}
	
	@Autowired
	public void setNewsSerivce(NewsService newsService) {
		this.newsService = newsService;
	}
	
	
}
