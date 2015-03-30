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

"""
This function will take the wordlist and extract content-heavy words
The parameter passes through a TextBlob

"""
def contentExtractor(wordlist)


wordSplitter("I am a testers. Please work with me")