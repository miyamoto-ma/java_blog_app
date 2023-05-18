package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// ログイン処理を実行
		Account account = new Account(name, pass);
		LoginLogic bo = new LoginLogic();
		Account loginUser = bo.execute(account);
		
		// ログイン処理の成否によって処理を分岐
		if(loginUser != null) {
			// セッションスコープにユーザー名を保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
		
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/writing.jsp");
			dispatcher.forward(request, response);
		} else {
			// リダイレクト
			response.sendRedirect("LoginServlet");
		}
	}
}
