<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>挑戦状</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<main class = "quiz outer-block">
    <div class = "explain">
        <div class = "explain-wrap">
            <span class = "explain-text">testestestest</span>
            <input name = "next-button" type = "button" value = "next" class = "next-button">
        </div>
    </div>
    <div class= "inner-block">
        <div class = "quiz-content">
            <div class = "finish">
                <div class = "scope-wrap">
                    <span class = "score">0</span>
                    <span class = "ja">点</span>
                    <span class = "full">/100点</span>
                </div>
                <span class = "evaluation"></span>
            </div>
            <div class = "quiz-question-number"></div>
            <h2 class = "quiz-question"></h2>
            <ul class = "quiz-answer">
                <li>
                    <label class = "quiz-button button01">
                        <input name = "radio" type = "radio" value = "">
                        <span class = "quiz-text01"></span>
                    </label>
                </li>
                <li>
                    <label class = "quiz-button button02">
                        <input name = "radio" type = "radio" value = "">
                        <span class = "quiz-text02"></span>
                    </label>
                </li>
                <li>
                    <label class = "quiz-button button03">
                        <input name = "radio" type = "radio" value = "">
                        <span class = "quiz-text03"></span>
                    </label>
                </li>
                <li>
                    <label class = "quiz-button button04">
                        <input name = "radio" type = "radio" value = "">
                        <span class = "quiz-text04"></span>
                    </label>
                </li>
            </ul>
        </div>
    </div>
</main>
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/data.js"></script>
<script src="js/common.js"></script>
</body>
</html>
