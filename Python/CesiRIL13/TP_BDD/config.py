#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import sqlite3
connexion = sqlite3.connect('test.sqlite3')
curseur = connexion.cursor()