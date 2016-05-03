package by.rfe.novik;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class Message {

	private String messageType;
	
	private String messageText;
	
	private int id;
	
	public Date getDate() {
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		return sqlDate;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Time getTime() {
		java.sql.Time sqlTime = new java.sql.Time(new java.util.Date().getTime());
		return sqlTime;
	}


	public String getMessageText() {
		return messageText;
	}


	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	

}
