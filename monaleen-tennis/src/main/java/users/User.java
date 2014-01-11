package users;

public abstract class User {
	
	String name, password, grade, gender, email, member_type;
	String ad_line1, ad_line2, ad_city, ad_county;
	String contact_num, em_con_name, em_con_num;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", gender="
				+ gender + ", email=" + email + ", member_type=" + member_type
				+ ", ad_line1=" + ad_line1 + ", ad_line2=" + ad_line2
				+ ", ad_city=" + ad_city + ", ad_county=" + ad_county
				+ ", contact_num=" + contact_num + ", em_con_name="
				+ em_con_name + ", em_con_num=" + em_con_num + "]";
	}

	

}