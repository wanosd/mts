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

	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void create(Tournament t) {
		tournamentDAO.createTournament(t);
	}

	public List<Tournament> getCurrentTournaments() {
		return tournamentDAO.listOpenTournaments();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public List<Tournament> getClosedTournaments(){
		return tournamentDAO.listClosedTournaments();
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public List<Tournament> getAllTournaments(){
		return tournamentDAO.listAllTournaments();
	}

	@Secured({"ROLE_ADMIN", "ROLE_MEMBER", "ROLE_COMMITTEE", "ROLE_WARNING", "ROLE_SUSPEND"})
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
	
	public List<Tournament> getStartedTournaments(){
		return tournamentDAO.listStartedTournaments();
	}
	
	public List<Tournament> getUnstartedTournaments(){
		return tournamentDAO.listUnstartedTournaments();
	}

	public boolean exists(String tournamentName) {
		return tournamentDAO.exists(tournamentName);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_COMMITTEE"})
	public void deleteTournament(Tournament t){
		tournamentDAO.deleteTournament(t);
	}
}
