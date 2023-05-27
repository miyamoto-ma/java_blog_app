package test;

import dao.BlogDAO;
import dao.GoodDAO;
import model.Blog;
import model.Good;

public class GoodDAOTest {
	public static void main(String[] args) {
//		testToggleGoodOK();		// 「いいね」追加/削除成功のテスト
//		testToggleGoodNG();		// 「いいね」追加/削除失敗のテスト
//		testGoodCountOK();		// 「いいね」の数の取得（1投稿分）成功のテスト
//		testGoodCountNG();		// 「いいね」の数の取得（1投稿分）失敗のテスト
		testDeleteGoodByBlogId();		// 投稿の削除前に該当「いいね」を削除
		testDeleteGoodByUserId();		// ユーザー削除前に該当「いいね」を削除
	}
	
	public static void testToggleGoodOK() {
		// 存在するユーザーとブログのIDを確認しておく
		Good good = new Good(10, 1);
		GoodDAO dao = new GoodDAO();
		String result = dao.toggleGood(good);
		if(result == "deleteOK" || result == "addOK") {
			System.out.println("testToggleGoodOK: 成功しました");
		} else {
			System.out.println("testToggleGoodOK: 失敗しました");
		}
		System.out.println(result);
	}
	
	public static void testToggleGoodNG() {
		// 存在しないユーザーかブログのIDを確認しておく
		Good good = new Good(1000, 100);
		GoodDAO dao = new GoodDAO();
		String result = dao.toggleGood(good);
		if(result != "deleteOK" && result != "addOK") {
			System.out.println("testToggleGoodNG: 成功しました");
		} else {
			System.out.println("testToggleGoodNG: 失敗しました");
		}
		System.out.println(result);
	}
	
	public static void testGoodCountOK() {
		// 存在するGoodのブログIDを確認しておく
		GoodDAO dao = new GoodDAO();
		int result = dao.goodCount(10);
		if(result != 0) {
			System.out.println("testGoodCountOK: 成功しました");
		} else {
			System.out.println("testGoodCountOK: 失敗しました");
		}
	}
	
	public static void testGoodCountNG() {
		// 存在しないGoodのブログIDを確認
		GoodDAO dao = new GoodDAO();
		int result = dao.goodCount(1000);
		if(result == 0) {
			System.out.println("testGoodCountNG: 成功しました");
		} else {
			System.out.println("testGoodCountNG: 失敗しました");
		}
	}
	
	
	// テスト用投稿データを作成する関数
	public static int addTestBlog(int userId) {
		BlogDAO dao = new BlogDAO();
		Blog blog = new Blog(userId, "test", "title", "img", "2000-01-01 00:00:00");
		int res_add = dao.addBlogResID(blog);
		if(res_add == 0) { 
			System.out.println("テスト用投稿データの追加に失敗しました"); 
			return res_add;
		}
		return res_add;
	}
	// テスト用投稿データを削除する関数
	public static boolean deleteBlog(int blogId) {
		BlogDAO dao = new BlogDAO();
		boolean result = dao.deleteBlog(blogId);
		if(!result) {
			System.out.println("テスト用投稿データの削除に失敗しました");
			return result;
		}
		return result;
	}
	
	public static void testDeleteGoodByBlogId() {
		// ブログを投稿するユーザーのID（調べておく select * from accounts)
		int userId = 33;
		// 新規ブログにイイネするユーザー5人（上記と同様に調べておく）
		int[] usersId = {1, 2, 33, 36, 38};
		
		// ブログを1件追加
		int blogId = addTestBlog(userId);
		System.out.println("追加したBlogId: " + blogId);
		
		GoodDAO g_dao = new GoodDAO();
		if(blogId != 0) {
			// 新規作成したブログに5人のユーザーがイイネする。
			for(int i=0; i<usersId.length; i++) {
				Good good = new Good(blogId, usersId[i]);
				String result_g = g_dao.toggleGood(good);
				if(!result_g.equals("addOK")) {
					System.out.println("testDeleteGoodByBlogId: ブログのイイネに失敗しました");
					System.out.println(result_g);
					// テスト用投稿データの削除
					boolean result_d2 = deleteBlog(blogId);
					if(result_d2) {
						System.out.println("テスト用投稿データを削除しました");
					}
					return;
				}
			}
			// 該当ブログの「いいね」を削除する（ここがメイン）
			boolean result = g_dao.deleteGoodByBlogId(blogId);
			if(result) {
				System.out.println("testDeleteGoodByBlogId: 成功しました");
			} else {
				System.out.println("testDeleteGoodByBlogId: 失敗しました");
			}
			
			// テストが終わったら追加したブログを削除
			boolean result_d = deleteBlog(blogId);
			if(result_d) {
				System.out.println("テスト用投稿データを削除しました");
			}
		} else {
			System.out.println("testDeleteGoodByBlogId: テスト用ブログの投稿に失敗しました");
		}
	}
	
	public static void testDeleteGoodByUserId() {
		// ブログを投稿するユーザーのID（調べておく select * from accounts)
		// ※テスト後、このユーザーの「いいね」はすべて削除されるので要注意
		int userId = 33;
		// 上記ユーザーがイイネするブログ5件
		int blogsId[] = new int[5];
		
		GoodDAO g_dao = new GoodDAO();
		// ブログを5件追加し、それぞれに「いいね」
		for(int i=0; i<blogsId.length; i++) {
			blogsId[i] = addTestBlog(userId);
			System.out.println("BlogId:" + blogsId[i] + "のテスト用投稿データを追加しました");
			if(blogsId[i] != 0) {
				// 追加したブログに「いいね」する
				Good good = new Good(blogsId[i], userId);
				String result_g = g_dao.toggleGood(good);
				if(!result_g.equals("addOK")) {
					System.out.println("testDeleteGoodByUserId: ブログのイイネに失敗しました");
					System.out.println(result_g);
					// テスト用投稿データの削除
					boolean result_d2 = deleteBlog(blogsId[i]);
					if(result_d2) {
						System.out.println("テスト用投稿データを削除しました");
					}
					return;
				}
			} else {
				System.out.println("testDeleteGoodByBlogId: テスト用ブログの投稿に失敗しました");
				System.out.println("blogId:" + blogsId[i]);
			}
		}
		
		// 追加したブログから「いいね」を削除する（ここがメイン）
		boolean result = g_dao.deleteGoodByUserId(userId);
		if(result) {
			System.out.println("testDeleteGoodByUserId: 成功しました");
		} else {
			System.out.println("testDeleteGoodByUserId: 失敗しました");
		}
		
		// テストが終わったら追加したブログを削除
		for(int i=0; i<blogsId.length; i++) {
			boolean result_d = deleteBlog(blogsId[i]);
			if(result_d) {
				System.out.println("BlogId:" + blogsId[i] + "のテスト用投稿データを削除しました");
			}
		}
	}
		
}
