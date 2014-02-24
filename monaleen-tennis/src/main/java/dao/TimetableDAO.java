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

import timetable.MonaleenTTV1;
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
	
	public void deleteTimetable(Timetable t){
		session().delete(t);
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

	public Timetable getById(String id) {
		Criteria crit = session().createCriteria(MonaleenTTV1.class);
		crit.add(Restrictions.eq("id", Integer.valueOf(id)));
		return (Timetable) crit.uniqueResult();
	}

	public boolean nextExists(int id) {
		Criteria crit = session().createCriteria(MonaleenTTV1.class);
		crit.add(Restrictions.eq("id", id));
		MonaleenTTV1 tt = (MonaleenTTV1) crit.uniqueResult();
		return tt != null;
	}

	public boolean prevExists(int id) {
		Criteria crit = session().createCriteria(MonaleenTTV1.class);
		crit.add(Restrictions.eq("id", id));
		MonaleenTTV1 tt = (MonaleenTTV1) crit.uniqueResult();
		return tt != null;
	}
	
}
