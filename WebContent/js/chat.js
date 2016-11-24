var webSocket = new WebSocket("ws://env-7443164.j.dnr.kz/WebChat/ChatWebSocket");
var selectedContact = -1;
var chl = true;
webSocket.onopen = function(event){
	sendUserId(userId);
}

webSocket.onmessage = function(event){
	var message = event.data;
	console.log("received: " + message);
	var msg = JSON.parse(message);
	var type = msg.type;
	if (type == "contactList"){
		updateContactList(msg.message);
	}
	else if (type == "addContactResponse"){
		handleAddContactResponse(msg.message);
	}
	else if (type == "messagesList"){
		updateMessagesList(msg.message);
	}
}

webSocket.onerror = function(event){
	window.alert("Unexpected server error occured. Please update the page.");
}

function sendMessage(message){
	console.log("sending: " + message);
	webSocket.send(message);
}

function sendUserId(userId){
	msg = {
		type: "userId",
		userId: userId
	};
	sendMessage(JSON.stringify(msg));
}
function updateContactList(contacts){
	var div = document.getElementById('contactList');
	div.innerHTML = "";
	for (var i = 0; i < contacts.length; i++){
		var contact = contacts[i];
		if (i == 0 && selectedContact == -1){
			changeContact(contact.id); 
		}
		var status;
		if (contact.secs <= 5) status = "st-live";
		else if (contact.secs <= 60) status = "st-busy";
		else status = "st-off";
		var firstChar = (contact.name).toUpperCase().charAt(0);		
		div.innerHTML  += 	"<div style='cursor:pointer' onclick='changeContact("+ contact.id +")' id='contact" + contact.id + "'class='chat-member " + ((contact.id == selectedContact) ? "active" : "")+ "'>" +
				        		"<div class='img-container'>" +
				        	    	"<a href='#'><img class='mg-responsive' src='img/profile_pictures/" + firstChar + ".png' alt='' /></a>" +
				        	    	"<span class='" + status + " status'></span>" +
				        	    "</div>" +
				        	    "<h4>" + contact.name + "</h4>" +
				        	    "<p>" + contact.lastSeen + "</p>" +
				        	    "<div class='clearfix'></div>" +
				        	"</div>";
	}
}
function updateMessagesList(messages){
	var div = document.getElementById('messagesList');
	div.innerHTML = "";
	for (var i = 0; i < messages.length; i++){
		var message = messages[i];
		var type;
		if (message.senderId == userId){
			type = "chat-out";
		}
		else{
			type = "chat-in";
		}
		var firstChar = (message.senderName).toUpperCase().charAt(0);
		div.innerHTML += "<div class='chat-box " + type + "'>" +
						    "<div class='img-container'>" +
						        "<img class='img-responsive' src='img/profile_pictures/" + firstChar + ".png' alt='' />" +
						    "</div>" +
						    "<div class='message'>" +
						        "<h5><i class='fa fa-clock-o'></i>&nbsp; " + message.date + "</h5>" +
						        "<p>" + message.content + "</p>" +
						    "</div>" +
						    "<div class='clearfix'></div>" +
						"</div>";
	}
	if (messages.length == 0){
		div.innerHTML += "<p style='text-align:center;color:#c1d5f4;font-size:25px;'>Send message to start conversation</p>";
	}
	div.innerHTML += "<div id='comeHere'></div>";
	if (chl){
		location.hash = "#comeHere";
		chl = false;
	}
}
function addContact(){
	var email = document.getElementById('addContactEmail').value;
	msg = {
		type: "addContact",
		userId: userId,
		contactEmail: email
	};
	sendMessage(JSON.stringify(msg));
}

function handleAddContactResponse(response){
	var div = document.getElementById('addContactError');
	var color;
	if (response.success == true){
		color = "green";
	}
	else{
		color = "red";
	}
	div.innerHTML = "<p style='color:" + color + "'>" + response.message +"</p>";	
}

function changeContact(contactId){
	if (contactId != selectedContact){
		if (selectedContact != -1){
			console.log("old contact id: #contact" + selectedContact);
			var divOld = document.getElementById("contact" + selectedContact);
			if (divOld == null) console.log("NULL");
			if (divOld != null) divOld.classList.remove("active");
		}
		var divNew = document.getElementById("contact" + contactId);
		if (divNew != null) divNew.classList.add("active");
		console.log("Sending: " + contactId);
		selectedContact = contactId;
		msg = {
			type: "getMessages",
			userId: userId,
			contactId: contactId
		};
		sendMessage(JSON.stringify(msg));
		chl = true;
	}
}

function sendChatMessage(){
	var message = document.getElementById("chatMessage").value;
	msg = {
		type: "sendMessage",
		message: message,
		senderId: userId,
		receiverId: selectedContact
	};
	if (selectedContact != -1 && message != ""){
		sendMessage(JSON.stringify(msg));
		document.getElementById("chatMessage").value = "";
	}
	chl = true;
}