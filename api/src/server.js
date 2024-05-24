import http from 'http';
import express from 'express';
import bodyParser from 'body-parser';
import apiRouter from './api-v1/api-router';

const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

app.use('/api/v1', apiRouter);

const porta = 5500
http.createServer (app).listen(porta, () => 
    console.log(`Servidor pronto na porta ${porta}`));