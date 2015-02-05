#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
#!/usr/bin/env python
# -*- coding: utf-8 -*-
__author__ = 'Dos Santos Julien'
import time

def clean(int,list) :
    for i in range(int,len(list)):
        if i >= len(list):
            break;
        if list[i] % int == 0:
            list.remove(list[i])
    return list

list = range(2,1000001)
#print list
result = []
avant = time.clock()

for i in list :
    if(i*i > len(list)):
        break;
    result = clean(i,list)


#print list
print time.clock() - avant

