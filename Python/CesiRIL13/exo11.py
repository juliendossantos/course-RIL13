__author__ = 'Dos Santos Julien'
def indexMax(list) :
    keep = 0
    index = 0
    for i in range(0,len(list)) :
        if(list[i] > keep) :
            keep = list[i]
            index = i
    return index

serie = [5,8,2,1,9,3,6,7]

print indexMax(serie)