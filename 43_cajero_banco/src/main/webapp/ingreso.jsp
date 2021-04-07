<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<head>
<meta charset="UTF-8">
<title>Banca-Ingreso efectivo</title>
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
	<h3>Ingreso</h3>
	<h3>Cuenta: ${sessionScope.cuenta.numeroCuenta}</h3>
	<form action="Controller?option=doIngresar" method="post" onsubmit="return validar();">
		
		<div class="form-group">
			<label>Cantidad a Ingresar</label><input type="text" name="cantidad" id="cantidad" class="form-control" style="width:30%" placeholder="cantidad" />
		</div>
		<button type="submit" class="btn btn-default">Ingresar</button>
		
	</form>
	<br/><br/>
	<a href="Controller?option=toInicio">Login</a>  
</div>

</body>
</html>