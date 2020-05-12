var apiclient = apiclient;
var app = app;
var websocket = (function () {
    var stompClient=null;


    function initStompClient(){
        console.info('Connecting to SockJS...');
        var socket = new SockJS('/rooms');
        stompClient = Stomp.over(socket);
    };

    function connectAndSubscribeUser(id,nick){
        initStompClient();
        stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/sala/'+id,function(eventbody){
				console.log(localStorage.getItem("nameRoom"));
				console.log(eventbody.body);
				users = JSON.parse(eventbody.body);
				console.log(users);
				document.getElementById("num").innerHTML = users.length;
            });
			stompClient.send('/app/sala/'+id+'/unir/'+nick,{},'');
			
        });
    };
	function desconnectUser(id,nick){
		stompClient.send('/app/sala/'+id+'/salir/'+nick,{},'');
	}
	
    return{
        init : initStompClient,
        connectAndSubscribeUser : connectAndSubscribeUser,
		desconnectUser : desconnectUser

    };


})();