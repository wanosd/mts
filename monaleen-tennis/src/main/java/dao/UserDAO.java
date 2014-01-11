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
import org.springframework.stereotype.Component;

import users.Member;
import users.User;

@Component("userDAO")
public class UserDAO {
	
	private NamedParameterJdbcTemplate jdbc; 
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	
	public boolean createUser(User user){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user); 
		return jdbc.update("insert into users (name, password, email, gender) values (:name, :password, :email, :gender)", params) == 1;
	}
	
	/*
	 * Method to get a list of all users in the database
	 */
	public List<User> getUsers(){
	    return jdbc.query("select * from users", new RowMapper<User>(){
			public User mapRow(ResultSet rs, int row) throws SQLException {
				User user = new Member();
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));	
				user.setEmail(rs.getString("email"));	
				user.setGender(rs.getString("gender"));	
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
				user.setEmail(rs.getString("email"));	
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
		params.addValue("email", email);
		params.addValue("name", name);
		jdbc.update("update users set email = :email where name = :name", params);
	}
}
