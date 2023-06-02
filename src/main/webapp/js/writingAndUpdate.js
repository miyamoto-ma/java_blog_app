"use strict";


/**
 * 画像ファイルのサイズと種類をチェックする
 */
const img_max_size = 1024 * 1024 * 1;	// 画像の最大サイズ１MB
const allow_exts = new Array('jpg', 'jpeg', 'gif');	// 許可する拡張子
const input_file = document.getElementById("file");	// input[type="file"}の要素

// changeイベントで呼び出す関数
const handleFileSelect = () => {
	const files = input_file.files;
	for(let i = 0; i < files.length; i++) {
		let filename = files[i].name;	// ファイル名
		let ext = getExt(filename).toLowerCase();	// 小文字にした拡張子
		
		if (files[i].size > img_max_size && allow_exts.indexOf(ext) === -1) {
			// ファイルサイズがオーバーし、拡張子も違う
			alert(files[i].name + 'はアップロードできません。\n (拡張子は[.jpg, .jpeg, .gif]のいずれか)\n (ファイルサイズは1MB以内)');	// エラーメッセージを表示
			input_file.value = '';	// inputの中身をリセット
			return false;		// 処理を終了
		} else if(files[i].size > img_max_size) {
			// ファイルサイズがオーバーした場合
			alert('ファイルサイズが1MBを超えています。');
			input_file.value = '';	// inputの中身をリセット
			return false;
		} else if(allow_exts.indexOf(ext) === -1){
			// 拡張子が違う場合
			alert('画像ファイル(.jpg, .jpeg, .gif)を選択してください。');
			input_file.value = '';	// inputの中身をリセット
			return false;
		}
		return true;
	}
};

// ファイル名から拡張子を取得する関数
function getExt(filename) {
	let pos = filename.lastIndexOf('.');
	if(pos === -1) return '';
	return filename.slice(pos + 1);	
}

// ファイル選択時にhandleFileSelect関数を発火
input_file.addEventListener('change', handleFileSelect);




/**
 * input要素の現在の入力文字数を出力する
 */
const input_title = document.getElementById("p_title");		// input（タイトル）の要素
const char_title = document.getElementById("char_title");	// input（タイトル）の文字数を表示する要素
const textarea_text = document.getElementById("p_text");	// textarea（内容）の要素
const char_text = document.getElementById("char_text");		// textarea（内容）の文字数を表示する要素

// target: 対象要素、char_span:現在の文字数の出力場所
function charCount(target, char_span) {
	target.addEventListener('keyup', () => {
		 char_span.textContent = target.value.length;
	});
}

charCount(input_title, char_title);
charCount(textarea_text, char_text);

/**
 * 画像プレビュー
 */
let file = document.getElementById("file");
let reader = new FileReader();

reader.addEventListener("load", function() {
	document.getElementById("new_img").src = reader.result;
}, false);
file.addEventListener("change", function(e) {
	let input = file.files[0];
	reader.readAsDataURL(input);
}, false);



















