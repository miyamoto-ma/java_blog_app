package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Function.Htmlspecialchars;
import model.Blog;
import model.BlogLogic;
import model.Paginate;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 色々な変数
		final int itemsPerPage = 10;	// 1ページあたりの表示件数  
		long currentPage = 1; 			// 現在のページ
		long maxBlogsCount;				// ブログの総件数
		long maxPagesCount;				// ページの総数
		boolean isExistPrePage;			// 前のページが存在するか
		boolean isExistNextPage;		// 次のページが存在するか
				
		// ブログ総件数の取得
		BlogLogic bo = new BlogLogic();
		maxBlogsCount = bo.executeGetTotal();
		
		// ページの総数を取得
		maxPagesCount = (maxBlogsCount / itemsPerPage) + 1;
		
		// 現在のページを取得
		String paramPage =request.getParameter("page");	// URLから現在ページを取得
		if(paramPage != null) {
			Htmlspecialchars h = new Htmlspecialchars();
			paramPage = h.escape(paramPage);
			long paramPageLong = Long.parseLong(paramPage);	// 上記をlong型に変換
			if(paramPage.length() > 0 && paramPageLong > 0 && paramPageLong <= maxPagesCount) {
				currentPage = paramPageLong;
			}
		}
		
		// 前のページが存在するか
		isExistPrePage = currentPage > 1;
		
		// 次のページが存在するか
		isExistNextPage = currentPage < maxPagesCount;

		// JavaBeansにセット
		Paginate paginate = new Paginate(itemsPerPage, currentPage, maxBlogsCount, maxPagesCount, isExistPrePage, isExistNextPage);
		HttpSession session = request.getSession();
		session.setAttribute("paginate", paginate);	
		// ブログ一覧を取得
		List<Blog> blogList = bo.executeFindByPage(currentPage, itemsPerPage);
		ServletContext application = this.getServletContext();
		application.setAttribute("blogList", blogList);
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/blog.jsp?page=" + currentPage);
		dispatcher.forward(request, response);
	}
}
