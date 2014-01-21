package events.tournaments;

import java.util.List;

import users.User;
import events.Event;

public class Tournament extends Event {
	
	private List<User> eligible; // Contains a list of eligible members
	private String tournamentName = "default tournament"; // Specified the name of the tournament
	private String tournamentGender = "MIXED"; // Specifies where a tournament is M(ale), F(emale) or MIXED;
	private String tournamentType = "S"; // Specifies S(ingles) or D(oubles)
	private String tournamentCategory = "O"; // Specifies Member_Type to be S(enior) only, J(unior) only, or O(pen) Tournament
	private String tournamentStyle = null; // Specfies type of Tournament to be  (L)adder/(L)eague, (B)ucket or (G)roup - Probably change this to a class later on.
	
}
