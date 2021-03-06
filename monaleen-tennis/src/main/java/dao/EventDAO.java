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

import users.User;
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
	
	public void deleteEvent(I_Event e){
		logger.info("Deleting event....");
		session().delete(e);
	}
	
	public I_Event getEventById(int id){
		Criteria crit = session().createCriteria(Event.class);
		crit.add(Restrictions.eq("id", id));
		return (Event) crit.uniqueResult();
	}
	
	public void updateEvent(I_Event e){
		session().saveOrUpdate(e);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Event> getEnabledEvents(){
		return session().createQuery("from Event where enabled ='1' AND author != 'BOOKING_SYSTEM'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getDisabledEvents(){
		return session().createQuery("from Event where enabled ='0' AND author != 'BOOKING_SYSTEM'").list();
	}

	public I_Event getEventID(String name) {
		Criteria crit = session().createCriteria(Event.class);
		crit.add(Restrictions.eq("name", name));
		return (Event) crit.uniqueResult();
	}

	public boolean exists(String e) {
		Criteria crit = session().createCriteria(Event.class);
		crit.add(Restrictions.eq("name", e)); 
		List<Event> events = crit.list();
		logger.info("EVENT LIST SIZE IS: " + events.size());
		if (events.size() >= 1){
			return events != null;	
		}
		else
			return events == null;
	}
	
	@SuppressWarnings("unchecked")
	public void deleteUserEntries(String id){
		List<I_Event> events = session().createQuery("from Event where courtid = '" + Integer.valueOf(id) + "'").list();
		for (int i = 0; i < events.size(); i++){
			deleteEvent(events.get(i));
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getAllEvents() {
		return session().createQuery("from Event where enabled ='1' AND author !='BOOKING_SYSTEM'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getAllEventsEnabledFilter() {
		return session().createQuery("from Event where enabled ='1' OR author = 'BOOKING_SYSTEM'").list();
	}
	
	@SuppressWarnings("unchecked")
	public int checkBookingsUserCourt(String loggedin, String id) {
		List<Event> event = session().createQuery("from Event where name = '" + loggedin + "' AND courtid = '" + id + "'").list();
		if (event == null){
			return 0;
		}
		return event.size();
	}

	@SuppressWarnings("unchecked")
	public void removeBooking(String loggedin, int id) {
		List<Event> event = session().createQuery("from Event where name = '" + loggedin + "' AND courtid = '" + id + "'").list();
		session().delete(event.get(0));
		
	}
	

}
