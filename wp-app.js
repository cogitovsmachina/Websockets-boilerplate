var Websocket = require('faye-websocket'),
    http	  = require('http'),
    express   = require('express');

var app = express.createServer()

app.get('/', function(req, res){
	res.send('Hello World');
});


app.get('/messages/:message', function(req, res){
	console.log('New message?', req.params.message);
	res.send('Your message is:' +req.params.message);
}) 

app.addListener('upgrade', function(request, socket, head){
    var ws = new Websocket(request, socket, head);
	console.log('new websocket instance');
	
//	setInterval(function () {
//		ws.send('Do you hear me?');
//	}, 1000);
	
	ws.onmessage = function(event, id) {
		console.log("Message:", event.data)
		ws.send(event.data);
	}                       
	
	ws.onclose = function(event) {
		console.log('closed connection:', event.code, event.reason);
		ws = null;
	};
});       

app.listen(8080);
console.log('Server running at 127.0.0.1:8080');