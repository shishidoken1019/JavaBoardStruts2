<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/reset.css" type="text/css" />
<link rel="stylesheet" href="./css/basic.css" type="text/css" />
</head>
<body>
	<div id="header">
	<s:form cssClass="form-horizontal">
		<ul class="header_right">
			<li><a href="<s:url method="loginTop" />">ログイントップへ</a></li>
			<li>
				<%
					if (session.getAttribute("SE_login_name") == null) {
						out.println("<a href=\"create_login\">アカウント作成</a>");
					} else {
						out.println("<a href=\"login_detail\">アカウント情報</a>");
					}
				%>
			</li>
			<li><a href="./board_help">ヘルプ</a></li>
		</ul>
		
</s:form>
	</div>
</body>
</html>