<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBlogPage - ログイン画面 -</title>
<link rel="stylesheet" type="text/css" href="/blog/css/destyle.min.css">
<link rel="stylesheet" type="text/css" href="/blog/css/login.css">
</head>
<body>
<main class="l_main">
<h1 class="l_title">ログインフォーム</h1>
<c:if test="${param.ErrLogin == true}">
	<p class="err_msg"><c:out value="※ログインに失敗しました" /></p>
</c:if>
<form class="l_form" action="LoginServlet" method="post">
ユーザー名：<input class="l_name input" type="text" name="name" required maxlength="20" value="${loginUser.name}"><br>
パスワード：<input class="l_pass input" type="password" name="pass" required maxlength="20"><br>
<div class="l_button_set">
	<div class="l_button button"><input type="submit" value="ログイン"></div>
	<div class="l_button button"><input type="reset" value="クリア"></div>
	<a class="l_button button" href="WelcomeServlet">戻る</a>
</div>
</form>
</main>
</body>
</html>