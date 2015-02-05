#!/usr/bin/python
# -*- coding: utf-8 -*-
import time

# taille du tableau
taille = 20000
avant = time.clock()
# on remplit le tableau
tableau=range(2,taille+1)

# Affichage du tableau de base
print 'originel ' + str(tableau)

for curseur in tableau : # on parcourt chaque case "survivante" du tableau
    if curseur*curseur>taille:
        break # si le double dépasse la taille on sort
    f=taille/curseur # on calcule le nombre d'itération
    for j in range(2,f+1): # on parcourt les multiples
        if curseur*j in tableau: # si le multiple est présent
            tableau.remove(curseur*j) # on supprime l'élément
print 'final' + str(tableau)
print 'dernier ' + str(tableau[-1])
print 'Time execution (sec) : ',time.clock() - avant
