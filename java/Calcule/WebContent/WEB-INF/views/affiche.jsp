<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String commande = (String) request.getAttribute("commande");
	String nb = request.getAttribute("nombre").toString();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Affiche</title>
</head>
<body>
	<table>
		<tr>
			<td>Commande invoquée</td>
			<td><%=commande%></td>
		</tr>
		<tr>
			<td>Nombre demandé</td>
			<td><%=nb%></td>
		</tr>
	</table>
</body>
</html>