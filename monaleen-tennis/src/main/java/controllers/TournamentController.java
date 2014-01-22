package controllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import events.tournaments.Tournament;
import service.TournamentService;
import users.FormValidationGroup;
import users.User;


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
	public String doCreateTournament(Model model, @Valid @ModelAttribute("tournament") Tournament t, BindingResult result) {

		logger.info("Showing Registration Page....");
		
		if (result.hasErrors()) {
			return "createTournament";
		}

			try{
				tournamentService.create(t);
				logger.info("Tournament Created");
				return "/";
			}catch (Exception e){
				System.out.println("ERROR!!!!!!!!!!!" + e.getClass());
				return "error";
			}
			
		}
	}

