package kz.aleh.web.chat;

public class SignUpResponse {
	private boolean success;
	private int errorCode;
	public SignUpResponse(){
		
	}
	public SignUpResponse(boolean success, int errorCode){
		this.success = success;
		this.errorCode = errorCode;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
