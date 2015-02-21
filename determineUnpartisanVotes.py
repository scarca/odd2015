from sunlight import congress 
from sunlight.pagination import PagingService

class VoteLinker:
	
	def __init__(self):
		self.paging_service = PagingService(congress)
		self.legislators = list(self.paging_service.legislators(limit=1000))
	def getValue(self,category, bid):
		for politician in self.legislators: 
			if politician[category] == bid: 
				return politician 
	def determineParty(self, bd):
		#returns dem-vote margin and r-vote margin 
		blob = congress.votes(vote_type="passage", bill_id=bd, fields="breakdown")
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
		votes=blob[0]
		for voter in votes['voter_ids']: 
			print(voter)
			him= self.getValue("bioguide_id", voter)
			if him['party'] == 'D': 
				if votes['voter_ids'][voter] == 'Yea' and dMargin < 0:
					dVoters[self.getValue('bioguide_id', voter)['crp_id']] = bill_identifier
			elif him['party'] == 'R':
				if votes['voter_ids'][voter] == 'Yea' and rMargin < 0: 
					rVoters[self.getValue('bioguide_id', voter)['crp_id']] = bill_identifier 
			else:
				print("Fuck you, too.")
		return (dVoters, rVoters)
a = VoteLinker()
print(a.getValue('bioguide_id', 'M000933'))
print(a.getVotingRecord("hr803-113"))

