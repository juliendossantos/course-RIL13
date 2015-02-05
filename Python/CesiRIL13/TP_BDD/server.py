#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import install
from flask import Flask
from flask import render_template
from config import connexion, curseur
from models import Client
app = Flask(__name__)

@app.route("/")
def list():
    clients = []
    curseur.execute("SELECT * FROM client")
    results = curseur.fetchall()
    for row in results :
        clients.append(Client(row[0],row[1],row[2],row[3]))
    return render_template('index.html', clients=clients)

@app.route("/<ville>/ca")
def ca(ville):
    curseur.execute("SELECT sum(ca) FROM client WHERE ville = '%s' GROUP BY ville" % (ville))
    valeurs = curseur.fetchall()
    return 'Le CA total de %s est de %f euros' %(ville,valeurs[0][0])

if __name__ == "__main__":
    app.debug = True
    app.run()