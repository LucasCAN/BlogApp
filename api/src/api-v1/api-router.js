import express from 'express';
import postRouter from './paths/posts-router';

const apiRouter = express.Router();

apiRouter.use('/posts', postRouter);
apiRouter.use('/', (req, res, next) => res.send('API V1'));

export default apiRouter;