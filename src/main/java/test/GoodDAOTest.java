package test;

import dao.GoodDAO;
import model.Good;

public class GoodDAOTest {
	public static void main(String[] args) {
		testToggleGoodOK();		// 「いいね」追加/削除成功のテスト
		testToggleGoodNG();		// 「いいね」追加/削除失敗のテスト
		
	}
	
	public static void testToggleGoodOK() {
		// 存在するユーザーとブログのIDを確認しておく
		Good good = new Good(10, 1);
		GoodDAO dao = new GoodDAO();
		String result = dao.toggleGood(good);
		if(result == "deleteOK" || result == "addOK") {
			System.out.println("testToggleGoodOK: 成功しました");
		} else {
			System.out.println("testToggleGoodOK: 失敗しました");
		}
		System.out.println(result);
	}
	
	public static void testToggleGoodNG() {
		// 存在しないユーザーかブログのIDを確認しておく
		Good good = new Good(1000, 100);
		GoodDAO dao = new GoodDAO();
		String result = dao.toggleGood(good);
		if(result != "deleteOK" && result != "addOK") {
			System.out.println("testToggleGoodNG: 成功しました");
		} else {
			System.out.println("testToggleGoodNG: 失敗しました");
		}
		System.out.println(result);
	}
}
