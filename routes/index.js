var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Campaign Clarity', Message:'hi' });
});
router.get('/blob', function(req, res, next){
    res.write('omg')
})

module.exports = router;
