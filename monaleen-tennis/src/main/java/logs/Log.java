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
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="accessed")
	private String accessed;
	
	@Column(name="informationType")
	private String informationType;

	@Column(name = "logtype")
	private String logtype;
	
	public Log(){
		
	}
	
	public Log(String username, String date, String informationType, String logType){
		this.username = username;
		this.accessed = date;
		this.informationType = informationType;
		this.logtype = logType;
	}
	
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

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}
	
	
	
	

}
