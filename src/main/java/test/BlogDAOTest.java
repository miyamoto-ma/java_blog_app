package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.BlogDAO;
import model.Blog;

public class BlogDAOTest {

	public static void main(String[] args) {
//		testAddBlogOK();		// ブログを追加できた場合のテスト
//		testAddBlogNG();		// ブログを追加できなかった場合のテスト
//		testFindAll();			// ブログを取得できたかのテスト
//		testFindByIdOK();			// ブログ1件分の取得成功のテスト
//		testFindByIdNG();			// ブログ1件分の取得失敗のテスト
//		testFindByPageOK();		// ブログを取得できた場合のテスト（ページネーション）
//		testFindByPageNG();		// ブログを取得出来なかった場合のテスト（ページネーション）
//		testGetTotal();			// ブログの総数を取得する
//		testDeleteBlogOK();			// ブログを削除できた場合のテスト
//		testDeleteBlogNG();			// ブログを削除できなかった場合のテスト
//		testUpdateBlogOK();			// ブログを更新できた場合のテスト
//		testUpdateBlogNG();			// ブログを更新できなかった場合のテスト
//		testDeleteUserId();		// 特定ユーザーの投稿の一括削除のテスト
		
		}
	public static void testAddBlogOK() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatNow = dtf.format(now);
		Blog blog = new Blog(1,"テスト", "これはテストです", "", formatNow);
		BlogDAO dao = new BlogDAO();
		boolean result = dao.addBlog(blog);
		if(result) {
			System.out.println("testAddBlogOK: 成功しました");
		} else {
			System.out.println("testAddBlogOK: 失敗しました");
		}
	}
	public static void testAddBlogNG() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatNow = dtf.format(now);
		String title = "";
		for (int i=0; i<11; i++) {
			title += "あいうえおかきくけこ";
		}
		Blog blog = new Blog(1,title, "これはテストです", "", formatNow);
		BlogDAO dao = new BlogDAO();
		boolean result = dao.addBlog(blog);
		if(!result) {
			System.out.println("testAddBloNG: 成功しました");
		} else {
			System.out.println("testAddBlogNG: 失敗しました");
		}
	}
	public static void testFindAll() {
		BlogDAO dao = new BlogDAO();
		List<Blog> result = dao.findAll();
		if(result.size() > 0) {
			System.out.println("testFindAll: 成功しました");
		} else {
			System.out.println("testFindAll: 失敗しました");
		}
	}
	
	public static void testFindByIdOK() {
		// 存在するブログのIDをデータベースで調べておく。
		BlogDAO dao = new BlogDAO();
		Blog result = dao.findById(10);
		if(result != null) {
			System.out.println("testFindByIdOK: 成功しました");
		} else {
			System.out.println("testFindByIdOK: 失敗しました");
		}
	}
	
	public static void testFindByIdNG() {
		BlogDAO dao = new BlogDAO();
		Blog result = dao.findById(1000);		// 現状存在しないID
		if(result == null) {
			System.out.println("testFindByIdNG: 成功しました");
		} else {
			System.out.println("testFindByIdNG: 失敗しました");
		}
	}
	

	
	public static void testFindByPageOK() {
		int loginUserId = 1;
		long currentPage = 1;
		int itemsPerPage = 10;
		BlogDAO dao = new BlogDAO();
		List<Blog> result = dao.findByPage(loginUserId, currentPage, itemsPerPage);
		if(result.size() > 0) {
			System.out.println("testFindByPageOK: 成功しました");
		} else {
			System.out.println("testFindByPageOK: 失敗しました");
		}
		for(Blog blog : result) {
			System.out.println(blog.getGId());
		}
	}
	
	public static void testFindByPageNG() {
		// 現在データが43行の状態。
		int loginUserId = 10;
		long currentPage = 10;
		int itemsPerPage = 10;
		BlogDAO dao = new BlogDAO();
		List<Blog> result = dao.findByPage(loginUserId, currentPage, itemsPerPage);
		if(result.size() == 0) {
			System.out.println("testFindByPageNG: 成功しました");
		} else {
			System.out.println("testFindByPageNG: 失敗しました");
		}
		System.out.println(result);
	}

	public static void testGetTotal() {
		BlogDAO dao = new BlogDAO();
		long result = dao.getTotal();
		if(result > 0) {
			System.out.println("testGetTotal: 成功しました");
		} else {
			System.out.println("testGetTotal: 失敗しました");
		}
	}

	public static void testDeleteBlogOK() {	
		// サーバーで先にテストするIDが存在するか調べておく。
		BlogDAO dao = new BlogDAO();
		boolean result = dao.deleteBlog(133);
		System.out.println(result);
		if(result) {
			System.out.println("testDeleteBlogOK: 成功しました");
		} else {
			System.out.println("testDeleteBlogOK: 失敗しました");
		}
	}
	
	public static void testDeleteBlogNG() {
		BlogDAO dao = new BlogDAO();
		boolean result = dao.deleteBlog(1000);	// 現状存在しないID
		System.out.println(result);
		if(!result) {
			System.out.println("testDeleteBlogNG: 成功しました");
		} else {
			System.out.println("testDeleteBlogNG: 失敗しました");
		}
	}
	
	public static void testUpdateBlogOK() {
		// 存在するIDを確認しておく。
		BlogDAO dao = new BlogDAO();
		Blog blog = new Blog(14, "testUpdateBlogOK", "これはtestUpdateBlogOK用のデータです", "");
		boolean result = dao.updateBlog(blog);
		if (result) {
			System.out.println("testUpdateBlogOK: 成功しました");
		} else {
			System.out.println("testUpdateBlogOK: 失敗しました");
		}
	}
	
	public static void testUpdateBlogNG() {
		BlogDAO dao = new BlogDAO();
		Blog blog = new Blog(1000, "testUpdateBlogOK", "これはtestUpdateBlogNG用のデータです", "");
		boolean result = dao.updateBlog(blog);
		if (!result) {
			System.out.println("testUpdateBlogNG: 成功しました");
		} else {
			System.out.println("testUpdateBlogNG: 失敗しました");
		}
	}
	
//	テスト用データ
//	insert into blogs (user_id, title, text, datetime) values (33, 'test331', 'test331', '2023-05-26 00:00:00');
//	insert into blogs (user_id, title, text, datetime) values (33, 'test332', 'test332', '2023-05-26 00:00:00');
//	insert into blogs (user_id, title, text, datetime) values (33, 'test333', 'test333', '2023-05-26 00:00:00');
//	insert into blogs (user_id, title, text, datetime) values (33, 'test334', 'test334', '2023-05-26 00:00:00');
//	insert into blogs (user_id, title, text, datetime) values (33, 'test335', 'test335', '2023-05-26 00:00:00');
	public static void testDeleteUserId() {
		int userId = 33;
		BlogDAO dao = new BlogDAO();
		boolean result = dao.deleteUserId(userId);
		if(result) {
			System.out.println("testDeleteUserIdOK: 成功しました");
		} else {
			System.out.println("testDeleteUserIdOK: 失敗しました");
		}
	}

}
