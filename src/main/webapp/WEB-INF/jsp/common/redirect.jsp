<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	var historyBack = '${historyBack}' == 'true';
	var msg = '${msg}'.trim();
	if (msg) {
		alert(msg);
	}
	if (historyBack) {
		history.back();
	}

	var redirectUrl = '${redirectUrl}'.trim();

	if (redorectUrl) {
		location.replace(redirectUrl);
	}
</script>