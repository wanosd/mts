package logs;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="emaillog")
public class Log {
	
	@GeneratedValue
	@Id
	int id;
	
	@Column(name="username")
	String username;
	
	@Column(name="accessed")
	String accessed;
	
	@Column(name="informationType")
	String informationType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessed() {
		return accessed;
	}

	public void setAccessed(String accessed) {
		this.accessed = accessed;
	}

	public String getInformationType() {
		return informationType;
	}

	public void setInformationType(String informationType) {
		this.informationType = informationType;
	}
	
	

}
