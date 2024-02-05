import tkinter.filedialog as fd
import tkinter

import os, math
from sklearn import datasets, svm
import joblib

WIDTH = 640  # 幅
HEIGHT = 400  # 高さ

DIGITS_PKL = "digit-clf.pkl"


# 予測モデルの作成
def train_digits():
    # 手書き数字データを読み込む
    digits = datasets.load_digits()
    # 訓練する
    data_train = digits.data
    label_train = digits.target
    clf = svm.SVC(gamma=0.001)
    clf.fit(data_train, label_train)
    # 予測モデルを保存
    joblib.dump(clf, DIGITS_PKL)
    print("予測モデルを保存しました=", DIGITS_PKL)
    return clf


# データから数字を予測する
def predict_digits(data):
    # モデルファイルを読み込む
    if not os.path.exists(DIGITS_PKL):
        clf = train_digits()  # モデルがなければ生成
    clf = joblib.load(DIGITS_PKL)
    # 予測
    global n
    n = clf.predict([data])
    print("判定結果=", n)


# 手書き数字画像を8×8グレイスケールのデータ配列に変換
def image_to_data(imagefile):
    import numpy as np
    from PIL import Image
    image = Image.open(imagefile).convert('L')
    image = image.resize((8, 8), resample=Image.LANCZOS)
    img = np.asarray(image, dtype=float)
    img = np.floor(16 - 16 * (img / 256))  # 行列演算
    img = img.flatten()
    # print(img)
    return img


def func():
    ### グローバル変数
    global image
    global name
    ### ファイルダイアログ
    name = tkinter.filedialog.askopenfilename(title="ファイル選択", initialdir="C:/", filetypes=[("Image File", "*.png")])

    ### 画像ロード
    image = tkinter.PhotoImage(file=name)

    ### キャンバスに表示
    canvas.delete("all")
    canvas.create_image(WIDTH / 2, HEIGHT / 2, image=image)

    data = image_to_data(name)
    predict_digits(data)
    canvas.create_text(WIDTH / 2, HEIGHT - 20, text="この画像は数字の {} です".format(n), font=("Helvetica", 14),
                       fill="black")


def main():
    global canvas

    ### メイン画面作成
    main = tkinter.Tk()

    ### 画面サイズ設定
    main.geometry("640x440")

    ### ボタン作成・配置
    button = tkinter.Button(main, text="ファイル選択", command=func)
    button.pack()

    ### キャンバス作成・配置
    canvas = tkinter.Canvas(main, width=WIDTH, height=HEIGHT)
    canvas.pack()

    ### イベントループ
    main.mainloop()


if __name__ == '__main__
    main()