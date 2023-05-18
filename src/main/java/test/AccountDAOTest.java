package test;

import dao.AccountDAO;
import model.Account;

public class AccountDAOTest {

	public static void main(String[] args) {
		testFindByAccountOK();		// ユーザーが見つかる場合のテスト
		testFindByAccountNG();		// ユーザーが見つからない場合のテスト
	}
	public static void testFindByAccountOK() {
		Account account = new Account("manabu", "J_BlogTest");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByAccount(account);
		if(result != null &&
				result.getName().equals("manabu") &&
				result.getPass().equals("J_BlogTest")) {
			System.out.println("testFindByAccountOK: 成功しました");
		} else {
			System.out.println("testFindByAccountOK: 失敗しました");
		}
	}
	public static void testFindByAccountNG() {
		Account account = new Account("manabu", "J_BlogTestNG");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByAccount(account);
		if(result == null) {
			System.out.println("testFindByAccountNG: 成功しました");
		} else {
			System.out.println("testFindByAccountNG: 失敗しました");
		}
	}
}
