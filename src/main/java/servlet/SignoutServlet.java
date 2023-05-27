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
import model.BlogLogic;
import model.GoodLogic;
import model.SignLogic;

@WebServlet("/SignoutServlet")
public class SignoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("loginUser");
		
		if(account != null) {
			// ログイン中のユーザーID
			int userId = account.getId();
			
			// ユーザー削除前にそのユーザーの「いいね」を削除
			GoodLogic bo_g = new GoodLogic();
			boolean res_good = bo_g.executeDeleteGoodByUserId(userId);
			
			boolean res_blog = false;
			if(res_good) {				
				// ユーザー削除前にそのユーザーの投稿ブログを削除
				BlogLogic bo_blog = new BlogLogic();
				res_blog = bo_blog.executeDeleteUserId(userId);	
				if(!res_blog) {
					System.out.println("該当ユーザーのブログの削除に失敗しました");
				}
			} else {
				System.out.println("該当ユーザーの「いいね」の削除に失敗しました");
			}
			
			if(res_good && res_blog) {
				// ユーザーを削除
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
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WelcomeServlet");
		dispatcher.forward(request, response);
	}

}
