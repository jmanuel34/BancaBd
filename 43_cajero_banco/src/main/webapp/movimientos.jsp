<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>

		<h1>Lista de movimientos de la cuenta:
			${sessionScope.cuenta.numeroCuenta}</h1>

		<table border="1">
			<tr>
				<th>Movimiento</th>
				<th>Fecha</th>
				<th>Cantidad</th>
				<th>Operacion</th>
			</tr>
			<c:forEach var="mv" items="${requestScope.movimientos}">
				<tr>
					<td>${mv.idMovimiento}</td>
					<td>${mv.fecha}</td>
					<td>${mv.cantidad}</td>
					<td>${mv.operacion}</td>
				</tr>
			</c:forEach>
		</table>
</center>
<a href="Controller?option=toOperacion">Operar en cuenta</a>  
<a href="Controller?option=toInicio">Login</a>  
</body>
</html>