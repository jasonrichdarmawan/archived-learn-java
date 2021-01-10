document.getElementById("connect").addEventListener("click", connect);
document.getElementById("disconnect").addEventListener("click", disconnect);
document.getElementById("sendMessage").addEventListener("click", sendMessage);

var stompClient = null;
var user = null;
var channelId = null;

function view(connected) {
  document.getElementById("connect").disabled = connected;
  document.getElementById("disconnect").disabled = !connected;
  document.getElementById("sendMessage").disabled = !connected;
}

function connect(e) {
  e.preventDefault();

  user = document.getElementById("user").value;
  var password = document.getElementById("password").value;
  channelId = document.getElementById("channelId").value;

  if (user && password && channelId) {
    var socket = new SockJS("/chat");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (status) {
      console.log(`StompClient: ${status}}`);

      stompClient.subscribe(`/topic/channel/${channelId}`, function (payload) {
        var message = JSON.parse(payload.body);

        var table = document.getElementById("messages");
        var row = table.insertRow();
        var cell1 = row.insertCell();
        var cell2 = row.insertCell();
        var cell3 = row.insertCell();
        cell1.innerHTML = message.user;
        cell2.innerHTML = message.text;
        cell3.innerHTML = message.iat;
      });

      // TODO: stompClient.connect(, callback) behavior.
      view(true);
    });
  }
}

function disconnect() {
  stompClient.disconnect();
  view(false);
  console.log("Disconnected");
}

function sendMessage(e) {
  e.preventDefault();

  var text = document.getElementById("messageText").value;

  var messageModel = { user, text };

  stompClient.send(`/app/channel/${channelId}`, {}, JSON.stringify(messageModel));
  document.getElementById("messageText").value = "";
}