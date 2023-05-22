package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		System.out.println(reqMap.get("str1"));
		System.out.println(reqMap.get("str2"));
		// 戻り値用のオブジェクト作成
		Map<String, String> map = new HashMap<>();
		map.put("A", "あいう");
		map.put("B", "えお");
		
		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("str", reqMap.get("str1") + reqMap.get("str2") + "が送信されました");
		resMap.put("ret", "true");
		resMap.put("map", map);
		resMap.put("ary", list);
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
		
		
//		String test = request.getParameter("test");
//		System.out.println(test);
//		request.setAttribute("testResult", "OK");
//		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/blog.jsp");
//		dispatcher.forward(request, response);
	}

}
