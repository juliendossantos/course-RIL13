#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
from controllers import Controller
import install

c = Controller()
c.caTotal()
c.caTotal(['Dax'])
c.caTotal(['Dax','Pau','Bordeaux'])