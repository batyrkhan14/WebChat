package kz.aleh.web.chat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Link
 *
 */
@Entity
@Table(name = "LINK")
@NamedQueries({
		@NamedQuery(name = "Link.getContactsOfUser", query = "SELECT ln FROM Link ln WHERE ln.receiver.id = :userId OR ln.sender.id = :userId"),
		@NamedQuery(name = "Link.getLinkByIds", query = "SELECT ln FROM Link ln where (ln.receiver.id = :receiverId and ln.sender.id = :senderId)"
				+ "or (ln.receiver.id = :senderId and ln.sender.id = :receiverId)") })
public class Link implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column
	private boolean accepted;

	@ManyToOne
	@JoinColumn(name = "SENDER_ID")
	User sender;

	@ManyToOne
	@JoinColumn(name = "RECEIVER_ID")
	User receiver;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
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

	public Link() {
		super();
	}

}
