import requests
import csv
import json

from sunlight import congress


apikey = a07d09d6d82b4d9985b29f79c123aaec
sunlight.config.API_KEY = apikey
keywordspoint = 'https://congress.api.sunlightfoundation.com/'

file_object = open(keyWords_Energy, r)

file_object.readline