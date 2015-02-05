#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
def fileRead(name) :
    txt = ''
    file = open(name+'.txt','r')
    for ligne in file:
        txt = txt + ligne
    file.close()
    print txt
    return txt

def fileWrite(name) :
    file = open(name+'.txt','w')
    while True :
        line = raw_input('tapez votre texte : ')
        if line == '' :
            break
        file.write(line + "\n")
    file.close()

while True :
    fileName = ''
    while fileName == '' :
        fileName = raw_input("Nom de votre fichier (ou x pour quitter le programme) :")

    if fileName == 'x' :
        break

    while True :

        mode = ''
        while not (mode == 'e' or mode =='l' or mode =='x') :
            mode = raw_input("Si vous souhaitez écrire taper (e), si vous souhaitez lire le fichier taper (l), sinon taper (x) pour changer de fichier :")

        if mode == 'e' :
            fileWrite(fileName)
        elif mode == 'l' :
            fileRead(fileName)
        elif mode == 'x' :
            break
