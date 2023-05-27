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
	
	// 特定のブログのいいねを一括削除（ブログ削除前に使用）
	public boolean executeDeleteGoodByBlogId(int blogId) {
		GoodDAO dao = new GoodDAO();
		boolean result = dao.deleteGoodByBlogId(blogId);
		return result;
	}
	
	// 特定ユーザーのいいねを一括削除（ユーザー削除前に使用）
	public boolean executeDeleteGoodByUserId(int userId) {
		GoodDAO dao = new GoodDAO();
		boolean result = dao.deleteGoodByUserId(userId);
		return result;
	}
}
