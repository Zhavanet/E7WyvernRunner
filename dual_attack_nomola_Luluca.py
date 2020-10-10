# -*- coding: utf-8 -*-
"""
Created on Sun Jul 19 13:48:30 2020


"""

import random
import matplotlib.pyplot as plt

def bdefchance(dualcha, landcha):
    sfail=(1-landcha)+(landcha*0.15)
    fail =sfail*((1-dualcha)+dualcha*(sfail))**2
    return 1-fail


""" 
# Luluca EE extra S1
def bdefchanceL(dualcha,landcha):
    sfail = (1-landcha)+(landcha*0.15);
    failEE = sfail * (( 0.65) + 0.35 * sfail);
    fail = failEE *( (1-(0.05+dualcha)) + (0.05+dualcha)*(failEE))**2;
    return 1-fail;
"""    
    
def wyvernT(name,dualcha,landcha):
    counter = 0 #compteur de runs
    bdefcounter = 0 #compteur de bdef
    bdef = False
    bdeftime = 0
    while counter < 100000: #on fait 100 000 tours de jeu
        if counter%4 == 0: #on simule 4 tours de combat pour 1 run et on reset tout chaque 4 tours
            bdef = False
            bdeftime = 0
        if not bdef:
            if random.randint(0,100) < bdefchance(dualcha,landcha)*100 : #test bdef
                bdef = True
                bdeftime = 1
        if bdeftime > 0:
            bdefcounter = bdefcounter + 1
            
        bdeftime = bdeftime -1
        if bdeftime == 0:
            bdef = False
        counter = counter +1
        
    return bdefcounter/1000

def wyvernL(name,dualcha,landcha):
    counter = 0
    bdefcounter = 0
    bdef = False
    bdeftime = 0
    while counter < 1000000:
        if counter%4 == 0:
            bdef = False
            bdeftime = 0
        if random.randint(0,100) < bdefchance(dualcha,landcha)*100:
            bdef = True
            bdeftime = 2
        if bdeftime > 0:
            bdefcounter = bdefcounter + 1
            bdeftime = bdeftime -1
        else :
            bdef = False
        counter = counter +1
        
    return bdefcounter/10000

#"Defense break chance each 'team turn' using {} is : {}{}".format(name,bdefcounter/10000,"%");
"""
print(wyvernT(str("Taranor"),0.2,0.75));
                 
print(wyvernL(str("Luluca"),0.12,0.5));
"""
"""
print(wyvernT(str("Taranor"),0.10,0.75));

print(wyvernL(str("Luluca"),0,0.5));
"""

def div(integer):
    final = integer/100
    return final

L1=(range(0,30))
xaxis= []
for i in range (0,30):
    xaxis.append(div(L1[i]))

yaxis = []
for i in range(0,30):
    yaxis.append(wyvernT(str("Taranor"),xaxis[i],0.75))


yaxis2 = []
for i in range(0,30):
    yaxis2.append(wyvernL(str("Luluca"),xaxis[i],0.65))



plt.plot(xaxis,yaxis,'r',label='Taranor only S1')
plt.plot(xaxis,yaxis2,'b',label='Luluca S1 +15% EE')
plt.xlabel('dual attack chance')
plt.ylabel('bdef % chance')
plt.title('% bdef chance function of dual atk chance')
plt.legend()
plt.show()
