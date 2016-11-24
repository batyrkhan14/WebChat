package kz.aleh.web.chat.websocket;

import kz.aleh.web.chat.dao.Dao;

public class LastSeenUpdater extends Thread{
	private int userId;
	private boolean running;
	private Dao dao;
	public LastSeenUpdater(int userId){
		this.userId = userId;
		this.running = true;
		this.dao = new Dao();
	}
	public void terminate(){
		this.running = false;
	}
	@Override
	public void run() {
		super.run();
		while (running){
			dao.updateLastSeen(userId);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
