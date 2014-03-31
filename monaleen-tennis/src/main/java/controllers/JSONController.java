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
		List<Timetable> series = timetableService.getTimetableSeries(1);
		Timetable current = series.get((Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1));
	
		Map<String, Object> data = new HashMap<String, Object>();
		for (int i = 1; i <= timetableService.getNewSeries(); i++){
			
			String key = "day" + i;
			
			switch(day_of_week){
			case 1: {
				data.put(key, current.getMonday());
			}
			case 2: {
				data.put(key, current.getTuesday());
			}
			case 3: {
				data.put(key, current.getWednesday());
			}
			case 4: {
				data.put(key, current.getThursday());
			}
			case 5: {
				data.put(key, current.getFriday());
			}
			case 6: {
				data.put(key, current.getSaturday());
			}
			case 7: {
				data.put(key, current.getSunday());
			}
			}
		}// end for
		
		return data;
	}
	
	
}
