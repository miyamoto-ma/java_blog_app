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

@WebServlet("/PostingServlet")
public class PostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account loginUser = (Account)session.getAttribute("loginUser");
		if(loginUser != null) {
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/writing.jsp");
			dispatcher.forward(request, response);
		} else {
			// フォワード
			request.setAttribute("ErrMsg", "ログインし直してください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomeServlet");
			dispatcher.forward(request, response);
		}
	}

}
