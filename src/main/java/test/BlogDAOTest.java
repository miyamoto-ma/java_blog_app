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
		testFindAll();			// ブログを取得できたかのテスト
		testFindByPageOK();		// ブログを取得できた場合のテスト（ページネーション）
		testFindByPageNG();		// ブログを取得出来なかった場合のテスト（ページネーション）
		testGetTotal();			// ブログの総数を取得する
//		testDeleteBlogOK();			// ブログを削除できた場合のテスト
//		testDeleteBlogNG();			// ブログを削除できなかった場合のテスト
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
	
	public static void testFindByPageOK() {
		long currentPage = 3;
		int itemsPerPage = 10;
		BlogDAO dao = new BlogDAO();
		List<Blog> result = dao.findByPage(currentPage, itemsPerPage);
		if(result.size() > 0) {
			System.out.println("testFindByPageOK: 成功しました");
		} else {
			System.out.println("testFindByPageOK: 失敗しました");
		}
	}
	
	public static void testFindByPageNG() {
		// 現在データが43行の状態。
		long currentPage = 10;
		int itemsPerPage = 10;
		BlogDAO dao = new BlogDAO();
		List<Blog> result = dao.findByPage(currentPage, itemsPerPage);
		if(result.size() == 0) {
			System.out.println("testFindByPageNG: 成功しました");
		} else {
			System.out.println("testFindByPageNG: 失敗しました");
		}
		
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
		boolean result = dao.deleteBlog(2);
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
}
