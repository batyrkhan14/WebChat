package kz.aleh.web.chat.dto;

import java.io.Serializable;
import java.util.Date;

import kz.aleh.web.chat.model.User;

/**
 * @author root
 *
 */
public class ContactDto implements Serializable{
	private static final long SECONDS_PER_WEEK = 604800;
	private static final long SECONDS_PER_DAY = 86400;
	private static final long SECONDS_PER_HOUR = 3600;
	private static final long SECONDS_PER_MINUTE = 60;	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private String lastSeen;
	private boolean isAccepted;
	private boolean isReceiver;
	private long secs;

	public ContactDto(User user, boolean isAccepted, boolean isReceiver) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.isAccepted = isAccepted;
		this.isReceiver = isReceiver;
		this.lastSeen = getWrappedDate(user.getLastSeen());
	}

	public ContactDto(User user, boolean isAccepted) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.isAccepted = isAccepted;
	}
	
	private String getWrappedDate(Date d){
		Date now = new Date();
		long seconds = (now.getTime()-d.getTime())/1000;
		this.secs = seconds;
		String result = "Last seen ";
		if (seconds < 5){
			return "Online";
		}
		else if (seconds > SECONDS_PER_WEEK){
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public boolean isReceiver() {
		return isReceiver;
	}

	public void setReceiver(boolean isReceiver) {
		this.isReceiver = isReceiver;
	}

}
