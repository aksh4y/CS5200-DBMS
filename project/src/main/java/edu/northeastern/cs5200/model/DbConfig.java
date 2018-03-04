package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://cs5200-spring2018-sadarangani.czn5drydodmg.us-east-2.rds.amazonaws.com/sadarangani_akshay_spring_2018";
	private static final String USERNAME = "asadarangani";
	private static final String PASSWORD = "1Won'tTellY0u";

	//public static DeveloperDao developerSingleton = DeveloperDao.getInstance();
	
	public static Connection conn = null;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if(conn == null || conn.isClosed())
			conn = connect();
		return conn;
	}
	
	public Connection connect() throws SQLException, ClassNotFoundException {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);	
			return conn;
	}
}
