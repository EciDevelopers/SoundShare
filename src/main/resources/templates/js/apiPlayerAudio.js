var player;
var song;
var apiclient = apiclient;
var ctrlq;
  function onYouTubeIframeAPIReady(song) {
	console.log('aquiiiii');
    ctrlq = document.getElementById("youtube-audio");
    ctrlq.innerHTML = '<img id="youtube-icon" src=""/><div id="youtube-player"></div>';
    ctrlq.onclick = toggleAudio;
	var idvideo = apiclient.getIdSong(song,verificar);
	console.log(idvideo);
   
	
  };
  var verificar = function(res) {
    console.log(res);
	player = new YT.Player('youtube-player', {	
      height: '0',
      width: '0',
      videoId: res,
      playerVars: {
        autoplay: ctrlq.dataset.autoplay,
        loop: ctrlq.dataset.loop,
      },
      events: {
        'onReady': onPlayerReady,
        'onStateChange': onPlayerStateChange
      }
    });
	
  };

  function togglePlayButton(play) {
    document.getElementById("youtube-icon").src = play ? "../img/pause.png" : "../img/play2.png";
  }

  function toggleAudio() {

    if ( player.getPlayerState() == 1 || player.getPlayerState() == 3 ) {
      player.pauseVideo();
      togglePlayButton(false);
    } else {
      player.playVideo();
      togglePlayButton(true);
    }
  }

  function onPlayerReady(event) {
    player.setPlaybackQuality("small");
    document.getElementById("youtube-audio").style.display = "block";
    togglePlayButton(player.getPlayerState() !== 5);
  }

  function onPlayerStateChange(event) {
    if (event.data === 0) {
      togglePlayButton(false);
    }
  }
