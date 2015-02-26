import csv
import json




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

def keyWordMaker(sector):
	#This takes three top sectors and creates three arrays of keywords
	#um no switch statements in python.... so i'll just use multiple if-else
	#Open up json
	jsonfile = open("keywords.json", "r")
	kw = json.load(jsonfile)
	#do whatever here spencer
	#access each
	for key, value in kw.items():
		print(key, value)

keyWordMaker("a")
