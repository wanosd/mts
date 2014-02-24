package dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import analytics.MTCAnalytics;

@Repository
@Transactional
@Component("analyticsDAO")
public class AnalyticsDAO {
	
private static Logger logger = Logger.getLogger(EventDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}
	
	public void save(MTCAnalytics a){
		session().update(a);;
	}
	
	public MTCAnalytics get(){
		return (MTCAnalytics) session().createQuery("from Analytics").list().get(0);
	}

}
