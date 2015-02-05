#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import datetime
log = open('log.txt','w')

date = datetime.datetime.now()

log.write('<' + date.strftime("%Y/%m/%d %H:%M") + '> New log entry')
log.close()

log = open('log.txt','r')
logs = log.readlines()

for logLine in logs :
    print logLine
log.close()

