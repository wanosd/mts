package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void updateEvent(I_Event e){
		eventDAO.updateEvent(e);
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
	
	public I_Event getEventIdByName(String name){
		return eventDAO.getEventID(name);
	}
	
	
}
