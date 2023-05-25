package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Function.Htmlspecialchars;
import model.Account;
import model.SignLogic;

@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/signin.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		Htmlspecialchars h = new Htmlspecialchars();
		if(name != null) { name = h.escape(name); }
		if(pass != null) { pass = h.escape(pass); }
		
		Account user = new Account(name, pass);
		SignLogic bo = new SignLogic();
		boolean result = bo.executeAddUser(user);
		
		if(result) {
			// サインインが成功ならそのままログイン処理へ
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginServlet");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("SigninServlet" + "?ErrSignin=true");
		}
	}
}
