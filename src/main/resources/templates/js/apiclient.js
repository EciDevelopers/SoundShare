var apiclient = (function () {
     var getSong = (function (callback) {
            axios({
                method: 'GET',
                url: '/canciones',

            })
                .then(response => callback(response))
                .catch(error => console.log(error));
     });
    return {
        getSong:getSong,
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

		getIdSong: function(nombre,callback) {
			console.log(nombre);
			if (nombre !== undefined){
			console.log('xddddddddddddddd')
			//const Url = 'http://localhost:8080/canciones/getByName/';
			const Url = 'https://soundsharearsw.herokuapp.com/canciones/getByName/';
			jQuery.ajax({
				url: Url+nombre,
				type: "GET",
				success: function (respuesta) {
					console.log(respuesta.id);
					callback(respuesta.id);
				}
			});
          
        }
		}
	};	
})();