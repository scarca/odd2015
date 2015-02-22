from os.path import isfile
from hashlib import md5
from sunlight import congress 
from sunlight.pagination import PagingService
import json 

class VoteLinker:
	def checkMD5(fd):
		realMD5 = open('checksum.md5', 'r').readline()[:-1]
		checksum = md5()
		#generating CheckSum
		with open(fd, 'rb') as f:
			for p in iter(lambda : f.read(128), b''):
				checksum.update(p)
		checked = checksum.hexdigest()
		return (checked, realMD5 == checked)
	def createLeg():
		pag = PagingService(congress) 
		legislators = list(pag.legislators(limit=15000, all_legislators='true'))
		string = json.dumps(legislators)
		f = open('legislatorsList', 'w')
		f.write(string)
		del pag
		del legislators
		del string
		#create MD5Sum
		(checksum, trash) = VoteLinker.checkMD5("legislatorsList")
		f=open('checksum.md5', 'w')
		f.write(checksum)

	def __init__(self):
		#do a check to see if the file exists.
		#A file is the fastest way to do this, since there are a LOT of legislators
		print("initializing")
		if not isfile('legislatorsList'):
			print("creating Legislators because file does not exist")
			VoteLinker.createLeg()
		elif not VoteLinker.checkMD5('legislatorsList'):
			print("creating legislatures because file checksome is wrong")
			VoteLinker.createLeg()
		print("Loading Legislators...") 
		self.legislators = json.load(open('legislatorsList', 'r'))
	def getValue(self,category, bid):
		for politician in self.legislators: 
			if politician[category] == bid: 
				return politician 
	def determineParty(self, bd):
		#returns dem-vote margin and r-vote margin 
		blob = congress.votes(vote_type="passage", bill_id=bd, fields="breakdown")
		blob[0]['breakdown']['party']['R']['Yea'] += blob[1]['breakdown']['party']['R']['Yea']
		blob[0]['breakdown']['party']['R']['Nay'] += blob[1]['breakdown']['party']['R']['Nay']
		blob[0]['breakdown']['party']['D']['Nay'] += blob[1]['breakdown']['party']['D']['Nay']
		blob[0]['breakdown']['party']['D']['Yea'] += blob[1]['breakdown']['party']['D']['Yea']

		party=blob[0]
		rVotes = (party['breakdown']['party']['R']['Yea'] - party['breakdown']['party']['R']['Nay'])
		dVotes = (party['breakdown']['party']['D']['Yea'] - party['breakdown']['party']['D']['Nay'])
		return (dVotes, rVotes)
		
	def getVotingRecord(self, bill_identifier): 
		#Determine which party went which way
		(dMargin, rMargin)=self.determineParty(bill_identifier)
		dVoters = {}; 
		rVoters = {}; 
		blob = congress.votes(vote_type="passage", bill_id=bill_identifier, fields="voter_ids")
		blob[0].update(blob[1])
		votes=blob[0]
		number = 0; 
		numberNQuery= 0;
		for voter in votes['voter_ids']: 
			number += 1
			print(number, voter)
			him= self.getValue("bioguide_id", voter)
			#catch the weird case where someone isn't there??? 
			if him == None: 
				print("Querying this SOB")
				[him] = congress.legislators(all_legislators='true', bioguide_id=voter)
			else:
				numberNQuery +=1 
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
					print("Fuck you, too.")
			except TypeError:
				print(him)
				print(congress.legislators(all_legislators='true', bioguide_id=voter))
				print(voter)
				break
		return (dVoters, rVoters)
a = VoteLinker()
print(a.getVotingRecord("hr5021-113"))

