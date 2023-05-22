<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBlogPage</title>
<link rel="stylesheet" type="text/css" href="/blog/css/destyle.min.css">
<link rel="stylesheet" type="text/css" href="/blog/css/blog.css">
</head>
<body>
<header class="header" >
<div class="h_wrap">
<h1><a href="WelcomeServlet">MyBlogPage</a></h1>
<ul>
<li><a href="LoginServlet">ログイン</a></li>
<c:if test="${loginUser != null}">
	<li><a href="PostingServlet">投稿</a></li>
	<li><a href="LogoutServlet">ログアウト</a></li>
</c:if>
</ul>
</div>
</header>


<c:if test="${blogList != null}">
<div class="content">

<c:if test="${ErrMsg != null}">
	<p class="err_msg"><c:out value="※${ErrMsg}" /></p>
</c:if>
<c:if test="${NormalMsg != null}">
	<p class="normal_msg"><c:out value="※${NormalMsg}" /></p>
</c:if>

<ul>
<c:forEach var="blog" items="${blogList}">
	<li>
	<p class="c_title"><c:out value="${blog.title}" /></p>

	<div class="blog_wrap">
		<c:if test="${not empty blog.img}">
			<img class="c_img" src="/blog/upload/${blog.img}" />
		</c:if>
		<div id="good_${blog.id}" class="good">いいね</div><div id="good_result_${blog.id}" class="good_result"></div>
		<div class="c_text">${blog.text}</div>
			<p class="c_author_wrap">
			<span class="c_author"> 投稿者：<c:out value="${blog.name }" /></span>
			<span class="c_datetime"><c:out value="[ ${blog.datetime} ]" /></span>
			<c:if test="${blog.userId == loginUser.id}">
				<a id="update" href="UpdateServlet?id=${blog.id}&page=${paginate.currentPage}">編集</a>
				<a id="delete" href="DeleteServlet?id=${blog.id}&page=${paginate.currentPage}" onclick="return confirm('削除しますか？')">削除</a>
			</c:if>
		</p>
	</div>
	
	</li>
</c:forEach>
</ul>

<ul class="paginate">
	<li>
		<c:if test="${paginate.isExistPrePage()}">
			<a href="WelcomeServlet?page=1">top</a>
		</c:if>
	</li>
	
	<li>
		<c:if test="${paginate.isExistPrePage()}">
			<a href="WelcomeServlet?page=${paginate.currentPage - 1}">≪</a>
		</c:if>
	</li>
	
	<li>
		<c:out value="${paginate.currentPage}" /> / <c:out value="${paginate.maxPagesCount}" />
		(全<c:out value="${paginate.maxBlogsCount}" />件)
	</li>
	
	<li>
		<c:if test="${paginate.isExistNextPage()}">
			<a href="WelcomeServlet?page=${paginate.currentPage + 1}">≫</a>
		</c:if>
	</li>
	
	<li>
		<c:if test="${paginate.isExistNextPage()}">
			<a href="WelcomeServlet?page=${paginate.maxPagesCount}">last</a>
		</c:if>
	</li>

</ul>
</div>
</c:if>

<script>
	let goods = document.getElementsByClassName("good");
	let good_results = document.getElementsByClassName("good_result");
	for(let i=0; i<goods.length; i++) {
		goods[i].addEventListener("click", function() {
			let responseClone;
			fetch('EvaluationServlet', {
					method: "POST",
					body: JSON.stringify({
							str1: "test1OK",
							str2: "test2OK"
						})
					})
				.then(data => data.json())
				.then(res => {
					console.log(res.ret);
					console.log(res.str);
					console.log(res.ary);
					console.log(res.ary[0]);
					console.log(res.ary[1]);
					console.log(res.map);
					console.log(res.map.A);
					console.log(res.map.B);
					
				})
								
				;
				

		});
	}
</script>
</body>
</html>