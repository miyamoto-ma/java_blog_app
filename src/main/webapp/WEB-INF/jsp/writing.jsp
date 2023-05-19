<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBlogPage - 投稿画面 -</title>
<link rel="stylesheet" type="text/css" href="/blog/css/destyle.min.css">
<link rel="stylesheet" type="text/css" href="/blog/css/writing.css">
</head>
<body>
<main class="p_main">
	<h1 class="p_h1">投稿フォーム</h1>
	<div class="p_welcome">
		<p>ようこそ<c:out value="${loginUser.name}" />さん</p>
		<a href="${WelcomeServlet} ">ブログ一覧へ</a>
	</div>

	<c:if test="${ErrMsg != null}">
		<p class="p_err"><c:out value="※${ErrMsg}" /></p>
	</c:if>
	
	<c:if test="${ErrFile != null}">
		<p class="p_err"><c:out value="※${ErrFile}"	/></p>
	</c:if>
	
	
	<form class="p_form" action="BlogServlet" method="post" enctype="multipart/form-data">
		<div class="p_span_wrap">
			<span class="p_span">タイトル(100文字以内)：</span><p><span id="char_title">0</span>/100</p>
		</div>
		<input id="p_title" class="p_title input" type="text" name="title" required maxlength="100"><br>
		<div class="p_span_wrap">
			<span class="p_span">内容(400文字以内)：</span><p><span id="char_text">0</span>/400</p>
		</div>
		<textarea id="p_text" class="p_text input" name="text" required maxlength="400"></textarea><br>
		<div class="p_img">
			<span class="p_span">画像(任意)：</span>
			<label class="p_button button_f">
				ファイル選択
				<input id="file" type="file" name="img" accept=".jpg, .jpeg, .gif">	
			</label>
			<span class="img_ctn">※.jpg, .jpeg, .gif画像のみ(1MB以内)</span>
		</div>
		<div class="p_button_set">
			<div class="p_button button"><input type="submit" value="投稿"></div>
			<div class="p_button button"><input type="reset" value="クリア"></div>
		</div>
	</form>
</main>

<script src="/blog/js/writing.js"></script>
</body>
</html>