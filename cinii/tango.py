import requests
from bs4 import BeautifulSoup
import csv
import re

def save_to_csv(texts, filename):
    with open(filename, mode="w", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)

        for text in texts:
            text = re.sub(r"[(（].*?[)）]", "", text)
            text = re.sub(r"[!$%&\’\\()*+,-./:;?@[\\]^_`{|}~「」〔〕“”〈〉『』【】＆＊・（）／()＄＃＠。,？！｀＋￥％]", "", text)
            text = text.replace(" ", "").replace("\u3000", "")  # Remove full-width spaces
            text = re.sub(r"[\uFF01-\uFF0F\uFF1A-\uFF20\uFF3B-\uFF40\uFF5B-\uFF65\u3000-\u303F]", "", text)
            text = text.replace("（", "").replace("）", "")
            print(text)
            writer.writerow([text])

if __name__ == "__main__":
    res = requests.get('https://www.y-history.net/appendix/appendix-list.html')
    soup = BeautifulSoup(res.content, 'html.parser')

    texts = []

    li_elements = soup.find_all('li')
    for li in li_elements:
        for a in li.find_all('a'):
            text = a.get_text()
            texts.append(text)

    if texts:
        output_filename = "tango.csv"
        save_to_csv(texts, output_filename)
