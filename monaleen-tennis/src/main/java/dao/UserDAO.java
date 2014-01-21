package dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import users.User;

@Transactional
@Component("userDAO")
public class UserDAO {

	private NamedParameterJdbcTemplate jdbc;
	private static Logger logger = Logger.getLogger(UserDAO.class);

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}

	public UserDAO() {
		System.out.println("Loaded UserDAO");
	}

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	//Start of actual Hibernate queries
	
	@Transactional
	public void createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	/*
	 * Method to get a list of all users in the database
	 */
	public List<User> getUsers() {
		logger.info("Selecting All Enabled Members....");
		return jdbc.query("select * from users where enabled = 1",
				new UserRowMapper());
	}

	/*
	 * Method to get a list of all users in the database
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		logger.info("Selecting All Members....(Hibernate)");
		return session().createQuery("from User").list();
	}

	/*
	 * Method to search for a user by name
	 */
	public User getUserByName(String name) {

		return jdbc.queryForObject("select * from users where name = :name",
				new MapSqlParameterSource("name", name), new UserRowMapper());
	}
	
	/*
	 * Method to search for a user by username
	 */
	public User getUserByUserName(String username) {

		return jdbc.queryForObject("select * from users where username = :username",
				new MapSqlParameterSource("username", username), new UserRowMapper());
	}

	/*
	 * Method to allow a user to change their current email address Needs to be
	 * properly error checked in that the name should not be used (perhaps Id or
	 * what not)
	 */
	public void changeUserEmailAddress(String email, String name) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", email);
		params.addValue("name", name);
		jdbc.update("update users set username = :email where name = :name",
				params);
	}

	public boolean exists(String username) {
		return jdbc.queryForObject(
				"select count(*) from users where username = :username",
				new MapSqlParameterSource("username", username), Integer.class) > 0;
	}

	public List<User> getPendingUsers() {
		return jdbc.query("select * from users where enabled != 1", new UserRowMapper());
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

		jdbc.update(
				"insert into authorities (username, authority) values (:username, :role)",
				params);

		return jdbc
				.update("insert into users (username, password, name, gender, member_type, grade, ad_line1, ad_line2, ad_city, ad_county, contact_num, em_con_name, em_con_num, enabled) values (:username, :password, :name, :gender, :member_type, :grade, :ad_line1, :ad_line1, :ad_city, :ad_county, :contact_num, :em_con_name, :em_con_num, false)",
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
