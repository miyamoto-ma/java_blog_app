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
			String sql = "SELECT BLOGS.ID As ID, NAME, USER_ID, TITLE, TEXT, IMG, DATETIME FROM BLOGS JOIN ACCOUNTS ON BLOGS.USER_ID = ACCOUNTS.ID ORDER BY DATETIME DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				int userId = rs.getInt("USER_ID");
				String name = rs.getString("NAME");
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				String img = rs.getString("IMG");
				String datetime = rs.getString("DATETIME");
				Blog blog = new Blog(id, userId, name, title, text, img, datetime);
				blogs.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return blogs;
		}
		return blogs;
	}
	
	// ブログ取得処理（1件分のみ）
	// id: ブログのid
	public Blog findById(int id) {
		Blog blog = new Blog();
		blog = null;
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT TITLE, TEXT, IMG FROM BLOGS WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs);
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				String img = rs.getString("IMG");
				blog = new Blog(id, title, text, img);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return blog;
		}
		return blog;
	}

	// ブログ取得処理（ページネーション実装）
	// currentPage: 現在のページ、 itemsPerPage: 1ページ分の表示件数
	public  List<Blog> findByPage(int loginUserId, long currentPage, int itemsPerPage) {
		List<Blog> blogs = new ArrayList<> ();	// 戻り値となるブログリストを格納する
		long offsetRows = (currentPage - 1) * itemsPerPage;	// 除外する行数（先頭から）
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT BLOGS.ID AS ID, BLOGS.USER_ID AS USER_ID, NAME, TITLE, TEXT, IMG, DATETIME, COUNT(GOODS_1.BLOG_ID) AS G_COUNT, GOODS_2.ID AS G_ID"
					+ " FROM BLOGS"
					+ " JOIN ACCOUNTS ON BLOGS.USER_ID = ACCOUNTS.ID"
					+ " LEFT JOIN GOODS AS GOODS_1 ON BLOGS.ID = GOODS_1.BLOG_ID"
					+ " LEFT JOIN GOODS AS GOODS_2 ON BLOGS.ID = GOODS_2.BLOG_ID AND GOODS_2.USER_ID = ?"
					+ " GROUP BY BLOGS.ID"
					+ " ORDER BY DATETIME DESC"
					+ " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, loginUserId);
			pStmt.setLong(2,offsetRows);
			pStmt.setInt(3,itemsPerPage);
			ResultSet rs = pStmt.executeQuery();	
			while(rs.next()) {
				int id = rs.getInt("ID");
				int userId = rs.getInt("USER_ID");
				String name = rs.getString("NAME");
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				String img = rs.getString("IMG");
				String datetime = rs.getString("DATETIME");
				int gCount = rs.getInt("G_COUNT");
				int gId = rs.getInt("G_ID");
				Blog blog = new Blog(id, userId, name, title, text, img, datetime, gCount, gId);
				blogs.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return blogs;
		}
		return blogs;
	}

	
	
	// ブログの総数を取得
	public long getTotal() {
		long total = 0;	// ブログの総数を格納する
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT COUNT(*) As count FROM BLOGS";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				total = rs.getLong("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return total;
		}
//		System.out.println(total);
		return total;
	}
	
	// ブログの削除処理（書いた人のみ）
	public boolean deleteBlog(int blogId) {
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// INSERT文の準備
			String sql = "DELETE FROM BLOGS WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, blogId);
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
	
	// ブログの編集処理
	public boolean updateBlog(Blog blog) {
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// UPDATE文の準備
			String sql = "UPDATE BLOGS SET TITLE = ?, TEXT = ?, IMG = ? WHERE ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, blog.getTitle());
			pStmt.setString(2, blog.getText());
			pStmt.setString(3, blog.getImg());
			pStmt.setInt(4, blog.getId());
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
