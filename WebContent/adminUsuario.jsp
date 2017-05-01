<%@ page import="entidades.Item"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="controlador.Controlador"%>
<%@ page import="entidades.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Disco</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/dashboard.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript">
	function validar(){
		if(formTabla.nombre == ""){alert('Ingrese nombre');return false;}
		else if(formTabla.apellido == ""){alert('Ingrese Apeliido');return false;}
		else if(formTabla.dni == ""){alert('Ingrese DNI');return false;}
		else if(formTabla.email==""){alert('Ingrese Email'); return false;}
		else if(formTabla.nombreUsuario == ""){alert('Ingrese Nombre de Usuario');return false;}
		else if(formTabla.clave == ""){alert('Ingrese Clave'); return false;}
		else if(formTabla.confirmarClave = ""){alert('No confirmó la clave'); return false;}
		return true;
	}
</script>

</head>

<%
	Usuario user = (Usuario)request.getSession().getAttribute("userSession");
	if(user != null){ if(user.getTipoUsuario() == Usuario.TiposUsuario.Administrador){ 
	Controlador ctrl = new Controlador();
%>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
		</div>
		<div>
			<ul class="nav navbar-nav">
				<li><a href="itemTop.jsp">Discos</a></li>
				<li class="active"><a href="adminInicio.jsp">Editar</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="valid.jsp">Cerrar Sesión</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<% if(user.getTipoUsuario() == Usuario.TiposUsuario.Administrador){ %>
					<li><a href="adminArtista.jsp">Artistas</a></li>
					<li><a href="adminGenero.jsp">Géneros</a></li>
					<li><a href="adminItem.jsp">Discos</a></li>
					<li class="active"><a href="adminUsuario.jsp">Usuarios<span
							class="sr-only">(current)</span></a></li>
					<% } %>
					<li><a href="adminStockPrecio.jsp">Remarcar</a></li>
			</div>
		</div>
	</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

		<h2 class="page-header">Usuarios</h2>

		<br>

		<form role="form" action="srvUsuario" method="post" id="formTabla"
			name="formTabla" onsubmit="return validar()">
			<table>
				<tr>
					<td><b>Código:</b></td>
					<td><input type="text" readonly class="form-control"
						id="idUsuario" name="idUsuario"
						value="<%if(request.getAttribute("idUsuario")!=null){%><%=request.getAttribute("idUsuario") %><% }%>"
						size="43"></td>
				</tr>
				<tr>
					<td><b>Nombre:</b></td>
					<td>
						<div class="form-inline">
							<input type="text" class="form-control" id="nombre" name="nombre"
								value="<%if(request.getAttribute("nombre")!=null){%><%=request.getAttribute("nombre") %><% }%>"
								size="55">
						</div>
					</td>
				</tr>
				<tr>
					<td><b>Apellido:</b></td>
					<td>
						<div class="form-inline">
							<input type="text" class="form-control" id="apellido"
								name="apellido"
								value="<%if(request.getAttribute("apellido")!=null){%><%=request.getAttribute("apellido") %><% }%>"
								size="55">
						</div>
					</td>
				</tr>
				<tr>
					<td><b>DNI:</b></td>
					<td>
						<div class="form-inline">
							<input type="text" class="form-control" id="dni" name="dni"
								value="<%if(request.getAttribute("dni")!=null){%><%=request.getAttribute("dni") %><% }%>"
								size="55">
						</div>
					</td>
				</tr>
				<tr>
					<td><b>Email:</b></td>
					<td>
						<div class="form-inline">
							<input type="text" class="form-control" id="email" name="email"
								value="<%if(request.getAttribute("email")!=null){%><%=request.getAttribute("email") %><% }%>"
								size="55">
						</div>
					</td>
				</tr>
				<tr>
					<td><b>Nombre de Usuario:</b></td>
					<td>
						<div class="form-inline">
							<input type="text" class="form-control" id="nombreUsuario"
								name="nombreUsuario"
								value="<%if(request.getAttribute("nombreUsuario")!=null){%><%=request.getAttribute("nombreUsuario") %><% }%>"
								size="55">
						</div>
					</td>
				</tr>
				<tr>
					<td><b>Clave:</b></td>
					<td>
						<div class="form-inline">
							<input type="text" class="form-control" id="clave" name="clave"
								value="<%if(request.getAttribute("clave")!=null){%><%=request.getAttribute("clave") %><% }%>"
								size="55">
						</div>
					</td>
				</tr>
				<tr>
					<td><b>Confirmar Clave:</b></td>
					<td>
						<div class="form-inline">
							<input type="text" class="form-control" id="confirmarClave"
								name="confirmarClave"
								value="<%if(request.getAttribute("confirmarClave")!=null){%><%=request.getAttribute("confirmarClave") %><% }%>"
								size="55">
						</div>
					</td>
				</tr>
				<tr>
					<td><b>Habilitado:</b></td>
					<td>
						<div class="form-inline">
							<input type="checkbox" class="form-control" id="habilitado"
								name="habilitado"
								<%if(request.getAttribute("habilitado")!=null){%>
								<%if(Boolean.parseBoolean(request.getAttribute("habilitado").toString())==true){ %>
								checked <% } }%>" size="55">
						</div>
					</td>
				</tr>
				<tr>
					<td><b>Tipo de Usuario:</b></td>
					<td><select class="form-control" id="cmbTipo" name="cmbTipo">
							<option>Seleccione un Tipo</option>
							<%
          		 		for(TipoUsuario tipo : ctrl.getAllTipoUsuario()){
           			%>
							<option
								<%if(request.getAttribute("tipo")!=null){if(tipo.getDescripcion().equals(request.getAttribute("tipo").toString())){ %>
								selected <% } } %>>
								<%= tipo.getDescripcion() %>
							</option>
							<% } %>
					</select></td>
				</tr>
				<%if (request.getAttribute("messageError")!=null){ %>
				<tr>
					<td rowspan="2"><font color="#FF0000"><%=request.getAttribute("messageError") %></font>
					</td>
				</tr>
				<%} %>
			</table>
			<br>
			<div align="center">
				<input class="btn btn-success" type="submit" value="Guardar"
					id="saveUser" name="saveUser" /> <input class="btn btn-default"
					type="submit" value="Cancelar" id="clearForm" name="clearForm" />
			</div>
		</form>

		<br>
		<br>
		<br>

		<table class="table table-hover" style="background-color: #ffffff">
			<thead>
				<tr>
					<th>ID</th>
					<th>Apellido</th>
					<th>Nombre</th>
					<th>Usuario</th>
					<th>Clave</th>
					<th>Tipo</th>
					<th>Habilitado</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<% 
        	for(Usuario u : ctrl.getAllUsuario()){  
        %>
				<tr>
					<td><%= u.getId() %></td>
					<td><%= u.getApellido() %></td>
					<td><%= u.getNombre() %></td>
					<td><%= u.getNombreUsuario() %></td>
					<td><%= u.getClave() %></td>
					<td><%= u.getTipoUsuario() %></td>
					<td><input type="checkbox" readonly disabled
						<%if(u.isHabilitado()){ %> checked <%} %>></td>
					<form role="form" action="srvUsuario" method="post" id="botonera"
						name="botonera">
						<td style="vertical-align: middle"><input type="hidden"
							name="idSelect" id="idSelect" value="<%=u.getId()%>" /> <input
							class="btn btn-success btn-sm" type="submit" value="Modificar"
							id="eventUpdate" name="eventUpdate" /> <input
							class="btn btn-danger btn-sm" type="submit" value="Eliminar"
							id="eventDelete" name="eventDelete" /></td>
					</form>
				</tr>
				<% 	} %>
			</tbody>
		</table>


	</div>

</body>
<%
	} else{
		if(user.getTipoUsuario() == Usuario.TiposUsuario.Empleado) response.sendRedirect("adminInicio.jsp");
		else response.sendRedirect("itemTop.jsp");
	} } else response.sendRedirect("login.jsp");
%>
</html>
