package service;

import java.util.List;

import news.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.NewsDAO;

@Service("newsService")
public class NewsService {
	
	private NewsDAO newsDAO;

	@Autowired
	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}
	
	public void createNews(News n){
		newsDAO.createNewsStory(n);
	}

	public List<News> getAllNews(){
		return newsDAO.getNews();
	}
	
	public void deleteNews(News n){
		newsDAO.deleteNewsStory(n);
	}
	
	public News getNewsStory(String id){
		return newsDAO.getNewsStory(id);
	}
}
