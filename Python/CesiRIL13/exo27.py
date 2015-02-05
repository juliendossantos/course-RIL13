#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import re

def validEmail(email):
    if(regex.match(email)):
        return True
    else:
        return False

regex = re.compile("[a-zA-Z0-9._-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]{2,}")

adresses = ["pierre@monmail.com", "pierre@monmail.ca","8@monmail.com","@monmail.com","olivier@monmail"]

for adresse in adresses:
    if validEmail(adresse):
        print "L'adresse '" + adresse + "' est valide"
    else :
        print "L'adresse '" + adresse + "' n'est pas valide"
