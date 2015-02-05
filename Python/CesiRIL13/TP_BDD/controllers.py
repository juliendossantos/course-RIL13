#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
from config import connexion, curseur
from models import Client

class Controller(object):

    def caTotal(self, villes=[]):

        if(len(villes) == 0) :
            curseur.execute("SELECT ca FROM client")
            valeurs = curseur.fetchall()
            total = 0
            for v in valeurs :
              total = total + v[0]
            return "Le CA total est de %f €" % (total)
        else :
            qry = "SELECT ca FROM client"
            for ville in villes :
                curseur.execute("SELECT sum(ca) FROM client WHERE ville = '%s' GROUP BY ville" % (ville))
                valeurs = curseur.fetchall()
                responses.append('Le CA total de %s est de %f' %(ville,valeurs[0][0]))
            return responses