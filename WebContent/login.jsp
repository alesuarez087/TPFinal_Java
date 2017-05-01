<%@ page import="entidades.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<%
	Usuario user = (Usuario)request.getAttribute("userSession");
	if(user == null){
%>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
			<ul class="nav navbar-nav">
				<li><a href="itemTop.jsp">Discos</a></li>
			</ul>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="inicio.jsp">Iniciar Sesión</a></li>
			</ul>
			<form action="srvItem" method="post" class="navbar-form navbar-right">
				<input type="text" class="form-control" id="buscar" name="buscar"
					placeholder="Que estás buscando?">
			</form>
		</div>
	</div>
	</nav>

	<br>
	<br>
	<br>
	<div class="col-sm-4 col-sm-offset-1">
		<div class="panel panel-primary">
			<h3 class="panel-heading">Iniciar Sesión</h3>
			<div class="panel-body">
				<form role="form" action="srvInicio" method="post" id="formInicio"
					name="formInicio">
					<div class="form-group">
						<label for="userregister">Usuario:</label> <input type="text"
							class="form-control" id="userLogin" name="userLogin">
					</div>
					<div class="form-group">
						<label for="password">Contraseña:</label> <input type="password"
							class="form-control" id="passLogin" name="passLogin">
					</div>
					<%if (request.getAttribute("messageError")!=null){ %>
					<font color="#FF0000"> <%=request.getAttribute("messageError") %>
					</font> <br>
					<%} %>
					<div class="form-group">
						<input class="btn btn-success btn-block" type="submit"
							value="Ingresar" id="btnIngresar" name="eventLogin" />
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="col-sm-4 col-sm-offset-2">
		<div class="panel panel-primary">
			<h3 class="panel-heading">Registrarse</h3>
			<div class="panel-body">
				<form role="form" action="srvInicio" method="post" id="formReg"
					name="formReg">
					<div class="form-group">
						<%if (request.getAttribute("messageError2")!=null){ %>
						<font color="#FF0000"> <%=request.getAttribute("messageError2") %>
						</font>
						<%} %>
						<label for="user">Usuario</label> <input type="text"
							class="form-control" id="userCreate" name="userCreate">
					</div>
					<div class="form-group">
						<label for="password">Contraseña:</label> <input type="password"
							class="form-control" id="passCreate" name="passCreate">
					</div>
					<div class="form-group">
						<label for="nombre">Nombre:</label> <input type="text"
							class="form-control" id="nombre" name="nombre">
					</div>
					<div class="form-group">
						<label for="apellido">Apellido:</label> <input type="text"
							class="form-control" id="apellido" name="apellido">
					</div>
					<div class="form-group">
						<label for="dni">DNI:</label> <input type="text"
							class="form-control" id="dni" name="dni">
					</div>
					<div class="form-group">
						<label for="dni">Email:</label> <input type="text"
							class="form-control" id="email" name="email">
					</div>
					<div class="form-group">
						<input class="btn btn-success btn-block" type="submit"
							value="Registrar" id="eventoRegistrar" name="eventRegisterUser" />
					</div>
				</form>
			</div>
		</div>
	</div>

	<%
	} else response.sendRedirect("itemTop.jsp");
%>

</body>
</html>