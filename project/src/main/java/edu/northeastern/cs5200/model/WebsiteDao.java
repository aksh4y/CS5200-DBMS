package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Statement;

public class WebsiteDao {

	DbConfig cfg;
	public static WebsiteDao instance = null;
	public static WebsiteDao getInstance() {
		if (instance == null) {
			instance = new WebsiteDao();
		}
		return instance;
	}
	
	private WebsiteDao() {
		cfg = new DbConfig();
	}
	
	public int createWebsiteForDeveloper(int developerId, Website website) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO Website (id, developerID, name, description, visits) VALUES (?,?,?,?,?)";
		Connection conn = cfg.getConnection();		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, website.getId());
		stmt.setInt(2, developerId);
		stmt.setString(3, website.getName());
		stmt.setString(4, website.getDescription());
		stmt.setInt(5, website.getVisits());
		
		int result = stmt.executeUpdate();
		if(result == 0)
			throw new SQLException("Could not create website");
		
		RoleDao rd = RoleDao.getInstance();
		result = rd.assignWebsiteRole(developerId, website.getId(), Role.owner);
		stmt.close();
		conn.close();
		return result;
	}
	
	public Collection<Website> findWebsitesForDeveloper(int developerId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Website WHERE developerID = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, developerId);
		Collection<Website> websites = new ArrayList<Website>();
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			Website w = new Website(res.getInt("id"),
					res.getInt("developerID"),
					res.getString("name"),
					res.getString("description"),
					res.getDate("created"),
					res.getDate("updated"),
					res.getInt("visits"));
			websites.add(w);
		}
		
		stmt.close();
		conn.close();
		return websites;
	}

	public Collection<Website> findAllWebsites() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Website";
		Connection conn = cfg.getConnection();
		Statement stmt = conn.createStatement();
		Collection<Website> websites = new ArrayList<Website>();
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			Website w = new Website(res.getInt("id"),
					res.getInt("developerID"),
					res.getString("name"),
					res.getString("description"),
					res.getDate("created"),
					res.getDate("updated"),
					res.getInt("visits"));
			websites.add(w);
		}
		
		stmt.close();
		conn.close();
		return websites;
	}
	
	public Website findWebsiteById(int websiteId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Website WHERE Website.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, websiteId);
		ResultSet res = stmt.executeQuery();
		Website website = null;
		if (res.next()) {
			website = new Website(res.getInt("id"),
					res.getInt("developerID"),
					res.getString("name"),
					res.getString("description"),
					res.getDate("created"),
					res.getDate("updated"),
					res.getInt("visits"));

		}
		stmt.close();
		conn.close();
		return website;
	}

	public int updateWebsite(int websiteId, Website website) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE Website SET developerID = ?, name = ?, description = ?, visits = ? WHERE Website.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, website.getDeveloperId());
		stmt.setString(2, website.getName());
		stmt.setString(3, website.getDescription());
		stmt.setInt(4, website.getVisits());
		stmt.setInt(5, websiteId);
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;	
	}
	
	public int deleteWebsite(int websiteId) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM Website WHERE Website.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, websiteId);
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;
		
	}
}
