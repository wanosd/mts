package controllers;

import news.News;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Showing Home Page...." + currentUser);
		News news = newsService.getLatestStory();
		model.addAttribute("newsHeader", news.getSummary());
		model.addAttribute("newsContent", news.getContent());
		return "index";
	}

	@Autowired
	public void setNewsSerivce(NewsService newsService) {
		this.newsService = newsService;
	}
	
	
}
