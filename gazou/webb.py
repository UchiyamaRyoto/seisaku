import os
import sys, io
import cgi
import cv2
import joblib
import numpy as np
from sklearn import datasets, svm

DIGITS_PKL = "digit-clf.pkl"

def train_digits():
    digits = datasets.load_digits()
    # 訓練する
    data_train = digits.data
    label_train = digits.target
    clf = svm.SVC(gamma=0.001)
    clf.fit(data_train, label_train)
    # 予測モデルを保存
    joblib.dump(clf, DIGITS_PKL)
    return clf

def predict_digits(image_path):
    img = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)
    img = cv2.resize(img, (8, 8), interpolation=cv2.INTER_AREA)
    img = np.asarray(img, dtype=float)
    img = np.floor(16 - 16 * (img / 256))
    img = img.flatten()

    if not os.path.exists(DIGITS_PKL):
        clf = train_digits()
    clf = joblib.load(DIGITS_PKL)

    prediction = clf.predict([img])
    return prediction[0]


sys.stdout = io.TextIOWrapper(
    sys.stdout.buffer,
    encoding='utf-8'
)
print("Content-Type:text/html;charset=utf-8")
print("")
html_body = """
<html>
<head><meta charset="utf-8">
<title>Python CGI Image</title>
</head>
<body>
"""

print(html_body)


form = cgi.FieldStorage()
if "imagefile" in form:
    img = form.getvalue("imagefile")
    arr = np.asarray(
        bytearray(img),
        dtype=np.uint8
    )
    d_img = cv2.imdecode(arr, -1)
    cv2.imwrite('numimage.png', d_img)
    print("<img src='../numimage.png'>")

    image_path = 'numimage.png'
    result = predict_digits(image_path)
    print(f"この画像は数字の{result}です。")

html_body = """
<form method="post" enctype="multipart/form-data">
<input type="file" name="imagefile">
<input type="submit" value="送信する">
</form>
</body>
</html>"""
print(html_body)