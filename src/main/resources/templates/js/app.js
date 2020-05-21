var apiclient = apiclient;
var websocket = websocket;
var app = (function () {
	var orden = [];
    class Acto{
        constructor(youtubeaudio){
            this.youtubeaudio=youtube-audio;
        }
    }


    var id;

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
	var addRoom = function(id,nombre,genero,tipo) {
    		var newRoom = {
    			id : id,
    			nombre : nombre,
    			genero : genero,
    			tipo : tipo,
    		};
    		apiclient.addRoom(JSON.stringify(newRoom));
    };

	function createtable(canciones){
        apiclient.getSongsBySala(canciones,showSongs);
    }

    function createtableroom(){
        apiclient.getRooms(showRooms);
    }

    function createtableusers(){
        apiclient.getUsers(showUsers);
    }

    function createtablesongsuser(){
        console.log(localStorage.getItem("selectedUser"));
        apiclient.getSongsByUser(localStorage.getItem("selectedUser"),showSongsByUser);
    }

    function showSongsByUser(canciones){
        canciones.map(function(canciones) {	
              $("#tablasCancionesUser").append(
                  '<tr class="table-success"> <td>'+
                  canciones.id +
                  '</td> <td>' +
                  canciones.nombre +
                  '</td> <td>' +
                  canciones.genero +
                  '</td> <td>' +
                  canciones.author +
                  "</td> <td> <form><button type='button' class='btn btn-primary' style='width:50%;background-color: #17202A; border: 0'  onclick='apiyoutube.onYouTubeIframeAPIReady(\""+ canciones.nombre +"\",0,0)'><img  src='../img/play2.png' style='width:50%' alt='x'/></button></form></td>"
              );
        });
    }
	
    function showUsers(users){
        users.map(function(users){
            $("#tablasUsers").append(
                '<tr class="table-success"> <td>'+
                users.id +
                '</td> <td>' +
                users.nombre +
                '</td> <td>' +
                users.nickname +
                "</td> <td> <form><button type='button' class='btn btn-primary' style='width:50%;background-color: #17202A; border: 0' onclick='apiclient.banUser(\""+ users.nickname+"\")'><img  src='../img/eliminar.png' style='width:50%' alt='x'/></button></form></td>"
            );

        });
		
    }

	

    function showSongs(canciones){
		console.log('gamaaaaaaaaaaaaaaaaaaaa');
		var $table = $("#tablas").find('tr').find('td:eq(0)');
		 jQuery.each($table, function(i, item) {
          orden.push($(this).text());
		  console.log($(this).text());
       });
        canciones.map(function(canciones) {
		if(!orden.includes(canciones.id)){	
		$("#tablas").append(
		  '<tr class="table-success"> <td>'+
		  canciones.id +
		  '</td> <td>' +
		  canciones.nombre +
		  '</td> <td>' +
		  canciones.genero +
		  '</td> <td>' +
		  canciones.author +
		  "</td> <td> <form><button type='button' class='btn btn-primary' style='width:50%;background-color: #17202A; border: 0'  onclick='websocket.addSongByUser(\""+ canciones.nombre+"\")'><img  src='../img/agregar.png' style='width:50%' alt='x'/></button></form></td>"
		);
			  
        }});
    }

    function showRooms(salas){
        salas.map(function(salas){
            $("#tablasrooms").append(
              '<tr class="table-success"> <td>'+
              salas.id +
              '</td> <td>' +
              salas.nombre +
              '</td> <td>' +
              salas.genero +
              '</td> <td>' +
              salas.tipo +
              "</td> <td> <form><a href='sala.html?id="+salas.id+"' ><button type='button' class='btn btn-primary' style='width:50%;background-color: #17202A;border: 0' ><img  src='../img/sala.png' style='width:50%' alt='x'/></button></a></form></td>"

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
	function getUserLogged(room){
		console.log(room);
		var url = 'https://webchatsoundshare.herokuapp.com/'+room+'?nickname='+localStorage.getItem("selectedUser");
		console.log(url);
		document.getElementById("chat").data = url;
		return url;
    }
	function getNameRoom(){
		apiclient.getNameRoom(window.location.search.substring(1).substring(3),loadNameRoom);
		console.log(window.location.search.substring(1).substring(3));
		websocket.connectAndSubscribeUser(window.location.search.substring(1).substring(3),localStorage.getItem("selectedUser"));
    }
	function printUserstoSala(users){
		console.log(users);
    }
	
	function loadNameRoom(nombre){
		createtable(nombre);
		localStorage.setItem("nameRoom",nombre);
		namesala = nombre;
		apiclient.getUsersToSala(nombre,printUserstoSala);
		console.log(nombre);
		nomesala=nombre;
		document.getElementById("nombre").innerHTML = 'welcome room '+nombre+' !';
		getUserLogged(nombre);
    }
    return {
      
		addUser : addUser,
		addSong : addSong,
		addRoom : addRoom,
		setUserLogged : setUserLogged,
		printUserLogged : printUserLogged,
		getUserLogged : getUserLogged,
		createtable : createtable,
		createtableroom : createtableroom,
		createtableusers : createtableusers,
		createtablesongsuser : createtablesongsuser,
		getNameRoom : getNameRoom,
		printUserstoSala :  printUserstoSala
    };

})();
