'use strict';

const express = require('express');

const PORT = 8080;
const HOST = '0.0.0.0';
const url = require('url');
const querystring = require('querystring');

const app = express();
app.get('/', (req, res) => {
  var status = req.query.status;
  if (status != null) {
    if (status == true)
      res.send('PROSAO');
    else if (status == false)
      res.send('NIJE PROSAO');
  } else {
    res.send('NIJE OCENJEN');
  }

});
app.listen(PORT, HOST);
