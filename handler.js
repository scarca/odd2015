var cp = require('child_process')

var handler = new Object()
handler.startPy = function(commands, callback){
    /*Provide the path to the file please*/
    var py = cp.spawn("python",commands)
    py.stdout.on('data', function(data){
        console.log("Output: ", data.toString())
    })
    py.stderr.on('data', function(data){
        console.log("ERROR:", data.toString())
    })
    py.on('close', function(code){
        callback(code)
    })
}

handler.startJava = function(commands, callback){
    /* provide path please*/
    var ja = cp.spawn("java", commands)
    ja.stdout.on('data', function(data){
        //Don't need to do anything here.
    })
    ja.stderr.on('data', function(data){
        console.log("ERROR: ", data.toString())
    })
    ja.on('close', function(code){
        callback(code)
    })
}

module.exports = handler
