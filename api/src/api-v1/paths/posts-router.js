import express from 'express';
import postModel from '../models/post-model';

const postRouter = express.Router();

postRouter.get('/', listaPosts);
postRouter.post('/', inserePosts);


function listaPosts (req, res, next) {
    postModel.lista({}, (err, lista) => {
        if (!err) {
            res.json(lista);
        } else {
            res.status(400).send(err.message);
        }
    })        
}

function inserePosts (req, res, next) {
    postModel.insere(req.body, (err, objNovo) => { 
        if (!err) {
            res.json(objNovo);
        } else {
            res.status(400).send(err.message);
        }
     }); 
}

export default postRouter;