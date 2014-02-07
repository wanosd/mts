package dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import events.*;
import events.tournaments.Tournament;

@Repository
@Transactional
@Component("eventDAO")
public class EventDAO {
	
private static Logger logger = Logger.getLogger(EventDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}
	
	public void createNewEvent(I_Event e){
		logger.info("Creating new event....");
		session().save(e);
	}
	
	public I_Event getEventById(int id){
		Criteria crit = session().createCriteria(Event.class);
		crit.add(Restrictions.eq("id", id));
		return (Event) crit.uniqueResult();
	}
	
	public void updateEvent(I_Event e){
		session().saveOrUpdate(e);
	}
	
	public void deleteEvent(int id){
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getEnabledEvents(){
		return session().createQuery("from Event where enabled ='1'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getDisabledEvents(){
		return session().createQuery("from Event where enabled ='0'").list();
	}
	
	

}
