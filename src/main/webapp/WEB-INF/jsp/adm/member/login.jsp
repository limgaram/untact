<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.0.3/tailwind.min.css" />
</head>
<body>
	<script>
		const LoginForm__checkAndSubmitDone = false;
		function LoginForm__checkAndSubmit(form) {
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
		LoginForm__checkAndSubmitDone = true;}
	</script>

	<section class="section-login">
		<div class="container mx-auto">
			<form action="doLogin" method="POST"
				onsubmit="LoginForm__checkAndSubmit(this); return false;">
				<div class=flex>
					<div class="p-4 w-36">
						<span>로그인아이디</span>
					</div>
					<div class="flex-grow p-4">
						<input class="w-full" autofocus="autofocus" type="text"
							placeholder="로그인 아이디를 입력해주세요." name="loginId" maxlength="20" />
					</div>
				</div>
				<div class="flex">
					<div class="p-4 w-36">
						<span>로그인비번</span>
					</div>
					<div class="flex-grow p-4">
						<input class="w-full" autofocus="autofocus" type="password"
							placeholder="로그인 비밀번호를 입력해주세요." name="loginPw" maxlength="20" />
					</div>
				</div>
				<div class="flex">
					<div class="p-4 w-36">
						<span>로그인</span>
					</div>
					<div class="flex-grow p-4">
						<input class="w-full" type="submit" value="로그인" />
					</div>
				</div>
			</form>
		</div>
	</section>
</body>
</html>