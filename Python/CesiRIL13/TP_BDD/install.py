#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
from config import connexion, curseur

curseur.execute("DROP TABLE client")
curseur.execute("CREATE TABLE 'client' (nom text, prenom text, ville text, ca float, objectif float)")
curseur.execute("INSERT INTO 'client' VALUES ('Martin','Pierre','Dax','15000',NULL)")
curseur.execute("INSERT INTO 'client' VALUES ('Dupin','Luc','Dax','20000',NULL)")
curseur.execute("INSERT INTO 'client' VALUES ('Dubois','Jean','Pau','25000',NULL)")
curseur.execute("INSERT INTO 'client' VALUES ('Dupont','Pierre','Bordeaux','12000',NULL);")

connexion.commit()