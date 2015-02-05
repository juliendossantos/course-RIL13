#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'

class CompteBancaire:

    def __init__(self,nom = "Dupont",solde = 1000):
        self.nom = nom
        self.solde = solde

    def depot(self,somme):
        self.solde = self.solde + somme

    def retrait(self,somme):
        self.solde = self.solde - somme

    def affiche(self):
        #"\" couper la ligne en deux
        print("Le solde du compte bancaire de {0} est de {1} euros.".\
              format(self.nom,str(self.solde)))

    def virementEnSuisse(self):
        self.solde = self.solde * 1.26

# compte1 = CompteBancaire('Duchmo1', 800)
# compte1.depot(350)
# compte1.retrait(200)
# compte1.affiche()
# compte1.virementEnSuisse()
# compte1.affiche()
#
# compte2 = CompteBancaire()
# compte2.depot(25)
# compte2.affiche()