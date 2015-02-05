#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import requests

r = requests.get('http://cesi.kolapsis.com/cesi_alternance/api/v1/training',headers = {'X-CESI-App-Auth':'54b783b5eebb9'})
print r.json()