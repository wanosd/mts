package dao;

import java.util.List;

import news.News;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("newsDAO")
public class NewsDAO {
	
	private static Logger logger = Logger.getLogger(NewsDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}
	
	public void createNewsStory(News n){
		session().save(n);
	}
	
	public void deleteNewsStory(News n){
		session().delete(n);;
	}
	
	public News getNewsStory(String id){
		Criteria crit = session().createCriteria(News.class);
		crit.add(Restrictions.eq("id", Integer.valueOf(id)));
		return (News) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getNews(){
		logger.info("Selecting All News....(Hibernate)");
		return session().createQuery("from News").list();
	}

	public News getLatestStory() {
		List<News> news = getNews();
		return news.get(news.size()-1);
	}

}
