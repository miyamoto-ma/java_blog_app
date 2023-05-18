package test;

import model.Account;
import model.LoginLogic;

public class LoginLogicTest {
	public static void main(String[] args) {
		testExecuteOK();		// ログイン成功のテスト
		testExecuteNG();		// ログイン失敗のテスト
	}
	public static void testExecuteOK() {
		Account account = new Account("manabu", "J_BlogTest");
		LoginLogic bo = new LoginLogic();
		Account loginUser = bo.execute(account);
		if (loginUser != null) {
			System.out.println("testExecuteOK: 成功しました");
		} else {
			System.out.println("testExecuteNG: 失敗しました");
		}
	}
	public static void testExecuteNG() {
		Account account = new Account("manabu", "J_BlogTestNG");
		LoginLogic bo = new LoginLogic();
		Account loginUser = bo.execute(account);
		if(loginUser == null) {
			System.out.println("testExecuteNG: 成功しました");
		} else {
			System.out.println("testExecuteNG: 失敗しました");
		}
	}
}
