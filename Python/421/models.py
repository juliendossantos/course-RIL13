#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import random
from Tkinter import *
from tkMessageBox import *

class De(StringVar):

    def __init__(self):
        StringVar.__init__(self)
        self.set(random.randrange(1,6))
        self.keep = IntVar()

    def lancer(self):
        if self.keep.get() == 0:
            self.set(random.randrange(1,6))

class Jeu(Tk):

    def __init__(self):
        Tk.__init__(self)
        self.count = 0

        self.d1, self.d2, self.d3 = De(),De(),De()
        self.btLancer = Button(self, text="Lancer", command=self.lancer)
        self.raz = Button(self,text="RAZ",command=self.raz)
        self.exit = Button(self,text="Quitter",command=self.exit)

        self.c1 =  Checkbutton(text="Keep ?", variable=self.d1.keep,onvalue=True, offvalue=False)
        self.c2 =  Checkbutton(text="Keep ?", variable=self.d2.keep,onvalue=True, offvalue=False)
        self.c3 =  Checkbutton(text="Keep ?", variable=self.d3.keep,onvalue=True, offvalue=False)

        self.label1 = Label(self,textvariable=self.d1)
        self.label12 = Label(self,textvariable=self.d2)
        self.label3 = Label(self,textvariable=self.d3)
        self.label1.pack()
        self.label12.pack()
        self.label3.pack()
        self.btLancer.pack()
        self.raz.pack()
        self.exit.pack()
        self.c1.pack()
        self.c2.pack()
        self.c3.pack()

        self.mainloop()

    def lancer(self):
        self.d1.lancer()
        self.d2.lancer()
        self.d3.lancer()
        self.count += 1
        self.check()

    def raz(self):
        print str(self.count)
        print "raz"
        self.lancer()
        self.count = 0
        self.c1.deselect()
        self.c2.deselect()
        self.c3.deselect()
        print str(self.count)

    def check(self):
        if (int(self.d1.get()) + int(self.d2.get()) + int(self.d3.get())) == 7:
            if(int(self.d1.get()) == 4 or int(self.d2.get()) == 4 or int(self.d3.get()) == 4):
                showinfo('Résultat', 'Vous avez gagné en %d coups' % (self.count))

    def exit(self):
        showwarning('Quitter', 'Etes-vous sur de vouloir quitter ?')
        self.quit()