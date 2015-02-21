from csv import DictReader
class VoteLinker:
	def __init__(self):
		self.reader = DictReader(open ('legislators.csv', 'r'))
	def getParty(self, bioguide):
		for theif in self.reader:
			if theif['bioguide_id'] == bioguide:
				return theif['party']

a = VoteLinker()
print(a.getParty('L000304'))
