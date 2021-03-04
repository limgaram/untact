<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">
		<form action="doAdd" method="POST">
			<input type="hidden" name="boardId" value="${param.boardId}" />

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>제목</span>
				</div>
				<div class="lg:flex-gorw">
					<input type="text" name="title" autofocus="autofocus"
						class="form-row-input w-full rounded-sm" placeholder="제목을 입력해주세요." />
				</div>
			</div>

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>내용</span>
				</div>
				<div class="lg:flex-gorw">
					<input name="body" class="form-row-input w-full rounded-sm"
						placeholder="내용을 입력해주세요." />
				</div>
			</div>

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>작성</span>
				</div>
				<div class="lg:flex-gorw">
					<div class="btns">
						<input type="submit"
							class="btn-primary bg-blue-500 hover:bg-blue-dark text-white font-bold py-2 px-4 rounded"
							value="작성" /> <input onclick="history.back();" type="button"
							class="btn-info bg-red-500 hover:bg-red-darktext-white font-bold py-2 px-4 rounded"
							value="취소" />
					</div>
				</div>
			</div>


		</form>
	</div>
</section>

<%@ include file="../part/mainLayoutFoot.jspf"%>