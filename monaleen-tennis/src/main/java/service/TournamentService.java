package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;



import dao.TournamentDAO;
import events.tournaments.Tournament;

@Service("tournamentService")
public class TournamentService {

	private TournamentDAO tournamentDAO;
	
	@Autowired
	public void setTournamentDAO(TournamentDAO tournamentDAO) {
		this.tournamentDAO = tournamentDAO;
	}

	@Secured("ROLE_ADMIN")
	public void create(Tournament t) {
		tournamentDAO.createTournament(t);
	}
	
	
}
