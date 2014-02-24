package analytics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="analytics")
public class MTCAnalytics{


	@GeneratedValue
	@Id
	int id;
	
	@Column(name="total_members")
	int total_members;
	
	@Column(name="pending_members")
	int pending_members;
	
	@Column(name="total_times_booked")
	int total_times_booked;
	
	@Column(name="total_times_unbooked")
	int total_times_unbooked;
	
	public void run(int total, int pending) {
		setTotal_members(total);
		setPending_members(pending);
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

	public int getPending_members() {
		return pending_members;
	}

	public void setPending_members(int pending_members) {
		this.pending_members = pending_members;
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
	
	
	
	

}
