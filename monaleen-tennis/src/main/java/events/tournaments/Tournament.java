package events.tournaments;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import users.User;
import events.Event;

@Entity
@Table(name="tournament")
public class Tournament extends Event {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany
	@JoinColumn(name="id")
	private List<User> eligible; // Contains a list of eligible members 
	
	private String tournamentName = "default tournament"; // Specified the name of the tournament
	private String tournamentGender = "MIXED"; // Specifies where a tournament is M(ale), F(emale) or MIXED;
	private String tournamentType = "S"; // Specifies S(ingles) or D(oubles)
	private String tournamentCategory = "O"; // Specifies Member_Type to be S(enior) only, J(unior) only, or O(pen) Tournament
	private String tournamentStyle = null; // Specfies type of Tournament to be  (L)adder/(L)eague, (B)ucket or (G)roup - Probably change this to a class later on.
	
	public Tournament(){
		this.eligible = new ArrayList<User>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getEligible() {
		return eligible;
	}

	public void setEligible(List<User> eligible) {
		this.eligible = eligible;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public String getTournamentGender() {
		return tournamentGender;
	}

	public void setTournamentGender(String tournamentGender) {
		this.tournamentGender = tournamentGender;
	}

	public String getTournamentType() {
		return tournamentType;
	}

	public void setTournamentType(String tournamentType) {
		this.tournamentType = tournamentType;
	}

	public String getTournamentCategory() {
		return tournamentCategory;
	}

	public void setTournamentCategory(String tournamentCategory) {
		this.tournamentCategory = tournamentCategory;
	}

	public String getTournamentStyle() {
		return tournamentStyle;
	}

	public void setTournamentStyle(String tournamentStyle) {
		this.tournamentStyle = tournamentStyle;
	}
	
	
	
}
