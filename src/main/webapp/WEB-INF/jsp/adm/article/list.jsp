<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">
		<c:foreach items="${articles}" var="article">
			<div class="flex justify-betwwen items-center mt-10">
				<span class="font-loght text-gray-600">${article.regDate}</span> <a
					href="list?boardId=${article.boardId}"
					class="text-2xl text-gray-700 font-bold hover: underline">${article.boardId == 1 ? "공지" : "자유"}</a>
			</div>
			<div class="mt-2">
				<a href="detail?id=${article.id}"
					class="text-2xl text-gray-700 font-bold hover:underline">${article.title}</a>
				<p class="mt-2 text-gray-600">${article.body}</p>
			</div>
			<div class="flex justify-between items-center mt-4">
				<a href="detail?id=${article.id}"
					class="text-blue-500 hover:underline">자세히 보기</a>
				<div>
					<a href="detail?id=${article.id}" class="flex items-center"> <img
						src="https://images.unsplash.com/photo-1492562080023-ab3db95bfbce?ixlib=rb-1.2.1&amp;ixid=eyJhcHBfaWQiOjEyMDd9&amp;auto=format&amp;fit=crop&amp;w=731&amp;q=80"
						alt="avatar" class="mx-4 w-10 h-10 object-cover rounded-full" />
						<h1 class="text-gray-700 font-bold hover:underline">${article.extra__writer}</h1>
					</a>
				</div>
			</div>
		</c:foreach>
	</div>
</section>

<%@ include file="../part/mainLayoutFoot.jspf"%>