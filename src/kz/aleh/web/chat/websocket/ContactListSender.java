package kz.aleh.web.chat.websocket;

public class ContactListSender extends Thread{
	private ChatWebSocket webSocket;
	private int userId;
	private volatile boolean running = true;
	public ContactListSender(ChatWebSocket webSocket, int userId){
		this.webSocket = webSocket;
		this.userId = userId;
	}
	public void terminate(){
		running = false;
	}
	@Override
	public void run() {
		super.run();
		System.out.println("started");
		while (running){
			webSocket.sendContactList(userId);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
