package analytics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.UserService;

@Component
@Entity
@Table(name="analytics")
public class MTCAnalytics{


	@GeneratedValue
	@Id
	int id;
	
	@Column(name="total_members")
	int total_members;
	
	@Column(name="total_pending")
	int total_pending;
	
	@Column(name="total_times_booked")
	int total_times_booked;
	
	@Column(name="total_times_unbooked")
	int total_times_unbooked;
	
	public void run(UserService userService) {
		setTotal_members(userService.getCurrentMembers().size());
	
	}
	
	public void increaseBooking(){
		setTotal_times_booked(getTotal_times_booked() + 1);
	}
	
	public void decreaseBooking(){
		setTotal_times_unbooked(getTotal_times_unbooked() + 1);
	}

	public int getTotal_members() {
		return total_members;
	}

	public void setTotal_members(int total_members) {
		this.total_members = total_members;
	}

	public int getTotal_times_booked() {
		return total_times_booked;
	}

	public void setTotal_times_booked(int total_times_booked) {
		this.total_times_booked = total_times_booked;
	}

	public int getTotal_times_unbooked() {
		return total_times_unbooked;
	}

	public void setTotal_times_unbooked(int total_times_unbooked) {
		this.total_times_unbooked = total_times_unbooked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal_pending() {
		return total_pending;
	}

	public void setTotal_pending(int total_pending) {
		this.total_pending = total_pending;
	}

	
	
	
	
	

}
