package events.tournaments;

import java.util.List;
import java.util.Map;

public interface I_TournamentSorter {

	public Map<String, String> sort(List<String> registered);
	
	public boolean check(Tournament t);
	
}
