package events.tournaments;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
//@Table(name="tournament_eligible")
public class Registered {
	
	
	@Id
	@Column(name="id")
	private int tournamentID;
	
	@Column(name="username")
	private String username;
	
	@OneToMany(mappedBy="tournament")
	private List<String> registered;
	
	public int getTournamentID() {
		return tournamentID;
	}

	public void setTournamentID(int tournamentID) {
		this.tournamentID = tournamentID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRegistered() {
		return registered;
	}

	public void setRegistered(List<String> registered) {
		this.registered = registered;
	}
	
	
}
