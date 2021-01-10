document.getElementById("connect").addEventListener("click", connect);
document.getElementById("disconnect").addEventListener("click", disconnect);

var stompClient = null;

function connect(e) {
  e.preventDefault();

  var user = document.getElementById("user").value;
  var password = document.getElementById("password").value;
  var channelId = document.getElementById("channelId").value;

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
      document.getElementById("connect").disabled = true;
      document.getElementById("disconnect").disabled = false;
    });
  }
}

function disconnect() {
  stompClient.disconnect();
  console.log("Disconnected");
}