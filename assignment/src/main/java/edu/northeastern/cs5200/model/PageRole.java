package edu.northeastern.cs5200.model;

public class PageRole {

	private int id;
	private int developerId;
	private int pageId;
	private Role role;
	
	public PageRole(int developerId, int pageId, Role role) {
		super();
		this.developerId = developerId;
		this.pageId = pageId;
		this.role = role;
	}

	public PageRole(int id, int developerId, int pageId, Role role) {
		super();
		this.id = id;
		this.developerId = developerId;
		this.pageId = pageId;
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
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
