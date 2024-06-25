const express = require('express');
const router = express.Router();
const User = require('../models/user.model');
const HttpStatus = require('http-status-codes');
const bcrypt = require('bcryptjs');

// Ricerca degli utenti per nome (spostato prima delle altre routes)
router.get('/search', (req, res) => {
  const { name } = req.query;
  const query = {};

  if (name) {
    query.name = { $regex: name, $options: 'i' }; // Ricerca case-insensitive
  }

  User.find(query)
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
  User.find()
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
  User.findById(id)
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
  User.create(obj)
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
  User.findByIdAndUpdate(id, { name: obj.name, contact: obj.contact, address: obj.address })
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
  User.findByIdAndDelete(id)
    .then(docs => {
      res.status(HttpStatus.OK).send(docs);
    })
    .catch(err => {
      res.status(HttpStatus.INTERNAL_SERVER_ERROR).send(err);
    });
});

// Registrazione di un nuovo utente
router.post('/register', async (req, res) => {
  const { name, email, password, contact, address } = req.body;

  try {
    // Verifica se l'email esiste già
    let existingUser = await User.findOne({ email });
    if (existingUser) {
      return res.status(HttpStatus.BAD_REQUEST).json({ error: 'Email già registrata.' });
    }

    // Crea un nuovo utente
    const newUser = new User({
      name,
      email,
      password: await bcrypt.hash(password, 10), // Hash della password prima di salvarla nel database
      contact,
      address,
    });

    // Salva il nuovo utente nel database
    await newUser.save();

    // Ritorna il nuovo utente creato
    res.status(HttpStatus.CREATED).json(newUser);
  } catch (err) {
    console.error('Errore durante la registrazione dell\'utente:', err);
    res.status(HttpStatus.INTERNAL_SERVER_ERROR).json({ error: 'Errore durante la registrazione dell\'utente.' });
  }
});

// Login di un utente esistente
router.post('/login', async (req, res) => {
  const { email, password } = req.body;

  try {
    // Trova l'utente tramite l'email
    const user = await User.findOne({ email });
    if (!user) {
      return res.status(HttpStatus.UNAUTHORIZED).json({ error: 'Credenziali non valide.' });
    }

    // Verifica la password
    const isPasswordValid = await bcrypt.compare(password, user.password);
    if (!isPasswordValid) {
      return res.status(HttpStatus.UNAUTHORIZED).json({ error: 'Credenziali non valide.' });
    }

    // Ritorna l'utente che ha effettuato il login
    res.status(HttpStatus.OK).json(user);
  } catch (err) {
    console.error('Errore durante il login dell\'utente:', err);
    res.status(HttpStatus.INTERNAL_SERVER_ERROR).json({ error: 'Errore durante il login dell\'utente.' });
  }
});

module.exports = router;
