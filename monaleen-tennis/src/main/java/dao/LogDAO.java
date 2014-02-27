package dao;

import java.util.List;
import java.util.Map;

import logs.Log;

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
@Component("emailLogDAO")
public class LogDAO {
	
private static Logger logger = Logger.getLogger(EventDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}
	
	public void createLogEntry(Log log){
		session().save(log);
	}
	
	@SuppressWarnings("unchecked")
	public List<Log> getLogs(){
		Criteria crit = session().createCriteria(Log.class);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Log> getNoShowLogStats() {
		Criteria crit = session().createCriteria(Log.class);
		crit.add(Restrictions.eq("logtype", "noshow"));
		return crit.list();
	}

}
