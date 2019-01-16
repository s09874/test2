<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BeatGoogle</title>
</head>
<body bgColor="#BD7B00">
	<%
		out.println("[related keywords]");
	%>
	<br>
	<%
		String[] reList = (String[]) request.getAttribute("related");
		for (int i = 0; i < reList.length; i++) {
	%>
	<h style="font-size:16px ;"><%=reList[i]%></h>
	<br>
	<%
		}
	
	%>
	<br>

	<%
		out.println("[Suggested result]");
	%>
	<br>
	<%
		String[][] orderList = (String[][]) request.getAttribute("result");
		for (int i = 0; i < orderList.length; i++) {
	%>
	<h style="font-size:16px ;"><%=orderList[i][0]%></h>
	<br>
	<a href='<%=orderList[i][1]%>'><%=orderList[i][1]%></a>
	<br>
	<h style="font-size:13px ;"><%=orderList[i][2]%></h>
	<br>
	<h style="font-size:13px ;"><%=orderList[i][3]%></h>
	<br>
	<br>
	<%
		}
	%>
</body>
</html>