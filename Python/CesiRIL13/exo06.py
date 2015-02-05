#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'

list = [10,1,3,4,19,9,12,8,7,6,5,13,19,24]
list2 = []
for i in range(0,len(list)) :
    if not(list[i] in list2) :
        list2.append(list[i])

list2.sort()

print list2