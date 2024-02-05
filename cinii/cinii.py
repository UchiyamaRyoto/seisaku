import requests
import csv
import re
import json

def search_cinii(query):
    url = 'https://ci.nii.ac.jp/opensearch/search?'
    params = {
        "q":query,
        "format": "json",
        "count": "200",
        "lang": "ja",
        "start": 1,
        "range": 2
    }

    response = requests.get(url, params=params)

    if response.status_code == 200:
        return response.json()["items"]


def save_to_csv(results, filename):
    with open(filename, mode="a", encoding="utf_8_sig", newline="") as file:
        writer = csv.writer(file)

        for result in results:
            title = result.get("title", "")

            # Check if the key exists in the dictionary before accessing it
            publication_date = result.get("prism:publicationDate", "")

            # Extract the CRID number from the URL
            link = result.get("@id", "")

            # Write the data to the CSV file
            writer.writerow([title, publication_date, link])

if __name__ == "__main__":
    query = []

    csvname = "tango.csv"
    with open(csvname, mode="r", encoding="utf_8_sig") as file:
        reader = csv.reader(file)
        for row in reader:
            if row:
                query.append(row[0])

    for i in range(1, 51):
        results = search_cinii(query[i])
        if results:
            output_filename = "kansei.csv"
            save_to_csv(results, output_filename)

        
