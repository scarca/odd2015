from os.path import isfile
from hashlib import md5
from sunlight import congress
from sunlight.pagination import PagingService
import json
#File to communicate with other progams... the output file
class VoteLinker:
	def checkMD5(fd):
		'''verify MD5 sum of file described by file descriptor fd

		Arguments:
		fd: the file descriptor
		Return value: (MD5Sum, Boolean), with the boolean representing whether or not the file matched'''

		print("Checking MD5SUM", file=sys.stderr)
		realMD5 = open('checksum.md5', 'r').readline()[:-1]
		checksum = md5()
		#generating CheckSum
		with open(fd, 'rb') as f:
			for p in iter(lambda : f.read(128), b''):
				checksum.update(p)
		checked = checksum.hexdigest()
		return (checked, realMD5 == checked)
	def createLeg():
		'''Recreate the list of legislatures in case something goes wrong, or the checksum doesn't match'''
		print("Recreating Ledger", file=sys.stderr)
		pag = PagingService(congress)
		legislators = list(pag.legislators(limit=30000, all_legislators='true'))
		print("Creating Bits and Bytes", file=sys.stderr)
		string = json.dumps(legislators)
		f = open('legislatorsList', 'w')
		f.write(string)
		del pag
		del legislators
		del string
		#create MD5Sum
		print("Recreating MD5Sum", file=sys.stderr)
		(checksum, trash) = VoteLinker.checkMD5("legislatorsList")
		f=open('checksum.md5', 'w')
		f.write(checksum)

	def __init__(self):
		#do a check to see if the file exists.
		#A file is the fastest way to do this, since there are a LOT of legislators
		print("initializing", file=sys.stderr)
		if not isfile('legislatorsList'):
			print("creating Legislators because file does not exist", file=sys.stderr)
			VoteLinker.createLeg()
		elif not VoteLinker.checkMD5('legislatorsList'):
			print("creating legislatures because file checksome is wrong", file=sys.stderr)
			VoteLinker.createLeg()
		print("Loading Legislators...", file=sys.stderr)
		self.legislators = json.load(open('legislatorsList', 'r'))
	def getValue(self,category, bid):
		''' Parse through the existing list to find a politician'''
		for politician in self.legislators:
			if politician[category] == bid:
				return politician
	def determineParty(self, bd):
		'''Determine party margins for each party'''
		#returns dem-vote margin and r-vote margin
		print("Determining Party Margins", file=sys.stderr)
		#literally just a placeholder. This gets the passed version of the bill and the breakdown of what party voted where
		blob = congress.votes(result="Passed", bill_id=bd, fields="breakdown")
		print("blob is: length ", len(blob), file=sys.stderr)
		try:
			blob[0]['breakdown']['party']['R']['Yea'] += blob[1]['breakdown']['party']['R']['Yea']
			blob[0]['breakdown']['party']['R']['Nay'] += blob[1]['breakdown']['party']['R']['Nay']
			blob[0]['breakdown']['party']['D']['Nay'] += blob[1]['breakdown']['party']['D']['Nay']
			blob[0]['breakdown']['party']['D']['Yea'] += blob[1]['breakdown']['party']['D']['Yea']
		#In case that there is no index 1, where there was no house/senate vote or something, just ignore it.
		except IndexError:
			pass
		party=blob[0]
		rVotes = (party['breakdown']['party']['R']['Yea'] - party['breakdown']['party']['R']['Nay'])
		dVotes = (party['breakdown']['party']['D']['Yea'] - party['breakdown']['party']['D']['Nay'])
		return (dVotes, rVotes)

	def getVotingRecord(self, bill_identifier):
		'''Take the bill_id and return which legislatures voted across party lines'''
		#Make sure the bill exists
		blob = congress.votes(vote_type="passage", bill_id=bill_identifier, fields="voter_ids")
		if blob == None:
			raise ValueError("Bill Does Not Exist")
			return ({},{})
		#Determine which party went which way
		(dMargin, rMargin)=self.determineParty(bill_identifier)
		dVoters = {};
		rVoters = {};
		#just in case there is no Senate vote
		try:
			blob[0].update(blob[1])
		except IndexError:
			pass
		votes=blob[0]
		for voter in votes['voter_ids']:
			him= self.getValue("bioguide_id", voter)
			#catch the weird case where someone isn't there???
			if him == None:
				print("Please regenerate the legislator index using the 'r' flag to prevent this from happenging agin. If this persists. please report following ID:",voter, file=sys.stderr)
				[him] = congress.legislators(all_legislators='true', bioguide_id=voter)
			try:
				if him['party'] == 'D':
					if votes['voter_ids'][voter] == 'Yea' and dMargin < 0:
						dVoters[self.getValue('bioguide_id', voter)['crp_id']] = bill_identifier
					elif votes['voter_ids'][voter] == 'Nay' and dMargin > 0:
						dVoters[self.getValue('bioguide_id', voter)['crp_id']] = bill_identifier
				elif him['party'] == 'R':
					if votes['voter_ids'][voter] == 'Yea' and rMargin < 0:
						rVoters[self.getValue('bioguide_id', voter)['crp_id']] = bill_identifier
					if votes['voter_ids'][voter] == 'Nay' and rMargin> 0:
						rVoters[self.getValue('bioguide_id', voter)['crp_id']] = bill_identifier
				else:
					pass
			except TypeError: #Redundancy. Should never actually occur
				print("Uh-oh. Something went wrong. Please report the following info.", file=sys.stderr)
				print(him)
				print(congress.legislators(all_legislators='true', bioguide_id=voter), file=sys.stderr)
				print(voter, file=sys.stderr)
				break
		return (dVoters, rVoters)
if __name__ == "__main__":
	import sys
	opt = {"v": "voting", "--voting-records": "voting", "voting records": "voting",
		   "r": "regenerate", "--regenerate": "regenerate", "regenerate": "regenerate"
		   }
	def voting(voteNumber):
		if len(sys.argv) < 3:
			printUsage()
			exit(1)
		a = VoteLinker()
		votes =[]
		try:
			votes=(a.getVotingRecord(sys.argv[voteNumber+ 1]))
		except ValueError:
			print("The Bill You Entered,", sys.argv[voteNumber+1],"Does Not Exist", file=sys.stderr)
			exit(2)
		for d in votes:
			for k,v in d.items():
				print(k)
	def regenerate():
		VoteLinker.createLeg()
	def printUsage():
		print("Meanings: \n voting = Look at partisian votes for specified bill. \n regenerate = update list of legislatures from the Sunlight Foundation's database.", file=sys.stderr)
		for k,v in opt.items():
			if len(k) == 1:
				print("Flag: -"+k, ":", v, file=sys.stderr)
			else:
				print("Option: "+k,":", v, file=sys.stderr)
	if len(sys.argv) < 2:
		printUsage()
		exit(1)
	com = sys.argv[1]
	if com[0] == "-" and not com[1] == "-":
		for cmd in com[1:]:
			if not cmd in opt:
				printUsage()
				exit(1)
			if opt[cmd] == "voting":
				voting(1)
			elif opt[cmd] == "regenerate":
				regenerate()
			else:
				printUsage()
	else:
		for command in range(1,sys.argv):
			if opt[sys.argv[command]] == "regenerate":
				regenerate()
			elif opt[sys.argv[command]] == "voting":
				voting()
			else:
				printUsage()
				exit(1)
		exit(0)
