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
import model.GoodLogic;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		Htmlspecialchars h = new Htmlspecialchars();
		int blogId = Integer.parseInt(h.escape(request.getParameter("id")));
		String paramPage = h.escape(request.getParameter("page"));
		
		// 該当ブログの「いいね」を削除
		GoodLogic bo_g = new GoodLogic();
		boolean res_g = bo_g.executeDeleteGoodByBlogId(blogId);
		if(res_g) {
			BlogLogic bo = new BlogLogic();
			boolean result = bo.executeDelete(blogId);
			if(result) {
				// リダイレクト
				response.sendRedirect("WelcomeServlet?page=" + paramPage);
			} else {
				System.out.println("該当ブログの削除に失敗しました");
				// 失敗時はフォワードで戻る
				request.setAttribute("ErrMsg", "投稿の削除に失敗しました");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/blog.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			System.out.println("該当ブログの「いいね」を削除できませんでした");
			request.setAttribute("ErrMsg", "投稿の削除に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/blog.jsp");
			dispatcher.forward(request, response);
		}
	}
}
