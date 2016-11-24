package kz.aleh.web.chat;

import java.util.ArrayList;
import java.util.List;

import kz.aleh.web.chat.model.User;

public class LogInResponse {
	private boolean success;
	private int userId;
	private String email;
	private String name;

	public LogInResponse(boolean success) {
		this.success = success;
	}

	public LogInResponse(boolean success, User user) {
		this.success = success;
		this.userId = user.getId();
		this.email = user.getEmail();
		this.name = user.getName();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
