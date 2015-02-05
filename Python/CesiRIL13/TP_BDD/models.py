#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
from config import connexion, curseur
class Client(object) :
    def __init__(self, nom = '', prenom='', ville='', ca = 0, objectif = 0) :
        self.nom = nom
        self.prenom = prenom
        self.ville = ville
        self.ca = ca
        self.objectif = objectif

    def save(self) :
        try :
            curseur.execute("INSERT INTO 'client' VALUES("
                                + self.nom + ", "
                                + self.prenom + ", "
                                +self.ville+ ","
                                +self.ca+ ","
                                +self.objectif+")")
            connexion.commit()
        except:
            return False
        return True

    def caclculeObjectif(self):
        objectif = self.ca*1.1
