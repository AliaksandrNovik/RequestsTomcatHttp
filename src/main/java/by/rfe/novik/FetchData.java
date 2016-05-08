package by.rfe.novik;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FetchData {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	
	static final String DB_URL = "jdbc:mysql://127.5.127.2:3306/tomcat7";
	//static final String DB_URL = "jdbc:mysql://146.185.164.93:3306/tomcat7";

	static final String USER = "admin6PK4xdu";
	static final String PASS = "w6WTVFDyPxGU";
	
	//  Database credentials
	//static final String USER = "root";
	//static final String PASS = "7Ea4fae339";
	
	
	
	Statement stmt = null;
	static  Connection connection = null;
	
	
	public FetchData()  {
	
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		     e.printStackTrace();
		}
		try {
			connection= DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
	public void putIntoDataBase(Message message) throws IOException{
		  try {
	            String sql = "INSERT INTO message (date, time, messageType, messageText) values (?,?, ?, ?)";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setDate(1, message.getDate());
	            statement.setTime(2, message.getTime());
	            statement.setString(3, message.getMessageType() );
	            statement.setString(4, message.getMessageText());
	            statement.executeUpdate();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	}
	
	public   List<Message> getList() {
		List<Message> listMessages = new ArrayList<Message>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from message ORDER BY id DESC LIMIT 20");
			while(rs.next()){
				Message message = new Message();
				message.setDate(rs.getDate("date").toString());
				message.setTime(rs.getTime("time"));
				message.setMessageText(rs.getString("messageText"));
				message.setMessageType(rs.getString("messageType"));
				message.setId(rs.getInt("id"));
				listMessages.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listMessages;
	}
}
