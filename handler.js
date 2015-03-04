var cp = require('child_process')

var handler = new Object()
handler.startPy = function(commands, callback){
    //Command[0] contains program parameters,
    //Command[1] contains input data
    var py = cp.spawn("python", commands[0])
    var ostring = ""
    py.stdout.on('data', function(data){
        ostring += data
    })
    py.stderr.on('data', function(data){
        console.log(data.toString())
    })
    py.on('close', function(code){
        callback(code, ostring)
    })
}

handler.startJava = function(commands, callback){
    /* provide path please*/
    var t = cp.spawn('tee', ['t.txt'])
    var ja = cp.spawn("java", commands[0])
    t.stdin.end(commands[1])
    t.stdout.on('data', function(data){
        ja.stdin.write(data)
    })
    t.on('close', function(code){
        console.log("NO MORE T")
        ja.stdin.end()
    })
    var ostring = ""
    ja.stdout.on('data', function(data){
        ostring += data.toString()
    })
    ja.stderr.on('data', function(data){
        console.log( data.toString())
    })
    ja.on('close', function(code){
        console.log(ostring)
        callback(code, ostring)
    })
}

module.exports = handler
