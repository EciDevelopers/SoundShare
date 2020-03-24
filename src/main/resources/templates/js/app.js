var app = (function () {

    class Acto{
        constructor(youtube-audio){
            this.youtube-audio=youtube-audio;
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

        publishPoint: function(youtube-audio){
            console.info(id);
            var pt=new Point(youtube-audio);

            stompClient.send("/topic/newpoint",{},JSON.stringify(youtube-audio));
            //publicar el evento
        },

        disconnect: function () {
            if (stompClient !== null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }
    };

})();