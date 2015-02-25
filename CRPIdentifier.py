import requests
import csv
import json

from sunlight import congress
from sys import argv

script, sector1, sector2, sector3 = argv

apikey = a07d09d6d82b4d9985b29f79c123aaec
sunlight.config.API_KEY = apikey
keywordspoint = 'https://congress.api.sunlightfoundation.com/'



'''
okay, this is pseudo code before I write actually my code. 
Goal: 
1st, find the sectors that x candidate received most money from.
2nd, find the bills that closely relate to those sectors.



functions:

a function that will take the three most sectors, and correlate them with a 'list' of keywords from that sector
i.e.
Energy
Construction
Environment

*** energy = [keyword1, keyword2, keyword3]
*** construction = [keyword1, keyword2, keyword3]
*** environment = [keyword1, keyword2, keyword2]

Then, go to the online API, and look for bills that match those keywords.
This function will also allow for parameters of last added, and _____ (add this in the morning)
also, reminder to myself to add raj's code in here in the morning.




'''







def billFinder(keyword, party):
	#connects to the sunlight foundation API and finds the bill keywords
	 




def keyWordMaker(sector):
	#This takes three top sectors and creates three arrays of keywords
	#um no switch statements in python.... so i'll just use multiple if-else
	if sector === "Energy":
		file = open(keywords_energy.txt, 'r')
	if sector === "Agribusiness"
		
	

