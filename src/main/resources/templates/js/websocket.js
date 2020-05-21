var apiclient = apiclient;
var app = app;
var apiyoutube = apiyoutube;
var websocket = (function () {
    var stompClient=null;
	var sala;
	var lista;
	var cont = 0;
	var posi;
	var sincronized = false;
	var user;
	var play = false;
	var action = false;
	var actionhope = false;
	var delta;
	var playnombres = [];
	
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
					console.log(delta);
					action === true;
					setTimeout(function() {
					   stompClient.send('/app/sala/'+sala+'/cancionActual/'+JSON.stringify(delta)+'/index/'+posi+'/seg/'+apiyoutube.getTime(),{},'');
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
	function fijarSong(canciones,pos,timer){
			console.log('fijar');
			for(var i=0;i<canciones.length;i++){
				apiclient.getNameSong(canciones[i],bucle);
			}
			console.log(canciones.length);
			console.log(timer);
			posi = pos;		
			delta ={
				lista : playnombres,
			};
			console.log(delta);
			setTimeout(function() {
				stompClient.send('/app/sala/'+sala+'/cancionActual/'+JSON.stringify(delta)+'/index/'+posi+'/seg/'+timer,{},'');
				lista =  playnombres;
				play = true;
				//connectAndSubscribeEstado();
			}, 1000);
	
	};
	function bucle(res) {
		console.log('bucle');
		console.log(res);
		if(!playnombres.includes(res)){
			playnombres.push(res);
		}
		
	};
	function addSongByUser(res) {
		console.log('song by user');
		console.log(res);
		stompClient.send('/app/sala/user/'+user+'/cancion/'+res,{},'');
		
	};
	function addSongPlay(canciones){
		console.log('add cancion alfa');
		for(var i=0;i<canciones.length;i++){
			
				apiclient.getNameSong(canciones[i],bucle);
			
		}
		delta ={
			lista : playnombres,
		};
		setTimeout(function() {
			console.log(delta);
			stompClient.send('/app/sala/'+sala+'/lista/'+JSON.stringify(delta)+'/index/'+posi+'/seg/'+apiyoutube.getTime(),{},'');
		}, 1000);
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
		var userlist = [];
		userlist.push(user);
		delta ={
			users : userlist,
		};
		console.log('exploittttttttttttt');
		stompClient.send('/app/sala/'+sala+'/play/'+JSON.stringify(delta)+'/estado/'+play,{},'');
		console.log('destroyer 26');

	
			
			
					
	};
	function cambiarNext(){	
		console.log('hopeeeeeeeeeeeeeee');
		console.log('nexttttttttttttttttt');
		console.log(cont);
		posi+=1;
		var userlist = [];
		userlist.push(user);
		delta ={
			lista : playnombres,
			users : userlist,
		};
		apiyoutube.next();
		stompClient.send('/app/sala/'+sala+'/next/'+JSON.stringify(delta)+'/index/'+posi+'/seg/'+0,{},'');					
	};
	function cambiarBack(){	
		console.log('hopeeeeeeeeeeeeeee backkk');
		console.log('backkkkk');
		console.log(cont);
		posi-=1;
		var userlist = [];
		userlist.push(user);
		delta ={
			lista : playnombres,
			users : userlist,
		};
		apiyoutube.back();
		stompClient.send('/app/sala/'+sala+'/back/'+JSON.stringify(delta)+'/index/'+posi+'/seg/'+0,{},'');					
	};
	function connectAndSubscribeMain(){
		initStompClient();
		console.log('conecto xd');
		stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
			var deltaini ={
				lista : [],
			};
            stompClient.subscribe('/topic/sala/'+sala+'/cancionActual',function(eventbody){
				var datos = JSON.parse(eventbody.body);
				console.log(datos);
				document.getElementById("song").innerHTML = datos[0];
				console.log('entre aqui');
				console.log(eventbody.body);
				console.log(user);
				console.log(sincronized);
				console.log(lista);
				app.createtable(localStorage.getItem("nameRoom"));
				if((lista === undefined && datos[0] !== 'ini') ){
					console.log('q onda');
					console.log('ciclo');
					var minut = parseInt(datos[1], 10);
					console.log(play);
					sincronized = true;
					console.log(posi);
					console.log(datos[2]);
					console.log(minut);
					apiyoutube.onYouTubeIframeAPIReady(datos[2],datos[3],minut+4);				
				}
				else{
					console.log('destroyer');
					cont++;
				}
            });	
			stompClient.send('/app/sala/'+sala+'/cancionActual/'+JSON.stringify(deltaini)+'/index/'+0+'/seg/'+0,{},'');
			connectAndSubscribeEstado();
			
			
        });
	};
	function connectAndSubscribeEstado(){
		initStompClient();
		console.log('conecto play');
		stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
			var deltaini ={
				users : [],
			};
            stompClient.subscribe('/topic/sala/'+sala+'/play',function(eventbody){
				var datos = JSON.parse(eventbody.body);
				if(datos[0] !== 'ini'){
					console.log(eventbody.body);
					console.log('seraaaaaaaaaaaaaa');
					if(!datos[0].includes(user)){
						datos[0].push(user);
						var delta ={
							users : datos[0],
						};
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
						stompClient.send('/app/sala/'+sala+'/play/'+JSON.stringify(delta)+'/estado/'+play,{},'');
					}
				}	
				
				
						
            });	
			stompClient.send('/app/sala/'+sala+'/play/'+JSON.stringify(deltaini)+'/estado/'+false,{},'');
			connectAndSubscribelista();
			
        });
	};
	function connectAndSubscribelista(){
		initStompClient();
		console.log('conecto lista');
		stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
			var deltaini ={
				lista : [],
			};
            stompClient.subscribe('/topic/sala/'+sala+'/lista',function(eventbody){
				var datos = JSON.parse(eventbody.body);
				console.log(eventbody.body);
				var datos = JSON.parse(eventbody.body);
				if(datos[0] !== 'ini'){
					delta ={
						lista : datos[2],
					};
					var num = parseInt(datos[1], 10);
					for(var i=0;i<datos[2].length;i++){
						console.log(datos[2][i]);
						apiclient.getIdSong(datos[2][i],apiyoutube.addcancion);
					}
					console.log('listaa updateeeeeeeeeeeeeee');	
					stompClient.send('/app/sala/'+sala+'/cancionActual/'+JSON.stringify(delta)+'/index/'+datos[3]+'/seg/'+num,{},'');
					lista = delta.lista;
					console.log(lista);
				
				}	
            });	
			stompClient.send('/app/sala/'+sala+'/lista/'+JSON.stringify(deltaini)+'/index/'+0+'/seg/'+0,{},'');
			connectAndSubscribeNext();
        });
	};
	function connectAndSubscribeNext(){
		initStompClient();
		console.log('conecto Nest');
		stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
			var deltaini ={
				lista : [],
				users : [],
			};
            stompClient.subscribe('/topic/sala/'+sala+'/next',function(eventbody){
				var datos = JSON.parse(eventbody.body);
				console.log(eventbody.body);
				console.log(posi);
				if(datos[0] !== 'ini'){				
					console.log('firessssssstone');
					var delta ={
						lista : datos[0],
						users : datos[1],
					};
					if(!datos[1].includes(user) && datos[2] !== player.getPlaylistIndex()){
						apiyoutube.next();
						delta.users.push(user);
						
					}
					posi = datos[2];
					stompClient.send('/app/sala/'+sala+'/cancionActual/'+JSON.stringify(delta)+'/index/'+posi+'/seg/'+0,{},'');
					

				}	
            });	
			stompClient.send('/app/sala/'+sala+'/next/'+JSON.stringify(deltaini)+'/index/'+0+'/seg/'+0,{},'');
			connectAndSubscribeBack();
        });
	};
		function connectAndSubscribeBack(){
		initStompClient();
		console.log('conecto back');
		stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
			var deltaini ={
				lista : [],
				users : [],
			};
            stompClient.subscribe('/topic/sala/'+sala+'/back',function(eventbody){
				var datos = JSON.parse(eventbody.body);
				console.log(eventbody.body);
				console.log(posi);
				if(datos[0] !== 'ini'){				
					console.log('firessssssstone backkkkkkkkk');
					var delta ={
						lista : datos[0],
						users : datos[1],
					};
					if(!datos[1].includes(user) && datos[2] !== player.getPlaylistIndex()){
						apiyoutube.back();
						delta.users.push(user);
					
					}
					posi = datos[2];
					stompClient.send('/app/sala/'+sala+'/cancionActual/'+JSON.stringify(delta)+'/index/'+posi+'/seg/'+0,{},'');	
					
					

				}	
            });	
			stompClient.send('/app/sala/'+sala+'/back/'+JSON.stringify(deltaini)+'/index/'+0+'/seg/'+0,{},'');
			connectAndSubscribeSongsUpdates();
        });
	};
	function connectAndSubscribeSongsUpdates(){
		initStompClient();
		console.log('conecto back');
		stompClient.connect({}, function (frame) {
			console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/sala/user/'+user,function(eventbody){
				if(eventbody.body !== null && eventbody.body === user){
					alert("Registro Exitoso");
				}
            });	
			stompClient.send('/app/sala/user/'+user+'/cancion/'+' ',{},'');
        });
	};
	
	
    return{
        init : initStompClient,
        connectAndSubscribeUser : connectAndSubscribeUser,
		desconnectUser : desconnectUser,
		fijarSong : fijarSong,
		cambiarEstado : cambiarEstado,
		addSongPlay : addSongPlay,
		cambiarNext : cambiarNext,
		cambiarBack : cambiarBack,
		addSongByUser : addSongByUser
		

    };


})();