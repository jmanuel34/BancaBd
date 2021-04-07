<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>

<title>Operacion Banca</title>
</head>
<body>
<center>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cuenta: ${sessionScope.cuenta.numeroCuenta}
            <h1>Seleccione Operacion</h1></center>
            <br/>
            <a href="Controller?option=toIngresar">Ingreso</a></br></br>
            <a href="Controller?option=toExtraer">Extracción</a></br></br>
            <a href="Controller?option=toTransferir">Transferencia</a></br></br>
            <a href="Controller?option=doMovimientos">Movimientos</a></br></br>
            </br></br>
            

<a href="Controller?option=toInicio">Inicio</a>          
            
</body>
</html>