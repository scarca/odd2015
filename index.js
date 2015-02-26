var children = require('child_process')
var http = require('http')

var handle = function(request, response){

}

var serv = http.createServer(handle)
serv.listen(10000)
