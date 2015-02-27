import csv
import json
from sunlight import congress
from sunlight.pagination import PagingService


pag = PagingService(congress)



def keyWordMaker(sector, chamber):
	#This takes three top sectors and creates three arrays of keywords
	#um no switch statements in python.... so i'll just use multiple if-else
	#Open up json
	jsonfile = open("keywords.json", "r")
	kw = json.load(jsonfile)
	#do whatever here spencer
	#access each
	for key, value in kw.items():
		print(key, value)

	enaction = "";
	if chamber.Lower() == "house":
		enactionHistory = "history.house_passage_result"
	elif chamber.Lower() == "senate":
		enactionHistory = "history.senate_passage_result"
	else:
		print "error: the chamber input did not exist"
	bill_list = list(pag.congress.search_bills(keywords=sector, chamber = chamber, enactionHistory = "pass", fields=official_title, summary, bill.id, limit = 1000000))
	print (bill_list)

if __name__ == "__main__":
	global sector, chamber
	sector = ""
	chamber = ""
	def action(verb, arg):
		if verb == "sector" or verb == "s":
			global sector
			sector = arg
			return chamber != ""
		if verb == "chamber" or verb =="c":
			global chamber
			chamber = arg
			return sector != ""

	for arg in sys.argv:
		nextArg = (False, "")
		done = False
		if arg[0] == "-":
			nextArg[0] = True
			nextArg[1] = arg[1:]
		elif nextArg[0]:
			act = arg
			done = action(nextArg[1], arg)
		if done:
			keyWordMaker(sector, chamber)
			break;
