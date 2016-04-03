module.exports = function(server){
    var io = require('socket.io')(server);
    var handler = require('./handler')
    var csv = require('csv')
    var https = require('https')
	var bl = require('bl');
    io.on('error', function(err){
        console.log(err);
    });

    io.on('connection', function(socket){
        var emitStatus = function(data){
		    console.log(data)
            socket.emit("update", {status: data});
        }
        var handle = function(bill){
			bill = bill.trim(); 
			bill = bill.replace(" ", ""); 
			bill = bill.replace("\n", ""); 
			bill = bill.replace("\t", ""); 
			bill = bill.replace("\r", ""); 
			//VERY VERY DANGEROUS. FIND A WAY TO SANITIZE INPUT TODO
            handler.startPy([[root + "/VoteLinker.py", "-v", bill]],emitStatus, function(code, data){
			console.log("Started Python")
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
			//Find the short title for user convinience 
			//
			//
		    https.get('https://congress.api.sunlightfoundation.com/bills/search?bill_id='+d+'&apikey=a07d09d6d82b4d9985b29f79c123aaec&fields=short_title', function(res){
			res.pipe(bl(function(err, data){ 
				var j = JSON.parse(data);
				var datum = null; 
				try{ 
					datum = j['results'][0]['short_title']; 
				} catch(err) { 
					datum = "Something went wrong. Make sure there is NO whitespace in your query (including no trailing space)" ; 
					console.log(data); 
				} 
				var msg = {'short_title': datum}; 
				socket.emit('init', msg); 
			}))
        })
		})
        socket.on('search', function(kw){
            console.log("KW:",kw['s'])
            kw['s'].replace(" ", "%20");
            https.get('https://congress.api.sunlightfoundation.com/bills/search?query='+kw['s']+'&apikey=a07d09d6d82b4d9985b29f79c123aaec&fields=bill_id,last_vote_at', function(res){
				res.pipe(bl(function(err, data){
              //  res.on('data', function(data){
					console.log("Received data:", data); 
                    var j = JSON.parse(data)
					console.log("[DEBUG] FINISHED PARSE"); 
                    if(j['count'] == 0){
						console.log("No Bill Found!");
                        var datum = {'err': "No Bill Found!"}
                        socket.emit('err', datum)
                    }
                    else{
						console.log("Found bill: ", j['results'])
						if(j['results'][0]['last_vote_at'] == null){ 
							var msg = "Bill " + j['results'][0]['bill_id'] + " never voted on. You might be interested in " + j['results'][1]['bill_id'] + "." ; 
							console.log(msg); 
							var datum = {'err': msg}; 
							socket.emit('err', datum); 
						}
						else{
                        	handle(j['results'][0]['bill_id'])
						}
                    }
                }))
            })
        })
    })
}
