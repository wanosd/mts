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

import events.tournaments.Tournament;
import service.TournamentService;
import service.UserService;
import users.FormValidationGroup;

@Controller
public class TournamentController {

	private static Logger logger = Logger.getLogger(MembersController.class);
	private TournamentService tournamentService;

	@RequestMapping("/createTournament")
	public String createTournament(Model model) {
		logger.info("Showing Create Tournament Page....");
		model.addAttribute("tournament", new Tournament());
		return "createTournament";
	}

	@RequestMapping(value = "/registerTournament", method = RequestMethod.POST)
	public String doCreateTournament(Model model,
			@Validated(FormValidationGroup.class) @ModelAttribute("tournament") Tournament t,
			BindingResult result) {

		logger.info("Showing Tournament Registration Page....");
		logger.info(result.getAllErrors());

		if (result.hasErrors()) {
			return "createTournament";
		} else {
			try {
				logger.info(t.toString());
				tournamentService.create(t);
				logger.info("Tournament Created");
				return "tournamentSuccess";
			} catch (Exception e) {
				System.out.println("ERROR!!!!!!!!!!!" + e.getClass());
				System.out.println("ERROR!!!!!!!!!!!" + e.getCause());
				System.out.println("ERROR!!!!!!!!!!!" + e.getLocalizedMessage());
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
	public String showTournaments(Model model){
		logger.info("Showing Tournament Display page....");
		model = tournamentRegStatus(model);
		return "tournaments";
	}
	
	@RequestMapping("/tournamentStatus")
	public String showTournamentStatus(Model model){
		logger.info("Showing Tournament Status page....");
		
		return "tournamentStatus";
	}
	
	@RequestMapping("/tourStatusChange")
	public String changeTourStatus(Model model, HttpServletRequest request){
		Tournament t = tournamentService.getTournamentById(request.getParameter("tournamentID"));
		if (t.isRegistrationOpen()){
			t.setRegistrationOpen(false);
			tournamentService.updateTournament(t);
			return showTournamentStatus(model);
		}else{
			t.setRegistrationOpen(true);
			tournamentService.updateTournament(t);
			return showTournamentStatus(model);
		}
		
	}
	
	
	@RequestMapping("/tournamentRegister")
	public String registerForTournament(Model model, HttpServletRequest request){
		logger.info("Registering for Tournament....");
		logger.info("Parameter ID is : " + request.getParameter("tournamentID"));
		model = tournamentRegStatus(model);
		Tournament t = tournamentService.getTournamentById(request.getParameter("tournamentID"));
		if (!checkRegistered(t)){
			tournamentService.register(t);
			model = tournamentRegStatus(model);
			return "tournaments";
		}
		else
			return "error";
	}
	
	@RequestMapping("/tournamentUnregister")
	public String unregisterForTournament(Model model, HttpServletRequest request){
		logger.info("Unregistering for Tournament....");
		logger.info("Parameter ID is : " + request.getParameter("tournamentID"));
		model = tournamentRegStatus(model);
		Tournament t = tournamentService.getTournamentById(request.getParameter("tournamentID"));
		if (checkRegistered(t)){
			tournamentService.unregister(t);
			model = tournamentRegStatus(model);
			return "tournaments";
		}
		else
			return "error";
	}
	
	@RequestMapping("/alreadyReg")
	public String duplicateReg() {
		return "alreadyReg";
	}

	private boolean checkRegistered(Tournament t) {
		List<String> users = t.getUsername();
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		for (String s : users){
			if (s.equals(name)){
				return true;
			}
		}
		return false;
	}

	@Autowired
	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}
	
	public Model tournamentRegStatus(Model model){
		List<Tournament> tournament = tournamentService.getCurrentTournaments();
		List <Tournament> unregTour = new ArrayList<Tournament>();
		List <Tournament> regTour = new ArrayList<Tournament>();
		for (int i = 0; i < tournament.size(); i++){
			if(checkRegistered(tournament.get(i))){
				regTour.add(tournament.get(i));
			}
			else{
				unregTour.add(tournament.get(i));
			}
		}
		model.addAttribute("unregTour", unregTour);
		model.addAttribute("regTour", regTour);
		return model;
	}

}
