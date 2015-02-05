__author__ = 'Dos Santos Julien'
import os, time
from stat import * # ST_SIZE etc

stat = os.stat('log.txt')

print stat[ST_SIZE]
print time.ctime(stat[ST_MTIME])
