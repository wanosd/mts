package controllers;

import java.util.Collections;
import java.util.List;

import news.News;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.NewsService;

@Controller
public class NewsController {
	
	private static Logger logger = Logger.getLogger(MembersController.class);
	
	private NewsService newsService;

	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	@RequestMapping("/news")
	public String displayNews(Model model) {
		logger.info("Showing News Page....");
		List<News> news = newsService.getAllNews();
		Collections.reverse(news); // reverses so latest entry is first
		model.addAttribute("news", news);
		return "news";
	}


	@RequestMapping("/createNews")
	public String createNews(Model model) {
		logger.info("Showing Create News Page....");
		model.addAttribute("news", new News());
		return "createNews";
	}
	
	@RequestMapping(value = "/saveNewsStory", method = RequestMethod.POST)
	public String saveNewsStory(Model model, @ModelAttribute("news") News n, BindingResult r) {
		logger.info("Showing Save News Page....");
		
		if (r.hasErrors()){
			return "createNews";
		}else{
			try{
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				n.setAuthor(auth.getName());
				newsService.createNews(n);
				return "news";
				
			}catch(Exception e){
				System.out.println("ERROR!!!!!!!!!!!" + e.getClass());
				System.out.println("ERROR!!!!!!!!!!!" + e.getCause());
				System.out.println("ERROR!!!!!!!!!!!" + e.getLocalizedMessage());
				return "error";
			}
		}
		
		
	}
}
