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
This function will take the wordlist and extract content-heavy words
The parameter passes through a TextBlob

"""
def contentExtractor(wordlist):
	result = []
	
	

wordSplitter("I am a testers. Please work with me")

#contentExtractor(wordSplitter("ABC, I am 123"))

wiki = TextBlob("I am me, I am you, you are me")
print wiki.tags
print wiki.words
print wiki.word_counts
print sum(wiki.word_counts.itervalues())