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

import model.Blog;
import model.BlogLogic;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 色々な変数
//		int itemsPerPage = 10;	// 1ページあたりの表示件数  
//		long currentPage = 1; 	// 現在のページ
//		long maxBlogLength;	// ブログの総件数
//		long maxPage = maxBlogLength / itemsPerPage;
//		boolean isExistPrePage;		// 前のページが存在するか
//		boolean isExistNextPage;	// 次のページが存在するか
//		List<Integer> pageNumList;	// ナビゲーションの	ページリンクリスト
		
		
		// ブログ一覧を取得
		BlogLogic bo = new BlogLogic();
		List<Blog> blogList = bo.executeFind();
		ServletContext application = this.getServletContext();
		application.setAttribute("blogList", blogList);
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/blog.jsp");
		dispatcher.forward(request, response);
	}

}
