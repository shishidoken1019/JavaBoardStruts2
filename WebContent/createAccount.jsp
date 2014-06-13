<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>アカウント作成画面</title>
<link rel="stylesheet" href="./css\reset.css" type="text/css" />
<link rel="stylesheet" href="./css\basic.css" type="text/css" />
<style type="text/css">
dl {
	width: 900px;
	float: left;
}

dd {
	padding: 0 0 20px 0;
}

</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h1>アカウント作成</h1>
	<s:form cssClass="form-horizontal" enctype="multipart/form-data">
	
			<p class="erorr"><s:property value="errorMsg" /></p>
		
		<P>半角文字のみ登録できますぅぅぅぅぅほんとにぃぃぃ</P>
		<p>
			ログイン名
			<s:textfield name="loginName" type="loginName"
				cssClass="form-control" />
		</p>
		<p>
			パスワード
			<s:textfield name="password" type="password" cssClass="form-control" />
		</p>
		<p>
			プロフィール画像選択(jpg,gif,jpeg,png)
			<s:textfield type="file" name="loginImage" />
			<s:submit method="create" value="アカウント作成" />
		</p>
	</s:form>
</body>
</html>