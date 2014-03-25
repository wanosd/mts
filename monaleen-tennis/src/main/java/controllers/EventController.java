package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.EventService;
import service.UserService;
import events.Event;
import events.tournaments.Tournament;

@Controller("eventController")
public class EventController {

	private EventService eventService;

	private UserService userService;

	private static Logger logger = Logger.getLogger(EventController.class);

	@RequestMapping("/createEvent")
	public String createEvent(Model model) {
		logger.info("Event Create Form...");
		model.addAttribute("event", new Event());
		return "createEvent";
	}

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public String saveEvent(Model model, @ModelAttribute("event") Event e,
			BindingResult result) {
		if (eventService.getEventById(1).equals(null)){
			e.setName("Free Court");
			e.setAuthor(userService.emailToName(SecurityContextHolder
					.getContext().getAuthentication().getName()));
			e.setEnabled(true);
			eventService.createEvent(e);
		}
		logger.info("Event Save Method....");
		if (result.hasErrors()) {
			return "createEvent";
		}

		if (eventService.exists(e.getName())) {
			result.rejectValue("name", "Duplicate Key",
					"This Event Name has already been used.");
			return "createEvent";
		} else {
			e.setAuthor(userService.emailToName(SecurityContextHolder
					.getContext().getAuthentication().getName()));
			e.setEnabled(false);
			eventService.createEvent(e);
			return viewEvent(model);
		}
	}

	@RequestMapping("/viewEvents")
	public String viewEvent(Model model) {
		List<Event> eventsDisabled = eventService.listDisabledEvents();
		List<Event> eventsEnabled = eventService.listEnabledEvents();
		model.addAttribute("eventsEnabled", eventsEnabled);
		model.addAttribute("eventsDisabled", eventsDisabled);
		return "viewEvents";
	}

	@RequestMapping("/changeEventStatus")
	public String changeEventStatus(Model model, HttpServletRequest request) {
		Event e = (Event) eventService.getEventById(Integer.valueOf(request
				.getParameter("eventID")));
		if (e.getName().equalsIgnoreCase("Free Court")){
			model.addAttribute("message", "You cannot modify the Free Court event");
			return viewEvent(model);
		}
		
		if (e.isEnabled()) {
			e.setEnabled(false);
			eventService.updateEvent(e);
			model.addAttribute("message", e.getName() + " disabled successfully");
			return viewEvent(model);
		} else {
			e.setEnabled(true);
			eventService.updateEvent(e);
			model.addAttribute("message", e.getName() + " enabled successfully");
			return viewEvent(model);
		}
	}

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
