package model;

import dao.AccountDAO;

public class LoginLogic {
	public Account execute(Account account) {
		AccountDAO dao = new AccountDAO();
		Account loginUser = dao.findByAccount(account);
		return loginUser;
	}
}
