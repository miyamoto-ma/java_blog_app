'use strict';

// 「いいね」機能のAjax通信
let goods = document.getElementsByClassName("good");
let good_results = document.getElementsByClassName("good_result");
for(let i=0; i<goods.length; i++) {
	goods[i].addEventListener("click", function(e) {
		if(e.target.dataset.userId != "" && e.target.dataset.userId >= 1){
		fetch('EvaluationServlet', {
				method: "POST",
				body: JSON.stringify({
						blogId: e.target.dataset.blogId
					})
				})
			.then(data => data.json())
			.then(res => {
				good_results[i].textContent = res.count;
				let str = res.result;
				if(str == "addOK") {
					goods[i].classList.add("active");
				} else if(str == "deleteOK") {
					goods[i].classList.remove("active");
				} else {
					alert(str);
				}
				
			})
			.catch(err => {
				alert("通信に失敗しました。");
			});
		} else {
			alert("ログインしてください。");
		}
			
	});
}



// モーダルウィンドウ
let modal_imgs = document.getElementsByClassName("c_img");
let modals = document.getElementsByClassName("modal")
let modal_closes = document.getElementsByClassName("modal_close");
let modal_backs = document.getElementsByClassName("modal_back");
let html = document.getElementById("html");

for(let i=0; i<modals.length; i++) {
	// 画像クリックでモーダル開く
	modal_imgs[i].addEventListener("click", function(e) {
		modals[i].classList.add("active");
		html.classList.add("modal_html");
	});
	
	// ×ボタンか背景をクリックでモーダル閉じる
	modal_closes[i].addEventListener("click", function(e) {
		modals[i].classList.remove("active");
		html.classList.remove("modal_html");
	});
	modal_backs[i].addEventListener("click", function(e) {
		modals[i].classList.remove("active");
		html.classList.remove("modal_html");
	});	
}














