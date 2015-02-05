#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'

class Voiture(object):

    def __init__(self):
        self._roues = 4

    @property
    def roues(self):
        print "Récupération du nombre de roues"
        return self._roues

    @roues.setter
    def roues(self,v):
        print "changement du nombre de roues"
        self._roues = v

ma_voiture = Voiture()
ma_voiture.roues = 5

print ma_voiture.roues
print dir(ma_voiture)
print ma_voiture.__dict__