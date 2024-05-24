import db from "../utils/db";

const moment = require('moment');

function insere (obj, callback) {
    db.postsDB.insert(obj, callback);
}

function lista(params, callback) {
    const sortField = params.sortField || 'data'; 
    const sortOrder = params.sortOrder || -1; // -1 para ordenar da mais recente para a mais antiga

    db.postsDB.find({}).exec((err, posts) => {
        if (err) {
            return callback(err);
        }

        // Conversão de data para formato que permita ordenação
        posts.forEach(post => {
            post.sortableDate = moment(post.data, 'DD/MM/YYYY HH:mm').toDate();
        });

        // Ordenação da lista de posts pela data convertida (mais recente para mais antiga)
        posts.sort((a, b) => {
            if (a.sortableDate < b.sortableDate) return sortOrder === 1 ? -1 : 1;
            if (a.sortableDate > b.sortableDate) return sortOrder === 1 ? 1 : -1;
            return 0;
        });

        callback(null, posts);
    });
}

export default {
    insere, 
    lista
}