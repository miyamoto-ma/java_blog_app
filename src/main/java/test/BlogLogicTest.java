package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Blog;
import model.BlogLogic;

public class BlogLogicTest {
	public static void main(String[] args) {
//		testExecuteAddOK();		// ブログ追加成功のテスト
//		testExecuteAddNG();		// ブログ追加失敗のテスト
//		testExecuteFindAll();			// ブログ一覧取得のテスト
		testExecuteFindByIdOK();		// ブログ1件分取得成功のテスト
		testExecuteFindByIdNG();		// ブログ1件分取得失敗のテスト
//		testExecuteFindByPageOK();		// ブログ１ページ分取得成功のテスト
//		testExecuteFindByPageNG();		// ブログ１ページ分取得失敗のテスト
//		testExecuteGetTotal();			// ブログ総件数取得のテスト
//		testExecuteDeleteOK();				// ブログ削除成功のテスト
//		testExecuteDeleteNG();				// ブログ削除失敗のテスト
//		testExecuteUpdateOK();			// ブログ編集成功のテスト
//		testExecuteUpdateNG();			// ブログ編集失敗のテスト
	}
	public static void testExecuteAddOK() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatNow = dtf.format(now);
		Blog blog = new Blog(1, "あいうえお", "これはテストです", "", formatNow);
		BlogLogic bo = new BlogLogic();
		boolean result = bo.executeAdd(blog);
		if(result) {
			System.out.println("testExecuteAddOK: 成功しました");
		} else {
			System.out.println("testExecuteAddOK: 失敗しました");
		}
	}
	public static void testExecuteAddNG() {
		String title = "";
		for (int i = 0; i<11; i++) {
			title += "あいうえおかきくけこ";
		}
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatNow = dtf.format(now);
		Blog blog = new Blog(1, title, "これはテストです", "", formatNow);
		BlogLogic bo = new BlogLogic();
		boolean result = bo.executeAdd(blog);
		if(!result) {
			System.out.println("testExecuteAddNG: 成功しました");
		} else {
			System.out.println("testExecuteAddNG: 失敗しました");
		}
	}
	
	public static void testExecuteFindAll() {
		BlogLogic bo = new BlogLogic();
		List<Blog> BlogList = bo.executeFindAll();
		if(BlogList.size() > 0) {
			System.out.println("testExecuteFind: 成功しました");
		} else {
			System.out.println("testExecuteFind: 失敗しました");
		}
	}
	
	public static void testExecuteFindByIdOK() {
		int id = 10;	// 存在するID
		BlogLogic bo = new BlogLogic();
		Blog blog = bo.executeFindById(id);
		if(blog != null) {
			System.out.println("testExecuteFindByIdOK: 成功しました");
		} else {
			System.out.println("testExecuteFindByIdOK: 失敗しました");
		}
	}
	
	public static void testExecuteFindByIdNG() {
		int id = 1000;	// 存在しないID
		BlogLogic bo = new BlogLogic();
		Blog blog = bo.executeFindById(id);
		if(blog == null) {
			System.out.println("testExecuteFindByIdNG: 成功しました");
		} else { 
			System.out.println("testExecuteFindByIdNG: 失敗しました");
		}
	}
	
	public static void testExecuteFindByPageOK() {
		long currentPage = 3;
		int itemsPerPage = 10;
		BlogLogic bo = new BlogLogic();
		List<Blog> BlogList = bo.executeFindByPage(currentPage, itemsPerPage);
		if(BlogList.size() > 0) {
			System.out.println("testExecuteFindByPageOK: 成功しました");
		} else {
			System.out.println("testExecuteFindByPageOK: 失敗しました");
		}
	}
	
	public static void testExecuteFindByPageNG() {
		// 現在ブログ総件数は43の状態
		long currentPage = 10;
		int itemsPerPage = 10;
		BlogLogic bo = new BlogLogic();
		List<Blog> BlogList = bo.executeFindByPage(currentPage, itemsPerPage);
		
		for(Blog blog : BlogList) {
			System.out.println(blog);
		}
		if(BlogList.size() == 0) {
			System.out.println("testExecuteFindByPageNG: 成功しました");
		} else {
			System.out.println("testExecuteFindByPageNG: 失敗しました");
		}
	}
	
	public static void testExecuteGetTotal() {
		BlogLogic bo = new BlogLogic();
		long total = bo.executeGetTotal();
		if(total > 0) {
			System.out.println("testExecuteGetTotal: 成功しました");
			System.out.println(total);
		} else {
			System.out.println("testExecuteGetTotal: 失敗しました");
			System.out.println(total);
		}
	}
	
	public static void testExecuteDeleteOK() {
		// 先にサーバーで、IDの存在を確認しておく。
		BlogLogic bo = new BlogLogic();
		boolean result = bo.executeDelete(4);
		System.out.println(result);
		if(result) {
			System.out.println("testExecuteDeleteOK: 成功しました");
		} else {
			System.out.println("testExecuteDeleteOK: 失敗しました");
		}
	}
	
	public static void testExecuteDeleteNG() {
		BlogLogic bo = new BlogLogic();
		boolean result = bo.executeDelete(1000);	// 現状存在しないID
		System.out.println(result);
		if(!result) {
			System.out.println("testExecuteDeleteOK: 成功しました");
		} else {
			System.out.println("testExecuteDeleteOK: 失敗しました");
		}
	}
	
	public static void testExecuteUpdateOK() {
		// 先にサーバーに存在するデータを確認しておく。
		BlogLogic bo = new BlogLogic();
		Blog blog = new Blog(16, "testExecuteUpdateOK", "これはtestExecuteUpdateOK用データです", "");
		boolean result = bo.executeUpdate(blog);
		if(result) {
			System.out.println("testExecuteUpdateOK: 成功しました");
		} else {
			System.out.println("testExecuteUpdateOK: 失敗しました");
		}
	}
	
	public static void testExecuteUpdateNG() {
		BlogLogic bo = new BlogLogic();
		Blog blog = new Blog(1000, "testExecuteUpdateOK", "これはtestExecuteUpdateOK用データです", "");
		boolean result = bo.executeUpdate(blog);
		if(!result) {
			System.out.println("testExecuteUpdateNG: 成功しました");
		} else {
			System.out.println("testExecuteUpdateNG: 失敗しました");
		}
	}
	
}
