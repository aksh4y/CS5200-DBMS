package edu.northeastern.cs5200.model;

public class WebsiteRole {

	private int id;
	private int developerId;
	private int websiteId;
	private Role role;
	
	public WebsiteRole(int developerId, int websiteId, Role role) {
		super();
		this.developerId = developerId;
		this.websiteId = websiteId;
		this.role = role;
	}

	public WebsiteRole(int id, int developerId, int websiteId, Role role) {
		super();
		this.id = id;
		this.developerId = developerId;
		this.websiteId = websiteId;
		this.role = role;
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
	public int getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(int websiteId) {
		this.websiteId = websiteId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
