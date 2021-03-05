<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../part/mainLayoutHead.jspf"%>

<script>
ArticleAdd__submited = false;
function ArticleAdd__ckeckAndSubmit(form){
	if(ArticleAdd__submited){
		alert('처리 중입니다.');
		return;
	}
	
	form.title.value = for.title.vlaue.trim();
	
	if(form.title.value.length == 0){
		alert('제목을 입력해주세요.');
		form.title.focus();
		
		return false;
	}
	
	form.body.value = form.body.value.trim();
	
	if(form.body.value.length == 0){
		alert('내용을 입력해주세요.');
		form.body.focus();
		
		return false;
	}
	
	var maxSizeMb = 50;
	var maxSize = maxSizeMb * 1024 * 1024;
	if(form.file__article__0__common__attatchment__1.value){
		if(form.file__article__0__common__attachment__1.files[0].size > maxSize){
			alert(maxSizeMb + "MB 이하의 파일을 업로드 해주세요.");
			form.file__article__0__common__attachment__1.focus();
			
			return;
		}
	}
	
	if(form.file__article__0__common__attatchment__2.value){
		if(form.file__article__0__common__attachment__2.files[0].size > maxSize){
			alert(maxSizeMb + "MB 이하의 파일을 업로드 해주세요.");
			form.file__article__0__common__attachment__2.focus();
			
			return;
		}
	}
	
	const startSubmitForm = function(data){
		let genFileIdStr = '';
		
		if(data && data.body.genFileIdStr){
			genFileIdStr = data.body.genFileIdStr;
		}
		
		form.genFileIdStr.value = genFileIdStr;
		
		form.file__article__0__common__attachment__1.value = '';
		form.file__article__0__common__attachment__2.value = '';
		
		form.submit();
	};
	
	
	const startUploadFiles = function(onSuccess) {
		var needToUpload = form.file__article__0__common__attachment__1.value.length > 0;
		if (!needToUpload) {
			needToUpload = form.file__article__0__common__attachment__2.value.length > 0;
		}
		
		if (needToUpload == false) {
			onSuccess();
			return;
		}
		
		var fileUploadFormData = new FormData(form);
		
		$.ajax({
			url : '/common/genFile/doUpload',
			data : fileUploadFormData,
			processData : false,
			contentType : false,
			dataType : "json",
			type : 'POST',
			success : onSuccess
		});
	}
	ArticleAdd__submited = true;
	startUploadFiles(startSubmitForm);}
</script>

<section class="section-1">
	<div class="bg-white shadow-md rounded container mx-auto p-8 mt-8">
		<form
			onsubmit="ArticleAdd__checkAndSubmit(this); return false; action="
			doAdd" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="genFileIdStr" vlaue="" /> 
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
					<span>첨부파일 1</span>
				</div>
				<div class="lg:flex-gorw">
					<input name="file" name="file__article__0__common__attachment_1"
						class="form-row-input w-full rounded-sm" />
				</div>
			</div>

			<div class="form-row flex flex-col lg:flex-row">
				<div class="lg:flex lg:items-center lg:w-28">
					<span>첨부파일 2</span>
				</div>
				<div class="lg:flex-gorw">
					<input name="file" name="file__article__0__common__attachment_2"
						class="form-row-input w-full rounded-sm" />
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