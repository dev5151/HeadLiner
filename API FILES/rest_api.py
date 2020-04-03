from flask import Flask, request, jsonify 
from textblob import TextBlob
import pandas as pd 
import requests
import json


# creating a Flask application
app = Flask(__name__)

list=[]
contentList=[]
positiveNews=[]
negativeNews=[]
newPositiveNews=[]
neutralNews=[]
totalLength=0
newNegativeNews=[]
newNeutralNews=[]

@app.route("/")

def fetchNews():

    news_api_url = ('http://newsapi.org/v2/top-headlines?''country=in&''apiKey=6efd3ee1e13b448a8e1c8b4c45ec0bc7')
    
    response = requests.get(news_api_url)

    data=response.json()
    
    return (data)
    

@app.route("/positive",methods=['GET'])

def fetchPositiveNews():

    news_api_url = ('http://newsapi.org/v2/top-headlines?''country=in&''apiKey=6efd3ee1e13b448a8e1c8b4c45ec0bc7')
    
    response = requests.get(news_api_url)

    data=response.json()

    totalLength=(len(data["articles"]))

    for i in range (0,totalLength):
        content = data["articles"][i]["content"]
        if not content :
            list.append(i)
        else:
            blob=TextBlob(content)
            contentList.append((i,blob.sentiment[0]))
        
    
    print(contentList) 
    print(list)

    for i in contentList:
        if 0<i[1]<=1:
            positiveNews.append(i[0])

    for i in range (0,totalLength):
        if i in positiveNews:
            newPositiveNews.append(data["articles"][i])

    return jsonify(newPositiveNews)

@app.route("/negative",methods=['GET'])

def fetchNegativeNews():

    news_api_url = ('http://newsapi.org/v2/top-headlines?''country=in&''apiKey=6efd3ee1e13b448a8e1c8b4c45ec0bc7')
    
    response = requests.get(news_api_url)

    data=response.json()

    print (json.dumps(data,indent=3))

    totalLength=(len(data["articles"]))

    for i in range (0,totalLength):
        content = data["articles"][i]["content"]
        if not content :
            list.append(i)
        else:
            blob=TextBlob(content)
            contentList.append((i,blob.sentiment[0]))
    
    for i in contentList:
        if -1<=i[1]<0:
            negativeNews.append(i[0])
        
    for i in range (0,totalLength):
        if i in negativeNews:
            newNegativeNews.append(data["articles"][i])

    return jsonify(newNegativeNews)

@app.route("/neutral",methods=['GET'])

def fetchNeutraleNews():

    news_api_url = ('http://newsapi.org/v2/top-headlines?''country=in&''apiKey=6efd3ee1e13b448a8e1c8b4c45ec0bc7')
    
    response = requests.get(news_api_url)

    data=response.json()


    totalLength=(len(data["articles"]))


    for i in range (0,totalLength):
        content = data["articles"][i]["content"]
        if not content :
            list.append(i)
        else:
            blob=TextBlob(content)
            contentList.append((i,blob.sentiment[0]))

    for i in contentList:
        if i[1]==0:
            neutralNews.append(i[0])

    for i in range (0,totalLength):
        if i in neutralNews:
            newNeutralNews.append(data["articles"][i])

    return jsonify(newNeutralNews)

if __name__=='__main__':
    app.run(port=5000,debug=True)   