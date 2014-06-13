<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%@ page import="boardAction.TestClass" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ログ</title>
<style type="text/css">
p.error {
	color: red;
}

</style>
</head>
<body>
<h1>ログインページ</h1>

<s:form cssClass="form-horizontal">
<p class="error"><s:property value="errorMsg" /></p>
<label class="col-sm-2 control-label">ログインID</label>
<s:textfield name="loginName" type="loginName" cssClass="form-control"/>
<p><label class="col-sm-2 control-label">パスワード</label>
<s:textfield name="password" type="password" cssClass="form-control"/></p>
<p><s:submit method ="login" value="ログイン"/></p>
<p><s:submit method ="createAccount" value="アカウント作成"/></p>
<p><s:submit method ="useBoard" value="ログインせずに利用"/></p>
<img src="C:\Users\Admin\workspace\Board_Revision3\WebContent\img/bbb.jpg">
<% TestClass f = new TestClass();
	f.go(); %>
</s:form>
</body>
</html>