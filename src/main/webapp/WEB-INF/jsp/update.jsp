<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Blog" %>
<%
Blog blog = (Blog)session.getAttribute("currentBlog");
String text = blog.getText();
String[] escape = {"<br />", "<br/>", "<br>"};
for (int i=0; i<escape.length; i++) {	
	text = text.replaceAll(escape[i], "\n");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBlogPage - 投稿編集 -</title>
<link rel="stylesheet" type="text/css" href="/blog/css/destyle.min.css">
<link rel="stylesheet" type="text/css" href="/blog/css/writingAndUpdate.css">
</head>
<body>
<main class="p_main">
	<h1 class="p_h1">編集フォーム</h1>
	<div class="p_welcome">
		<p>ようこそ<c:out value="${loginUser.name}" />さん</p>
		<a href="WelcomeServlet?page=${param.page}">ブログ一覧に戻る</a>
	</div>

	<c:if test="${ErrMsg != null}">
		<p class="p_err"><c:out value="※${ErrMsg}" /></p>
	</c:if>
	
	<form class="p_form" action="UpdateServlet?id=${param.id}&page=${param.page}" method="post" enctype="multipart/form-data">
		<div class="p_span_wrap">
			<span class="p_span">タイトル(100文字以内)：</span><p><span id="char_title">0</span>/100</p>
		</div>
		<input id="p_title" class="p_title input" type="text" name="title" required maxlength="100" value="${currentBlog.title}"><br>
		<div class="p_span_wrap">
			<span class="p_span">内容(400文字以内)：</span><p><span id="char_text">0</span>/400</p>
		</div>
		<textarea id="p_text" class="p_text input" name="text" required maxlength="400"><%=text %></textarea><br>
		<div class="p_img">
			<span class="p_span">画像(任意)：</span>

			<label class="p_button button_f">
				ファイル選択
				<input id="file" type="file" name="img" accept=".jpg, .jpeg, .gif">
			</label>
			<span class="img_ctn">※.jpg, .jpeg, .gif画像のみ(1MB以内)</span>						
		</div>
			<label class="p_change">
				<input type="checkbox" name="changeImg" value="true" />
				画像を変更する
			</label>
			<input type="hidden" name="currentImg" value="${currentBlog.img}" />
			
			<div class="preview">
				<div class="current_prev">
					現在の画像：
					<c:if test="${not empty currentBlog.img}">
					<img src="/blog/upload/${currentBlog.img}"  />
					</c:if>
				</div>
				<div class="new_prev">
					変更後の画像：
					<img id="new_img">
				</div>
			</div>

		<div class="p_button_set">
			<div class="p_button button"><input type="submit" value="投稿"></div>
			<div class="p_button button"><input type="reset" value="クリア"></div>
		</div>
	</form>
</main>

<script src="/blog/js/writingAndUpdate.js"></script>
</body>
</html>