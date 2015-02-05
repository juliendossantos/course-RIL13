#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'

class Voiture(object):

    def __init__(self,marque = 'Ford',couleur = 'rouge'):
        self._marque = marque
        self._couleur = couleur
        self._conducteur = "personne"
        self._vitesse = 0

    def choix_conducteur(self,nom):
        self._conducteur = nom

    def accelerer(self,taux,duree):
        if self._conducteur == "personne":
            print "Cette voiture n'a pas de conducteur"
        else:
            self._vitesse += taux*duree
        return self._vitesse

    def affiche_tout(self):
        if self._conducteur == "":
            print "Cette voiture n'a pas de conducteur"
        print self._marque,self._couleur,'pilotée par', self._conducteur,', vitesse =',self._vitesse,'m/s'

v1 = Voiture('Peugeot','bleue')
v2 = Voiture(couleur='verte')
v3 = Voiture('Mercedes')
v1.choix_conducteur('Roméo')
v2.choix_conducteur('Juliette')
v2.accelerer(1.8,12)
v3.accelerer(1.9,11)
v2.affiche_tout()
v3.affiche_tout()