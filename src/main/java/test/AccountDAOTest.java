package test;

import dao.AccountDAO;
import model.Account;

public class AccountDAOTest {

	public static void main(String[] args) {
		testFindByAccountOK();		// ユーザーが見つかる場合のテスト
		testFindByAccountNG();		// ユーザーが見つからない場合のテスト
		testAddUserOK();				// ユーザーの追加成功のテスト
		testAddUserNG();				// ユーザーの追加失敗のテスト
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
	
	public static void testAddUserOK() {
		// 登録されていない名前のユーザーを確認しておく
		Account account = new Account("testAdd1", "testAdd1");
		AccountDAO dao = new AccountDAO();
		boolean result = dao.addUser(account);
		if(result) {
			System.out.println("testAddUserOK: 成功しました");
		} else {
			System.out.println("testAddUserOK: 失敗しました");
		}
	}
	
	public static void testAddUserNG() {
		// 登録されている名前のユーザーを確認しておく
		Account account = new Account("test", "test");
		AccountDAO dao = new AccountDAO();
		boolean result = dao.addUser(account);
		if(!result) {
			System.out.println("testAddUserNG: 成功しました");
		} else {
			System.out.println("testAddUserNG: 失敗しました");
		}
	}
}
