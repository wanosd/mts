package events.tournaments;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicTournamentSort implements I_TournamentSorter {

	public Map<String, String> sort(List<String> registered) {
		Map<String, String> teams = new HashMap<String, String>();		
		if ((registered.size() % 2) != 0){
			teams.put("NoTeam", registered.get(registered.size()-1));
			registered.remove(registered.size()-1);
		}
		int j = 1;
		for (int i = 0; i < registered.size(); i++){
			teams.put("Team"+j, registered.get(i) + " & " + registered.get(i+1));
			i++;
			j++;
		}
		
		return teams;

	}

	public boolean check(Tournament t) {
		if ((t.getUsername().size()) < 4){
			return false;
		}
		if (t.getTournamentType().equalsIgnoreCase("s")){
			return false;
		}
		else{
			return true;
		}
	}

}
