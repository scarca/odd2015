var express = require('express');
var u = require('url')
var csv = require('csv')
var reader = require('../reader')
var handler = require('../handler')
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Campaign Clarity', Message:'hi' });
});
router.get('/vlink', function(req, res, next){
    var bill = u.parse(req.url, true).query.bill
    handler.startPy([['VoteLinker.py', '-v', bill]], function(code, data){
        if(code == 2){
            arr = [['Error: ', "Bill does not exist"]]
            res.render('vlink')
        }else{
            handler.startJava([['ContributersByIndustry'], data],function(code, output){
                csv.parse(output, {delimiter: ","}, function(err, done){
                    res.render('vlink', {arr: done} )
                })
            })
        }
    })
});
module.exports = router;
