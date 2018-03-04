package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Statement;

public class UserDao {

	DbConfig cfg;
	public static UserDao instance = null;
	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	private UserDao() {
		cfg = new DbConfig();
	}
	
	public int createUser(User user) throws SQLException, ClassNotFoundException {
		String sql;
		if(user.getId() != -1)	// id specified
			sql = "INSERT INTO Person (id, firstName, lastName, username, password, email, dob) VALUES (?,?,?,?,?,?,?)";
		else	// AI ID
			sql = "INSERT INTO Person (firstName, lastName, username, password, email, dob) VALUES (?,?,?,?,?,?)";
		Connection conn = cfg.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		int i = 0;
		if(user.getId() != -1)
			stmt.setInt(++i, user.getId());
		stmt.setString(++i, user.getFirstName());
		stmt.setString(++i, user.getLastName());
		stmt.setString(++i, user.getUsername());
		stmt.setString(++i, user.getPassword());
		stmt.setString(++i, user.getEmail());
		stmt.setDate(++i, user.getDob());
		
		int result = stmt.executeUpdate();
		
		if(result == 0)
			throw new SQLException("SQL error occurred");
		
		// Useful when developer object has no pre-assigned ID
		int id = findUserByCredentials(user.getUsername(), user.getPassword()).getId();
		
		sql = "INSERT INTO User (personID, userAgreement) VALUES(?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.setBoolean(2, user.isUserAgreement());
		
		result = stmt.executeUpdate();
		
		if(result == 0)
			throw new SQLException("SQL error occurred");
		
		stmt.close();
		conn.close();
		return result;
	}

	public Collection<User> findAllUsers() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM User JOIN Person ON User.id = Person.id";
		Connection conn = cfg.getConnection();
		Statement stmt = conn.createStatement();
		Collection<User> users = new ArrayList<User>();
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			User d = new User(res.getInt("id"),
					res.getString("firstName"),
					res.getString("lastName"),
					res.getString("username"),
					res.getString("password"),
					res.getString("email"),
					res.getDate("dob"),
					res.getInt("personID"),
					res.getBoolean("userAgreement"));
			users.add(d);
		}
		
		stmt.close();
		conn.close();
		return users;
	}
	
	public User findUserById(int userId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM User JOIN Person ON User.personID = Person.id WHERE User.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, userId);
		ResultSet res = stmt.executeQuery();
		User u = null;
		if (res.next()) {
			u = new User(res.getInt("id"),
					res.getString("firstName"),
					res.getString("lastName"),
					res.getString("username"),
					res.getString("password"),
					res.getString("email"),
					res.getDate("dob"),
					res.getInt("personID"),
					res.getBoolean("userAgreement"));
		}
		
		stmt.close();
		conn.close();
		return u;
	}

	public User findUserByUsername(String username) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM User JOIN Person ON User.personID = Person.id WHERE Person.username = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet res = stmt.executeQuery();
		User u = null;
		if (res.next()) {
			u = new User(res.getInt("id"),
					res.getString("firstName"),
					res.getString("lastName"),
					res.getString("username"),
					res.getString("password"),
					res.getString("email"),
					res.getDate("dob"),
					res.getInt("personID"),
					res.getBoolean("userAgreement"));
		}
		stmt.close();
		conn.close();
		return u;
	}

	public User findUserByCredentials(String username, String password) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM User JOIN Person ON User.personID = Person.id WHERE Person.username = ? AND Person.password = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet res = stmt.executeQuery();
		User u = null;
		if (res.next()) {
			u = new User(res.getInt("id"),
					res.getString("firstName"),
					res.getString("lastName"),
					res.getString("username"),
					res.getString("password"),
					res.getString("email"),
					res.getDate("dob"),
					res.getInt("personID"),
					res.getBoolean("userAgreement"));
		}
		stmt.close();
		conn.close();
		return u;
	}
	
	public int updateUser(int userId, User user) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE Person SET firstName = ?, lastName = ?, username = ?, password = ?, email = ?, dob = ? WHERE Person.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, user.getFirstName());
		stmt.setString(2, user.getLastName());
		stmt.setString(3, user.getUsername());
		stmt.setString(4, user.getPassword());
		stmt.setString(5, user.getEmail());
		stmt.setDate(6, user.getDob());
		stmt.setInt(7, userId);
		int result = stmt.executeUpdate();
		if(result == 0)
			throw new SQLException("Error while updating Person");
		sql = "UPDATE User SET userAgreement = ? WHERE User.id = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setBoolean(1, user.isUserAgreement());
		stmt.setInt(2, userId);
		result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;	
	}
	
	public int deleteUser(int userId) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM User WHERE User.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, userId);
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;
		
	}
}
