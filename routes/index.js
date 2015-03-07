var express = require('express');
var https = require('https');
var u = require('url')
var csv = require('csv')
var handler = require('../bin/handler')
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Campaign Clarity',page:'Home' });
});
router.get('/bill_id_search', function(req, res, next){
    var bill = u.parse(req.url, true).query.bill
    handler.startPy([['VoteLinker.py', '-v', bill]],console.log, function(code, data){
        if(code == 2){
            arr = [['Error: ', "Bill does not exist"]]
            res.render('vlink')
        }else{
            handler.startJava([['ContributersByIndustry'], data],console.log, function(code, output){
                csv.parse(output, {delimiter: ","}, function(err, done){
                    res.render('vlink', {page: "ID Search", arr: done} )
                })
            })
        }
    })
});
var querySearch = function(kw, callback){
    //Encode for the web!
    kw.replace(" ", "%20")
    //Submit Request
    https.get('https://congress.api.sunlightfoundation.com/bills/search?query='+kw+'&apikey=a07d09d6d82b4d9985b29f79c123aaec&fields=bill_id&house_passage_result=pass', function(res){
        res.on('data', function(d){
            var j = JSON.parse(d);
            if (j['count'] === '0'){
                callback(1, null)
            } else {
                callback(null, j['results'][0]['bill_id']);
            }
        });
    })
}
router.get('/bill_kw_search', function(req, res, next){
    var kw = u.parse(req.url, true).query.keywords
    console.log("searching with kw: ", kw)
    //Do Sunlight API Query
    querySearch(kw, function(err, billID){
        if(err){
            res.send('No Results Found!')
        } else {
            res.redirect(200, '/bill_id_search?bill=' + billID)
        }
    });

})
router.get('/search', function(req, res, next){
    res.render("search", {page: "Bill Search"})
})
module.exports = router;
