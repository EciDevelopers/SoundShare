var apiclient = apiclient;
var app = app;
var apiyoutube = apiyoutube;
var websocket = (function () {
    var stompClient=null;
	var sala;
	var song;
	var cont = 0;
	var copied = true;
	var sincronized = false;
	var user;
	
    function initStompClient(){
        console.info('Connecting to SockJS...');
        var socket = new SockJS('/rooms');
        stompClient = Stomp.over(socket);
    };

    function connectAndSubscribeUser(id,nick){
        initStompClient();
		sala = id;
		user = nick;
        stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/sala/'+id,function(eventbody){
				console.log(localStorage.getItem("nameRoom"));
				console.log(eventbody.body);
				users = JSON.parse(eventbody.body);
				console.log(users);
				document.getElementById("num").innerHTML = users.length;
				console.log(apiyoutube.getTime());
				if(sala !== undefined && song !== undefined && apiyoutube.getTime() !== undefined && copied === true){
					console.log('copiar');
					copied = false;
					stompClient.send('/app/sala/'+sala+'/cancionActual/'+song+'/seg/'+apiyoutube.getTime(),{},'');
				}	
				

				
            });
			console.log('aquiiiiiiiiiiiiiiiiiiiiiiii');
			stompClient.send('/app/sala/'+id+'/unir/'+nick,{},'');
			connectAndSubscribeMain();
			
			
			
			
        });
    };
	function desconnectUser(id,nick){
		stompClient.send('/app/sala/'+id+'/salir/'+nick,{},'');
	};
	function fijarSong(cancion){
		song = cancion;
		stompClient.send('/app/sala/'+sala+'/cancionActual/'+cancion+'/seg/'+0,{},'');
	};
	function connectAndSubscribeMain(){
		initStompClient();
		console.log('conecto xd');
		stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/sala/'+sala+'/cancionActual',function(eventbody){
				var datos = JSON.parse(eventbody.body);
				console.log(datos);
				document.getElementById("song").innerHTML = datos[0];
				console.log('entre aqui');
				console.log(eventbody.body);
				console.log(user);
				console.log(sincronized);
				if(song === undefined && sincronized === false){
					console.log('q onda');
					console.log('ciclo');
					sincronized = true;
					var minut = parseInt(datos[1], 10);
					apiyoutube.onYouTubeIframeAPIReady(datos[0],minut+4);
				}
            });
			stompClient.send('/app/sala/'+sala+'/cancionActual/'+' '+'/seg/'+0,{},'');
			
			
        });
	}
	
    return{
        init : initStompClient,
        connectAndSubscribeUser : connectAndSubscribeUser,
		desconnectUser : desconnectUser,
		fijarSong : fijarSong

    };


})();