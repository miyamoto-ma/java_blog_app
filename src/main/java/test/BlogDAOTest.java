package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.BlogDAO;
import model.Blog;

public class BlogDAOTest {

	public static void main(String[] args) {
		testAddBlogOK();		// ブログを追加できた場合のテスト
		testAddBlogNG();		// ブログを追加できなかった場合のテスト
		testFindAll();			// ブログを取得できたかのテスト
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
		if(result != null) {
			System.out.println("testFindAll: 成功しました");
		} else {
			System.out.println("testFindAll: 失敗しました");
		}
	}

}
