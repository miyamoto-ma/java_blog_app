package model;

import dao.GoodDAO;

public class GoodLogic {
	// 「いいね」の追加/削除処理
	public String executeToggle(Good good) {
		GoodDAO dao = new GoodDAO();
		String result = dao.toggleGood(good);
		return result;
	}
	
	// 「いいね」の数の取得（1投稿分）
	public int executeCount(int blogId) {
		GoodDAO dao = new GoodDAO();
		int result = dao.goodCount(blogId);
		return result;
	}
}
