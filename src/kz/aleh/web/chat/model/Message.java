package kz.aleh.web.chat.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity
@Table(name = "MESSAGE")
@NamedQueries({
		@NamedQuery(name = "Message.getMessagesByIds", query = "SELECT msg FROM Message msg where (msg.receiver.id = :receiverId and msg.sender.id = :senderId)"
				+ "or (msg.receiver.id = :senderId and msg.sender.id = :receiverId)") })
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column
	private Date date;

	@Column
	private String content;

	@ManyToOne
	@JoinColumn(name = "SENDER_ID")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "RECEIVER_ID")
	private User receiver;

	public Message() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

}
