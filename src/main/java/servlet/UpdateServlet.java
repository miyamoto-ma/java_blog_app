package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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
import model.Blog;
import model.BlogLogic;

@WebServlet("/UpdateServlet")
@MultipartConfig(location="C:\\java_temp")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Htmlspecialchars h = new Htmlspecialchars();
		int id = Integer.parseInt(h.escape(request.getParameter("id")));
		BlogLogic bo = new BlogLogic();
		Blog blog = bo.executeFindById(id);
		HttpSession session = request.getSession();
		session.setAttribute("currentBlog", blog);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/update.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		Htmlspecialchars h = new Htmlspecialchars();
		int id = Integer.parseInt(h.escape(request.getParameter("id")));
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		if(title != null) { title =  h.escape(title); }
		if(text != null) { text =  h.escape(text); }
		String filename = null;
		String changeImg = request.getParameter("changeImg");
		if(changeImg != null && changeImg.equals("true")) {	// 画像を変更する場合
			// name属性がimgのファイルをPartオブジェクトとして取得
			Part img = request.getPart("img");
	//		System.out.println(img.getSize());
			if(img.getSize() != 0) {
				// ファイル名を取得
				filename = Paths.get(img.getSubmittedFileName()).getFileName().toString();
				// アップロードするフォルダ
				String path = getServletContext().getRealPath("/upload");
				// 実際にファイルが保存されるパス確認
				System.out.println(path);
	//			System.out.println(filename);
	//			System.out.println(img.getSize());
	//			System.out.println(img.getContentType());
				// 書き込み
				img.write(path + File.separator + filename);
			}
		} else {
			filename = request.getParameter("currentImg");
		}
		Blog blog = new Blog(id, title, text, filename);
		BlogLogic bo = new BlogLogic();
		boolean result = bo.executeUpdate(blog);
		
		String paramPage =request.getParameter("page");
		// ブログの追加の成否によって処理を分岐
		if (result) {
			// リダイレクト
			response.sendRedirect("WelcomeServlet?page=" + paramPage);
		} else {
			// フォワード
			request.setAttribute("ErrMsg", "ブログの編集に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/update.jsp");
			dispatcher.forward(request, response);
		}
	}

}
