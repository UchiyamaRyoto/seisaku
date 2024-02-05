$(function(){
	// ニュースのjsonファイルを取得
	$.ajax({
	  url: "../news.json",			// 取得対象となるjsonファイルのパス
	  cache: false,					// 取得したデータをキャッシュさせない
	  type: "GET",					// GETメソッドで取得
	  dataType: "json",				// json形式でデータを取得
	  timeout: 10000				// 接続からタイムアウトまでの時間
	})
	// データ取得処理成功時の処理
	// 引数に取得したデータが格納されています。今回はdataという名前の引数にしています。
	.done(function (data) {

	});
});