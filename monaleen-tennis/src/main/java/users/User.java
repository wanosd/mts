package users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="users")
public class User {
	
	@Id
	int id;
	
	@NotNull(groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Pattern(regexp=".+\\@.+\\..+", message="This does not appear to be a valid email address", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="username")
	String username;
	
	@Size(min=5, max=45, message="Named must be between 5 and 45 characters",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="name")
	String name;
	
	@Column(name="password")
	@Size(min=5, max=15, message="Password must be between 5 and 45 characters", groups=FormValidationGroup.class)
	String password;
	
	@Column(name="gender")
	String gender;

	@Column(name="member_type")
	String member_type;
	
	@Column(name="grade")
	String grade;
	
	@Size(min=5, max=45, message="Address be between 5 and 45 characters", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="ad_line1")
	String ad_line1;
	
	@Size(min=5, max=45, message="Address be between 5 and 45 characters", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="ad_line2")
	String ad_line2;
	
	@Column(name="ad_city")
	String ad_city;
	
	@Column(name="ad_county")
	String ad_county;
	
	@Pattern(regexp="08[35679]([0-9]{7})", message="Number must be in the format 083, 085, 086, 087, 089 and 7 additional numbers eg 0851234567", groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	@Column(name="contact_num")
	String contact_num;
	
	@Column(name="em_con_name")
	String em_con_name;
	
	@Column(name="em_con_num")
	String em_con_num;

	@Column(name="enabled")
	boolean enabled;

	@Column(name="authority")
	String authority;
	
	public User(){}
	
	public User(String username, String password, String name, String gender, String grade, String member_type, String ad_line1, String ad_line2, String ad_city, String ad_county, String contact_num, String em_con_name, String em_con_num, boolean enabled, String authority) {
		this.name = name;
		this.password = password;
		this.username = username;
		this.gender = gender;
		this.grade = grade; 
		this.member_type = member_type;
		this.ad_line1 = ad_line1;
		this.ad_line2 = ad_line2;
		this.ad_city = ad_city;
		this.ad_county = ad_county;
		this.contact_num = contact_num;
		this.em_con_name = em_con_name;
		this.em_con_num = em_con_name;	
		this.enabled = enabled;
		this.authority = authority;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	@Autowired
	public void setName(@Value("${jdbc.user}")String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	@Autowired
	public void setPassword(@Value("${jdbc.user}")String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}

	public String getAd_line1() {
		return ad_line1;
	}

	public void setAd_line1(String ad_line1) {
		this.ad_line1 = ad_line1;
	}

	public String getAd_line2() {
		return ad_line2;
	}

	public void setAd_line2(String ad_line2) {
		this.ad_line2 = ad_line2;
	}

	public String getAd_city() {
		return ad_city;
	}

	public void setAd_city(String ad_city) {
		this.ad_city = ad_city;
	}

	public String getAd_county() {
		return ad_county;
	}

	public void setAd_county(String ad_county) {
		this.ad_county = ad_county;
	}

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getEm_con_name() {
		return em_con_name;
	}

	public void setEm_con_name(String em_con_name) {
		this.em_con_name = em_con_name;
	}

	public String getEm_con_num() {
		return em_con_num;
	}

	public void setEm_con_num(String em_con_num) {
		this.em_con_num = em_con_num;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name
				+ ", password=" + password + ", gender=" + gender
				+ ", member_type=" + member_type + ", grade=" + grade
				+ ", ad_line1=" + ad_line1 + ", ad_line2=" + ad_line2
				+ ", ad_city=" + ad_city + ", ad_county=" + ad_county
				+ ", contact_num=" + contact_num + ", em_con_name="
				+ em_con_name + ", em_con_num=" + em_con_num + ", enabled="
				+ enabled + ", authority=" + authority + "]";
	}


	

}