package controllers;

import java.security.Principal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.TimetableService;
import timetable.Timetable;

@Controller
public class JSONController {
	
	private TimetableService timetableService;
	private static Logger logger = Logger.getLogger(JSONController.class);
	
	@Autowired
	public void setTimetableService(TimetableService timetableService) {
		this.timetableService = timetableService;
	}
	
	
	@RequestMapping(value="/jsoncurrentday", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> getCurrentDay(){

		int day_of_week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
		Map<String, Object> data = new HashMap<String, Object>();
	
		for (int i = 1; i <= timetableService.getNewSeries(); i++){
			
			String key = "court" + i;
			String[] days = {"error", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
			List<Timetable> series = timetableService.getTimetableSeries(i);
			Timetable current = series.get((Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1));
			if (day_of_week == 1){
				data.put("dayofweek", days[1]);
				data.put(key, current.getMonday());
			}
			else if (day_of_week == 2){
				data.put("dayofweek", days[2]);
				data.put(key, current.getTuesday());
			}
			else if (day_of_week == 3){
				data.put("dayofweek", days[3]);
				data.put(key, current.getWednesday());
			}
			else if (day_of_week == 4){
				data.put("dayofweek", days[4]);
				data.put(key, current.getThursday());
			}
			else if (day_of_week == 5){
				data.put("dayofweek", days[5]);
				data.put(key, current.getFriday());
			}
			else if (day_of_week == 6){
				data.put("dayofweek", days[6]);
				data.put(key, current.getSaturday());
			}
			else if (day_of_week == 7){
				data.put("dayofweek", days[7]);
				data.put(key, current.getSunday());
			}
			else{
				data.put("dayofweek", days[0]);
			}
		}// end for
		return data;
	}
}
