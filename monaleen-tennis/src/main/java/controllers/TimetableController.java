package controllers;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import logs.Log;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import reports.CSVCreator;
import service.EventService;
import service.LogService;
import service.RoleService;
import service.TimetableService;
import service.UserService;
import timetable.MonaleenTTV1;
import timetable.Timetable;
import users.User;
import email.Email;
import email.I_Message;
import events.Event;
import events.I_Event;

@Controller
public class TimetableController {

	private TimetableService timetableService;
	private EventService eventService;
	private UserService userService;
	private RoleService roleService;
	private LogService logService;
	private static Logger logger = Logger.getLogger(TimetableController.class);
	private JavaMailSender mailSender;

	/**
	 * Autowiring for service objects
	 */
	
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

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	
	/**
	 * Methods to display Timetable page
	 */

	@RequestMapping("/timetable")
	public String showTimetable(Model model) {
		logger.info("Showing Timetable page....");
		List<Timetable> timetable = timetableService.getEnabledTimetables();
		model.addAttribute("timetable", timetable);
		return "timetable";
	}

	@RequestMapping("/createTimetable")
	public String createTimetablePage(Model model,
			@ModelAttribute("timetable") MonaleenTTV1 t) {
		checkEventExists("Free Court");
		model.addAttribute("timetable", t);
		return "createTimetable";
	}
	

	@RequestMapping(value = "/timetableDefaults", method = RequestMethod.POST)
	public String timetableDefaults(Model model, HttpServletRequest request,
			@ModelAttribute("timetable") MonaleenTTV1 t, BindingResult result) {
		if (result.hasErrors()) {
			return "createTimetable";
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

		t = (MonaleenTTV1) timetableService.getById(request.getParameter("id"));
		t = (MonaleenTTV1) constructTimetable(t, request);
		t.setSeries(timetableService.getNewSeries() + 1);
		for (int i = 0; i < t.getTotal(); i++) {
			MonaleenTTV1 test = new MonaleenTTV1();
			copyList(test, t);
			test.setName(t.getName() + " Week " + (i + 1));
			test.setSeries(t.getSeries());
			test.setSlots(t.getSlots());
			test.setTotal(t.getTotal());
			test.setEnabled(false);
			timetableService.create(test);
		}
		timetableService.delete(t);

		model.addAttribute("timetable", timetableService.getEnabledTimetables());

		return showTimetableStatus(model);
	}
	
	@RequestMapping("/chooseEdit")
	public String edit(Model model) {
		logger.info("Showing Timetable Edit page....");
		List<Timetable> timetable = timetableService.getAllTimetables();
		model.addAttribute("timetable", timetable);
		return "chooseEdit";
	}

	@RequestMapping("/editTimetable")
	public String editTimetable(Model model, HttpServletRequest request) {
		List<Event> events = eventService.listAllEventsEnabledFilter();
		logger.info(events.toString());
		List<String> eventName = new ArrayList<String>();
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getName().contains("@")) {
				for (int k = 0; k < eventName.size(); k++) {
					if (!eventName.contains(sortEmailtoName(events.get(i)
							.getName()))) {
						eventName.add(sortEmailtoName(events.get(i).getName()));
					}
				}
			} else {
				eventName.add(events.get(i).getName());
			}
		}
		Timetable t;
		List<Timetable> series = timetableService.getTimetableSeries(Integer.parseInt(request.getParameter("series")));
		t = (MonaleenTTV1) series.get((Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1));
		logger.info("TEST: " + t.getMonday());
		int count = t.getSlots();
		model.addAttribute("timetable", t);
		model.addAttribute("events", eventName);
		model.addAttribute("count", count);
		return "confirmEdit";
	}


	@RequestMapping("/finalizeEditTT")
	public String finalizeEdit(Model model, HttpServletRequest request,
			@ModelAttribute("timetable") MonaleenTTV1 t) {
		
		List<Timetable> series = timetableService.getTimetableSeries(Integer.parseInt(request.getParameter("series")));
		t = (MonaleenTTV1) series.get((Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1));
		t = (MonaleenTTV1) constructTimetable(t, request);
		timetableService.update(t);
		model.addAttribute("timetable", timetableService.getEnabledTimetables());
		return showTimetable(model);
	}
	
	public Timetable constructTimetable(Timetable t, HttpServletRequest request){
		List<String> monday = new ArrayList<String>();
		List<String> tuesday = new ArrayList<String>();
		List<String> wednesday = new ArrayList<String>();
		List<String> thursday = new ArrayList<String>();
		List<String> friday = new ArrayList<String>();
		List<String> saturday = new ArrayList<String>();
		List<String> sunday = new ArrayList<String>();
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
		
		return t;
	}
	@RequestMapping(value = "/gotoCourt", method = RequestMethod.POST)
	public String chooseCourt(Model model, HttpServletRequest request) {
		int courtID = Integer.valueOf(request.getParameter("courtID"));
		Timetable first = timetableService.getById(request.getParameter("courtID"));
		List<Timetable> firstSeries = timetableService.getTimetableSeries(first.getSeries());
		Timetable current = firstSeries.get((Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1));
		logger.info("GETTING POSITION: " + (Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1));
		model = addDateToTimetable(model, String.valueOf(current.getId()));
		model.addAttribute("series",timetableService.getById(request.getParameter("courtID")).getSeries());
		model.addAttribute("name", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		model.addAttribute("court", current);
		logger.info("TT:" + firstSeries.get(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) - 1).toString());
		model.addAttribute("realname", sortEmailtoName(SecurityContextHolder
				.getContext().getAuthentication().getName()));
		String loggedIn = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		int left = bookingsLeft(loggedIn, String.valueOf(courtID));
		model.addAttribute("bookings", left);
		int nextCourt = courtID + 1;
		int prevCourt = courtID - 1;
		if (seriesMatch(courtID, nextCourt)) {
			model.addAttribute("next",(current.getId() + 1));
		}
		if (seriesMatch(courtID, prevCourt)) {
				model.addAttribute("prev", (current.getId() - 1));
		}
		return "court";
	}
	
	@RequestMapping("/changeCourt")
	public String prevNext(Model model, HttpServletRequest request){
		Timetable tt = timetableService.getById(request.getParameter("courtID"));
		int courtID = Integer.valueOf(request.getParameter("courtID"));
		model = addDateToTimetable(model, String.valueOf(tt.getId()));
		model.addAttribute("series",timetableService.getById(request.getParameter("courtID")).getSeries());
		model.addAttribute("name", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		model.addAttribute("court", tt);
		model.addAttribute("realname", sortEmailtoName(SecurityContextHolder
				.getContext().getAuthentication().getName()));
		String loggedIn = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		int left = bookingsLeft(loggedIn, String.valueOf(courtID));
		model.addAttribute("bookings", left);
	
		int nextCourt = courtID + 1;
		int prevCourt = courtID - 1;
		if (seriesMatch(courtID, nextCourt)) {
			model.addAttribute("next",(tt.getId() + 1));
		}
		if (seriesMatch(courtID, prevCourt)) {
			model.addAttribute("prev", (tt.getId() - 1));
		}
		
		return "court";
	}

	private Model addDateToTimetable(Model model, String courtID) {
		String format = "dd/MM/yyyy";
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = new Date();
		String input = df.format(date);
		int week = 0;
		try {
			date = df.parse(input);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			week = cal.get(Calendar.WEEK_OF_YEAR);
			int position = checkPosition(timetableService.getById(courtID)) + 1;
			cal = Calendar.getInstance();
			cal.set(Calendar.WEEK_OF_YEAR, position);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			model.addAttribute("week", week);
			model.addAttribute("date", input);
			model.addAttribute("testDate", df.format(cal.getTime()));
			model.addAttribute("position", position);
			return model;
		} catch (ParseException e) {
			return model;
		}

	}

	private int checkPosition(Timetable byId) {
		List<Timetable> list = timetableService.getTimetableSeries(byId
				.getSeries());
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(byId)) {
				return i;
			}
		}
		return 0;
	}

	private boolean seriesMatch(int courtID, int nextCourt) {
		if (timetableService.getById(String.valueOf(nextCourt)) == null) {
			return false;
		}
		if (timetableService.getById(String.valueOf(courtID)).getSeries() == timetableService
				.getById(String.valueOf(nextCourt)).getSeries()) {
			return true;
		} else
			return false;
	}

	@RequestMapping(value = "/courtBooked")
	public String courtBooked(Model model, String id) {
		model = addDateToTimetable(model, id);
		model.addAttribute("court", timetableService.getById(id));
		model.addAttribute("name", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		model.addAttribute("realname", sortEmailtoName(SecurityContextHolder
				.getContext().getAuthentication().getName()));
		String loggedIn = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		int left = bookingsLeft(loggedIn, String.valueOf(id));
		model.addAttribute("bookings", left);
		int courtID = Integer.valueOf(id);
		int nextCourt = courtID + 1;
		int prevCourt = courtID - 1;
		if (seriesMatch(courtID, nextCourt)) {
			model.addAttribute("next", Integer.valueOf(id) + 1);
		}
		if (seriesMatch(courtID, prevCourt)) {
			model.addAttribute("prev", Integer.valueOf(id) - 1);
		}

		return "court";
	}

	@RequestMapping("/deleteTimetable")
	public String deleteTimetable(Model model) {
		model.addAttribute("timetable", timetableService.getTimetableAllSeries());
		return "deleteTimetable";
	}
	

	@RequestMapping("/confirmTTDelete")
	public String confirmTTDelete(Model model, HttpServletRequest request) {
		Timetable t = timetableService.getById(request
				.getParameter("timetableID"));
		timetableService.deleteSeries(t.getSeries());
		model.addAttribute("timetable", timetableService.getTimetableAllSeries());
		return "deleteTimetable";
	}

	@RequestMapping("/timetableStatus")
	public String showTimetableStatus(Model model) {
		logger.info("Showing Timetable Status page....");
		model.addAttribute("timetableEnabled",
				timetableService.getTimetableAllSeries());
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
		logger.info("DAY OF THE WEEK IS: " + Calendar.DAY_OF_WEEK);

		String loggedIn = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		if (roleService.getNoBookings(userService.getUserByUsername(loggedIn).getAuthority()) == eventService.checkBookingsUser(loggedIn, request.getParameter("ttid"))) {
			return "bookingExists";

		}

		else {

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
			e.setCourtid(Integer.valueOf(request.getParameter("ttid")));
			eventService.createEvent(e);
			userService.getUserByUsername(
					SecurityContextHolder.getContext().getAuthentication()
							.getName()).setBookings_left(
					userService.getUserByUsername(
							SecurityContextHolder.getContext()
									.getAuthentication().getName())
							.getBookings_left() + 1);
			return courtBooked(model, request.getParameter("ttid"));
		}
	}

	@RequestMapping("/unbookCourt")
	public String unbookCourt(Model model, HttpServletRequest request) {
		String loggedin = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Parameter is " + request.getParameter("position"));
		logger.info("Day is " + request.getParameter("day"));
		logger.info("TTID is " + request.getParameter("ttid"));
		Timetable t = timetableService.getById(request.getParameter("ttid"));
		List<String> list = null;
		list = t.getList(request.getParameter("day"));
		replaceValue(list, request.getParameter("position"), false);
		t.setList(list, "day");
		unbookEvent(loggedin, t.getId());
		timetableService.update(t);
		userService.getUserByUsername(loggedin).setBookings_left(userService.getUserByUsername(loggedin).getBookings_left() - 1);
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
		eventService.deleteUserEvents(request.getParameter("courtID"));
		return "admin";
	}

	@RequestMapping("/reset")
	public String reset(Model model) {
		logger.info("Showing Timetable Reset page....");
		List<Timetable> timetable = timetableService.getEnabledTimetables();
		model.addAttribute("timetable", timetable);
		return "resetTimetable";
	}

	
	
	
	@RequestMapping("/reportNoShow")
	public String reportNoShow(Model model, HttpServletRequest request){
		I_Message message = new Email(mailSender);
		String text = "Reported no show from " + request.getParameter("bookedUser");
		message.set(SecurityContextHolder.getContext().getAuthentication().getName(), "monaleentennisclub@gmail.com", "No Show", text , null);
		return reportUser(model, request.getParameter("bookedUser"), message, mailSender);
	}
	
	/**
	 * Non Mapping Methods
	 * @return
	 */
	
	/**
	 * Returns the real name of currently logged in user
	 * @param email
	 * @return
	 */
	public String sortEmailtoName(String email) {
		return userService.getUserByUsername(email).getName();
	}

	/**
	 * Replaces a position in a List with the name of the person booking
	 * @param list
	 * @param position
	 * @param book
	 * @return
	 */
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

	public void copyList(MonaleenTTV1 copy, MonaleenTTV1 original) {
		for (int i = 0; i < original.getMonday().size(); i++) {
			copy.getMonday().add(original.getMonday().get(i));
			copy.getTuesday().add(original.getTuesday().get(i));
			copy.getWednesday().add(original.getWednesday().get(i));
			copy.getThursday().add(original.getThursday().get(i));
			copy.getFriday().add(original.getFriday().get(i));
			copy.getSaturday().add(original.getSaturday().get(i));
			copy.getSunday().add(original.getSunday().get(i));
		}
	}
	
	public void checkEventExists(String event){
		if (!eventService.exists(event)) {
			logger.info("into create default event");
			Event e = new Event();
			e.setName("Free Court");
			e.setAuthor(userService.emailToName(SecurityContextHolder
					.getContext().getAuthentication().getName()));
			e.setEnabled(true);
			eventService.createEvent(e);
		}
	}
	
	public int bookingsLeft(String username, String id){
		return roleService.getNoBookings(userService.getUserByUsername(
				username).getAuthority())
				- eventService.checkBookingsUser(username, id);
	}
	
	private void unbookEvent(String loggedin, int id) {
		eventService.removeBooking(loggedin, id);	
	}
	
	private String reportUser(Model model, String username, I_Message message, JavaMailSender mailSender){
		logger.info("Reporting NoShow");
		DateFormat df = new SimpleDateFormat("ddmmyyyyHHmmss");
		Date today = Calendar.getInstance().getTime();
		String date = df.format(today);
		date.replace("\\", "");
		date.replace(" ", "");
		Log log = new Log(SecurityContextHolder.getContext().getAuthentication().getName(), date, username, "noshow");
		if (logService == null) {
			logger.info("Log Service is Null");
		}
		logger.info("About to attempt sending message");
		if (message.send(mailSender)){
			logService.createLog(log);
			model.addAttribute("message", "User reported");
			return "emailSent";
		}
		else{
			log.setInformationType("Failed Attempt");
			logService.createLog(log);
			model.addAttribute("message", "Message Not Sent. Try again.");
			return "emailSent";
		}
	}
	
	
}
