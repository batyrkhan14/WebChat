package kz.aleh.web.chat.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import kz.aleh.web.chat.dto.ContactDto;
import kz.aleh.web.chat.dto.MessageDto;
import kz.aleh.web.chat.model.Link;
import kz.aleh.web.chat.model.Message;
import kz.aleh.web.chat.model.User;

public class Dao {

	@PersistenceContext(unitName = "WebChat")
	EntityManager entityManager;

	public Dao() {
		entityManager = Persistence.createEntityManagerFactory("WebChat").createEntityManager();
	}

	public synchronized int addUser(User user) throws Exception {
		beginTransaction();
		entityManager.persist(user);
		commit();
		return user.getId();
	}

	public synchronized int addMessage(Message message) throws Exception {
		beginTransaction();
		entityManager.persist(message);
		System.out.println("messageID: " + message.getId());
		commit();
		return message.getId();
	}

	public synchronized void deleteUser(int userId) throws Exception {
		beginTransaction();
		entityManager.remove(getUser(userId));
		commit();
	}

	public synchronized void editUser(User user) {
		beginTransaction();
		entityManager.merge(user);
		commit();
	}

	public synchronized User getUser(int userId) throws Exception {
		User user = entityManager.find(User.class, userId);
		return user;
	}

	public synchronized User getUserByEmail(String email) throws Exception {
		Query query = entityManager.createNamedQuery("User.findUserByEmail").setParameter("email", email);
		return (User) query.getSingleResult();
	}

	public synchronized List<ContactDto> getContactListOfUser(int userId) {
		Query q = entityManager.createNamedQuery("Link.getContactsOfUser").setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Link> links = (List<Link>) q.getResultList();
		//System.out.println("Link size: " + links.size());
		List<ContactDto> result = new ArrayList<>();
		for (Link link : links) {
			int contactId;
			boolean isReceiver;
			if (link.getSender().getId() == userId) {
				contactId = link.getReceiver().getId();
				isReceiver = true;
			} else {
				contactId = link.getSender().getId();
				isReceiver = true;
			}
			User contact = entityManager.find(User.class, contactId);
			ContactDto contactDto = new ContactDto(contact, link.isAccepted(), isReceiver);
			result.add(contactDto);
		}
		//System.out.println("Result size: " + result.size());
		return result;
	}

	public Link getLinkByIds(int receiverId, int senderId) throws Exception {
		Query q = entityManager.createNamedQuery("Link.getLinkByIds").setParameter("receiverId", receiverId)
				.setParameter("senderId", senderId);
		return (Link) q.getSingleResult();
	}

	public int addLink(User sender, User receiver) throws Exception {
		Link ln = new Link();
		ln.setReceiver(receiver);
		ln.setSender(sender);
		ln.setAccepted(false);
		beginTransaction();
		entityManager.persist(ln);
		commit();
		return ln.getId();
	}

	public List<MessageDto> getMessages(int senderId, int receiverId) {
		List<MessageDto> messages = new ArrayList<>();
		Query q = entityManager.createNamedQuery("Message.getMessagesByIds").setParameter("senderId", senderId)
				.setParameter("receiverId", receiverId);
		@SuppressWarnings("unchecked")
		List<Message> msgs = (List<Message>) q.getResultList();
		for (Message msg : msgs) {
			messages.add(new MessageDto(msg));
		}
		return messages;
	}

	public void updateLastSeen(int userId) {
		try {
			User user = getUser(userId);
			user.setLastSeen(new Date());
			editUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void beginTransaction() {
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
	}

	private void commit() {
		entityManager.getTransaction().commit();
	}

}
