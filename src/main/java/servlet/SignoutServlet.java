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
import model.SignLogic;

@WebServlet("/SignoutServlet")
public class SignoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("loginUser");
		
		if(account != null) {
			SignLogic bo = new SignLogic();
			boolean result = bo.executeDeleteUser(account);
			System.out.println(result);
			if(result) {
				request.setAttribute("ResultDel", "true");
				session.invalidate();
			} else {
				request.setAttribute("ResultDel", "false");
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomeServlet");
		dispatcher.forward(request, response);
	}

}
