module.exports = function(server){
    var io = require('socket.io')(server);
    var handler = require('./handler')
    var csv = require('csv')
    io.on('error', function(err){
        console.log(err);
    });

    io.on('connection', function(socket){
        var emitStatus = function(data){
            socket.emit("update", {status: data});
        }
        console.log("connected!!!");
        socket.on('query', function(data){
            console.log("query:", data['q']);
            var d = data['q']
            handler.startPy([[root + "/VoteLinker.py", "-v", d ]],emitStatus, function(code, data){
                if(code == 2){
                    var datum = {err: "Bill Does Not Exist"}
                    socket.emit("err", datum)
                } else {
                    handler.startJava([['-classpath', root, 'ContributersByIndustry'], data],emitStatus, function(code, out){
                        csv.parse(out, {delimiter: ","}, function(err, done){
                            console.log(done);
                            socket.emit("done", {arr: done})
                        })
                    })
                }
            });
        })
    })
}
