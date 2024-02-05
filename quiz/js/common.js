(function ($) {

    const prefecturalCapital = prefectural;
    console.log(prefecturalCapital);

    // 問題数の合計
    let $questionTotalNum = 20;
    // 現在の質問数
    let $currentNum = 0;
    // 得点
    let $pointPerCorrect = 5;
    // 説明文
    let $explainText = "";
    // スコアの判定
    let scoreText = "aaa";
    // クイズの量指定
    let quizId = ["01", "02", "03", "04", "05","06", "07", "08", "09", "10","11", "12", "13", "14", "15",
    "16", "17", "18", "19", "20","21", "22", "23", "24", "25","26", "27", "28", "29", "30",
    "31", "32", "33", "34", "35","36", "37", "38", "39", "40","41", "42", "43", "44", "45",
    "46", "47", "48", "49", "50","51", "52", "53", "54", "55","56", "57", "58", "59", "60",
    "61", "62", "63", "64", "65","66", "67", "68", "69", "70","71", "72","73","74","75",
    ,"76","77","78","79","80"
    ];
    // クイズ並び替え
    shuffleQuiz(quizId);

    let questionObject = (function () {
        let Obj = function ($target) {
            // 質問番号
            this.$questionNumber = $target.find(".quiz-question-number");
            // 質問文
            this.$questionName = $target.find(".quiz-question");
            // 選択肢
            this.$questionButton = $target.find(".quiz-button");
            this.$button01 = $target.find(".button01");
            this.$button02 = $target.find(".button02");
            this.$button03 = $target.find(".button03");
            this.$button04 = $target.find(".button04");
            // テキスト
            this.$answer01 = $target.find(".quiz-text01");
            this.$answer02 = $target.find(".quiz-text02");
            this.$answer03 = $target.find(".quiz-text03");
            this.$answer04 = $target.find(".quiz-text04");
            // 点数
            this.$score = $target.find(".score-wrap .score");
            // 次へのボタン
            this.$nextButton = $target.find(".next-button");
            // 説明
            this.$explain = $target.find(".explain");

            this.init();
        };

        Obj.prototype = {
            init: function () {
                // イベント処理
                this.event();
            },
            event: function () {
                // イベント内
                let _this = this;

                // スコア
                let score = 0;
                
                // 初めて表示されたとき
                $(window).on("load", function () {
                    // valueの取得
                    let value = quizId[$currentNum];
                    // 次の問題を取得
                    let nextQuestion = _this.searchQuestion(value);
                    // 次の問題を表示
                    _this.changeQuestion(nextQuestion);
                    _this.shuffleAnswer($(".quiz-answer"));
                });

                // 選択肢が押されたとき
                this.$questionButton.on("click", function () {
                    //正解の選択肢が押されたとき
                    if ($(this).hasClass("button01")) {
                        $(this).parents(".quiz-answer").addClass("is-correct");
                        score = score + $pointPerCorrect;
                    } else {
                        $(this).parents(".quiz-answer").addClass("is-incorrect");
                    }
                    $(this).addClass("is-checked");

                            $(".explain").addClass("is-show");
                            $(".explain-wrap .explain-text").text($explainText);
                    return false;
                });

                this.$nextButton.on("click", function () {

                    $(".explain").removeClass("is-show");

                    if ($currentNum + 1 == $questionTotalNum) {
                        if(score > 80){
                            scoreText = "もしかして文系ですかぁ！？！！";
                        }else if(score > 60){
                            scoreText = "革命児！天才児！ギフテッド！！あなたみたいな人は見たことない！";
                        }else if(score > 40){
                            scoreText = "頭悪すぎる。今まで何して生きてたの？勉学に励もう！";
                        }else{
                            scoreText = "ベーコンが裸足で駆けてきてあなたを殴るレベルだよ！！";
                        }
                        $(".finish").addClass("is-show");
                        $(".scope-wrap .score").text(score);
                        $(".evaluation").text(scoreText);
                    }

                     // 現在の数字を更新
                     $currentNum = $currentNum + 1;

                     // 次の質問番号取得
                     let value = quizId[$currentNum];

                     // 次の質問取得
                     let nextQuestion = _this.searchQuestion(value);

                     // 次の質問表示
                     _this.changeQuestion(nextQuestion);

                     // クラスの削除
                     _this.$questionButton.removeClass("is-checked");
                     $(".quiz-answer").removeClass("is-correct").removeClass("is-incorrect");

                     // 選択肢の並び替え
                     _this.shuffleAnswer($(".quiz-answer"));
                })
            },

            // 次の質問を取得する関数
            searchQuestion: function (questionId) {
                let nextQuestion = null;
                prefecturalCapital.forEach(function (item) {
                    if (item.id == questionId) {
                        nextQuestion = item;
                    }
                });
                return nextQuestion;
            },

            // 次の質問を表示する関数
            changeQuestion: function (nextQuestion) {
                let _this = this;

                if(nextQuestion) {
                    let numPlusOne = $currentNum + 1;
                    _this.$questionNumber.text("質問" + numPlusOne);

                    _this.$questionName.text(nextQuestion.question);

                    _this.$answer01.text(nextQuestion.answer01);
                    _this.$answer02.text(nextQuestion.answer02);
                    _this.$answer03.text(nextQuestion.answer03);
                    _this.$answer04.text(nextQuestion.answer04);

                    $explainText = nextQuestion.explain;
                }
            },

            // 選択肢の並び替えをする関数
            shuffleAnswer: function (container) {
                let content = container.find("> *");
                let total = content.length;
                content.each(function () {
                    content.eq(Math.floor(Math.random() * total)).prependTo(container);
                });
            },
        };

        return Obj;
    })();

    let quiz = $(".quiz");
    if (quiz[0]) {
        let queInstance = new questionObject(quiz);
    }

    // 回答順を変更
    function shuffleQuiz(array) {
        for (let i = array.length - 1; i > 0; i--) {
            let random = Math.floor(Math.random() * (i + 1));
            let selected = array[i];
            array[i] = array[random];
            array[random] = selected;
        }
        return array;
    }
})(jQuery);
