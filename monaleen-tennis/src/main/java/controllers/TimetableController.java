package controllers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.EventService;
import service.TimetableService;
import service.UserService;
import timetable.MonaleenTTV1;
import timetable.Timetable;
import users.User;
import events.Event;

@Controller
public class TimetableController {

	private TimetableService timetableService;

	private EventService eventService;

	private UserService userService;

	private static Logger logger = Logger.getLogger(TimetableController.class);

	@Autowired
	public void setTimetableService(TimetableService timetableService) {
		this.timetableService = timetableService;
	}

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
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
		if (result.hasErrors()) {
			result.rejectValue("slots", "Duplicate Key",
					"Must be greater than 0");
			return createTimetablePage(model, t, result);
		}
		timetableService.create(t);
		List<Event> events = eventService.listEnabledEvents();
		List<String> eventName = new ArrayList<String>();
		for (int i = 0; i < events.size(); i++) {
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
		while (test.hasMoreElements()) {
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

		for (int i = 0; i < t.getSlots(); i++) {
			monday.add(request.getParameter("monday[" + String.valueOf(i) + "]"));
			tuesday.add(request.getParameter("tuesday[" + String.valueOf(i)
					+ "]"));
			wednesday.add(request.getParameter("wednesday[" + String.valueOf(i)
					+ "]"));
			thursday.add(request.getParameter("thursday[" + String.valueOf(i)
					+ "]"));
			friday.add(request.getParameter("friday[" + String.valueOf(i) + "]"));
			saturday.add(request.getParameter("saturday[" + String.valueOf(i)
					+ "]"));
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

	@RequestMapping(value = "/gotoCourt", method = RequestMethod.POST)
	public String chooseCourt(Model model, HttpServletRequest request) {
		model.addAttribute("court",
				timetableService.getById(request.getParameter("courtID")));
		model.addAttribute("name", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		model.addAttribute("realname", sortEmailtoName(SecurityContextHolder
				.getContext().getAuthentication().getName()));
		return "court";
	}

	@RequestMapping(value = "/courtBooked")
	public String courtBooked(Model model, String id) {
		model.addAttribute("court", timetableService.getById(id));
		model.addAttribute("name", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		model.addAttribute("realname", sortEmailtoName(SecurityContextHolder
				.getContext().getAuthentication().getName()));
		return "court";
	}

	@RequestMapping("/deleteTimetable")
	public String deleteTimetable(Model model) {
		model.addAttribute("timetable", timetableService.getAllTimetables());
		return "deleteTimetable";
	}

	@RequestMapping("/confirmTTDelete")
	public String confirmTTDelete(Model model, HttpServletRequest request) {
		Timetable t = timetableService.getById(request
				.getParameter("timetableID"));
		timetableService.delete(t);
		model.addAttribute("timetable", timetableService.getAllTimetables());
		return "deleteTimetable";
	}

	@RequestMapping("/timetableStatus")
	public String showTimetableStatus(Model model) {
		logger.info("Showing Timetable Status page....");
		model.addAttribute("timetableEnabled",
				timetableService.getEnabledTimetables());
		model.addAttribute("timetableDisabled",
				timetableService.getDisabledTimetables());
		return "timetableStatus";
	}

	@RequestMapping("/timetableStatusChange")
	public String changeTTStatus(Model model, HttpServletRequest request) {
		Timetable t = timetableService.getById(request
				.getParameter("timetableID"));
		if (t.isEnabled()) {
			t.setEnabled(false);
			timetableService.update(t);
			return showTimetableStatus(model);
		} else {
			t.setEnabled(true);
			timetableService.update(t);
			return showTimetableStatus(model);
		}

	}

	@RequestMapping("/bookCourt")
	public String bookCourt(Model model, HttpServletRequest request) {
		logger.info("Parameter is " + request.getParameter("position"));
		logger.info("Day is " + request.getParameter("day"));
		logger.info("TTID is " + request.getParameter("ttid"));

		if (eventService.exists(SecurityContextHolder.getContext()
				.getAuthentication().getName())
				&& !userIsAdmin()) {
			return "bookingExists";

		} else {

			Timetable t = timetableService
					.getById(request.getParameter("ttid"));
			List<String> list = null;
			list = t.getList(request.getParameter("day"));
			replaceValue(list, request.getParameter("position"), true);
			t.setList(list, request.getParameter("day"));
			timetableService.update(t);
			Event e = new Event();
			e.setName(SecurityContextHolder.getContext().getAuthentication()
					.getName());
			e.setAuthor("BOOKING_SYSTEM");
			eventService.createEvent(e);
			return courtBooked(model, request.getParameter("ttid"));
		}
	}

	@RequestMapping("/unbookCourt")
	public String unbookCourt(Model model, HttpServletRequest request) {
		logger.info("Parameter is " + request.getParameter("position"));
		logger.info("Day is " + request.getParameter("day"));
		logger.info("TTID is " + request.getParameter("ttid"));
		Timetable t = timetableService.getById(request.getParameter("ttid"));
		List<String> list = null;
		list = t.getList(request.getParameter("day"));
		replaceValue(list, request.getParameter("position"), false);
		t.setList(list, "day");
		Event e = new Event();
		e.setName(SecurityContextHolder.getContext().getAuthentication()
				.getName());
		e.setAuthor("BOOKING_SYSTEM");
		eventService.deleteEvent(e);
		timetableService.update(t);
		logger.info("UNBOOK SHOULD HAVE WORKED!");
		return courtBooked(model, request.getParameter("ttid"));
	}

	@RequestMapping("/resetTimetable")
	public String resetTimetable(Model model, HttpServletRequest request) {
		Timetable t = timetableService.getById(request.getParameter("courtID"));
		String[] days = { "monday", "tuesday", "wednesday", "thursday",
				"friday", "saturday", "sunday" };
		List<String> list = null;
		for (int i = 0; i < days.length; i++) {
			list = t.getList(days[i]);
			for (int j = 0; j < list.size(); j++) {
				list.set(j, "Free Court");
			}
			t.setList(list, days[i]);
		}
		eventService.deleteUserEvents(); // add variable to specify court
		return "admin";
	}

	@RequestMapping("/reset")
	public String reset(Model model) {
		logger.info("Showing Timetable page....");
		List<Timetable> timetable = timetableService.getEnabledTimetables();
		model.addAttribute("timetable", timetable);
		return "resetTimetable";
	}

	public String sortEmailtoName(String email) {
		return userService.getUserByUsername(email).getName();
	}

	public List<String> replaceValue(List<String> list, String position,
			boolean book) {
		if (book) {
			list.set(Integer.valueOf(position),
					sortEmailtoName(SecurityContextHolder.getContext()
							.getAuthentication().getName()));
			return list;
		} else {
			list.set(Integer.valueOf(position), "Free Court");
			return list;
		}

	}

	public boolean userIsAdmin() {
		List<User> admin = userService.getAdmins();
		for (int i = 0; i < admin.size(); i++) {
			if (SecurityContextHolder.getContext().getAuthentication()
					.getName().equals(admin.get(i).getUsername())) {
				return true;
			}
		}
		return false;
	}

}
