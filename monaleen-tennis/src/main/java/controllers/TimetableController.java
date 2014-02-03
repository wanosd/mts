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

	private static Logger logger = Logger.getLogger(TimetableController.class);

	@RequestMapping("/timetable")
	public String showTimetable(Model model) {
		logger.info("Showing Timetable page....");
		MonaleenTTV1 t = new MonaleenTTV1();
		t = (MonaleenTTV1) timetableService.getTimetables().get(0);
		List<String> monday = t.getMonday();
		List<String> tuesday = t.getTuesday();
		List<String> wednesday = t.getWednesday();
		List<String> thursday = t.getThursday();
		List<String> friday = t.getFriday();
		List<String> saturday = t.getSaturday();
		List<String> sunday = t.getSunday();
		model.addAttribute("monday", monday);
		model.addAttribute("tuesday", tuesday);
		model.addAttribute("wednesday", wednesday);
		model.addAttribute("thursday", thursday);
		model.addAttribute("friday", friday);
		model.addAttribute("saturday", saturday);
		model.addAttribute("sunday", sunday);
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
		t = (MonaleenTTV1) model.asMap().get("timetable");
		model.addAttribute("timetable2", t);
		model.addAttribute("count", t.getSlots());
		return "fillTimetable";
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

}
