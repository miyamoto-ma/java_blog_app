package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class AccountDAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/blog";
	private final String DB_USER = "manabu";
	private final String DB_PASS = "Mpa0515";
	
	public Account findByAccount(Account account) {
		Account r_account = null;
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		
		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文を準備
			String sql = "SELECT ID, NAME, PASS FROM ACCOUNTS WHERE NAME = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getName());
			pStmt.setString(2, account.getPass());
			//SELECT文を実行し、結果を取得
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				r_account = new Account(id, name, pass);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return r_account;
	}
}
