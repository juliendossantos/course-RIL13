#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'


def switchDictionnare(dict):
    dictSwitch = {}
    for key,value in dict.items():
        dictSwitch[value] = key
    return dictSwitch

dictionnaire = {'Screen':'Ecran','Computer':'Ordinateur','Mouse':'Souris','Hard disk':'Disque dur','Keyboard':'Clavier'}

print switchDictionnare(dictionnaire)
print '-'*100

txt = 'Mouse'
print 'La traduction de', txt,' est ', dictionnaire[txt]