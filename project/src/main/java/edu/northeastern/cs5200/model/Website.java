package edu.northeastern.cs5200.model;

import java.sql.Date;

public class Website {

	private int id;
	private int developerId;
	private String name;
	private String description;
	private Date created;
	private Date updated;
	private int visits;
	
	public Website() {
		super();
	}
	
	public Website(int id, int developerId, String name, String description, int visits) {
		super();
		this.id = id;
		this.developerId = developerId;
		this.name = name;
		this.description = description;
		this.visits = visits;
	}	
	public Website(int id, int developerId, String name, String description, Date created, Date updated, int visits) {
		super();
		this.id = id;
		this.developerId = developerId;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
}
