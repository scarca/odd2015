var csv = require('csv')
var fs = require('fs')
var reader = new Object()
reader.parser = csv.parse({delimiter:","})
reader.parser.csv = []


reader.go = function(data, callback){
    reader.parser.on('finish', function(){
        callback(reader.parser.csv)
    })
}

module.exports = reader
