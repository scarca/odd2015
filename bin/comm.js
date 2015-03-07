module.exports = function(server){
    var io = require('socket.io')(server);
    var handler = require('./handler')
    var csv = require('csv')
    var https = require('https')
    io.on('error', function(err){
        console.log(err);
    });

    io.on('connection', function(socket){
        var emitStatus = function(data){
            socket.emit("update", {status: data});
        }
        var handle = function(bill){
            handler.startPy([[root + "/VoteLinker.py", "-v", bill]],emitStatus, function(code, data){
                if(code == 2){
                    var datum = {err: "Bill Does Not Exist"}
                    socket.emit("err", datum)
                } else {
                    handler.startJava([['-classpath', root, 'ContributersByIndustry'], data],emitStatus, function(code, out){
                        csv.parse(out, {delimiter: ","}, function(err, done){
                            socket.emit("done", {arr: done})
                        })
                    })
                }
            });
        }
        console.log("connected!!!");
        socket.on('query', function(data){
            console.log("query:", data['q']);
            var d = data['q']
            handle(d);
        })

        socket.on('search', function(kw){
            console.log("KW:",kw['s'])
            kw['s'].replace(" ", "%20");
            https.get('https://congress.api.sunlightfoundation.com/bills/search?query='+kw['s']+'&apikey=a07d09d6d82b4d9985b29f79c123aaec&fields=bill_id&house_passage_result=pass', function(res){
                res.on('data', function(data){
                    var j = JSON.parse(data)
                    if(j['count'] == 0){
                        var datum = {'err': "No Bill Found!"}
                        socket.emit('err', datum)
                    }
                    else{
                        handle(j['results'][0]['bill_id'])
                    }
                })
            })
        })
    })
}
