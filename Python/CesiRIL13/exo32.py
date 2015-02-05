#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
from exo24 import CompteBancaire
import math

class CompteEpargne(CompteBancaire):

    def __init__(self,nom,solde):
        CompteBancaire.__init__(self,nom,solde)
        self._taux = .3

    def changerTaux(self,taux):
        self._taux = taux

    def capitalisation(self,nbrMois):
        print "Capitalisation sur {} mois au taux mensuelle de {}".format(nbrMois,self._taux)
        for i in range(0,nbrMois):
            self.solde += self.solde*(self._taux/100)
        self.solde = round(self.solde,2)

c1 = CompteEpargne('Duvidier',600)
c1.depot(350)
c1.affiche()
c1.capitalisation(12)
c1.affiche()
c1.changerTaux(.5)
c1.capitalisation(12)
c1.affiche()