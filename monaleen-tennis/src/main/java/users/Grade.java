package users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="grades")
public class Grade {
	
	@Id
	@GeneratedValue
	int id;
	
	@Column(name="gradename")
	String gradename;

	public Grade(String gradename){
		this.gradename = gradename;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGradename() {
		return gradename;
	}

	public void setGradename(String gradename) {
		this.gradename = gradename;
	}
	
	
}
