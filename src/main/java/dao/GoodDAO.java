package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Good;

public class GoodDAO {
	Db_conf dbConf = new Db_conf();
	String jdbcUrl = dbConf.getJDBC_URL();
	String dbUser = dbConf.getDB_USER();
	String dbPass = dbConf.getDB_PASS();
	
	// 「いいね」の追加/削除処理
	public String toggleGood(Good good) {
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		String result;
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			// まず、データの存在確認
			String sql_select = "SELECT ID FROM GOODS WHERE BLOG_ID = ? AND USER_ID = ?";
			PreparedStatement pStmt_select = conn.prepareStatement(sql_select);
			pStmt_select.setInt(1, good.getBlogId());
			pStmt_select.setInt(2, good.getUserId());
			ResultSet rs = pStmt_select.executeQuery();
			if(rs.next()) {
				// データがすでに存在する場合は削除
				int id = rs.getInt("ID");
				String sql_delete = "DELETE FROM GOODS WHERE ID = ?";
				PreparedStatement pStmt_delete = conn.prepareStatement(sql_delete);
				pStmt_delete.setInt(1, id);
				int result_delete = pStmt_delete.executeUpdate();
				if(result_delete != 1) {
					result = "削除に失敗しました";
					return result;
				}
				result = "deleteOK";
				
			} else {
				// データが存在しない場合は追加
				String sql_add = "INSERT INTO GOODS (BLOG_ID, USER_ID) VALUES (?, ?)";
				PreparedStatement pStmt_add = conn.prepareStatement(sql_add);
				pStmt_add.setInt(1, good.getBlogId());
				pStmt_add.setInt(2, good.getUserId());
				int result_add = pStmt_add.executeUpdate();
				if(result_add != 1) {
					result = "追加に失敗しました";
					return result;
				}
				result = "addOK";
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return "データを更新出来ませんでした";
		}
	}

	
	// 「いいね」の総数取得
	public int goodCount(int blogId) {
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		int result = 0;
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			String sql = "SELECT COUNT(*) AS count FROM GOODS WHERE BLOG_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, blogId);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	// 特定のBLOG_IDのいいねを削除（該当ブログ削除前に実行）
	public boolean deleteBlogId(int blogId) {
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			// 該当BLOG_IDのデータが存在するか確認
			String sql_conf = "SELECT * FROM GOODS WHERE BLOG_ID = ?";
			PreparedStatement pStmt_conf = conn.prepareStatement(sql_conf);
			pStmt_conf.setInt(1, blogId);
			ResultSet rs = pStmt_conf.executeQuery();
			if(rs.next()) {
				// GOODSテーブルにデータがあるので、そのデータを削除
				String sql_delete = "DELETE FROM GOODS WHERE BLOG_ID = ?";
				PreparedStatement pStmt_delete = conn.prepareStatement(sql_delete);
				pStmt_delete.setInt(1, blogId);
				pStmt_delete.executeUpdate();
			}		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// 特定のUSER_IDのいいねを削除（該当ユーザー削除前に実行）
	public boolean deleteUserId(int userId) {
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			// 該当USER_IDのデータが存在するか確認
			String sql_conf = "SELECT * FROM GOODS WHERE USER_ID = ?";
			PreparedStatement pStmt_conf = conn.prepareStatement(sql_conf);
			pStmt_conf.setInt(1, userId);
			ResultSet rs = pStmt_conf.executeQuery();
			if(rs.next()) {
				// GOODSテーブルにデータがあるので、そのデータを削除
				String sql_delete = "DELETE FROM GOODS WHERE USER_ID = ?";
				PreparedStatement pStmt_delete = conn.prepareStatement(sql_delete);
				pStmt_delete.setInt(1, userId);
				pStmt_delete.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
