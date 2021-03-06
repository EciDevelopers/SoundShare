var apiclient = (function () {
    return {
		getUsersToSala : function (name,callback){
            console.log("sip");
			console.log(name);
            jQuery.ajax({
				//url: 'http://localhost:8080/salas/'+name+'/users',
                url: 'https://soundsharearsw.herokuapp.com/salas/'+name+'/users',
                method: 'GET',
                success: function (respuesta) {
                    callback(respuesta);
                }
            });
        },
		getColaCancionesToSala : function (name,callback){
            console.log("lista");
			console.log(name);
            jQuery.ajax({
				//url: 'http://localhost:8080/salas/'+name+'/colacanciones',
                url: 'https://soundsharearsw.herokuapp.com/'+name+'/colacanciones',
                method: 'GET',
				async: false,
                success: function (respuesta) {
                    callback(respuesta);
                }
            });
        },

        getSongsByUser : function (nickname,callback){
            console.log("entrada");
            jQuery.ajax({
                //url: 'http://localhost:8080/users/'+nickname+'/canciones',
                url: 'https://soundsharearsw.herokuapp.com/users/'+nickname+'/canciones',
                method: 'GET',
                success: function (respuesta) {
                    callback(respuesta);
                }
            });
        },

		getSong : function (id,callback){
            console.log("entrada");
            jQuery.ajax({
				//url: 'http://localhost:8080/canciones/'+id,
                url: 'https://soundsharearsw.herokuapp.com/canciones/'+id,
                method: 'GET',
                success: function (respuesta) {
                    callback(respuesta);
                }
            });
        },
		
        getSongs : function (callback){
            console.log("entrada");
            jQuery.ajax({
				//url: 'http://localhost:8080/canciones',
                url: 'https://soundsharearsw.herokuapp.com/canciones',
                method: 'GET',
                success: function (respuesta) {
                    callback(respuesta);
                }
            });
        },
		getSongsBySala : function (nombre,callback){
            console.log("entrada delta");
            console.log(nombre);
			if (nombre !== undefined){
				console.log('xddddddddddddddd')
				//const Url = 'http://localhost:8080/salas/';
				const Url = 'https://soundsharearsw.herokuapp.com/salas/';
				jQuery.ajax({
					url: Url+nombre+'/colacanciones',
					type: "GET",
					success: function (respuesta) {
						console.log(respuesta);
						callback(respuesta);
					}
				});
			}
        },

        getRooms : function(callback){
            console.log("entrada sala");
            jQuery.ajax({
                //url: 'http://localhost:8080/salas',
                url: 'https://soundsharearsw.herokuapp.com/salas',
                method: 'GET',
                success: function (respuesta) {
                    callback(respuesta);
                }
            });
        },

        getUsers : function(callback){
            jQuery.ajax({
                //url: 'http://localhost:8080/users',
                url: 'https://soundsharearsw.herokuapp.com/users',
                method: 'GET',
                success: function (respuesta) {
                     callback(respuesta);
                }
            })
        },

		addUser : function (user){
			//const Url = 'http://localhost:8080/users/create';
			const Url = 'https://soundsharearsw.herokuapp.com/users/create';
			console.log(user);
        	var postRequest=$.ajax({
				url:  Url,
				type: 'POST',
				data: user,
				contentType: "application/json"
			});
			postRequest.then(
				function(){
					alert("successful sign up");
					location.reload(); 
				},
				function(){
					alert("sign up failed");
				}
			);
        },
		addSong : function (song){
			//const Url = 'http://localhost:8080/canciones/create';
			const Url = 'https://soundsharearsw.herokuapp.com/canciones/create';
			console.log(song);
        	var postRequest=$.ajax({
				url:  Url,
				type: 'POST',
				data: song,
				contentType: "application/json"
			});
			postRequest.then(
				function(){
					alert("successful Register Song");
					location.reload(); 
				},
				function(){
					alert("failed  Register Song");
				}
			);
        },
        addRoom: function(room){
            //const Url = 'http://localhost:8080/salas/create';
            const Url = 'https://soundsharearsw.herokuapp.com/salas/create';
            console.log(room);
            var postRequest=$.ajax({
                url:  Url,
                type: 'POST',
                data: room,
                contentType: "application/json"
            });
            postRequest.then(
                function(){
                    alert("successful Register room");
                    location.reload();
                },
                function(){
                    alert("failed  Register room");
                }
            );
        },

		getIdSong: function(nombre,callback) {
			console.log(nombre);
			if (nombre !== undefined){
			console.log('xddddddddddddddd')
			//const Url = 'http://localhost:8080/canciones/getByName/';
			const Url = 'https://soundsharearsw.herokuapp.com/canciones/getByName/';
			jQuery.ajax({
				url: Url+nombre,
				type: "GET",
				async: false,
				success: function (respuesta) {
					console.log(respuesta.id);
					callback(respuesta.id);
				}
			});
          
        }
		},
		getNameSong: function(id,callback) {
			console.log(nombre);
			if (id !== undefined){
			console.log('xddddddddddddddd')
			//const Url = 'http://localhost:8080/canciones/';
			const Url = 'https://soundsharearsw.herokuapp.com/canciones/';
			jQuery.ajax({
				url: Url+id,
				type: "GET",
				async: false,
				success: function (respuesta) {
					console.log(respuesta.nombre);
					callback(respuesta.nombre);
				}
			});
          
        }
		},
		getNameRoom: function(id,callback) {
			console.log(id);
			if (id !== undefined){
			console.log('xddddddddddddddd')
			//const Url = 'http://localhost:8080/salas/getSalaById/';
			const Url = 'https://soundsharearsw.herokuapp.com/salas/getSalaById/';
			jQuery.ajax({
				url: Url+id,
				type: "GET",
				success: function (respuesta) {
					callback(respuesta.nombre);
				}
			});
          
        }
		},
		banUser: function(nombre){
			//const Url = 'http://localhost:8080/users/ban/';
			const Url = 'https://soundsharearsw.herokuapp.com/users/ban/';
			jQuery.ajax({
				url: Url + nombre+'/'+true,
				type: 'GET',
				success: function () {
					alert('User ban');
				}
		
			});
		},
		desbanUser: function(nombre){
			//const Url = 'http://localhost:8080/users/ban/';
			const Url = 'https://soundsharearsw.herokuapp.com/users/ban/';
			jQuery.ajax({
				url: Url + nombre +'/'+ false,
				type: 'GET',
				success: function () {
					alert('Desban User');
				}
		
			});
		}
	};	
})();