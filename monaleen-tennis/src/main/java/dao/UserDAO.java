package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import users.Member;
import users.User;

@Component("userDAO")
public class UserDAO {
	
	private NamedParameterJdbcTemplate jdbc; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserDAO() {
		System.out.println("Loaded UserDAO");
	}
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	@Transactional
	public boolean createUser(User user){
		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user); 
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
		
		
		jdbc.update("insert into authorities (username, authority) values (:username, :role)", params);
		
		return jdbc.update("insert into users (username, password, name, gender, member_type, grade, ad_line1, ad_line2, ad_city, ad_county, contact_num, em_con_name, em_con_num, enabled) values (:username, :password, :name, :gender, :member_type, :grade, :ad_line1, :ad_line1, :ad_city, :ad_county, :contact_num, :em_con_name, :em_con_num, false)", params) == 1;
	}
	
	/*
	 * Method to get a list of all users in the database
	 */
	public List<User> getUsers(){
	    return jdbc.query("select * from users where enabled = 1", new RowMapper<User>(){
			public User mapRow(ResultSet rs, int row) throws SQLException {
				User user = new Member();
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));	
				user.setGrade(rs.getString("grade"));
				user.setContact_num(rs.getString("contact_num"));
				user.setMember_type(rs.getString("member_type"));
				return user;
			}
		});
	}
	
	/*
	 * Method to search for a user by name
	 */
	public User getUserByName(String name){
		
		MapSqlParameterSource params = new MapSqlParameterSource(); 
		params.addValue("name", name);
		
	    return jdbc.queryForObject("select * from users where name = :name", params, new RowMapper<User>(){
			public User mapRow(ResultSet rs, int row) throws SQLException {
				User user = new Member();
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));	
				user.setUsername(rs.getString("username"));	
				user.setGender(rs.getString("gender"));	
				return user;
			}
		});
	}
	
	/*
	 * Method to allow a user to change their current email address
	 * Needs to be properly error checked in that the name should not be used (perhaps Id or what not)
	 */
	public void changeUserEmailAddress(String email, String name){
		MapSqlParameterSource params = new MapSqlParameterSource(); 
		params.addValue("username", email);
		params.addValue("name", name);
		jdbc.update("update users set username = :email where name = :name", params);
	}

	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username = :username", new MapSqlParameterSource("username", username), Integer.class) > 0;
	}

	public List<User> getPendingUsers() {
		return jdbc.query("select * from users where enabled != 1", new RowMapper<User>(){
			public User mapRow(ResultSet rs, int row) throws SQLException {
				User user = new Member();
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));	
				user.setGrade(rs.getString("grade"));
				user.setContact_num(rs.getString("contact_num"));
				user.setMember_type(rs.getString("member_type"));
				return user;
			}
		});
	}
}
