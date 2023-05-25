package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;

public class AccountDAO {
	Db_conf dbConf = new Db_conf();
	String jdbcUrl = dbConf.getJDBC_URL();
	String dbUser = dbConf.getDB_USER();
	String dbPass = dbConf.getDB_PASS();
	
	public Account findByAccount(Account account) {
		Account r_account = null;
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		
		// データベースへの接続
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
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
	
	public boolean addUser(Account account) {
		ReadJDBC jdbc = new ReadJDBC();
		jdbc.read();
		
		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass)) {
			String sql = "INSERT INTO ACCOUNTS (NAME, PASS) VALUES (?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, account.getName());
			pStmt.setString(2, account.getPass());
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
}
