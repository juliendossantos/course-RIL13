# -*- coding: utf-8 -*-
# ! /usr/bin/env python

class Voiture(object):

    def __init__(self):
        self._roues=4

    @property
    def roues(self):
        print "Récupération du nombre de roues"
        return self._roues

    @roues.setter
    def roues(self, v):
        print "Changement du nombre de roues"
        self._roues  =  v

ma_voiture=Voiture()
ma_voiture.roues=5
print  ma_voiture.roues

