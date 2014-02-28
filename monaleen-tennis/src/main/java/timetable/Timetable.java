package timetable;

import java.util.List;

public interface Timetable {
	
	public boolean isEnabled();
	
	public void setEnabled(boolean e);
	
	public String getName();
	
	public List<String> getMonday();
	public void setMonday(List<String> day);
	
	public List<String> getTuesday();
	public void setTuesday(List<String> day);
	
	public List<String> getWednesday();
	public void setWednesday(List<String> day);
	
	public List<String> getThursday();
	public void setThursday(List<String> day);
		
	public List<String> getFriday();
	public void setFriday(List<String> day);
	
	public List<String> getSaturday();
	public void setSaturday(List<String> day);
	
	public List<String> getSunday();
	public void setSunday(List<String> day);
	
	public List<String> getList(String day);
	
	public void setList(List<String> list, String day);
	
	public int getSlots();
	
	public int getSeries();

	public int getId();

}
