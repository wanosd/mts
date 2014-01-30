package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import users.User;
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

	public List<Tournament> getCurrentTournaments() {
		return tournamentDAO.listOpenTournaments();
	}
	
	@Secured("ROLE_ADMIN")
	public List<Tournament> getClosedTournaments(){
		return tournamentDAO.listClosedTournaments();
	}

	public void register(Tournament t) {
		tournamentDAO.registerForTournament(t);
	}

	public Tournament getTournamentById(String id) {
		return tournamentDAO.getTournament(id);
	}
	
	public void updateTournament(Tournament t){
		tournamentDAO.updateTournament(t);
	}

	public void unregister(Tournament t) {
		tournamentDAO.unregisterForTournament(t);
	}
}
