package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import timetable.Timetable;
import dao.TimetableDAO;

@Service("timetableService")
public class TimetableService {
	
	private TimetableDAO timetableDAO;

	@Autowired
	public void setTimetableDAO(TimetableDAO timetableDAO) {
		this.timetableDAO = timetableDAO;
	}
	
	@Secured("ROLE_ADMIN")
	public void create(Timetable t){
		timetableDAO.createTimetable(t);
	}
	
	public void update(Timetable t){
		timetableDAO.updateTimetable(t);
	}
	
	public List<Timetable> getAllTimetables(){
		return timetableDAO.listTimetables();
	}
	
	public List<Timetable> getEnabledTimetables(){
		return timetableDAO.getEnabledTimetables();
	}
	
	public List<Timetable> getDisabledTimetables(){
		return timetableDAO.getDisabledTimetables();
	}
	

}
