package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Function.Htmlspecialchars;
import model.Account;
import model.Blog;
import model.BlogLogic;

@WebServlet("/BlogServlet")
@MultipartConfig(location="C:\\java_temp")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Account loginUser = (Account)session.getAttribute("loginUser");
		int userId = loginUser.getId();

		String title = request.getParameter("title");
		String text = request.getParameter("text");
		Htmlspecialchars h = new Htmlspecialchars();
		if(title != null) { title =  h.escape(title); }
		if(text != null) { text =  h.escape(text); }
		// name属性がimgのファイルをPartオブジェクトとして取得
		Part img = request.getPart("img");

		System.out.println(img.getSize());
		String filename = null;
		if(img.getSize() != 0) {
			// ファイル名を取得
			filename = Paths.get(img.getSubmittedFileName()).getFileName().toString();
			// アップロードするフォルダ
			String path = getServletContext().getRealPath("/upload");
			// 実際にファイルが保存されるパス確認
			System.out.println(path);
			System.out.println(filename);
			System.out.println(img.getSize());
			System.out.println(img.getContentType());
			// 書き込み
			img.write(path + File.separator + filename);
		}
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String datetime = dtf.format(now);
		
		Blog blog = new Blog(userId, title, text, filename, datetime);
		BlogLogic bo = new BlogLogic();
		boolean result = bo.executeAdd(blog);
		
		// ブログの追加の成否によって処理を分岐
		if (result) {
			// リダイレクト
			response.sendRedirect("WelcomeServlet");
		} else {
			// フォワード
			request.setAttribute("ErrMsg", "ブログの投稿に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("LoginServlet");
			dispatcher.forward(request, response);
		}
	}

}
