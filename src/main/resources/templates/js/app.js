var apiclient = apiclient;
var app = (function () {
    class Acto{
        constructor(youtubeaudio){
            this.youtubeaudio=youtube-audio;
        }
    }

    var stompClient = null;
    var id;

    var connectAndSubscribe = function (callback) {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/newpoint', function (eventbody) {
                var obj = JSON.parse(eventbody.body);
                callback(new Acto(obj.youtube-audio));
            });
        });
    };
	var addUser = function(nombre,pass,nickname) {
		var newUser = {
			nombre : nombre,
			pass : pass,
			nickname : nickname,
		};
		apiclient.addUser(JSON.stringify(newUser));
	};
	var addSong = function(nombre,autor,genero,url) {
		var newSong = {
			id : url.split('v=')[1],
			nombre : nombre,
			genero : genero,
			author : autor,
			
		};
		apiclient.addSong(JSON.stringify(newSong));

	};

	function createtable(){
        apiclient.getSongs(showSongs);
    }

    function showSongs(canciones){
        canciones.map(function(canciones) {
              $("#tablas").append(
                  '<tr class="table-success"> <td>'+
                  canciones.id +
                  '</td> <td>' +
                  canciones.nombre +
                  '</td> <td>' +
                  canciones.genero +
                  '</td> <td>' +
                  canciones.author +
                  '</td> <td>' +
                  canciones.minuto
              );
        });
    }

	function setUserLogged(nickname){
        console.log(nickname);
        localStorage.setItem("selectedUser",nickname);
		

    }
	function printUserLogged(){
		if (localStorage.getItem("selectedUser") !== undefined){
			document.getElementById("nick").innerHTML ='welcome ' + localStorage.getItem("selectedUser") + ' !';
			console.log('user');
			console.log(localStorage.getItem("selectedUser"));
			
		}

    }
	function getUserLogged(){
		var url = 'https://webchatsoundshare.herokuapp.com?nickname='+localStorage.getItem("selectedUser");
		document.getElementById("chat").data = url;
		return url;
    }
	
    return {
        init: function () {
            id = $("#nDib").val();
            var can = document.getElementById("play");
            can.addEventListener("pointerdown",function (evento) {
                var position = getMousePosition(evento);
                app.publishPoint(youtube-audio);
            })
            //websocket connection
            connectAndSubscribe(addPointToCanvas);
        },

        publishPoint: function(youtubeaudio){
            console.info(id);
            var pt=new Point(youtubeaudio);

            stompClient.send("/topic/newpoint",{},JSON.stringify(youtube-audio));
            //publicar el evento
        },

        disconnect: function () {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        },
		addUser : addUser,
		addSong : addSong,
		setUserLogged : setUserLogged,
		printUserLogged : printUserLogged,
		getUserLogged : getUserLogged,
		createtable : createtable
    };

})();
