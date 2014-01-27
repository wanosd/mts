package controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
				t.getRegistered().add("Mister Mister");
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
		List<Tournament> tournament = tournamentService.getCurrentTournaments();
		List<String> reg = tournament.get(0).getRegistered();
		model.addAttribute("tournaments", tournament);
		model.addAttribute("reg", reg);
		
		return "tournaments";
	}

	@Autowired
	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}
	

}
