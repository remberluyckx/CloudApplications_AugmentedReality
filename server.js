var DSServer = require("deepstream.io");
var express = require("expres");

// Set up Deepstream.io
var deepserver = new DSServer();
deepserver.set("host", "vpn.4de.win");
deepserver.set("port", 46020);

deepserver.start();

// Set up express
var app = express();
app.use(express.static('static'));
app.listen(80, function() {
    console.log("Express : Listening on port 80.");
});