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
	
	public Timetable getById(String id){
		return timetableDAO.getById(id);
	}
	
	public void delete(Timetable t){
		timetableDAO.deleteTimetable(t);
	}
	
	public boolean nextExists(int id){
		return timetableDAO.nextExists(id);
	}

	public boolean prevExists(int id) {
		return timetableDAO.prevExists(id);
	}
	
	public int getNewSeries(){
		return timetableDAO.newSeries();
	}

	public List<Timetable> getTimetableSeries(int series) {
		return timetableDAO.getTimetableSeries(series);
		
	}
	


}
