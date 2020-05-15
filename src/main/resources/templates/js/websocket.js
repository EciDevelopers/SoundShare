var apiclient = apiclient;
var app = app;
var apiyoutube = apiyoutube;
var websocket = (function () {
    var stompClient=null;
	var sala;
	var song;
	var cont = 0;
	var sincronized = false;
	var user;
	var play = false;
	var action = false;
	
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
				if( users.length == 1){
					cont++;
				}
				if(sala !== undefined && song !== undefined && apiyoutube.getTime() !== undefined ){
					console.log('copiar');
					action === true;
					setTimeout(function() {
					   stompClient.send('/app/sala/'+sala+'/cancionActual/'+song+'/seg/'+apiyoutube.getTime(),{},'');
					}, 1000);
				}	
				

				
            });
			console.log('aquiiiiiiiiiiiiiiiiiiiiiiii');
			stompClient.send('/app/sala/'+id+'/unir/'+nick,{},'');
			connectAndSubscribeMain();
	
        });
    };
	function desconnectUser(){
		if(sala !== undefined && user !== undefined){
			stompClient.send('/app/sala/'+sala+'/salir/'+user,{},'');
			window.history.back();
		}
	};
	function fijarSong(cancion){
		song = cancion;
		stompClient.send('/app/sala/'+sala+'/cancionActual/'+cancion+'/seg/'+0,{},'');
		play = true;
		connectAndSubscribeEstado();
	};
	function cambiarEstado(){	
		console.log('hopeeeeeeeeeeeeeee');
		console.log('por acaaaaaaaaaaaaaaa');
		console.log(cont);
		if(play === true){
			console.log('pause main');
			apiyoutube.pause();
			play = false;
		}
		else{
			console.log('play main');
			apiyoutube.play();
			play = true;
		}	
		console.log('exploittttttttttttt');
		var hope = cont;
		stompClient.send('/app/sala/'+sala+'/play/'+user+'/estado/'+play+'/intento/'+hope,{},'');
		cont++;

	
			
			
					
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
				if(song === undefined && datos[0] !== 'ini' && sincronized === false){
					console.log('q onda');
					console.log('ciclo');
					var minut = parseInt(datos[1], 10);
					console.log(play);
					sincronized = true;
					connectAndSubscribeEstado();
					apiyoutube.onYouTubeIframeAPIReady(datos[0],minut+4);				
				}
				else{
					cont++;
				}
            });	
			stompClient.send('/app/sala/'+sala+'/cancionActual/'+' '+'/seg/'+0,{},'');
			
			
        });
	};
	function connectAndSubscribeEstado(){
		initStompClient();
		console.log('conecto play');
		stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/sala/'+sala+'/play',function(eventbody){
				var datos = JSON.parse(eventbody.body);
				console.log(eventbody.body);
				console.log('seraaaaaaaaaaaaaa');
				var num = parseInt(datos[2], 10);
				console.log(num);
				if(datos[0] !== user && num !== 0){
					cont = num;
					if(datos[1] === 'false'){
						console.log('pause');
						apiyoutube.pause();
						play = false;
					}
					else{
						console.log('play');
						apiyoutube.play();
						play = true;
					}
				}
				
				
				
						
            });	
			stompClient.send('/app/sala/'+sala+'/play/'+user+'/estado/'+false+'/intento/'+0,{},'');
        });
	};
	
	
    return{
        init : initStompClient,
        connectAndSubscribeUser : connectAndSubscribeUser,
		desconnectUser : desconnectUser,
		fijarSong : fijarSong,
		cambiarEstado : cambiarEstado

    };


})();