package Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

import View.AddController;
import Exceptions.ShortPasswordException;
import Utils.Gender;
import Utils.Job;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Toggle;

public class ZooEmployee extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static int idCounter = 1;
	
	private Job job;
	private String password;
	
	
	public ZooEmployee(String firstName, String lastName, LocalDate date, Gender gender, Section section, Job job, String password) {
		super(idCounter++,firstName, lastName, date, gender, section);
		this.job = job;
		idCounter = getId();
		this.password = password;
	}
	
	public ZooEmployee(int id) {
		super(id);
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) { 
		this.password = password;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+" Name: "+getFirstName()+" "+getLastName()+", Job: "+job;
	}


	

	

	
}
