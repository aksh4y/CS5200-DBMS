package edu.northeastern.cs5200.model;

import java.sql.Date;

public class User extends Person {
	
	private int personId;
	private boolean userAgreement;
	
	public User() {
		super();
	}
	
	public User(int id, String firstName, String lastName, String username, String password, String email, Date dob,
			int personId, boolean userAgreement) {
		super(id, firstName, lastName, username, password, email, dob);
		this.personId = personId;
		this.userAgreement = userAgreement;
	}
	
	public User(int id, String firstName, String lastName, String username, String password, String email, Date dob,
			boolean userAgreement) {
		super(id, firstName, lastName, username, password, email, dob);
		this.userAgreement = userAgreement;
	}


	public boolean isUserAgreement() {
		return userAgreement;
	}
	public void setUserAgreement(boolean userAgreement) {
		this.userAgreement = userAgreement;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	
	
}
