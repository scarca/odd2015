var express = require('express');
var reader = require('../reader')
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Campaign Clarity', Message:'hi' });
});
router.get('/vlink', function(req, res, next){
    console.log(__dirname)
    reader.go('/Users/eagle/Documents/Projects/odd2015/src/CongresspeopleContribs.csv', function(data){
        res.render('vlink', {arr: data} )
    })
});
module.exports = router;
