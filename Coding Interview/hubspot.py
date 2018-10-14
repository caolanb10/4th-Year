import os
import sys
import requests
import json
from datetime import datetime

API_KEY = "d22acffc13cdc16abf13e198911d"
URL = "https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=d22acffc13cdc16abf13e198911d"

# Request function to reach endpoint API
def makeRequest(url):
    r = requests.get(url)
    status = r.status_code
    header =  r.headers
    rJson = json.loads(r.text or r.content)
    return rJson

# Creates JSON element for response
def create_json(attendeeCount, attendees, countryName, startDate):
    jsonData = {}
    jsonData['attendeeCount'] = attendeeCount
    jsonData['attendees'] = attendees
    jsonData['name'] = countryName
    jsonData['startDate'] = startDate
    jsonOut = json.dumps(jsonData)
    return jsonOut

# Functio for posting to endpoint URL
def post(url, data):
    r = requests.post(url, data=data)
    status = r.status_code
    print(r.status_code == requests.codes.ok)
    return(r.status_code)

# Converts JSON string date to python datetime
def conv_date(date):
    newDate = datetime.strptime(date.replace("-", " "), '%Y %m %d')
    return newDate

# Function for removing any dates listed by a partner that are not consecutive
def remove_unconsecutive_dates(dateList):
    newList = []
    for x in range(0, len(dateList) - 1):
        if(conv_date(dateList[x+1]).day - conv_date(dateList[x]).day == 1):
            l = [dateList[x], dateList[x+1]]
            newList.extend(l)
    return newList

# Returns the first most common elemnt in a list
def most_common(lst):
    return max(set(lst), key=lst.count)

# Determines from the most common day if the it is the second day that is most
# commonly free or the first day
def which_day(lst, cday):
    before = 0
    after = 0
    cday = conv_date(cday)
    for date in lst:
        date = conv_date(date)
        diff = cday - date
        if diff.days == 1:
            before+=1
        if diff.days == -1:
            after+=1
        secondCommon = cday.replace(day=cday.day+1) if after>before else \
                       cday.replace(day=cday.day-1)

        if before == 0 and after == 0:
            secondCommon = None
        else:
            secondCommon = str(secondCommon)[:10]
    return secondCommon

# Manipulates a partners available days
def manipulate(data):
    for partner in data["partners"]:
        partner["availableDates"] = remove_unconsecutive_dates(partner["availableDates"])

    return data

def main():
    getJson = makeRequest(URL)
    newJson = manipulate(getJson)
    countryDict = {}

    # Create a dictionary entry for each country found and add in the available dates
    for partner in newJson["partners"]:
        avDates = partner["availableDates"]
        country = partner["country"]
        if(country in countryDict):
            countryDict[country] = countryDict[country] + avDates
        else:
            countryDict[country] = avDates

    # For dates available for each country, find the two most common days
    for dates in countryDict.items():
        dates[1].sort()
        datesL = list(dates)
        mostCom = most_common(dates[1])
        secCom = which_day(dates[1], mostCom)
        twoDates = [mostCom, secCom]
        datesL[1] = twoDates
        countryDict[dates[0]] = datesL[1]
    # Create dictionary item for JSON response
    responseJson = {}

    responseJson["countries"] = []

    attendeeCount = {}
    emailList = {}

    # For all partners check if they can attend the two most common available dates and
    # Create variables to be used in JSON response body
    for partner in newJson["partners"]:
        if countryDict[partner["country"]][0] in partner["availableDates"] \
           and countryDict[partner["country"]][1] in partner["availableDates"]:
           if(partner["country"] in attendeeCount):
               attendeeCount[partner["country"]] += 1
           else:
               attendeeCount[partner["country"]] = 1
           if(partner["country"] in emailList):
               emailList[partner["country"]].append(partner["email"])
           else:
               emailList[partner["country"]] = [partner["email"]]

    # Create JSON array object for each country
    for k, v in emailList.items():
        startDate = countryDict[k][0] if conv_date(countryDict[k][0]) < \
                    conv_date(countryDict[k][1]) else countryDict[k][1]
        thisJson = create_json(attendeeCount[k], v, k, startDate)
        responseJson["countries"].append(thisJson)

    # Post JSON response
    post(URL, responseJson)

main()
