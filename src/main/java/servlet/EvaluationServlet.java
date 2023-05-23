package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Account;
import model.Good;
import model.GoodLogic;

@WebServlet("/EvaluationServlet")
public class EvaluationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 送信されたJSONの取得
		BufferedReader buffer = new BufferedReader(request.getReader());
		String jsonText = buffer.readLine();
//		System.out.println(jsonText + "jsonText");
		// JSONをオブジェクトに変換
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> reqMap = mapper.readValue(jsonText, new TypeReference<Map<String, String>>(){});
		int blogId = Integer.valueOf(reqMap.get("blogId"));
		System.out.println(blogId);
		
		HttpSession session = request.getSession();
		Account loginUser = (Account)session.getAttribute("loginUser");
		int loginUserId = loginUser.getId();
		System.out.println(loginUserId);
		
		Good good = new Good(blogId, loginUserId);
		GoodLogic bo = new GoodLogic();
		
		// 「いいね」の追加/削除処理
		String result = bo.executeToggle(good);
		System.out.println(result);
		
		// 該当ブログの「いいね」数を取得
		int count = bo.executeCount(blogId);
		System.out.println(count);
		
		
		// 戻り値用のオブジェクト作成		
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("result", result);
		resMap.put("count", count);
		System.out.println(resMap);
		// オブジェクトをJSON文字列に変更
		String resJson = mapper.writeValueAsString(resMap);
		
		// ヘッダ情報などセット
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		
		// JSONを返す
		PrintWriter out = response.getWriter();
		out.print(resJson);
	}

}
