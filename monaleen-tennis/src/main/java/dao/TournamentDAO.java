package dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import events.tournaments.Tournament;

@Repository
@Transactional
@Component("tournamentDAO")
public class TournamentDAO {
	
	private static Logger logger = Logger.getLogger(TournamentDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
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

}
