package users;

public class Member extends User {
	
	private Member(String name, String password, String email, String gender, String grade, String member_type, String ad_line1, String ad_line2, String ad_city, String ad_county, String contact_num, String em_con_name, String em_con_num) {
		this.name = name;
		this.password = password;
		this.email = email;
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
	}
	
	public Member(){}
	
	public static Member createMember(String name, String password, String email, String gender, String grade, String member_type, String ad_line1, String ad_line2, String ad_city, String ad_county, String contact_num, String em_con_name, String em_con_num){
		return new Member(name, password, email, gender, grade, member_type, ad_line1, ad_line2, ad_city, ad_county, contact_num, em_con_name, em_con_num);
	}
}
