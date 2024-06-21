const express = require('express');
const router = express.Router();
const user = require('../models/user.model');
const HttpStatus = require('http-status-codes');

// Ricerca degli utenti per nome (spostato prima delle altre routes)
router.get('/search', (req, res) => {
  const { name } = req.query;
  const query = {};

  if (name) {
    query.name = { $regex: name, $options: 'i' }; // Ricerca case-insensitive
  }

  user.find(query)
    .then(docs => {
      res.send(docs);
    })
    .catch(err => {
      console.error('Errore durante la ricerca degli utenti:', err);
      res.status(HttpStatus.INTERNAL_SERVER_ERROR).json({ error: 'Errore durante la ricerca degli utenti.' });
    });
});

// Visualizza tutti gli users creati
router.get('/', (req, res) => {
  user.find()
    .then(docs => {
      res.send(docs);
    })
    .catch(err => {
      res.status(HttpStatus.INTERNAL_SERVER_ERROR).send(err);
    });
});

// Ottieni utenti grazie ad un id
router.get('/:id', (req, res) => {
  let id = req.params.id;
  user.findById(id)
    .then(docs => {
      res.send(docs);
    })
    .catch(err => {
      res.status(HttpStatus.INTERNAL_SERVER_ERROR).send(err);
    });
});

// Crea nuovi utenti
router.post('/', (req, res) => {
  const obj = req.body;
  user.create(obj)
    .then(doc => {
      res.status(201).send(doc); // 201 Created
    })
    .catch(err => {
      console.error('Errore durante la creazione dell\'utente:', err);
      res.status(500).send(err); // 500 Internal Server Error
    });
});

// Metodo per la modifica
router.put('/:id', (req, res) => {
  let id = req.params.id;
  const obj = req.body;
  user.findByIdAndUpdate(id, { name: obj.name, contact: obj.contact, address: obj.address })
    .then(doc => {
      res.status(HttpStatus.OK).send(doc);
    })
    .catch(err => {
      res.status(HttpStatus.INTERNAL_SERVER_ERROR).send(err);
    });
});

// Elimina utenti
router.delete('/:id', (req, res) => {
  let id = req.params.id;
  user.findByIdAndDelete(id)
    .then(docs => {
      res.status(HttpStatus.OK).send(docs);
    })
    .catch(err => {
      res.status(HttpStatus.INTERNAL_SERVER_ERROR).send(err);
    });
});

module.exports = router;


module.exports = router;
