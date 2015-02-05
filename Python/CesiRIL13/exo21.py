#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import sqlite3
connexion = sqlite3.connect('test.sqlite3')
curseur = connexion.cursor()
curseur.execute("CREATE TABLE IF NOT EXISTS 'client' (Nom varchar)")
curseur.execute("INSERT INTO 'client' VALUES('martin')")
curseur.execute("INSERT INTO 'client' VALUES('pierre')")
connexion.commit()
curseur.execute("SELECT * FROM 'client'")
valeurs = curseur.fetchall()

for v in valeurs :
    print v[0]