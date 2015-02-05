#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
def clean(int,list) :
    for i in range(int,len(list)):
        if i >= len(list):
            break;
        if list[i] % int == 0:
            list.remove(list[i])
    return list

list = range(2,1001)

for i in list :
    if(i*i > len(list)):
        break;
    list = clean(i,list)

print list

