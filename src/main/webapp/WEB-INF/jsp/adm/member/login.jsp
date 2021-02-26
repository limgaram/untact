<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../part/head.jspf"%>

<script>
	const LoginForm__checkAndSubmitDone = false;
	function LoginForm__checkAndSubmit(form) {
		if (LoginForm__checkAndSubmitDone) {
			return;
		}
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('로그인아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
		if (form.loginPw.value.length == 0) {
			alert('로그인비번을 입력해주세요.');
			form.loginPw.focus();
			return;
		}
		form.submit();
		LoginForm__checkAndSubmitDone = true;
	}
</script>
<section class="section-login h-screen">
	<div class="container mx-auto h-full flex items-center justify-center">
		<form class="bg-white w-full shadow-md rounded px-8 pt-6 pb-8 mb-4 "
			action="doLogin" method="POST"
			onsubmit="LoginForm__checkAndSubmit(this); return false;">
			<div class="flex">
				<div class="p-4 w-36">
					<span>로그인아이디</span>
				</div>
				<div class="flex-grow p-4">
					<input
						class="shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker"
						autofocus="autofocus" type="text" placeholder="로그인 아이디를 입력해주세요."
						name="loginId" maxlength="20" />
				</div>
			</div>
			<div class="flex">
				<div class="p-4 w-36">
					<span>로그인비번</span>
				</div>
				<div class="flex-grow p-4">
					<input
						class="shadow appearance-none border border-red rounded w-full py-2 px-3 text-grey-darker"
						autofocus="autofocus" type="password"
						placeholder="로그인 비밀번호를 입력해주세요." name="loginPw" maxlength="20" />
				</div>
			</div>
			<div class="flex">
				<div class="p-4 w-36">
					<span>로그인</span>
				</div>
				<div class="flex-grow p-4">
					<input
						class="bg-blue-500 hover:bg-blue-dark text-white font-bold py-2 px-4 rounded"
						type="submit" value="로그인" />
				</div>
			</div>
		</form>
	</div>
</section>

<%@ include file="../part/foot.jspf"%>



