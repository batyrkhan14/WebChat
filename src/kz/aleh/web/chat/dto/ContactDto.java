package kz.aleh.web.chat.dto;

import java.io.Serializable;

import kz.aleh.web.chat.model.User;

/**
 * @author root
 *
 */
public class ContactDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String email;
	private boolean isAccepted;
	private boolean isReceiver;

	public ContactDto(User user, boolean isAccepted, boolean isReceiver) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.isAccepted = isAccepted;
		this.isReceiver = isReceiver;
	}

	public ContactDto(User user, boolean isAccepted) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.isAccepted = isAccepted;
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
