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
		
		<div class="c_text">${blog.text}</div>
		
		<div class="good_author_wrap">
			<div>
				<div id="good_${blog.id}" data-blog-id="${blog.id}" data-user-id="${loginUser.id}" class="good">
					<!--?xml version="1.0" encoding="utf-8"?-->
					<!-- Generator: Adobe Illustrator 18.1.1, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
					
					<svg version="1.1" id="_x32_" class="heart" data-blog-id="${blog.id}" data-user-id="${loginUser.id}" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 512 512" style="width: 256px; height: 256px; opacity: 1;" xml:space="preserve">
					<style type="text/css">
						.st0{fill:#ff8eae;}
					</style>
					<g>
						<path id="heart_path" class="st0" data-blog-id="${blog.id}" data-user-id="${loginUser.id}" d="M380.63,32.196C302.639,33.698,264.47,88.893,256,139.075c-8.47-50.182-46.638-105.378-124.63-106.879
							C59.462,30.814,0,86.128,0,187.076c0,129.588,146.582,189.45,246.817,286.25c3.489,3.371,2.668,3.284,2.668,3.284
							c1.647,2.031,4.014,3.208,6.504,3.208v0.011c0,0,0.006,0,0.011,0c0,0,0.006,0,0.011,0v-0.011c2.489,0,4.856-1.177,6.503-3.208
							c0,0-0.821,0.086,2.669-3.284C365.418,376.526,512,316.664,512,187.076C512,86.128,452.538,30.814,380.63,32.196z" style="fill: rgb(75, 75, 75);"></path>
					</g>
					</svg>		
					いいね！
				</div>
				<p id="good_result_${blog.id}" class="good_result">0</p>
			</div>
		
			<p class="c_author_wrap">
				<span class="c_author"> 投稿者：<c:out value="${blog.name }" /></span>
				<span class="c_datetime"><c:out value="[ ${blog.datetime} ]" /></span>
				<c:if test="${blog.userId == loginUser.id}">
					<a id="update" href="UpdateServlet?id=${blog.id}&page=${paginate.currentPage}">編集</a>
					<a id="delete" href="DeleteServlet?id=${blog.id}&page=${paginate.currentPage}" onclick="return confirm('削除しますか？')">削除</a>
				</c:if>
			</p>
		</div>	
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
		goods[i].addEventListener("click", function(e) {
			if(e.target.dataset.userId != "" && e.target.dataset.userId >= 1){
			fetch('EvaluationServlet', {
					method: "POST",
					body: JSON.stringify({
							blogId: e.target.dataset.blogId
						})
					})
				.then(data => data.json())
				.then(res => {
					good_results[i].textContent = res.count;
					let str = res.result;
					if(str == "addOK") {
						goods[i].classList.add("active");
					} else if(str == "deleteOK") {
						goods[i].classList.remove("active");
					} else {
						alert(str);
					}
					
				})
				.catch(err => {
					alert("通信に失敗しました。");
				});
			} else {
				alert("ログインしてください。");
			}
				

		});
	}
</script>
</body>
</html>