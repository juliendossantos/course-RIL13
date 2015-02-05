#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import threading
import time

def MyTimer(tempo = 5.0):

    ## initialisation du timer
    threading.Timer(tempo, MyTimer, [tempo]).start()
    ## affichage de l'heure
    print time.strftime('%d/%m/%y %H:%M:%S',time.localtime())

MyTimer()