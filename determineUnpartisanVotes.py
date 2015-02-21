from sunlight import congress 
from sunlight.pagination import PagingService

class VoteLinker:
	def __init__(self):
		paging_service = PagingService(congress)
		self.legislators = paging_service.legislators(limit=1000)
	def getValue(self,category, id):
		for politician in self.legislators: 
			if politician[category] == id: 
				return politician 
a = VoteLinker()
