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
				console.log(err)
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



// トップに戻るボタン（トップに戻る機能）
const top_btn = document.getElementById("top_btn");
top_btn.addEventListener("click", function() {
	window.scroll({top: 0, behavior: 'smooth'});
});

// トップに戻るボタン（下に移動したら出現する）
const window_h = window.innerHeight;
let timer = null;
function func() {
	clearTimeout(timer);
	timer = setTimeout(function() {
		if(window.pageYOffset > window_h) {
			top_btn.classList.remove("transparent");
		} else {
			top_btn.classList.add("transparent");
		}
	}, 16);
}
window.addEventListener("scroll",func, {passive: true});



// サインアウトの確認
const signout = document.getElementById("signout");
signout.addEventListener("click",function() {
	let result = confirm("本当にアカウントを削除してよろしいですか？");
	if(result) {
		location.href = "SignoutServlet";
	}
	return false;
});







