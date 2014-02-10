package controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import events.Event;
import service.EventService;
import service.TimetableService;
import service.UserService;
import timetable.MonaleenTTV1;
import timetable.Timetable;
import users.User;

@Controller
public class TimetableController {

	private TimetableService timetableService;
	
	private EventService eventService;
	
	private static Logger logger = Logger.getLogger(TimetableController.class);

	@Autowired
	public void setTimetableService(TimetableService timetableService) {
		this.timetableService = timetableService;
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@RequestMapping("/timetable")
	public String showTimetable(Model model) {
		logger.info("Showing Timetable page....");
		List<Timetable> timetable = timetableService.getEnabledTimetables();
		model.addAttribute("timetable", timetable);
		return "timetable";
	}

	@RequestMapping("/createTimetable")
	public String createTimetablePage(Model model,
			@ModelAttribute("timetable") MonaleenTTV1 t, BindingResult result) {
		model.addAttribute("timetable", t);
		return "createTimetable";
	}

	@RequestMapping(value = "/timetableDefaults", method = RequestMethod.POST)
	public String timetableDefaults(Model model, HttpServletRequest request,
			@ModelAttribute("timetable") MonaleenTTV1 t, BindingResult result) {
		logger.info("TIMETABLE SLOTS " + t.getSlots());
		logger.info(t);
		t.setEnabled(false);
		timetableService.create(t);
		return "admin";
	}

	@RequestMapping(value = "/finalizeTimetable", method = RequestMethod.POST)
	public String finalTimetable(Model model, HttpServletRequest request,
			@ModelAttribute("timetable2") MonaleenTTV1 t, BindingResult result) {
		logger.info(t);
		logger.info("Slots: " + t.getSlots());
		Enumeration<String> test = request.getParameterNames();
		while (test.hasMoreElements()){
			String param = (String) test.nextElement();
			System.out.println(param);
		}
		String test1 = request.getParameter("monday0");
		System.out.println("TEST WORKING!!!!" + test1);
		logger.info(test1);
		
		return "timetable";
	}
	
	@RequestMapping(value = "/gotoCourt", method=RequestMethod.POST)
	public String chooseCourt(Model model, HttpServletRequest request){
		
		
		return "court";
	}

}
