package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrivilegeDao {
	DbConfig cfg;
	public static PrivilegeDao instance = null;
	public static PrivilegeDao getInstance() {
		if (instance == null) {
			instance = new PrivilegeDao();
		}
		return instance;
	}
	
	private PrivilegeDao() {
		cfg = new DbConfig();
	}
	
	public int assignWebsitePrivilege(int developerId, int websiteId, Privilege privilege) throws SQLException, ClassNotFoundException {
		String sql = "INSERT Into WebsitePrivilege (websiteID, developerID, privilege) VALUES (?,?,?)";
		Connection conn = cfg.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, websiteId);
		statement.setInt(2, developerId);
		statement.setString(3, privilege.toString());
		int res = statement.executeUpdate();
		statement.close();
		conn.close();
		return res;
	}
	
	public int assignPagePrivilege(int developerId, int pageId, Privilege privilege) throws SQLException, ClassNotFoundException {
		String sql = "INSERT Into PagePrivilege (pageID, developerID, privilege)  VALUES (?,?,?)";
		Connection conn = cfg.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, pageId);
		statement.setInt(2, developerId);
		statement.setString(3, privilege.toString());
		int res = statement.executeUpdate();
		statement.close();
		conn.close();
		return res;
	}
	
	public int deleteWebsitePrivilege(int developerId, int websiteId, Privilege privilege) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM WebsitePrivilege WHERE developerID=? AND websiteID=? AND privilege=?";
		Connection conn = cfg.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, developerId);
		statement.setInt(2, websiteId);
		statement.setString(3, privilege.toString());
		int res = statement.executeUpdate();
		statement.close();
		conn.close();
		return res;
	}
	
	public int deletePagePrivilege(int developerId, int pageId, Privilege privilege) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM PagePrivilege WHERE developerID=? AND pageID=? AND privilege=?";
		Connection conn = cfg.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, developerId);
		statement.setInt(2, pageId);
		statement.setString(3, privilege.toString());
		int res = statement.executeUpdate();
		statement.close();
		conn.close();
		return res;
	}	
}
