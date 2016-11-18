package kz.aleh.web.chat.websocket;

public class AddContactResponse {
	private boolean success;
	private String message;
	public AddContactResponse(String message, boolean success){
		this.success = success;
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
