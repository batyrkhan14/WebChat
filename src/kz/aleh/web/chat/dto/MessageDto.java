package kz.aleh.web.chat.dto;

import java.util.Date;

import kz.aleh.web.chat.model.Message;

public class MessageDto {
	private int id;
	private int senderId;
	private int receiverId;
	private String content;
	private Date date;

	public MessageDto(Message message) {
		this.content = message.getContent();
		this.date = message.getDate();
		this.id = message.getId();
		this.receiverId = message.getReceiver().getId();
		this.senderId = message.getSender().getId();
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
