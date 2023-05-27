package test;

import dao.BlogDAO;
import dao.GoodDAO;
import model.Blog;
import model.Good;
import model.GoodLogic;

public class GoodLogicTest {
	public static void main(String[] args) {
//		testExecuteToggleOK();			// 「いいね」追加/削除成功のテスト
//		testExecuteToggleNG();			// 「いいね」追加/削除失敗のテスト
//		testExecuteCountOK();				// 「いいね」の数取得成功のテスト（1投稿分）
//		testExecuteCountNG();				// 「いいね」の数取得失敗のテスト（1投稿分）
		testExecuteDeleteGoodByBlogId();	// 特定ブログのいいね一括削除のテスト
		testExecuteDeleteGoodByUserId();	// 特定ユーザーのいいね一括削除のテスト
	}
	
	public static void testExecuteToggleOK() {
		// 存在するBlogsのIDとUsersのIDを調べておく
		Good good = new Good(12, 1);
		GoodLogic bo = new GoodLogic();
		String result = bo.executeToggle(good);
		if(result == "deleteOK" || result == "addOK") {
			System.out.println("testExecuteToggleOK: 成功しました");
		} else {
			System.out.println("testExecuteToggleOK: 失敗しました");
		}
		System.out.println(result);
	}
	
	public static void testExecuteToggleNG() {
		// 存在しないGoodsを調べておく。
		Good good = new Good(1000, 100);
		GoodLogic bo = new GoodLogic();
		String result = bo.executeToggle(good);
		if(result != "deleteOK" && result != "addOK") {
			System.out.println("testExecuteToggleNG: 成功しました");
		} else {
			System.out.println("testExecuteToggleNG: 失敗しました");
		}
		System.out.println(result);
	}
	
	public static void testExecuteCountOK() {
		// 存在するGoodsのBLOG_IDを調べておく
		GoodLogic bo = new GoodLogic();
		int result = bo.executeCount(10);
		if(result != 0) {
			System.out.println("testExecuteCountOK: 成功しました");
		} else {
			System.out.println("testExecuteCountOK: 失敗しました");;
		}
		System.out.println(result);
	}
	
	public static void testExecuteCountNG() {
		// 存在しないGoodsのBLOG_IDを調べておく
		GoodLogic bo = new GoodLogic();
		int result = bo.executeCount(1000);
		if(result == 0) {
			System.out.println("testExecuteCountNG: 成功しました");
		} else {
			System.out.println("testExecuteCountNG: 失敗しました");;
		}
		System.out.println(result);
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
		
		public static void testExecuteDeleteGoodByBlogId() {
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
						System.out.println("testExecuteDeleteGoodByBlogId: ブログのイイネに失敗しました");
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
				GoodLogic g_bo = new GoodLogic();
				boolean result = g_bo.executeDeleteGoodByBlogId(blogId);
				if(result) {
					System.out.println("testExecuteDeleteGoodByBlogId: 成功しました");
				} else {
					System.out.println("testExecuteDeleteGoodByBlogId: 失敗しました");
				}
				
				// テストが終わったら追加したブログを削除
				boolean result_d = deleteBlog(blogId);
				if(result_d) {
					System.out.println("テスト用投稿データを削除しました");
				}
			} else {
				System.out.println("testExecuteDeleteGoodByBlogId: テスト用ブログの投稿に失敗しました");
			}
		}
		
		public static void testExecuteDeleteGoodByUserId() {
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
						System.out.println("testExecuteDeleteGoodByUserId: ブログのイイネに失敗しました");
						System.out.println(result_g);
						// テスト用投稿データの削除
						boolean result_d2 = deleteBlog(blogsId[i]);
						if(result_d2) {
							System.out.println("テスト用投稿データを削除しました");
						}
						return;
					}
				} else {
					System.out.println("testExecuteDeleteGoodByUserId: テスト用ブログの投稿に失敗しました");
					System.out.println("blogId:" + blogsId[i]);
				}
			}
			
			// 追加したブログから「いいね」を削除する（ここがメイン）
			GoodLogic g_bo = new GoodLogic();
			boolean result = g_bo.executeDeleteGoodByUserId(userId);
			if(result) {
				System.out.println("testExecuteDeleteGoodByUserId: 成功しました");
			} else {
				System.out.println("testExecuteDeleteGoodByUserId: 失敗しました");
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
