package dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import service.RoleService;
import users.Grade;
import users.User;

@Repository
@Transactional
@Component("userDAO")
public class UserDAO {

	private NamedParameterJdbcTemplate jdbc;
	private static Logger logger = Logger.getLogger(UserDAO.class);

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleService roleService;
	
	public Session session(){ 
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}

	public UserDAO() {
		logger.info("User DAO Created.....");
	}

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	//Start of actual Hibernate queries
	
	@Transactional
	public void createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setBookings_left(0);
		session().save(user);
	}
	
	public void update(User user) {
		session().update(user);
		
	}


	/*
	 * Method to get a list of all users in the database
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		logger.info("Selecting All Enabled Members....");
		return session().createQuery("from User where enabled = '1'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAdmins() {
		logger.info("Selecting All Admin Members....");
		return session().createQuery("from User where authority = 'ROLE_ADMIN'").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getCommittee() {
		logger.info("Selecting All Admin Members....");
		return session().createQuery("from User where authority = 'ROLE_COMMITTEE'").list();
	}

	/*
	 * Method to get a list of all users in the database
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		logger.info("Selecting All Members....(Hibernate)");
		return session().createQuery("from User").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getUserGrades(){
		return session().createQuery("from Grade").list();
	}
	
	public void createGrade(String name){
		session().save(new Grade(name));
	}

	/*
	 * Method to search for a user by name
	 */
	public User getUserByName(String name) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("name", name)); 
		User user = (User) crit.uniqueResult();
		return user;
	}
	
	/**
	 * Method to search for a user by ID
	 */
	public User getUserByID(int id) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("id", id)); 
		User user = (User) crit.uniqueResult();
		return user;
	}
	
	/*
	 * Method to search for a user by username
	 */
	public User getUserByUserName(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username)); 
		User user = (User) crit.uniqueResult();
		return user;
	}

	/*
	 * Method to allow a user to change their current email address Needs to be
	 * properly error checked in that the name should not be used (perhaps Id or
	 * what not)
	 */
	public void changeUserDetails(User formUser, String username) {
		Criteria crit = session().createCriteria(User.class);
		System.out.println("USER RETRIEVED : " + formUser.toString());
		crit.add(Restrictions.eq("username", username)); // Restrictions.idEq for primary key integer
		User user = (User) crit.uniqueResult();
		logger.info(user.toString());
		user.setAd_line1(formUser.getAd_line1());
		user.setAd_line2(formUser.getAd_line2());
		user.setAd_city(formUser.getAd_city());
		if (getAdmins().contains(getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()))){
			user.setPassword(passwordEncoder.encode(formUser.getPassword()));
		}
		user.setAd_county(formUser.getAd_county());
		user.setContact_num(formUser.getContact_num());
		user.setEm_con_name(formUser.getEm_con_name());
		user.setEm_con_num(formUser.getEm_con_num());
		logger.info("AUTHORITY EDITED IS: " + formUser.getAuthority());
		user.setAuthority(formUser.getAuthority());
		System.out.println("USER BEING UPDATED RETRIEVED : " + user.toString());
		session().saveOrUpdate(user);
		logger.info("User Profile Changed");
	}
	
	public void changeUserDetailsAdmin(User formUser) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Criteria crit = session().createCriteria(User.class);
		System.out.println("USER RETRIEVED : " + formUser.toString());
		crit.add(Restrictions.eq("username", auth.getName())); // Restrictions.idEq for primary key integer
		User user = (User) crit.uniqueResult();
		user.setPassword(passwordEncoder.encode(formUser.getPassword()));
		user.setAd_line1(formUser.getAd_line1());
		user.setAd_line2(formUser.getAd_line2());
		user.setAd_city(formUser.getAd_city());
		user.setAd_county(formUser.getAd_county());
		user.setContact_num(formUser.getContact_num());
		user.setEm_con_name(formUser.getEm_con_name());
		user.setEm_con_num(formUser.getEm_con_num());
		System.out.println("USER BEING UPDATED RETRIEVED : " + user.toString());
		session().saveOrUpdate(user);
		logger.info("User Profile Changed");
	}

	public boolean exists(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username)); 
		User user = (User) crit.uniqueResult();
		return user != null;
	}
	
	public void enableMember(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username)); // Restrictions.idEq for primary key integer
		User user = (User) crit.uniqueResult();
		user.setEnabled(true);
		session().saveOrUpdate(user);
		logger.info("User Enabled");
	}
	
	public void disableMember(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.eq("username", username)); // Restrictions.idEq for primary key integer
		User user = (User) crit.uniqueResult();
		user.setEnabled(false);
		session().saveOrUpdate(user);
		logger.info("User Disabled");
	}

	@SuppressWarnings("unchecked")
	public List<User> getPendingUsers() {
		return session().createQuery("from User where enabled = '0'").list();
	}
	
	
	/**
	 * The following code is left in for comparative purposes to compare JDBC to Hibernate. They will only be used in a Test Class at a later stage and are left in the code for this purpose alone
	 */

	@Transactional
	public boolean createUserJDBC(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUsername());
		params.addValue("name", user.getName());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("gender", user.getGender());
		params.addValue("member_type", user.getMember_type());
		params.addValue("grade", user.getGrade());
		params.addValue("ad_line1", user.getAd_line1());
		params.addValue("ad_line2", user.getAd_line2());
		params.addValue("ad_city", user.getAd_city());
		params.addValue("ad_county", user.getAd_county());
		params.addValue("contact_num", user.getContact_num());
		params.addValue("em_con_name", user.getEm_con_name());
		params.addValue("em_con_num", user.getEm_con_num());
		params.addValue("role", "ROLE_MEMBER");

	

		return jdbc
				.update("insert into users (username, password, name, gender, member_type, grade, ad_line1, ad_line2, ad_city, ad_county, contact_num, em_con_name, em_con_num, enabled. authority) values (:username, :password, :name, :gender, :member_type, :grade, :ad_line1, :ad_line1, :ad_city, :ad_county, :contact_num, :em_con_name, :em_con_num, false, :role)",
						params) == 1;
	}

	/*
	 * Method to get a list of all users in the database
	 */
	public List<User> getUsersJDBC() {
		logger.info("Selecting All Enabled Members....");
		return jdbc.query("select * from users where enabled = 1",
				new UserRowMapper());
	}

	/*
	 * Method to get a list of all users in the database
	 */
	public List<User> getAllUsersJDBC() {
		logger.info("Selecting All Members....");
		return jdbc.query("select * from users", new UserRowMapper());
	}

	/*
	 * Method to search for a user by name
	 */
	public User getUserByNameJDBC(String name) {

		return jdbc.queryForObject("select * from users where name = :name",
				new MapSqlParameterSource("name", name), new UserRowMapper());
	}
	
	/*
	 * Method to search for a user by username
	 */
	public User getUserByUserNameJDBC(String username) {

		return jdbc.queryForObject("select * from users where username = :username",
				new MapSqlParameterSource("username", username), new UserRowMapper());
	}

	/*
	 * Method to allow a user to change their current email address Needs to be
	 * properly error checked in that the name should not be used (perhaps Id or
	 * what not)
	 */
	public void changeUserEmailAddressJDBC(String email, String name) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", email);
		params.addValue("name", name);
		jdbc.update("update users set username = :email where name = :name",
				params);
	}

	public boolean existsJDBC(String username) {
		return jdbc.queryForObject(
				"select count(*) from users where username = :username",
				new MapSqlParameterSource("username", username), Integer.class) > 0;
	}

	public List<User> getPendingUsersJDBC() {
		return jdbc.query("select * from users where enabled != 1", new UserRowMapper());
	}

	


	

	

	
}
