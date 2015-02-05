#!/usr/bin/env python
# -*- coding: utf-8 -*-
from __future__ import unicode_literals
__author__ = 'Dos Santos Julien'

def compteVoyelle(ch):
    count = 0
    voyelles = "aeiouyAEIOUYâäàéèêëïîôöüû"
    for voyelle in voyelles :
#        print voyelle + "\n"
#        print ch.count(voyelle)
        count += ch.count(voyelle)
    return count

text = "Maître corbeau sur un arbre perché"

print compteVoyelle(text)