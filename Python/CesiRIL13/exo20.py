#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import Tkinter

txt = ''
leCesi = open('leCesi.txt','r')
lignes = leCesi.readlines()

for ligne in lignes:
        txt = txt + ligne

leCesi.close()
widget = Tkinter.Label(None)
widget.config(text = txt)
widget.pack(side=Tkinter.BOTTOM)
widget.mainloop()