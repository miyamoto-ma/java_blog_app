package test;

import model.Good;
import model.GoodLogic;

public class GoodLogicTest {
	public static void main(String[] args) {
		testExecuteToggleOK();			// 「いいね」追加/削除成功のテスト
		testExecuteToggleNG();			// 「いいね」追加/削除失敗のテスト
		testExecuteCountOK();				// 「いいね」の数取得成功のテスト（1投稿分）
		testExecuteCountNG();				// 「いいね」の数取得失敗のテスト（1投稿分）
	}
	
	public static void testExecuteToggleOK() {
		// 存在するBlogsのIDとUsersのIDを調べておく
		Good good = new Good(12, 1);
		GoodLogic bo = new GoodLogic();
		String result = bo.executeToggle(good);
		if(result == "deleteOK" || result == "addOK") {
			System.out.println("testExecuteToggleOK: 成功しました");
		} else {
			System.out.println("testExecuteToggleOK: 失敗しました");
		}
		System.out.println(result);
	}
	
	public static void testExecuteToggleNG() {
		// 存在しないGoodsを調べておく。
		Good good = new Good(1000, 100);
		GoodLogic bo = new GoodLogic();
		String result = bo.executeToggle(good);
		if(result != "deleteOK" && result != "addOK") {
			System.out.println("testExecuteToggleNG: 成功しました");
		} else {
			System.out.println("testExecuteToggleNG: 失敗しました");
		}
		System.out.println(result);
	}
	
	public static void testExecuteCountOK() {
		// 存在するGoodsのBLOG_IDを調べておく
		GoodLogic bo = new GoodLogic();
		int result = bo.executeCount(10);
		if(result != 0) {
			System.out.println("testExecuteCountOK: 成功しました");
		} else {
			System.out.println("testExecuteCountOK: 失敗しました");;
		}
		System.out.println(result);
	}
	
	public static void testExecuteCountNG() {
		// 存在しないGoodsのBLOG_IDを調べておく
		GoodLogic bo = new GoodLogic();
		int result = bo.executeCount(1000);
		if(result == 0) {
			System.out.println("testExecuteCountNG: 成功しました");
		} else {
			System.out.println("testExecuteCountNG: 失敗しました");;
		}
		System.out.println(result);
	}
}
