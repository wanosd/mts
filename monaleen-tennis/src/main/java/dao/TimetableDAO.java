package dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import timetable.Timetable;


@Repository
@Transactional
@Component("timetableDAO")
public class TimetableDAO {
	
private static Logger logger = Logger.getLogger(TournamentDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void createTimetable(Timetable t) {
		session().save(t);
	}

	public void updateTimetable(Timetable t){
		session().saveOrUpdate(t);
	}
	
	@SuppressWarnings("unchecked")
	public List<Timetable> listTimetables(){
			logger.info("Selecting All Timetables....");
			return session().createQuery("from MonaleenTTV1").list();
	}

	@SuppressWarnings("unchecked")
	public List<Timetable> getEnabledTimetables() {
		logger.info("Selecting All Enabled Timetables....");
		return session().createQuery("from MonaleenTTV1 where enabled ='1'").list();
	}

	@SuppressWarnings("unchecked")
	public List<Timetable> getDisabledTimetables() {
		logger.info("Selecting All Enabled Timetables....");
		return session().createQuery("from MonaleenTTV1 where enabled ='0'").list();
	}
	
}
