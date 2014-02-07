package dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import users.User;
import events.tournaments.Tournament;

@Repository
@Transactional
@Component("tournamentDAO")
public class TournamentDAO {

	private static Logger logger = Logger.getLogger(TournamentDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}

	public TournamentDAO() {
		logger.info("Tournament DAO Created.....");
	}

	@Transactional
	public void createTournament(Tournament t) {
		session().save(t);
	}

	public void updateTournament(Tournament t) {
		session().saveOrUpdate(t);
	}

	@SuppressWarnings("unchecked")
	public List<Tournament> listOpenTournaments() {
		logger.info("Selecting All Open Tournaments....");
		return session()
				.createQuery(
						"from Tournament where registrationOpen = '1' and tournamentStarted ='0'")
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tournament> listStartedTournaments() {
		logger.info("Selecting All Open Tournaments....");
		return session().createQuery("from Tournament where tournamentStarted ='1'").list();
	}

	@SuppressWarnings("unchecked")
	public List<Tournament> listUnstartedTournaments() {
		logger.info("Selecting All Unstarted Tournaments....");
		return session().createQuery("from Tournament where tournamentStarted ='0'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tournament> listClosedTournaments() {
		logger.info("Selecting All Open Tournaments....");
		return session()
				.createQuery(
						"from Tournament where registrationOpen = '0' and tournamentStarted = '0'")
				.list();
	}

	public Tournament getTournament(String id) {
		Criteria crit = session().createCriteria(Tournament.class);
		crit.add(Restrictions.eq("id", Integer.valueOf(id)));
		return (Tournament) crit.uniqueResult();
	}

	public void registerForTournament(Tournament tour) {
		logger.info("Registering for Tournament....");
		logger.info("ID " + tour.getId());
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		tour.getUsername().add(auth.getName());
		session().saveOrUpdate(tour);
	}

	public void unregisterForTournament(Tournament tour) {
		logger.info("Registering for Tournament....");
		logger.info("ID " + tour.getId());
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		tour.getUsername().remove(auth.getName());
		session().saveOrUpdate(tour);

	}

	public boolean exists(String tournamentName) {
		Criteria crit = session().createCriteria(Tournament.class);
		crit.add(Restrictions.eq("tournamentName", tournamentName)); 
		Tournament tour = (Tournament) crit.uniqueResult();
		return tour != null;
	}
}
