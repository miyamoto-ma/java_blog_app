package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Blog;

public class BlogDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/blog";
	private final String DB_USER = "manabu";
	private final String DB_PASS = "Mpa0515";
	
	// ブログ追加処理
	public boolean addBlog(Blog blog) {
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// INSERT文の準備
			String sql = "INSERT INTO BLOGS (USER_ID, TITLE, TEXT, IMG, DATETIME) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, blog.getUserId());
			pStmt.setString(2, blog.getTitle());
			pStmt.setString(3, blog.getText());
			pStmt.setString(4, blog.getImg());
			pStmt.setString(5, blog.getDatetime());
			// INSERT文を実行して、結果を取得
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	// ブログ取得処理（すべてのブログ）
	public List<Blog> findAll() {
		List<Blog> blogs = new ArrayList<> ();
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		
		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// SELECT文を準備
			String sql = "SELECT NAME, USER_ID, TITLE, TEXT, IMG, DATETIME FROM BLOGS JOIN ACCOUNTS ON BLOGS.USER_ID = ACCOUNTS.ID ORDER BY DATETIME DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int userId = rs.getInt("USER_ID");
				String name = rs.getString("NAME");
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				String img = rs.getString("IMG");
				String datetime = rs.getString("DATETIME");
				Blog blog = new Blog(userId, name, title, text, img, datetime);
				blogs.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return blogs;
	}
	
//	public long getMaxBlogLength() {
//		long maxBlogLength = null;
//		ReadJDBC jdbc = new ReadJDBC();
//		jdbc.read();
//	}
}
