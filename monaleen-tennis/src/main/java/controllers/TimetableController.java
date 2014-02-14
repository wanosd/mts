package controllers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.EventService;
import service.TimetableService;
import timetable.MonaleenTTV1;
import timetable.Timetable;
import events.Event;

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
		if (result.hasErrors()){
			result.rejectValue("slots", "Duplicate Key",
					"Must be greater than 0");
			return createTimetablePage(model, t, result);
		}
		timetableService.create(t);
		List<Event> events = eventService.listEnabledEvents();
		List<String> eventName = new ArrayList<String>();
		for (int i = 0; i < events.size(); i++){
			eventName.add(events.get(i).getName());
		}
		int count = t.getSlots();
		model.addAttribute("timetable", t);
		model.addAttribute("events", eventName);
		model.addAttribute("count", count);
		return "fillTimetable";
	}

	@RequestMapping(value = "/finalizeTimetable", method = RequestMethod.POST)
	public String finalTimetable(Model model, HttpServletRequest request,
			@ModelAttribute("timetable") MonaleenTTV1 t, BindingResult result) {

		Enumeration<String> test = request.getParameterNames();
		while (test.hasMoreElements()){
			String param = (String) test.nextElement();
			System.out.println(param);
		}
		t = (MonaleenTTV1) timetableService.getById(request.getParameter("id"));
		
		
		List<String> monday = new ArrayList<String>();
		List<String> tuesday = new ArrayList<String>();
		List<String> wednesday = new ArrayList<String>();
		List<String> thursday = new ArrayList<String>();
		List<String> friday = new ArrayList<String>();
		List<String> saturday = new ArrayList<String>();
		List<String> sunday = new ArrayList<String>();
		String slots = request.getParameter("timetableID");
		System.out.println("Slots: " + slots);
	
		for (int i = 0; i < t.getSlots(); i++){
			monday.add(request.getParameter("monday[" + String.valueOf(i) + "]"));
			tuesday.add(request.getParameter("tuesday[" + String.valueOf(i) + "]"));
			wednesday.add(request.getParameter("wednesday[" + String.valueOf(i) + "]"));
			thursday.add(request.getParameter("thursday[" + String.valueOf(i) + "]"));
			friday.add(request.getParameter("friday[" + String.valueOf(i) + "]"));
			saturday.add(request.getParameter("saturday[" + String.valueOf(i) + "]"));
			sunday.add(request.getParameter("sunday[" + String.valueOf(i) + "]"));
		}

		t.setMonday(monday);
		t.setTuesday(tuesday);
		t.setWednesday(wednesday);
		t.setThursday(thursday);
		t.setFriday(friday);
		t.setSaturday(saturday);
		t.setSunday(sunday);
		timetableService.update(t);
	
		model.addAttribute("timetable", timetableService.getEnabledTimetables());
		
		return "timetable";
	}
	
	@RequestMapping(value = "/gotoCourt", method=RequestMethod.POST)
	public String chooseCourt(Model model, HttpServletRequest request){
		model.addAttribute("court", timetableService.getById(request.getParameter("courtID")));
		return "court";
	}
	
	@RequestMapping("/deleteTimetable")
	public String deleteTimetable(Model model){
		model.addAttribute("timetable", timetableService.getAllTimetables());
		return "deleteTimetable";
	}

}
