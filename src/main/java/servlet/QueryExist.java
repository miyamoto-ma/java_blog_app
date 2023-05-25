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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.SignLogic;

@WebServlet("/QueryExist")
public class QueryExist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 送信されたJSONの取得
		BufferedReader buffer = new BufferedReader(request.getReader());
		String jsonText = buffer.readLine();
		
		// JSONをオブジェクトに変換し、データを取り出す
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> reqMap = mapper.readValue(jsonText, new TypeReference<Map<String, String>>(){});
		String inputName = reqMap.get("inputName");
		System.out.println(inputName);
		
		// 取得したデータをデータベースに渡して処理
		SignLogic bo = new SignLogic();
		boolean exist = bo.executeQueryExist(inputName);
		
		// 返信用のマップデータを作成
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("exist", exist);
		
		// オブジェクトをJSON形式に変換
		String resJson = mapper.writeValueAsString(resMap);
		
		// ヘッダなどをセット
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		
		// JSONを返す
		PrintWriter out = response.getWriter();
		out.print(resJson);
		
	}

}
