package dao;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import users.*;

public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new Member();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));
		user.setGender(rs.getString("gender"));
		user.setMember_type(rs.getString("member_type"));
		user.setGrade(rs.getString("grade"));
		user.setAd_line1(rs.getString("ad_line1"));
		user.setAd_line2(rs.getString("ad_line2"));
		user.setAd_city(rs.getString("ad_city"));
		user.setAd_county(rs.getString("ad_county"));
		user.setContact_num(rs.getString("contact_num"));
		user.setEm_con_name(rs.getString("em_con_name"));
		user.setEm_con_num(rs.getString("em_con_num"));
		user.setEnabled(rs.getBoolean("enabled"));
		return user;
	}

}
