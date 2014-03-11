package controllers;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import logs.Log;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import email.*;
import analytics.MTCAnalytics;
import reports.CSVCreator;
import service.AnalyticsService;
import service.EventService;
import service.LogService;
import service.RoleService;
import service.TimetableService;
import service.UserService;
import timetable.Timetable;
import users.FormValidationGroup;
import users.Role;
import users.User;

@Controller
public class MembersController {

	private UserService userService;
	private LogService logService;
	private RoleService roleService;
	private AnalyticsService analyticsService;
	private EventService eventService;
	private TimetableService timetableService;
	private static Logger logger = Logger.getLogger(MembersController.class);

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping("/members")
	public String showMembers(Model model) {
		logger.info("Showing Members Page....");
		List<User> userList = userService.getCurrentMembers();
		model.addAttribute("users", userList);
		return "members";
	}

	@RequestMapping("/emailSent")
	public String emailSent() {
		return "emailSent";
	}

	@RequestMapping("/adminAnalysis")
	public String adminAnalysis(Model model) {
		List<Log> logs = logService.getNoShowLogStats();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < logs.size(); i++) {
			if (map.containsKey(logs.get(i).getInformationType())) {
				int current = map.get(logs.get(i).getInformationType());
				current++;
				map.put(logs.get(i).getInformationType(), current);
			} else {
				map.put(logs.get(i).getInformationType(), 1);
			}
		}
		model = analyseTimetable(model);
		model.addAttribute("map", map);
		logger.info("MAP" + map.toString());
		return "adminAnalysis";
	}

	@RequestMapping("/emailMembersToAddress")
	public String emailMembers(Model model) {
		List<User> users = userService.getCurrentMembers();
		String name = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		logger.info("Entering CreateCSVToEmail");
		DateFormat df = new SimpleDateFormat("ddmmyyyyHHmmss");
		Date today = Calendar.getInstance().getTime();
		String date = df.format(today);
		date.replace("\\", "");
		date.replace(" ", "");
		Log log = new Log(SecurityContextHolder.getContext()
				.getAuthentication().getName(), date, "ListExistingUsers",
				"datacopy");
		if (logService == null) {
			logger.info("Log Service is Null");
		}
		File file = new File(name.replace("@", "") + date + ".csv");
		CSVCreator.createCSVToEmail(users, file);
		logger.info("About to attempt sending message");
		if (sendMessage(file, SecurityContextHolder.getContext().getAuthentication().getName(),
				"This is a copy of MTC Users. Please treat with confidence",
				"Monaleen Tennis Club Users")) {
			logService.createLog(log);
			model.addAttribute("message", "Email successfully sent to : "
					+ SecurityContextHolder.getContext().getAuthentication()
							.getName());
		} else {
			log.setInformationType("Failed Attempt");
			logService.createLog(log);
			model.addAttribute("message", "Message Failed. Try Again Later.");
		}

		return "emailSent";
	}

	private boolean sendMessage(File file, String receiver, String text,
			String subject) {
		I_Message message = new Email(mailSender);
		message.set("admin@monaleentennisclub.ie", receiver, subject, text,
				file);
		return message.send(mailSender);
	}

	@RequestMapping("/viewAllMembers")
	public String viewAllMembers(Model model) {
		List<User> userList = userService.getCurrentMembers();
		List<User> nonuserList = userService.getPendingMembers();
		List<User> admins = userService.getAdmins();
		model.addAttribute("users", userList);
		model.addAttribute("nonusers", nonuserList);
		model.addAttribute("admin", admins);
		return "viewAllMembers";
	}

	@RequestMapping("/registerSuccess")
	public String showRegSuccess() {
		logger.info("Showing Reg Success Page....");
		return "registerSuccess";
	}

	@RequestMapping("/admin")
	public String showAdmin() {
		logger.info("Showing Admin Page....");
		userService.exists("test");
		return "admin";
	}

	@RequestMapping("/approveMembers")
	public String showPendingMembers(Model model) {
		logger.info("Showing Pending Members Page....");
		List<User> toApprove = userService.getPendingMembers();
		model.addAttribute("toApprove", toApprove);
		return "approveMembers";
	}

	@RequestMapping("/approveFinalize")
	public String approveMembers(Model model, HttpServletRequest request) {
		logger.info("Moving to approveFinalize and back to approveMembers");
		userService.enableUser(request.getParameter("username"));
		sendMessage(
				null,
				request.getParameter("username"),
				"You Account with Monaleen Tennis Club has been approved. You may now log in.",
				"Monaleen Tennis Club: Account Approved");
		createLog(request.getParameter("username"), "approveUser");
		List<User> toApprove = userService.getPendingMembers();
		/**
		 * MTCAnalytics a = analyticsService.get(); if (a == null){ a = new
		 * MTCAnalytics(); } a.run(userService); analyticsService.save(a);
		 **/
		model.addAttribute("toApprove", toApprove);
		return "approveMembers";
	}

	@RequestMapping("/blockMembers")
	public String showActiveMembers(Model model) {
		logger.info("Showing Pending Members Page....");
		List<User> toBlock = userService.getCurrentMembers();
		toBlock = removeLoggedIn(toBlock);
		model.addAttribute("toBlock", toBlock);
		return "blockMembers";
	}

	@RequestMapping("/blockFinalize")
	public String blockMembers(Model model, HttpServletRequest request) {
		logger.info("Moving to blockFinalize and back to blockMembers");
		userService.disableUser(request.getParameter("username"));
		createLog(request.getParameter("username"), "suspendUser");
		sendMessage(
				null,
				request.getParameter("username"),
				"You Account with Monaleen Tennis Club has been disabled. Please contact admin for details.",
				"Monaleen Tennis Club: Account Disabled");
		List<User> toBlock = userService.getCurrentMembers();
		toBlock = removeLoggedIn(toBlock);
		model.addAttribute("toBlock", toBlock);
		return "blockMembers";
	}

	@RequestMapping("/createmembers")
	public String createMembers(Model model) {
		roleService.defaultRoles();
		logger.info("Showing Create Members Page....");
		model.addAttribute("member", new User());
		return "createmembers";
	}

	/*
	 * Method will only take Post requests Registers user to database
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(
			Model model,
			@Validated(FormValidationGroup.class) @ModelAttribute("member") User member,
			BindingResult result) {

		logger.info("Showing Registration Page....");

		if (result.hasErrors()) {
			return "createmembers";
		}

		if (userService.exists(member.getUsername())) {
			result.rejectValue("username", "Duplicate Key",
					"This email address has already been used");
			return "createmembers";
		}

		else {
			try {
				member.setAuthority("ROLE_MEMBER");
				userService.create(member);
				DateFormat df = new SimpleDateFormat("ddmmyyyyHHmmss");
				Date today = Calendar.getInstance().getTime();
				String date = df.format(today);
				date.replace("\\", "");
				date.replace(" ", "");
				Log log = new Log(member.getName(), date, "userReg",
						"userReg");
				if (sendMessage(null, userService.getAdmins().get(0).getUsername(),
						"The following user: " +  member.getName() + " : " + member.getUsername() + " has registered on the site. Please review application.",
						"MemberRegistration") && sendMessage(null, member.getUsername(), "Your registration has been received. You will receive confirmation when approved.", "Monaleen GAA Tennis Club Registration")) {
					
					logService.createLog(log);
				} else {
					log.setInformationType("userReg - Email Fail");
					logService.createLog(log);
					model.addAttribute("message", "Message Failed. Try Again Later.");
				}
				return "registerSuccess";
			} catch (Exception e) {
				logger.info("ERROR!!!!!!!!!!!" + e.getClass());
				return "error";
			}

		}
	}

	@RequestMapping("/profile")
	public String editProfile(Model model) {
		logger.info("Showing Edit Profile Page.....");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = userService.getUserByUsername(auth.getName());
		model.addAttribute("member", user);
		return "profile";
	}

	@RequestMapping("/displayUsers")
	public String chooseUserAdmin(Model model) {
		model.addAttribute("users", userService.getCurrentMembers());
		return "displayUsers";
	}

	@RequestMapping("/adminEditProfile")
	public String chooseUserAdminEdit(Model model, HttpServletRequest request) {
		logger.info("Username is " + request.getParameter("username"));
		model.addAttribute("member",
				userService.getUserByUsername(request.getParameter("username")));
		model.addAttribute("roles", roleService.getRoleNames());
		return "adminEditProfile";
	}

	@RequestMapping(value = "/adminProfileUpdate", method = RequestMethod.POST)
	public String doAdminEditProfile(Model model,
			@ModelAttribute("member") User member, BindingResult result,
			HttpServletRequest request) {
		logger.info("Showing Profile Updated.....");
		if (result.hasErrors()) {
			logger.info("Errors found in BindingResult object....");
			return "adminEditProfile";
		}
		if (userService.exists(member.getUsername())) {
			result.rejectValue("username", "Duplicate Key",
					"This email address has already been used");
			return "adminEditProfile";
		} else {
			try {
				logger.info("About to  update user");
				userService.editProfile(member,
						request.getParameter("username"));
				logger.info("User Updated");
				return "profileUpdated";
			} catch (Exception e) {
				logger.info("Database update failed. Cause: " + e.getClass());
				return "error";
			}
		}
	}

	@RequestMapping(value = "/profileUpdated", method = RequestMethod.POST)
	public String doEditProfile(Model model,
			@ModelAttribute("member") User member, BindingResult result) {
		logger.info("Showing Profile Updated.....");
		if (result.hasErrors()) {
			logger.info("Errors found in BindingResult object....");
			return "profile";
		}
		if (userService.exists(member.getUsername())) {
			result.rejectValue("username", "Duplicate Key",
					"This email address has already been used");
			return "profile";
		} else {
			try {
				logger.info("About to  update user");
				userService.editProfile(member, SecurityContextHolder
						.getContext().getAuthentication().getName());
				logger.info("User Updated");
				return "profileUpdated";
			} catch (Exception e) {
				logger.info("Database update failed. Cause: " + e.getClass());
				return "error";
			}
		}
	}

	@RequestMapping("/createGrade")
	public String createGrade() {
		return "createGrade";
	}

	@RequestMapping("/saveGrade")
	public String saveGrade(Model model, HttpServletRequest request) {
		userService.createGrade(request.getParameter("grade"));
		return "grades";
	}

	public List<User> removeLoggedIn(List<User> users) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		User temp = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				temp = users.get(i);
			}
		}

		if (temp != null) {
			users.remove(temp);
			return users;
		} else {
			return users;
		}
	}

	public Model analyseTimetable(Model model) {

		Map<String, Map<String, Integer>> attributes = new HashMap<String, Map<String, Integer>>();
		List<Timetable> series = timetableService.getTimetableAllSeries();
		List<Timetable> firstSeries = timetableService
				.getTimetableSeries(series.get(0).getSeries());
		Timetable current = firstSeries.get((Calendar.getInstance().get(
				Calendar.WEEK_OF_YEAR) - 1));
		Timetable next = firstSeries.get(Calendar.getInstance().get(
				Calendar.WEEK_OF_YEAR));
		if (next != null) {
			attributes.put("nextmon", iterateList(next.getMonday()));
			attributes.put("nexttue", iterateList(next.getTuesday()));
			attributes.put("nextwed", iterateList(next.getWednesday()));
			attributes.put("nextthur", iterateList(next.getThursday()));
			attributes.put("nextfri", iterateList(next.getFriday()));
			attributes.put("nextsat", iterateList(next.getSaturday()));
			attributes.put("nextsun", iterateList(next.getSunday()));
		}
		attributes.put("monday", iterateList(current.getMonday()));
		attributes.put("tuesday", iterateList(current.getTuesday()));
		attributes.put("wednesday", iterateList(current.getWednesday()));
		attributes.put("thursday", iterateList(current.getThursday()));
		attributes.put("friday", iterateList(current.getFriday()));
		attributes.put("saturday", iterateList(current.getSaturday()));
		attributes.put("sunday", iterateList(current.getSunday()));
		model.addAllAttributes(attributes);
		return model;
	}

	public Map<String, Integer> iterateList(List<String> list) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("free", 0);
		map.put("club", 0);
		map.put("booked", 0);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals("Free Court")) {
				int j = map.get("free");
				j++;
				map.put("free", j);
			} else if (eventService.exists(list.get(i))) {
				int j = map.get("club");
				j++;
				map.put("club", j);
			} else {
				int j = map.get("booked");
				j++;
				map.put("booked", j);
			}
		}
		return map;
	}
	
	public String createLog(String username, String reason){
		DateFormat df = new SimpleDateFormat("ddmmyyyyHHmss");
		Date today = Calendar.getInstance().getTime();
		String date = df.format(today);
		date.replace("\\", "");
		date.replace(" ", "");
		Log log = new Log(SecurityContextHolder.getContext()
				.getAuthentication().getName(), date, username, reason);
		logService.createLog(log);
		return date;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setAnalyticsService(AnalyticsService analyticsService) {
		this.analyticsService = analyticsService;
	}

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Autowired
	public void setTimetableService(TimetableService timetableService) {
		this.timetableService = timetableService;
	}

}
