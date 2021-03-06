package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import dao.EventDAO;
import events.*;

@Service("eventService")
public class EventService {

	private EventDAO eventDAO;

	@Autowired
	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	public void createEvent(I_Event e){
		eventDAO.createNewEvent(e);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void updateEvent(I_Event e){
		eventDAO.updateEvent(e);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void deleteEvent(I_Event e){
		eventDAO.deleteEvent(e);
	}
	
	public List<Event> listEnabledEvents(){
		return eventDAO.getEnabledEvents();
	}
	
	public List<Event> listDisabledEvents(){
		return eventDAO.getDisabledEvents();
	}
	
	public I_Event getEventById(int id){
		return eventDAO.getEventById(id);
	}
	
	public List<Event> listAllEvents(){
		return eventDAO.getAllEvents();
	}
	
	public List<Event> listAllEventsEnabledFilter(){
		return eventDAO.getAllEventsEnabledFilter();
	}
	
	public I_Event getEventIdByName(String name){
		return eventDAO.getEventID(name);
	}
	
	public boolean exists(String e){
		return eventDAO.exists(e);
	}
	
	public void deleteUserEvents(String id){
		eventDAO.deleteUserEntries(id);
	}
	
	public int checkBookingsUser(String name, String id){
		return eventDAO.checkBookingsUserCourt(name, id);
	}

	public void removeBooking(String loggedin, int id) {
		eventDAO.removeBooking(loggedin, id);
		
	}

	public void defaultEvents() {
		
	}
	
	
	
}
