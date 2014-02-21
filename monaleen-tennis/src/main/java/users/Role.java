package users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="roles")
public class Role {
	
	@GeneratedValue
	@Id
	int id;
	
	@Column(name="name")
	String name;
	
	@Column(name="bookings_allowed")
	int bookings_allowed;
	
	public Role(){
		
	}

	public Role(String name, int bookings_allowed){
		this.name = name;
		this.bookings_allowed = bookings_allowed;
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

	public int getBookings_allowed() {
		return bookings_allowed;
	}

	public void setBookings_allowed(int bookings_allowed) {
		this.bookings_allowed = bookings_allowed;
	}

}
