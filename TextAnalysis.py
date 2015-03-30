from textblob import TextBlob

"""
This function will take a string and divide it into separate words
All the words will be in lowercase for the sake of functionality
All the words will be in singular format
The parameter passes through a string
"""
def wordSplitter(sentence):
	print sentence
	sentence = sentence.lower()
	split = TextBlob(sentence)
	print split.words.singularize()
	return split
	
	
"""
deletion is the string to be deleted
list is the list of strings
this function will get rid of unnecessary strings
"""
def wordDeleter(deletion, list):
	while deletion in list:
		list.remove(deletion)
	return list
	
"""
This function will take the wordlist and extract content-heavy words
The parameter passes through a TextBlob
Will transfer into a normal "list"
"""
def contentExtractor(TextBlobMe):
	result = []
	itercount = sum(TextBlobMe.word_counts.itervalues())
	for i in range(0, itercount):
		result.append(TextBlobMe.words[i])
	
	wordDeleter("the", result)
	wordDeleter("an", result)
	wordDeleter("a", result)
	wordDeleter("I", result)
	wordDeleter("you", result)
	
	

wordSplitter("I am a testers. Please work with me")



wiki = TextBlob("I am the, I am the, you are the")
#print wiki.tags
#print wiki.words
#print wiki.word_counts
print sum(wiki.word_counts.itervalues())
print contentExtractor(wiki)