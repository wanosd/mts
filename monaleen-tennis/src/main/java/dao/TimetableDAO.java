package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
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

	public Session session() {
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void createTimetable(Timetable t) {
		session().save(t);
	}

	public void updateTimetable(Timetable t) {
		session().saveOrUpdate(t);
	}

	public void deleteTimetable(Timetable t) {
		session().delete(t);
	}

	@SuppressWarnings("unchecked")
	public List<Timetable> listTimetables() {
		logger.info("Selecting All Timetables....");
		return session().createQuery("from MonaleenTTV1").list();
	}

	@SuppressWarnings("unchecked")
	public List<Timetable> getEnabledTimetables() {
		logger.info("Selecting All Enabled Timetables....");
		return session().createQuery("from MonaleenTTV1 where enabled ='1'")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Timetable> getDisabledTimetables() {
		logger.info("Selecting All Enabled Timetables....");
		return session().createQuery("from MonaleenTTV1 where enabled ='0'")
				.list();
	}

	public Timetable getById(String id) {
		Criteria crit = session().createCriteria(MonaleenTTV1.class);
		crit.add(Restrictions.eq("id", Integer.valueOf(id)));
		return (Timetable) crit.uniqueResult();
	}

	public boolean nextExists(int id) {
		if (enabled(id)) {
			Criteria crit = session().createCriteria(MonaleenTTV1.class);
			crit.add(Restrictions.eq("id", id));
			MonaleenTTV1 tt = (MonaleenTTV1) crit.uniqueResult();
			return tt != null;
		} else {
			return false;
		}
	}

	public boolean prevExists(int id) {
		if (enabled(id)) {
			Criteria crit = session().createCriteria(MonaleenTTV1.class);
			crit.add(Restrictions.eq("id", id));
			MonaleenTTV1 tt = (MonaleenTTV1) crit.uniqueResult();
			return tt != null;
		} else {
			return false;
		}
	}

	public boolean enabled(int id) {
		Criteria crit = session().createCriteria(MonaleenTTV1.class);
		crit.add(Restrictions.eq("id", id));
		MonaleenTTV1 tt = (MonaleenTTV1) crit.uniqueResult();
		if (tt.isEnabled()) {
			return true;
		} else {
			return false;
		}
	}

	public int newSeries() {
		DetachedCriteria maxId = DetachedCriteria.forClass(MonaleenTTV1.class).setProjection( Projections.max("series") );
		List<Timetable> t = session().createCriteria(MonaleenTTV1.class).add(Property.forName("series").eq(maxId)).list();
		logger.info("NEW SERIES: SIZE IS: " + t.size());
		if (t.size() == 0){
			return 0;
		}
		else{
			logger.info("RETURNING SERIES NUMBER " + t.get(0).getSeries());
			return (t.get(0).getSeries());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Timetable> getTimetableSeries(int series) {
		return session().createQuery("from MonaleenTTV1 where series ='" + series +"'")
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Timetable> getTimetableSeriesSection(int start, int end, int series){
		List<Timetable> list = session().createQuery("from MonaleenTTV1 where series ='" + series +"'")
				.list();
		List<Timetable> section = new ArrayList<Timetable>();
		for (int i = start; i < end; i++){
			section.add(list.get(i));
		}
		return section;
	}

	public List<Timetable> getTimetableAllSeries() {
		int size = newSeries();
		List<Timetable> list = new ArrayList<Timetable>();
		for (int i = 1; i <=size; i++ ){
			list.add((Timetable) session().createQuery("from MonaleenTTV1 where series ='" + i + "'").list().get(0));
		}
		return list;
	}
	public void deleteSeries(int id) {
		List<Timetable> delete = getTimetableSeries(id);
		for (int i = 0; i < delete.size(); i++){
			session().delete(delete.get(i));
		}
	}

}
