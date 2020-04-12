var apiclient = (function () {
    const Url = 'http://localhost:8080/users/create';
	
    return {
		addUser : function (user){
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
        }
	};	
})();