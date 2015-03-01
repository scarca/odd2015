var csv = require('csv')
var fs = require('fs')
var bl = require('bl')
var reader = new Object()
reader.parser = csv.parse({delimiter:","})
reader.parser.csv = []
reader.parser.on('data', function(data){
    reader.parser.csv.push(data);
})

reader.go = function(file, callback){
    var stream = fs.createReadStream(file)
    reader.parser.on('finish', function(){
        callback(reader.parser.csv)
    })
    stream.pipe(reader.parser)
}

module.exports = reader
