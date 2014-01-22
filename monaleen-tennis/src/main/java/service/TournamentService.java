package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import dao.TournamentDAO;
import events.tournaments.Tournament;

@Service("tournamentService")
public class TournamentService {

	private TournamentDAO tournamentDAO;
	
	@Autowired
	public void setTournamentDAO(TournamentDAO tournamentDAO){
		this.tournamentDAO = tournamentDAO;
	}
	
	//Need to annotate to ROLE ADMIN later
	public void create(Tournament t) {
		tournamentDAO.createTournament(t);
	}
	
	
}
