package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Good;

public class GoodDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/blog";
	private final String DB_USER = "manabu";
	private final String DB_PASS = "Mpa0515";
	
	// 「いいね」の追加/削除処理
	public String toggleGood(Good good) {
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		String result;
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
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
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
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
}
