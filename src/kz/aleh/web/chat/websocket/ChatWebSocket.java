package kz.aleh.web.chat.websocket;

import java.util.Date;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kz.aleh.web.chat.dao.Dao;
import kz.aleh.web.chat.dto.ContactDto;
import kz.aleh.web.chat.dto.MessageDto;
import kz.aleh.web.chat.model.Link;
import kz.aleh.web.chat.model.Message;
import kz.aleh.web.chat.model.User;

@ServerEndpoint("/ChatWebSocket")
public class ChatWebSocket {
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	
	private ContactListSender contactListSender;
	private MessagesListSender messagesListSender;
	private LastSeenUpdater lastSeenUpdater;
	private Dao dao;
	{
		dao = new Dao();
	}
	private Session session;
	@OnMessage
	public void onMessage(String message){
		System.out.println("Received: " + message);
		JsonObject request = new JsonParser().parse(message).getAsJsonObject();
		String requestType = request.get("type").getAsString();
		switch(requestType){
			case "userId":
				contactListSender = new ContactListSender(this, request.get("userId").getAsInt());
				contactListSender.start();
				lastSeenUpdater = new LastSeenUpdater(request.get("userId").getAsInt());
				lastSeenUpdater.start();
				break;
			case "addContact":
				addContact(request.get("userId").getAsInt(), request.get("contactEmail").getAsString());
				break;
			case "getMessages":
				invokeMessagesListSender(request.get("userId").getAsInt(), request.get("contactId").getAsInt());
				break;
			case "sendMessage":
				addMessage(request.get("senderId").getAsInt(), request.get("receiverId").getAsInt(), request.get("message").getAsString());
				break;
		}
	}
	
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		System.out.println("Session: " + session);
	}
	
	@OnClose
	public void onClose(){
		this.session = null;
		terminateThreads();
	}
	
	@OnError
	public void onError(Throwable t){
		terminateThreads();
	}
	
	private void sendMessage(String s){
		//System.out.println("Sending: " + s);
		this.session.getAsyncRemote().sendText(s);
	}
	public void sendContactList(int userId){
		Dao temp = new Dao();
		List<ContactDto> contacts = temp.getContactListOfUser(userId);
		temp = null;
		Gson gson = new Gson();
		String msg = gson.toJson(new MessageWrapper("contactList", contacts));
		sendMessage(msg);				
	}
	private void terminateThreads(){
		if (contactListSender != null){
			contactListSender.terminate();
			try {
				contactListSender.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (messagesListSender != null){
			messagesListSender.terminate();
			try {
				messagesListSender.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (lastSeenUpdater != null){
			lastSeenUpdater.terminate();
			try {
				lastSeenUpdater.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void addContact(int userId, String contactEmail){
		String message = null;
		boolean success = false;
		if (!contactEmail.matches(EMAIL_PATTERN)){
			message = "Email is not valid";
		}
		else{
			User receiver = null, sender = null;
			Link link;
			try {
				receiver = dao.getUserByEmail(contactEmail);
				sender = dao.getUser(userId);
			} catch (Exception e) {
				e.printStackTrace();
				message = "No user with such email.";
			}
			if (receiver != null && sender != null){
				if (receiver.getId() == sender.getId()){
					message = "You can not add yourself.";
				}
				else{
					try {
						link = dao.getLinkByIds(receiver.getId(), sender.getId());
						message = "This contact is already in your contact list.";
					} catch (Exception e) {
						e.printStackTrace();
						link = null;
						
					}
					if (link == null){
						try {
							dao.addLink(sender, receiver);
							message = "Contact successfully added.";
							success = true;															
						} catch (Exception e) {
							e.printStackTrace();
							message = "Unknown server error, please try again.";
						}
					}					
				}
			}
		}
		
		if (message == null) message = "Unknown server error, please try again.";
		sendMessage(new Gson().toJson(new MessageWrapper("addContactResponse", new AddContactResponse(message, success))));
		if (success) this.sendContactList(userId);
	}
	public void sendMessagesList(int userId, int contactId){
		List<MessageDto> messages = dao.getMessages(userId, contactId);
		sendMessage(new Gson().toJson(new MessageWrapper("messagesList", messages)));
	}
	public void invokeMessagesListSender(int userId, int contactId){
		if (messagesListSender == null){
			messagesListSender = new MessagesListSender(this, userId, contactId);
			messagesListSender.start();					
		}
		else{
			messagesListSender.setContactId(contactId);
		}
	}
	public void addMessage(int senderId, int receiverId, String message){
		try {
			System.out.println("SenderId: " + senderId + ", ReceiverId: " + receiverId + " , Message: " + message);
			User sender = dao.getUser(senderId);
			User receiver = dao.getUser(receiverId);
			Message msg = new Message();
			msg.setSender(sender);
			msg.setReceiver(receiver);
			msg.setContent(message);
			msg.setDate(new Date());
			dao.addMessage(msg);
			this.sendMessagesList(senderId, receiverId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateLastSeen(int userId){
		dao.updateLastSeen(userId);
	}
}
