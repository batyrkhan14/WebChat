var webSocket = new WebSocket("ws://localhost:8080/WebChat/ChatWebSocket");
var selectedContact = -1;

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
		div.innerHTML  += 	"<div class='chat-member'>" +
				        		"<div class='img-container'>" +
				        	    	"<a href='#'><img class='mg-responsive' src='img/user/2.jpg' alt='' /></a>" +
				        	    	"<span class='st-live status'></span>" +
				        	    "</div>" +
				        	    "<h4><a href='#' onclick='changeContact("+ contact.id +")'>" + contact.name + "</a></h4>" +
				        	    "<p>I am online now.</p>" +
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
		div.innerHTML += "<div class='chat-box " + type + "'>" +
						    "<div class='img-container'>" +
						        "<img class='img-responsive' src='img/user/4.jpg' alt='' />" +
						    "</div>" +
						    "<div class='message'>" +
						        "<h5><i class='fa fa-clock-o'></i>&nbsp; " + message.date + "</h5>" +
						        "<p>" + message.content + "</p>" +
						    "</div>" +
						    "<div class='clearfix'></div>" +
						"</div>";
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
		console.log("Sending: " + contactId);
		selectedContact = contactId;
		msg = {
			type: "getMessages",
			userId: userId,
			contactId: contactId
		};
		sendMessage(JSON.stringify(msg));
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
	}
}