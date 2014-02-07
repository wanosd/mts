package events;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import users.FormValidationGroup;
import users.PersistenceValidationGroup;

@Entity
@Table(name="event")
public class Event implements I_Event {
	
	
	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=5, max=100, message="Event Name must be between 5 and 100 characters",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String name;
	
	private String author;
	
	private boolean enabled;
	
	public Event(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	
	
}
