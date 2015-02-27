import csv
import json
from sunlight import congress
from sunlight.pagination import PagingService


pag = PagingService(congress)



def keyWordMaker():
	#This takes three top sectors and creates three arrays of keywords
	#um no switch statements in python.... so i'll just use multiple if-else
	#Open up json
	jsonfile = open("keywords.json", "r")
	kw = json.load(jsonfile)
	#do whatever here spencer
	#access each
	for key, value in kw.items():
		print(key, value)
	sector = prompt("Please choose a sector that corresponds with the keywords that you want: ")
	
	chamber = prompt("please choose the chamber that the bill originated from: ")
	
	enaction = "";
	if chamber.Lower() == "house":
		enactionHistory = "history.house_passage_result"
	elif chamber.Lower() == "senate":
		enactionHistory = "history.senate_passage_result"
	else: 
		print "error: the chamber input did not exist"
	bill_list = list(pag.congress.search_bills(keywords=sector, chamber = chamber, enactionHistory = "pass", fields=official_title, summary, bill.id, limit = 1000000))
	print (bill_list)
	

