package model;

import dao.AccountDAO;

public class SignLogic {
	// サインイン
	public boolean executeAddUser(Account account) {
		AccountDAO dao = new AccountDAO();
		boolean result = dao.addUser(account);
		return result;
	}
	
	// ユーザーがすでに存在するかどうか（存在する場合true, 存在しない場合false）
	public boolean executeQueryExist(String name) {
		AccountDAO dao = new AccountDAO();
		boolean result = dao.queryExist(name);
		return result;
	}
	
	// サインアウト
	public boolean executeDeleteUser(Account account) {
		AccountDAO dao = new AccountDAO();
		boolean result = dao.deleteUser(account);
		return result;
	}
}
