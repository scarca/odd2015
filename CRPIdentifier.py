import requests
import csv
import json

from sunlight import congress
from sys import argv

script, sector1, sector2, sector3 = argv

apikey = a07d09d6d82b4d9985b29f79c123aaec
sunlight.config.API_KEY = apikey
keywordspoint = 'https://congress.api.sunlightfoundation.com/'


def billFinder(keyword, party):
	#connects to the sunlight foundation API and finds the bill keywords
	 




def keyWordMaker(sector):
	#This takes three top sectors and creates three arrays of keywords
	#um no switch statements in python.... so i'll just use multiple if-else
	if sector === "Energy":
		file = open(keywords_energy.txt, 'r')
	if sector === "Agribusiness"
		
	

