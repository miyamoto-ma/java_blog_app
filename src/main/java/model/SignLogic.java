package model;

import dao.AccountDAO;

public class SignLogic {
	// サインイン
	public boolean executeAddUser(Account account) {
		AccountDAO dao = new AccountDAO();
		boolean result = dao.addUser(account);
		return result;
	}
}
