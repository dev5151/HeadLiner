import requests
import json

url = ('http://newsapi.org/v2/top-headlines?'
       'country=us&'
       'apiKey=6efd3ee1e13b448a8e1c8b4c45ec0bc7')

response = requests.get(url)

with open('file.json','w') as f:
    json.dump(response.json(),f,indent=3)

print (json.dumps(response.json(),indent=3))

data=str(response.json()['articles']['content'])

print(data)