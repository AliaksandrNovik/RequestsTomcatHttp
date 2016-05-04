package by.rfe.novik;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://146.185.164.93:3306/tomcat7";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "7Ea4fae339";
	Statement stmt = null;
	Connection conn = null;
	
	public CreateDatabase() throws SQLException  {
	
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		     e.printStackTrace();
		}
		try {
		    conn= DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
	public void putIntoDataBase(Message message) throws IOException{
		  try {
	            String sql = "INSERT INTO message (date, time, messageType, messageText) values (?,?, ?, ?)";
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setDate(1, message.getDate());
	            statement.setTime(2, message.getTime());
	            statement.setString(3, message.getMessageType() );
	            statement.setString(4, message.getMessageText());
	            statement.executeUpdate();
	            conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	}

}
