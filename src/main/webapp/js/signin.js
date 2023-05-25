'use strict';

// 新規アカウント登録で名前が被らないように確認するAjax
const newName = document.getElementById("new_name");
const cautionName = document.getElementById("caution_name");
newName.addEventListener('change', function(e) {
	if(e.target.value.length > 0){
		fetch('QueryExist' ,{
				method: 'POST',
				body: JSON.stringify({
					inputName: e.target.value
				})
			})
			.then(data => data.json())
			.then(res => {
				let exist = res.exist;
				if(exist) {
					cautionName.textContent = "※既に使われています";
				} else {
					cautionName.textContent = "";
				}
			})
			.catch(err => {
				alert("通信に失敗しました。");
				console.log(err);
			});
		
	}
});








