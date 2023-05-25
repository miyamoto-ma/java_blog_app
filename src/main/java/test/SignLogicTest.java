package test;

import model.Account;
import model.SignLogic;

public class SignLogicTest {
	public static void main(String[] args) {
//		testExecuteAddUserOK();			// ユーザー登録成功のテスト
//		testExecuteAddUserNG();			// ユーザー登録失敗のテスト
		testExecuteQueryExistOK();		// (存在する)ユーザー存在の確認のテスト
		testExecuteQueryExistNG();		// (存在しない)ユーザー存在の確認のテスト
	}
		
	public static void testExecuteAddUserOK() {
		// 存在しないユーザーを確認しておく
		Account account = new Account("testEAdd1", "testEAdd1");
		SignLogic bo = new SignLogic();
		boolean result = bo.executeAddUser(account);
		if(result) {
			System.out.println("testExecuteAddUserOK: 成功しました");
		} else {
			System.out.println("testExecuteAddUserOK: 失敗しました");
		}
	}
	
	public static void testExecuteAddUserNG() {
		// 存在するユーザーを確認しておく
		Account account = new Account("test", "test");
		SignLogic bo = new SignLogic();
		boolean result = bo.executeAddUser(account);
		if(!result) {
			System.out.println("testExecuteAddUserNG: 成功しました");
		} else {
			System.out.println("testExecuteAddUserNG: 失敗しました");
		}
	}
	
	public static void testExecuteQueryExistOK() {
		// 存在するユーザー名を確認しておく
		String name = "test";
		SignLogic bo = new SignLogic();
		boolean result = bo.executeQueryExist(name);
		if(result) {
			System.out.println("testExecuteQueryExistOK: 成功しました");
		} else {
			System.out.println("testExecuteQueryExistOK: 失敗しました");
		}
	}
	
	public static void testExecuteQueryExistNG() {
		// 存在しないユーザー名を確認しておく
		String name = "test2";
		SignLogic bo = new SignLogic();
		boolean result = bo.executeQueryExist(name);
		if(!result) {
			System.out.println("testExecuteQueryExistNG: 成功しました");
		} else {
			System.out.println("testExecuteQueryExistNG: 失敗しました");
		}
	}
}
