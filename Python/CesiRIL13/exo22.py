#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import httplib as CURL
conn = CURL.HTTPConnection("localhost",666)

while True :

    mode = ''
    while not (mode == 'e' or mode =='l' or mode =='x') :
        mode = raw_input("Afficher tout les stagiaire (e), afficher un stagiaire (l), sinon taper (x) pour changer de fichier :")

    if mode == 'e' :
       conn.request("GET", "/stagiaires")
       response = conn.getresponse()
       print response.read()
    elif mode == 'l' :
        id_stagiaire = input("Saisir l'id du stagiaire :")
        conn.request("GET", "/stagiaires/"+str(id_stagiaire))
        response = conn.getresponse()
        if response.status == 404:
            print response.reason
        else :
            print response.read()
    elif mode == 'x' :
        break
