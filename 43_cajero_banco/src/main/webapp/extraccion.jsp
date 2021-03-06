<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>

<title>Banca-Extraccion efectivo</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type = "text/javascript">	
	function validar() {
		var cantidad=document.getElementById("cantidad").value;
		if (isNan(cantidad) ) {
			return false
		}
		return true;
	}	
</script>
</head>
<body>
<div class="container">
	<h3>Extraccion</h3>
	<h3>Numero de cuenta: ${sessionScope.cuenta.numeroCuenta}</h3>
	
	<form action="Controller?option=doExtraer" method="post" onsubmit="return validar();">
		
		<div class="form-group">
			<label>Cantidad a Extraer</label><input type="text" name="cantidad" id="cantidad" class="form-control" style="width:30%" placeholder="cantidad" />
		</div>
		<button type="submit" class="btn btn-default">Extraer</button>
		
	</form>
	<a href="Controller?option=toInicio">Login</a>  
	<br/><br/>
</div>

</body>
</html>