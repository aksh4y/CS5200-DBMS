package edu.northeastern.cs5200.orm.jpa.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	
	public Person() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void set(Person newPerson) {
		this.firstName = newPerson.firstName != null ?
				newPerson.firstName : this.firstName; 
		this.lastName = newPerson.lastName != null ?
				newPerson.lastName : this.lastName;
	}
}
