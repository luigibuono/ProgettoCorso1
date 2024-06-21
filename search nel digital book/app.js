//app.js

const express = require("express"),
cors= require('cors');
const apiRoutes = require ('./server/routes/api.routes');

//setup environment
require('dotenv').config();
//database
require('./server/config/db');

const app = express();
app.use(cors());
app.use(express.json());
app.use('/api',apiRoutes);

const port = process.env.PORT


app.listen(port, () => console.log(`app is running on port 3000`));






