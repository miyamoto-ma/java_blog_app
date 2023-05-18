package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Blog;
import model.BlogLogic;

public class BlogLogicTest {
	public static void main(String[] args) {
		testExecuteAddOK();		// ブログ追加成功のテスト
		testExecuteAddNG();		// ブログ追加失敗のテスト
		testExecuteFind();			// ブログ一覧取得のテスト
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
	/**
	 * 
	 */
	public static void testExecuteFind() {
		BlogLogic bo = new BlogLogic();
		List<Blog> BlogList = bo.executeFind();
		if(BlogList != null) {
			System.out.println("testExecuteFind: 成功しました");
		} else {
			System.out.println("testExecuteFind: 失敗しました");
		}
	}
}
