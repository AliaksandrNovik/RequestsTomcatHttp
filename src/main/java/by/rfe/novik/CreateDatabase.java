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
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";


	//  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	Connection conn = null;
	Statement stmt = null;
	
	public CreateDatabase() throws SQLException  {
	
		//STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	
	public void putIntoDataBase(Message message) throws IOException{
		  try {
	            String sql = "INSERT INTO person (date, time, messageType, messageText) values (?,?, ?, ?)";
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
