package controllers;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import analytics.MTCAnalytics;
import reports.CSVCreator;
import service.AnalyticsService;
import service.LogService;
import service.RoleService;
import service.UserService;
import users.FormValidationGroup;
import users.Role;
import users.User;

@Controller
public class MembersController {

	private UserService userService;
	private LogService logService;
	private RoleService roleService;
	private AnalyticsService analyticsService;
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
	
	@RequestMapping("/membership")
	public String showMembership(){
		return "membership";
	}
	
	@RequestMapping("/emailSent")
	public String emailSent(){
		return "emailSent";
	}
	
	@RequestMapping("/emailMembersToAddress")
	public String emailMembers(Model model){
		List<User> users = userService.getCurrentMembers();
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("Entering CreateCSVToEmail");
		Log log = new Log();
		log.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		DateFormat df = new SimpleDateFormat("ddmmyyyyHHmmss");
		Date today = Calendar.getInstance().getTime();
		String date = df.format(today);
		date.replace("\\", "");
		date.replace(" ", "");
		log.setAccessed(date);
		log.setInformationType("ListExistingUsers");
		if (logService == null) {
			logger.info("Log Service is Null");
		}
		
		File file = new File(name.replace("@","") + date + ".csv");
		CSVCreator.createCSVToEmail(users, file);
		logger.info("About to attempt sending message");
		if (sendMessage(file)){
			logService.createLog(log);
			model.addAttribute("message", "Email successfully sent to : " + SecurityContextHolder.getContext().getAuthentication().getName());
		}
		else{
			log.setInformationType("Failed Attempt");
			logService.createLog(log);
			model.addAttribute("message", "Message Failed. Try Again Later.");
		}
		
		return "emailSent";
	}
	
	private boolean sendMessage(File file) {
		MimeMessage message = mailSender.createMimeMessage();
		logger.info("MIME Message created");
		try {	
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			logger.info("MIME Helper Okay");
			helper.setFrom("admin@monaleentennisclub.ie");
			logger.info("Set From");
			helper.setTo(SecurityContextHolder.getContext().getAuthentication().getName());
			logger.info("Set To");
			helper.setSubject("Monaleen Tennis Club Users");
			logger.info("Set Sub");
			helper.setText("This email is confidental. Please do not forward or make copies");
			logger.info("Set Text. File is " + file.getName() + " at " + file.getAbsolutePath());
			helper.addAttachment(file.getName(), file);
			logger.info("Set File");
			logger.info("Finished putting message together");
		} catch (MessagingException e) {
			
			e.printStackTrace();
			return false;
		}
		logger.info("About to send");
		mailSender.send(message);
		logger.info("Sent");
		return true;
		
		
	}

	@RequestMapping("/viewAllMembers")
	public String viewAllMembers(Model model){
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
		List<User> toApprove = userService.getPendingMembers();
		/**
		MTCAnalytics a = analyticsService.get();
		if (a == null){
			a = new MTCAnalytics();	
		}
		a.run(userService);
		analyticsService.save(a);**/
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
		List<User> toBlock = userService.getCurrentMembers();
		toBlock = removeLoggedIn(toBlock);
		model.addAttribute("toBlock", toBlock);
		return "blockMembers";
	} 
	
	
	@RequestMapping("/createmembers")
	public String createMembers(Model model) {
		defaultRoles();
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

	@RequestMapping(value = "/profileUpdated", method = RequestMethod.POST)
	public String doEditProfile(
			Model model,
			@ModelAttribute("member") User member,
			BindingResult result) {
		logger.info("Showing Profile Updated.....");
		if (result.hasErrors()) {
			logger.info("Errors found in BindingResult object....");
			return "profile";
		} 
		
		if (userService.exists(member.getUsername())) {
			result.rejectValue("username", "Duplicate Key",
					"This email address has already been used");
			return "profile";
		}
		
		else {
			try {
				logger.info("About to  update user");
				userService.editProfile(member);
				logger.info("User Updated");
				return "profileUpdated";
			} catch (Exception e) {
				logger.info("Database update failed. Cause: " + e.getClass());
				return "error";
			}
		}
	}
	
	@RequestMapping("/createGrade")
	public String createGrade(){
		return "createGrade";
	}
	
	@RequestMapping("/saveGrade")
	public String saveGrade(Model model, HttpServletRequest request){
		userService.createGrade(request.getParameter("grade"));
		return "grades";
	}
	
	@RequestMapping("/createNewRole")
	public String newRole(Model model){
		defaultRoles();
		model.addAttribute("role", new Role());
		model.addAttribute("existing", roleService.getRoles());
		return "createNewRole";
	}
	
	private void defaultRoles() {
		List<Role> roles = roleService.getRoles();
		if (roles.size() == 0){
			Role admin = new Role("ROLE_ADMIN", 9999);
			Role committee = new Role("ROLE_COMMITTEE", 9999);
			Role member = new Role("ROLE_MEMBER", 3);
			Role member_warning = new Role("ROLE_WARNING", 2);
			Role member_suspend = new Role("ROLE_SUSPEND", 1);
			roleService.create(admin);
			roleService.create(committee);
			roleService.create(member);
			roleService.create(member_warning);
			roleService.create(member_suspend);
		}
		
	}

	@RequestMapping("/saveNewRole")
	public String saveNewRole(Model model, @ModelAttribute("role") Role role,
			BindingResult result){
		if (roleService.exists(role.getName())){
			result.rejectValue("name", "Duplicate Key",
					"This role exists already");
			return newRole(model);
		}
		else{
			roleService.create(role);
			return newRole(model);
		}
		
	}
	
	public List<User> removeLoggedIn(List<User> users){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User temp = null;
		for (int i = 0; i < users.size(); i++){
			if (users.get(i).getUsername().equals(username)){
				temp = users.get(i);
			}
		}
		
		if (temp != null){
			users.remove(temp);
			return users;
		}else{
			return users;
		}
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
	
	


	
	

	
	
}
