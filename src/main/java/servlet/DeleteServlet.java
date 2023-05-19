package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Function.Htmlspecialchars;
import model.BlogLogic;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Htmlspecialchars h = new Htmlspecialchars();
		int id = Integer.parseInt(h.escape(request.getParameter("id")));
		BlogLogic bo = new BlogLogic();
		boolean result = bo.executeDelete(id);
		
		String paramPage =request.getParameter("page");
		if(result) {
			// リダイレクト
				response.sendRedirect("WelcomeServlet?page=" + paramPage);
		} else {
			request.setAttribute("ErrDelete", "投稿の削除に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/blog.jsp");
			dispatcher.forward(request, response);
		}
	}

}
