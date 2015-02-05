#!/usr/bin/env python
#-*- coding:utf-8-*-
from Tkinter import *
from tkMessageBox import *
from random import randint
import time

# Do not change. You may experience problems with the design file. #
MainWindow=Tk()
MainWindow.title("Jeu 421")
MainWindow.resizable(width=FALSE, height=FALSE)
MainWindow.geometry("200x207+228+120")
MainWindow.configure(bg="cyan")


lance = 0
# Do not change. You may experience problems with the design file. #
v1 = StringVar()
v2 = StringVar()
v3 = StringVar()
v4 = StringVar()
v1.set('Dé1')
v2.set('Dé2')
v3.set('Dé3')
v4.set(time.strftime('%d/%m/%Y',time.localtime()))
Label1=Label(MainWindow,text="De1", textvariable=v1, bg="green")
Label1.place(relx=0.0797, rely=0.0507, relwidth=0.2174, relheight=0.1812)
Label2=Label(text="De2", textvariable=v2, bg="green")
Label2.place(relx=0.3797, rely=0.0507, relwidth=0.2174, relheight=0.1812)
Label3=Label(text="De3", textvariable=v3, bg="green")
Label3.place(relx=0.6797, rely=0.0507, relwidth=0.2174, relheight=0.1812)

Label4=Label(text="", textvariable=v4, bg="green")
Label4.place(relx=0.0597, rely=0.8507, relwidth=0.8174, relheight=0.1012)

def Checkbutton1Click():
	pass
var1 = IntVar()
Checkbutton1=Checkbutton(text="?", command=Checkbutton1Click, variable = var1, bg="cyan")
Checkbutton1.place(relx=0.0397, rely=0.2794, relwidth=0.3309, relheight=0.0551)

def Checkbutton2Click():
	pass
var2 = IntVar()
Checkbutton2=Checkbutton(text="?", command=Checkbutton2Click, variable = var2, bg="cyan")
Checkbutton2.place(relx=0.3397, rely=0.2794, relwidth=0.3309, relheight=0.0551)

def Checkbutton3Click():
	pass
var3 = IntVar()
Checkbutton3=Checkbutton(text="?", command=Checkbutton3Click, variable = var3, bg="cyan")
Checkbutton3.place(relx=0.6397, rely=0.2794, relwidth=0.3309, relheight=0.0551)

def LancerClick():
    global lance
    lance = lance + 1
    if var1.get() == 0:
        v1.set(str(randint(1,6)))
    if var2.get() == 0:
        v2.set(str(randint(1,6)))
    if var3.get() == 0:
        v3.set(str(randint(1,6)))
    if (int(v1.get()) + int(v2.get()) + int(v3.get()))==7 and (v1.get()== '4' or v2.get()== '4' or v3.get()== '4'):
        showinfo("Gagné", "bravo, gagné en " + str(lance)+ " coups")
        lance = 0

# Do not change. You may experience problems with the design file. #
Button1=Button(text="Lancer", command=LancerClick)
Button1.place(relx=0.0562, rely=0.5993, relwidth=0.2904, relheight=0.136)

def RAZClick():
    v1.set('Dé1')
    v2.set('Dé2')
    v3.set('Dé3')
    var1.set(0)
    var2.set(0)
    var3.set(0)
    showinfo("Visual Tkinter", "RAZ effectué avec succès")

# Do not change. You may experience problems with the design file. #
Button2=Button(text="RAZ", command=RAZClick)
Button2.place(relx=0.3897, rely=0.5993, relwidth=0.2904, relheight=0.136)

def QuitterClick():
    if askyesno('421', 'Êtes-vous sûr de vouloir quitter?'):
        showwarning('421', 'Tant pis...')
        MainWindow.quit()
    else:
        showinfo('421', 'Bien, bien!, jouons encore...')

# Do not change. You may experience problems with the design file. #
Button3=Button(text="Quitter", command=QuitterClick, bg="red")
Button3.place(relx=0.6875, rely=0.5993, relwidth=0.2904, relheight=0.136)

MainWindow.mainloop()
