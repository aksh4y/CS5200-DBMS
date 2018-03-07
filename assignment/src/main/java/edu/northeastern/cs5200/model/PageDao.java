package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Statement;

public class PageDao {

	DbConfig cfg;
	public static PageDao instance = null;
	public static PageDao getInstance() {
		if (instance == null) {
			instance = new PageDao();
		}
		return instance;
	}
	
	private PageDao() {
		cfg = new DbConfig();
	}
	
	public int createPageForWebsite(int websiteId, Page page) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO Page (id, websiteID, title, description, views) VALUES (?,?,?,?,?)";
		Connection conn = cfg.getConnection();		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, page.getId());
		stmt.setInt(2, websiteId);
		stmt.setString(3, page.getTitle());
		stmt.setString(4, page.getDescription());
		stmt.setInt(5, page.getViews());
		
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;
	}
	
	public Collection<Page> findPagesForWebsite(int websiteId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Page WHERE websiteID = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, websiteId);
		Collection<Page> pages = new ArrayList<Page>();
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			Page w = new Page(res.getInt("id"),
					res.getInt("websiteID"),
					res.getString("title"),
					res.getString("description"),
					res.getDate("created"),
					res.getDate("updated"),
					res.getInt("views"));
			pages.add(w);
		}
		
		stmt.close();
		conn.close();
		return pages;
	}

	public Collection<Page> findAllPages() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Page";
		Connection conn = cfg.getConnection();
		Statement stmt = conn.createStatement();
		Collection<Page> pages = new ArrayList<Page>();
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			Page w = new Page(res.getInt("id"),
					res.getInt("websiteID"),
					res.getString("title"),
					res.getString("description"),
					res.getDate("created"),
					res.getDate("updated"),
					res.getInt("views"));
			pages.add(w);
		}
		
		stmt.close();
		conn.close();
		return pages;
	}
	
	public Page findPageById(int pageId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Page WHERE Page.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pageId);
		ResultSet res = stmt.executeQuery();
		Page page = null;
		if (res.next()) {
			page = new Page(res.getInt("id"),
					res.getInt("websiteID"),
					res.getString("title"),
					res.getString("description"),
					res.getDate("created"),
					res.getDate("updated"),
					res.getInt("views"));

		}
		stmt.close();
		conn.close();
		return page;
	}

	public int updatePage(int pageId, Page page) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE Page SET websiteID = ?, title = ?, description = ?, views = ? WHERE Page.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, page.getWebsiteId());
		stmt.setString(2, page.getTitle());
		stmt.setString(3, page.getDescription());
		stmt.setInt(4, page.getViews());
		stmt.setInt(5, pageId);
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;	
	}
	
	public int deletePage(int pageId) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM Page WHERE Page.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pageId);
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;
		
	}
	
	public int deleteLastUpdatedPageForWebsite(int websiteID) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Page WHERE websiteID = ? AND updated = (SELECT * FROM (SELECT MAX(updated) FROM Page WHERE websiteID = ?) AS Q)";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, websiteID);
		stmt.setInt(2, websiteID);
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;
	}
}
