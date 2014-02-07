package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import events.Event;
import events.tournaments.Tournament;
import service.EventService;
import service.TournamentService;
import service.UserService;
import users.FormValidationGroup;
import users.User;

@Controller
public class TournamentController {

	private static Logger logger = Logger.getLogger(MembersController.class);
	private TournamentService tournamentService;
	private UserService userService;
	private EventService eventService;

	@RequestMapping("/createTournament")
	public String createTournament(Model model) {
		logger.info("Showing Create Tournament Page....");
		model.addAttribute("tournament", new Tournament());
		return "createTournament";
	}

	@RequestMapping(value = "/registerTournament", method = RequestMethod.POST)
	public String doCreateTournament(
			Model model,
			@Validated(FormValidationGroup.class) @ModelAttribute("tournament") Tournament t,
			BindingResult result) {

		logger.info("Showing Tournament Registration Page....");
		logger.info(result.getAllErrors());

		if (result.hasErrors()) {
			return "createTournament";
		} 
		
		if (tournamentService.exists(t.getTournamentName())){
			if (eventService.exists(t.getTournamentName())){
				result.rejectValue("tournamentName", "Duplicate Key",
						"An event of this name already exists");
					return "createTournament";
			}
			result.rejectValue("tournamentName", "Duplicate Key",
					"A tournament of this name already exists");
			return "createTournament";
		}
		else {
			try {
				logger.info(t.toString());
				tournamentService.create(t);
				eventCreation(t);
				logger.info("Tournament Created");
				return "tournamentSuccess";
			} catch (Exception e) {
				return "error";
			}
		}
	}

	@RequestMapping("/tournamentSuccess")
	public String tournamentSuccess() {
		logger.info("Showing Tournament Success Page....");
		return "tournamentSuccess";
	}

	@RequestMapping("/tournaments")
	public String showTournaments(Model model) {
		logger.info("Showing Tournament Display page....");
		model = tournamentRegStatus(model);
		return "tournaments";
	}

	@RequestMapping("/tournamentStatus")
	public String showTournamentStatus(Model model) {
		logger.info("Showing Tournament Status page....");
		List<Tournament> tournamentEnabled = tournamentService
				.getCurrentTournaments();
		List<Tournament> tournamentDisabled = tournamentService
				.getClosedTournaments();
		List<Tournament> tournamentStarted = tournamentService.getStartedTournaments();
		List<Tournament> tournamentUnstarted = tournamentService.getUnstartedTournaments();
		model.addAttribute("tournamentEnabled", tournamentEnabled);
		model.addAttribute("tournamentDisabled", tournamentDisabled);
		model.addAttribute("tournamentStarted", tournamentStarted);
		model.addAttribute("tournamentUnstarted", tournamentUnstarted);
		
		return "tournamentStatus";
	}

	@RequestMapping("/tourStatusChange")
	public String changeTourStatus(Model model, HttpServletRequest request) {
		Tournament t = tournamentService.getTournamentById(request
				.getParameter("tournamentID"));
		if (t.isRegistrationOpen()) {
			t.setRegistrationOpen(false);
			tournamentService.updateTournament(t);
			Event e = (Event) eventService.getEventIdByName(t.getTournamentName());
			e.setEnabled(false);
			eventService.updateEvent(e);
			return showTournamentStatus(model);
		} else {
			t.setRegistrationOpen(true);
			tournamentService.updateTournament(t);
			Event e = (Event) eventService.getEventIdByName(t.getTournamentName());
			e.setEnabled(true);
			eventService.updateEvent(e);
			return showTournamentStatus(model);
		}
	}
	
	@RequestMapping("/tourStartChange")
	public String changeTourStart(Model model, HttpServletRequest request) {
		Tournament t = tournamentService.getTournamentById(request
				.getParameter("tournamentID"));
		if (t.isTournamentStarted()) {
			t.setTournamentStarted(false);
			tournamentService.updateTournament(t);
			return showTournamentStatus(model);
		} else {
			t.setTournamentStarted(true);
			tournamentService.updateTournament(t);
			return showTournamentStatus(model);
		}
	}

	@RequestMapping("/tournamentRegister")
	public String registerForTournament(Model model, HttpServletRequest request) {
		logger.info("Registering for Tournament....");
		logger.info("Parameter ID is : " + request.getParameter("tournamentID"));
		model = tournamentRegStatus(model);
		Tournament t = tournamentService.getTournamentById(request
				.getParameter("tournamentID"));
		if (!checkRegistered(t)) {
			tournamentService.register(t);
			model = tournamentRegStatus(model);
			return "tournaments";
		} else
			return "error";
	}

	@RequestMapping("/tournamentUnregister")
	public String unregisterForTournament(Model model,
			HttpServletRequest request) {
		logger.info("Unregistering for Tournament....");
		logger.info("Parameter ID is : " + request.getParameter("tournamentID"));
		model = tournamentRegStatus(model);
		Tournament t = tournamentService.getTournamentById(request
				.getParameter("tournamentID"));
		if (checkRegistered(t)) {
			tournamentService.unregister(t);
			model = tournamentRegStatus(model);
			return "tournaments";
		} else
			return "error";
	}
	
	@RequestMapping("/deleteTournament")
	public String deletePage(Model model){
		List<Tournament> tour = tournamentService.getAllTournaments();
		model.addAttribute("tour", tour);
		return "deleteTournament";
	}
	
	@RequestMapping("/confirmDelete")
	public String deleteTournament(Model model, HttpServletRequest request){
		Tournament t = tournamentService.getTournamentById(request
				.getParameter("tournamentID"));
		tournamentService.deleteTournament(t);
		eventService.deleteEvent(eventService.getEventIdByName(t.getTournamentName()));
		return "deleteTournament";
	}

	@RequestMapping("/alreadyReg")
	public String duplicateReg() {
		return "alreadyReg";
	}

	@RequestMapping("checkRegistered")
	public String checkRegistered(Model model, HttpServletRequest request) {
		Tournament t = tournamentService.getTournamentById(request
				.getParameter("id"));
		logger.info("ID of T: " + request.getParameter("id"));
		logger.info("Size of List: " + t.getUsername().size());
		List<String> registered = t.getUsername();
		registered = sortEmailtoName(registered);
		model.addAttribute("registered", registered);
		return "checkRegistered";
	}
	
	@RequestMapping("changeRegistration")

	private boolean checkRegistered(Tournament t) {
		List<String> users = t.getUsername();
		String name = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		for (String s : users) {
			if (s.equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Autowired
	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public Model tournamentRegStatus(Model model) {
		List<Tournament> tournament = tournamentService.getCurrentTournaments();
		List<Tournament> unregTour = new ArrayList<Tournament>();
		List<Tournament> regTour = new ArrayList<Tournament>();
		for (int i = 0; i < tournament.size(); i++) {
			if (checkRegistered(tournament.get(i))) {
				regTour.add(tournament.get(i));
			} else {
				unregTour.add(tournament.get(i));
			}
		}
		model.addAttribute("unregTour", unregTour);
		model.addAttribute("regTour", regTour);
		return model;
	}

	public List<String> sortEmailtoName(List<String> list) {
		logger.info("Convert List Size: " + list.size());
		List<String> finalList = new ArrayList<String>();
		List<User> users = userService.getCurrentMembers();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < users.size(); j++) {
				if (list.get(i).equals(users.get(j).getUsername())) {
					finalList.add(users.get(j).getName());
				}
			}
		}
		return finalList;
	}
	
	public void eventCreation(Tournament t){
		Event e = new Event();
		e.setName(t.getTournamentName());
		e.setAuthor(userService.emailToName(SecurityContextHolder.getContext().getAuthentication().getName()));
		eventService.createEvent(e);
	}

}
