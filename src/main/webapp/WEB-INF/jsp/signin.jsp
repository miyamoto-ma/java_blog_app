<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBlogPage - サインイン画面 -</title>
<link rel="stylesheet" type="text/css" href="/blog/css/destyle.min.css">
<link rel="stylesheet" type="text/css" href="/blog/css/login.css">
<link rel="stylesheet" type="text/css" href="/blog/css/signin.css">
</head>
<body>
<main class="l_main">
<h1 class="l_title">登録フォーム</h1>
<c:if test="${param.ErrSignin == true}">
	<p class="err_msg"><c:out value="※サインインに失敗しました" /></p>
</c:if>
<form class="l_form" action="SigninServlet" method="post">
<span class="desc">ユーザー名(20文字以内 / 重複不可)</span>：<span id="caution_name" class="caution_name"></span><br>
<input id="new_name" class="l_name input" type="text" name="name" required maxlength="20"><br>
<span class="desc">パスワード(20文字以内)</span>：<br>
<input class="l_pass input" type="password" name="pass" required maxlength="20"><br>
<div class="l_button_set">
	<div class="l_button button"><input type="submit" value="登録"></div>
	<div class="l_button button"><input type="reset" value="クリア"></div>
	<a class="l_button button" href="WelcomeServlet">戻る</a>
</div>
</form>
</main>

<script src="/blog/js/signin.js"></script>
</body>
</html>