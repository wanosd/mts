package controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.TimetableService;
import service.UserService;
import timetable.MonaleenTTV1;
import timetable.Timetable;
import users.User;

@Controller
public class TimetableController {
	
	private TimetableService timetableService;
	
	@Autowired
	public void setTimetableService(TimetableService timetableService) {
		this.timetableService = timetableService;
	}

	public static void setLogger(Logger logger) {
		TimetableController.logger = logger;
	}

	private static Logger logger = Logger.getLogger(MembersController.class);

	@RequestMapping("/timetable")
	public String showTimetable(Model model) {
		MonaleenTTV1 t = new MonaleenTTV1();
		t.setName("Test Timetable");
		t.setSlots(9);
		t.setStartTime(1);
		t.setEndTime(2);
		for (int i = 0; i < t.getSlots(); i++){
			t.getMonday().add("Monday" + i);
			t.getTuesday().add("Tuesday" + i);
			t.getWednesday().add("Wednesday" + i);
			t.getThursday().add("Thursday" + i);
			t.getFriday().add("Friday" + i);
			t.getSaturday().add("Saturday" + i);
			t.getSunday().add("Sunday" + i);
		}
		timetableService.create(t);
		return "/";
	}

	@RequestMapping(value = "/gettimetable", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getTimetable(Principal principal) {
		return null;
	}
}
