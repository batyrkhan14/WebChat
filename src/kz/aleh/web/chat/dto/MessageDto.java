package kz.aleh.web.chat.dto;

import java.util.Date;

import kz.aleh.web.chat.model.Message;
import kz.aleh.web.chat.model.User;

public class MessageDto {
	private static final long SECONDS_PER_WEEK = 604800;
	private static final long SECONDS_PER_DAY = 86400;
	private static final long SECONDS_PER_HOUR = 3600;
	private static final long SECONDS_PER_MINUTE = 60;
	private int id;
	private int senderId;
	private int receiverId;
	private String content;
	private String date;
	private String senderName;

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public MessageDto(Message message) {
		this.content = message.getContent();
		this.date = getWrappedDate(message.getDate());
		this.id = message.getId();
		this.receiverId = message.getReceiver().getId();
		this.senderId = message.getSender().getId();
		this.senderName = message.getSender().getName();
	}
	
	private String getWrappedDate(Date d){
		Date now = new Date();
		long seconds = (now.getTime()-d.getTime())/1000;
		String result = "";
		if (seconds > SECONDS_PER_WEEK){
			long weeks = seconds/SECONDS_PER_WEEK;
			result += weeks;
			if (weeks == 1) result += " week";
			else result += " weeks";
		}
		else if (seconds > SECONDS_PER_DAY){
			long days = seconds/SECONDS_PER_DAY;
			result += days;
			if (days == 1) result += " day";
			else result += " days";
		}
		else if (seconds > SECONDS_PER_HOUR){
			long hours = seconds/SECONDS_PER_HOUR;
			result += hours;
			if (hours == 1) result += " hour";
			else result += " hours";
		}
		else if (seconds > SECONDS_PER_MINUTE){
			long minutes = seconds/SECONDS_PER_MINUTE;
			result += minutes;
			if (minutes == 1) result += " minute";
			else result += " minutes";
		}
		else{
			result += seconds;
			if (seconds == 1) result += " second";
			else result += " seconds";
		}
		return result += " ago";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
