#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
txt = ''
leCesi = open('leCesi.txt','r')
lignes = leCesi.readlines()

for ligne in lignes:
        txt = txt + ligne

leCesi.close()
print txt