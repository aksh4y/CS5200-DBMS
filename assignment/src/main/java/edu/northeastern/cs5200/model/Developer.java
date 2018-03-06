package edu.northeastern.cs5200.model;

import java.sql.Date;

public class Developer extends Person {

	private int personId;
	private String developerKey;

	public Developer() {
		super();
	}
	
	public Developer(int personId, String firstName, String lastName, String username, String password, String email,
			Date dob, String developerKey) {
		super(personId, firstName, lastName, username, password, email, dob);
		this.personId = personId;
		this.developerKey = developerKey;
	}
	
	public Developer(String firstName, String lastName, String username, String password, String email,
			Date dob, String developerKey) {
		super(firstName, lastName, username, password, email, dob);
		this.developerKey = developerKey;
	}

	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getDeveloperKey() {
		return developerKey;
	}
	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}
}
