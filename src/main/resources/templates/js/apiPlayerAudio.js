var player;
var apiclient = apiclient;
var websocket = websocket;
var ctrlq;
var indexpro = 0;
var previousIndex = -1;
var apiyoutube = (function () {
	var playlist = [];
	var temporal = [];
	var actual;
	var timer;

	async function onYouTubeIframeAPIReady(play,pos,time) {
		console.log('timer');
		console.log(time);
		for await(let song of play){
		    apiclient.getIdSong(song,function bucle2(res) {
				console.log('bucle2');
				console.log(res);
				if(!temporal.includes(res)){
					 temporal.push(res);	
								
				}	
			});
		}
		timer = time;
		console.log('aquiiiii');
		console.log(play);
		playlist = play;
		console.log(pos);
		console.log(time);
		actual = play[pos];
		console.log(actual);
		ctrlq = document.getElementById("youtube-audio");
		ctrlq.innerHTML = '<img id="youtube-icon" src=""/><div id="youtube-player"></div>';
		ctrlq.onclick = toggleAudio;
		indexpro = pos;
		verificar();
		setTimeout(function() {
		   console.log('delta 15');	
		   console.log(temporal);
		   playlist = temporal;
		   player.loadPlaylist(temporal,indexpro,timer);
		   console.log(timer);			   
		   ctrlq.click();
		   ctrlq.addEventListener("click", function() { 
					console.log('espero');
					websocket.cambiarEstado();
		   } ); 
		   console.log(playlist);
		   websocket.fijarSong(playlist,indexpro,timer);		
		}, 2000);
		
		
		   
			
	};
	function replaylist(){
		return playlist;

	};
	function transcribir(cancion) {
		apiclient.getIdSong(cancion,addcancion);

	};
	function addcancion(cancion) {
		console.log('alfaomega');
		console.log(playlist);
		console.log(cancion);
		if(!playlist.includes(cancion)){
			playlist.push(cancion);
			console.log(playlist);
			websocket.addSongPlay(playlist);

		}

	};	
	
	var verificar = function() {	
		console.log(temporal);
		player = new YT.Player('youtube-player', {	
		  height: '0',
		  width: '0',
		  playerVars: {
			autoplay: ctrlq.dataset.autoplay,
			loop: ctrlq.dataset.loop,
			 playlist: playlist.join(','),
		  },
		  events: {
			'onReady': onPlayerReady,
			'onStateChange': function(evt) {
					
                    if (evt.data === YT.PlayerState.ENDED ) {
                        //alert(replaylist());
						indexpro++;
						timer=0;
						console.log(indexpro);
						console.log(timer);
						websocket.fijarSong(replaylist(),indexpro,timer);
						
						    
								
						
                    }					
					if( evt.data == -1 || evt.data == 0 ) {
						index = player.getPlaylistIndex();
						console.log(replaylist());
						console.log(player.getPlaylist());
						console.log(player.getPlaylist().length !== replaylist().length);
						if(player.getPlaylist().length != replaylist().length) {
							console.log('aqui voy');
							console.log(indexpro);
							player.loadPlaylist(replaylist(),indexpro,timer);
							console.log(timer);
							

						}	
						actual = replaylist()[indexpro];
						console.log(actual);
						var previousIndex = index;
					}	
                }
					

						
					
            }
		  
		});
		console.log('enrutadorrrrrrr');
	
		

	};
	
	

	function togglePlayButton(play) {
		document.getElementById("youtube-icon").src = play ? "../img/pause.png" : "../img/play2.png";
	};

	function toggleAudio() {
		if ( player.getPlayerState() == 1 || player.getPlayerState() == 3 ) {
		  player.pauseVideo();
		  togglePlayButton(false);
		} else {
		  player.playVideo();
		  togglePlayButton(true);
		}
		
	};
	function play() {
		player.playVideo();
		togglePlayButton(true);
	};
	
	function pause() {
		player.pauseVideo();
		togglePlayButton(false);
	};
	
	

	function onPlayerReady(event) {
		player.setPlaybackQuality("small");
		document.getElementById("youtube-audio").style.display = "block";
		togglePlayButton(player.getPlayerState() !== 5);
	};

	function onPlayerStateChange(event) {
		if (event.data === 0) {
		  togglePlayButton(false);
		}
	};
	function getTime() {
		if(player !== undefined ){
			var res = Math.round(player.getCurrentTime());
			return res;
		}	
	};


	return {
		onYouTubeIframeAPIReady : onYouTubeIframeAPIReady,
		verificar : verificar,
		getTime : getTime,
		play : play,
		pause : pause,
		addcancion : addcancion,
		transcribir : transcribir
		
		
				
	};	  
})();		  