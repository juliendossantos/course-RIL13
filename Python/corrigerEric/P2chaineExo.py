# -*- coding: utf-8 -*-
# ! /usr/bin/env python
from __future__ import unicode_literals
def voyelle(car):
 #"teste si le caractère <car> est une voyelle"
    if car in "AEIOUYÀÉÈÊËÎÏÔÛÙaeiouyàéèêëîïôûù":
        return True
    else:
        return False

def compteVoyelles(phrase):
 # "compte les voyelles présentes dans la chaîne de caractères <phrase>"
    n = 0
    for c in phrase:
        if voyelle(c):
            n = n + 1
    return n

texte ='Maître corbeau sur un arbre perché'
nv = compteVoyelles(texte)
print "La phrase <", texte, "> compte ", nv, " voyelles."



