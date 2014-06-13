<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="./css/reset.css" type="text/css" />
<link rel="stylesheet" href="./css/basic.css" type="text/css" />
<style type="text/css">
div#header {
	margin-bottom: 15px;
}

dd {
	padding: 0 0 40px 0;
}

p.page {
	float: left;
	padding: 0 10px 0 0;
}

div.body {
	width: 800px;
	padding: 0 0 0 90px;
}

div.body dl {
	width: 200px;
	float: left;
}

div.search {
	padding-left: 600px;
}

div.main {
	padding-top: 25px;
}

div.pager {
	clear: both;
}

div.page {
	clear: both;
}
</style>
<title>掲示板トップ画面</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="body">
		<s:form name="form1">
			<div class="search">
				<input type="text" name="search"
					value="<%out.print(request.getAttribute("search"));%>"></input> <input
					type="submit" value="検索"></input>
			</div>
			<div class="main">
				<dl>
					<s:iterator value="threadList">
						<dt>
							<s:submit type="image" src="./img/%{threadImage}" width="150" height="150" method="threadDetail" escape='false' onclick="setThreadIDNoSubmit(%{threadID})"/>
						</dt>
						<dd>
							<s:submit type="text" value="%{threadName}" method="threadDetail" onclick="setThreadIDNoSubmit(%{threadID})" />
						</dd>

					</s:iterator>
				</dl>

			</div>
			<s:textfield type="hidden" name="threadID" value="" />
			<s:textfield type="hidden" name="pageNumber" value="" />

		</s:form>
		<div class="pager">

			<%
				int page_count = 8;
				int page_id = 1;

				for (int i = 1; page_count >= i; i++) {
			%>
			<%
				if (page_id == i) {
			%>
			<p class="page">

				<input type="button" value=<%out.print(i);%>
					onclick="setValue(<%out.print(i);%>);">

			</p>
			<%
				} else {
			%>
			<p class="page">
				<input type="button" value=<%out.print(i);%>
					onclick="setValue(<%out.print(i);%>);">
			</p>
			<%
				}
				}
			%>

		</div>
	</div>
</body>
<script>
function setValue(){
	
	form1.submit();

}

function setThreadIDNoSubmit(threadID){
	
	form1.threadID.value=threadID;

}
</script>
</html>