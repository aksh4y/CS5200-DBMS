package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Statement;

public class DeveloperDao {

	DbConfig cfg;
	public static DeveloperDao instance = null;
	public static DeveloperDao getInstance() {
		if (instance == null) {
			instance = new DeveloperDao();
		}
		return instance;
	}
	
	private DeveloperDao() {
		cfg = new DbConfig();
	}
	
	public int createDeveloper(Developer developer) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO Person (id, firstName, lastName, username, password, email, dob) VALUES (?,?,?,?,?,?,?)";
		Connection conn = cfg.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, developer.getId());
		stmt.setString(2, developer.getFirstName());
		stmt.setString(3, developer.getLastName());
		stmt.setString(4, developer.getUsername());
		stmt.setString(5, developer.getPassword());
		stmt.setString(6, developer.getEmail());
		stmt.setDate(7, developer.getDob());
		
		int result = stmt.executeUpdate();
		
		if(result == 0)
			throw new SQLException("SQL error occurred");
		
		sql = "INSERT INTO Developer (id, developerKey) VALUES(?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, developer.getId());
		stmt.setString(2, developer.getDeveloperKey());
		
		result = stmt.executeUpdate();
		
		if(result == 0)
			throw new SQLException("SQL error occurred");
		
		stmt.close();
		conn.close();
		return result;
	}

	public Collection<Developer> findAllDevelopers() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Developer JOIN Person ON Developer.id = Person.id";
		Connection conn = cfg.getConnection();
		Statement stmt = conn.createStatement();
		Collection<Developer> devs = new ArrayList<Developer>();
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			Developer d = new Developer(res.getInt("id"),
					res.getString("firstName"),
					res.getString("lastName"),
					res.getString("username"),
					res.getString("password"),
					res.getString("email"),
					res.getDate("dob"),
					res.getString("developerKey"));
			devs.add(d);
		}
		
		stmt.close();
		conn.close();
		return devs;
	}
	
	public Developer findDeveloperById(int developerId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Developer JOIN Person ON Developer.id = Person.id WHERE Developer.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, developerId);
		ResultSet res = stmt.executeQuery();
		Developer dev = null;
		if (res.next()) {
			dev = new Developer(res.getInt("id"),
					res.getString("firstName"),
					res.getString("lastName"),
					res.getString("username"),
					res.getString("password"),
					res.getString("email"),
					res.getDate("dob"),
					res.getString("developerKey"));
		}
		
		stmt.close();
		conn.close();
		return dev;
	}

	public Developer findDeveloperByUsername(String username) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Developer JOIN Person ON Developer.id = Person.id WHERE Person.username = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet res = stmt.executeQuery();
		Developer dev = null;
		if (res.next()) {
			dev = new Developer(res.getInt("id"),
					res.getString("firstName"),
					res.getString("lastName"),
					res.getString("username"),
					res.getString("password"),
					res.getString("email"),
					res.getDate("dob"),
					res.getString("developerKey"));
		}
		stmt.close();
		conn.close();
		return dev;
	}

	public Developer findDeveloperByCredentials(String username, String password) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Developer JOIN Person ON Developer.id = Person.id WHERE Person.username = ? AND Person.password = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet res = stmt.executeQuery();
		Developer dev = null;
		if (res.next()) {
			dev = new Developer(res.getInt("id"),
					res.getString("firstName"),
					res.getString("lastName"),
					res.getString("username"),
					res.getString("password"),
					res.getString("email"),
					res.getDate("dob"),
					res.getString("developerKey"));
		}
		stmt.close();
		conn.close();
		return dev;
	}
	
	public int updateDeveloper(int developerId, Developer developer) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE Person SET firstName = ?, lastName = ?, username = ?, password = ?, email = ?, dob = ? WHERE Person.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, developer.getFirstName());
		stmt.setString(2, developer.getLastName());
		stmt.setString(3, developer.getUsername());
		stmt.setString(4, developer.getPassword());
		stmt.setString(5, developer.getEmail());
		stmt.setDate(6, developer.getDob());
		stmt.setInt(7, developerId);
		int result = stmt.executeUpdate();
		if(result == 0)
			throw new SQLException("Error while updating Person");
		sql = "UPDATE Developer SET developerKey = ? WHERE Developer.id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, developer.getDeveloperKey());
		stmt.setInt(2, developerId);
		result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;	
	}
	
	public int deleteDeveloper(int developerId) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM Developer WHERE Developer.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, developerId);
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;
		
	}
}
