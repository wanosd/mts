package service;

import java.util.List;

import news.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import dao.NewsDAO;

@Service("newsService")
public class NewsService {
	
	private NewsDAO newsDAO;

	@Autowired
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void createNews(News n){
		newsDAO.createNewsStory(n);
	}

	public List<News> getAllNews(){
		return newsDAO.getNews();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void deleteNews(News n){
		newsDAO.deleteNewsStory(n);
	}
	
	public News getNewsStory(String id){
		return newsDAO.getNewsStory(id);
	}

	public News getLatestStory() {
		return newsDAO.getLatestStory();
	}
}
