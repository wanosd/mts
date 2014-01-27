package events.tournaments;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import users.FormValidationGroup;
import users.PersistenceValidationGroup;
import users.User;
import events.Event;

@Entity
@Table(name="tournament")
public class Tournament implements Event {

	@Id
	@GeneratedValue
	private int id;
	
	@ElementCollection
	@CollectionTable (name = "tournament_eligible", joinColumns=@JoinColumn(name="id"))
	private List<String> username; // Contains a list of eligible members 
	
	@Size(min=5, max=45, message="Tournament Name must be between 5 and 60 characters",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String tournamentName; // Specified the name of the tournament
	
	private String tournamentGender = "MIXED"; // Specifies where a tournament is M(ale), F(emale) or MIXED;
	
	private String tournamentType = "S"; // Specifies S(ingles) or D(oubles)
	
	private String tournamentCategory = "O"; // Specifies Member_Type to be S(enior) only, J(unior) only, or O(pen) Tournament
	
	private String tournamentStyle = "L"; // Specfies type of Tournament to be  (L)adder/(L)eague, (B)ucket or (G)roup - Probably change this to a class later on.
	
	private boolean registrationOpen;
	
	public Tournament(){
		this.username = new ArrayList<String>();
	}
	
	public void register(String toRegister){
		username.add(toRegister);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getUsername() {
		return username;
	}

	public void setUsername(List<String> e) {
		this.username = e;
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
	
	
	public boolean isRegistrationOpen() {
		return registrationOpen;
	}

	public void setRegistrationOpen(boolean registrationOpen) {
		this.registrationOpen = registrationOpen;
	}

	@Override
	public String toString() {
		return "Tournament [id=" + id + ", eligible=" + username
				+ ", tournamentName=" + tournamentName + ", tournamentGender="
				+ tournamentGender + ", tournamentType=" + tournamentType
				+ ", tournamentCategory=" + tournamentCategory
				+ ", tournamentStyle=" + tournamentStyle + "]";
	}
	
	
	
}
