package timetable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import users.FormValidationGroup;
import users.PersistenceValidationGroup;

@Entity
@Component
@Table(name = "timetable")
public class MonaleenTTV1 implements Timetable {

	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=5, max=10, message="Court must be between 5 and 10 characters",groups={PersistenceValidationGroup.class, FormValidationGroup.class})
	private String name;
	
	@NotNull
	private int slots;
	
	private int startTime;
	
	private int endTime;
	
	@ElementCollection
	@CollectionTable (name = "monday", joinColumns=@JoinColumn(name="id"))
	List<String> monday;
	
	@ElementCollection
	@CollectionTable (name = "tuesday", joinColumns=@JoinColumn(name="id"))
	List<String> tuesday;
	
	@ElementCollection
	@CollectionTable (name = "wednesday", joinColumns=@JoinColumn(name="id"))
	List<String> wednesday;
	
	@ElementCollection
	@CollectionTable (name = "thursday", joinColumns=@JoinColumn(name="id"))
	List<String> thursday;
	
	@ElementCollection
	@CollectionTable (name = "friday", joinColumns=@JoinColumn(name="id"))
	List<String> friday;
	
	@ElementCollection
	@CollectionTable (name = "saturday", joinColumns=@JoinColumn(name="id"))
	List<String> saturday;
	
	@ElementCollection
	@CollectionTable (name = "sunday", joinColumns=@JoinColumn(name="id"))
	List<String> sunday;
	
	
	public MonaleenTTV1(){
		this.monday = new ArrayList<String>();
		this.tuesday = new ArrayList<String>();
		this.wednesday = new ArrayList<String>();
		this.thursday = new ArrayList<String>();
		this.friday = new ArrayList<String>();
		this.saturday = new ArrayList<String>();
		this.sunday = new ArrayList<String>();
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

	public int getSlots() {
		return slots;
	}

	public void setSlots(int noSlots) {
		this.slots = noSlots;
	}

	public List<String> getMonday() {
		return monday;
	}

	public void setMonday(List<String> monday) {
		this.monday = monday;
	}

	public List<String> getTuesday() {
		return tuesday;
	}

	public void setTuesday(List<String> tuesday) {
		this.tuesday = tuesday;
	}

	public List<String> getWednesday() {
		return wednesday;
	}

	public void setWednesday(List<String> wednesday) {
		this.wednesday = wednesday;
	}

	public List<String> getThursday() {
		return thursday;
	}

	public void setThursday(List<String> thursday) {
		this.thursday = thursday;
	}

	public List<String> getFriday() {
		return friday;
	}

	public void setFriday(List<String> friday) {
		this.friday = friday;
	}

	public List<String> getSaturday() {
		return saturday;
	}

	public void setSaturday(List<String> saturday) {
		this.saturday = saturday;
	}

	public List<String> getSunday() {
		return sunday;
	}

	public void setSunday(List<String> sunday) {
		this.sunday = sunday;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "MonaleenTTV1 [id=" + id + ", name=" + name + ", slots=" + slots
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	
	
	
	
}
