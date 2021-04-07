<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<meta charset="UTF-8">
<title>Banca-Transferencia</title>

<meta charset="UTF-8">
<title>Banca-Ingreso</title>
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
	<h3>Transferencia</h3>
	<h3>Numero de cuenta: ${sessionScope.cuenta.numeroCuenta}</h3>
	<form action="Controller?option=doTransferir" method="post" onsubmit="return validar();">
		
		<div class="form-group">
			<label>Cuenta Abono</label><input type="text" name="cuentaAbono" id="cuentaAbono" class="form-control" style="width:30%" placeholder="Cuenta Abono" />
		</div>
		<div class="form-group">
			<label>Cantidad a Tranferir</label><input type="text" name="cantidad" id="cantidad" class="form-control" style="width:30%" placeholder="cantidad" />
		</div>
		<button type="submit" class="btn btn-default">Transferir</button>
		
	</form>
	<br/><br/>
	<a href="Controller?option=toInicio">Login</a>  
</div>
</body>
</html>