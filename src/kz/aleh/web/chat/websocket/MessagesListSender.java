package kz.aleh.web.chat.websocket;

public class MessagesListSender extends Thread{
	private ChatWebSocket webSocket;
	private int userId;
	private int contactId;
	private boolean running;
	public MessagesListSender(ChatWebSocket webSocket, int userId, int contactId){
		this.webSocket = webSocket;
		this.userId = userId;
		this.contactId = contactId;
		running = true;
	}
	public void terminate(){
		running = false;
	}
	public void setContactId(int contactId){
		this.contactId = contactId;
	}
	@Override
	public void run() {
		super.run();
		while(running){
			webSocket.sendMessagesList(userId, contactId);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
