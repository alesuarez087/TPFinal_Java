<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="controlador.Controlador"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrito de Compras</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/dashboard.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<% 
	Controlador ctrl = new Controlador(); 
	Usuario user = (Usuario)request.getSession().getAttribute("userSession");
	ArrayList<VentaItem> carrito = (ArrayList<VentaItem>) request.getSession().getAttribute("carrito");
	if(user != null){ if(user.getTipoUsuario() == Usuario.TiposUsuario.Usuario){  
%>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
			<ul class="nav navbar-nav">
				<li><a href="itemTop.jsp">Discos</a></li>
				<li><a href="compras.jsp">Compras</a></li>
			</ul>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<% 	if(user != null){ int nro=0;
			if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito") != null){ 
           		nro = ((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")).size();
           	} else nro = 0; %>
				<li class="active"><a href="carrito.jsp"> <img alt="Brand"
						src="bootstrap/img/carrito25white.png"> Carrito de compras <span
						clase="badge">(<%=nro %>)</a></li>
				<% } %>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="valid.jsp">Cerrar Sesión</a></li>
				</ul>
			</ul>
			<form action="srvItem" method="post" class="navbar-form navbar-right">
				<input type="text" class="form-control" id="buscar" name="buscar"
					placeholder="Que estás buscando?">
			</form>
		</div>
	</div>
	</nav>
	<br>
	<div class="col-sm-9 col-md-10">
		<%if(request.getSession().getAttribute("compraValida")!=null){ %>
		<h4>
			Su compra se realizó con éxito. Haga <a href="itemTop.jsp">click</a>
			para continuar
		</h4>
		<% request.getSession().setAttribute("errorStock", null); 
	} else if(request.getSession().getAttribute("errorStock")!=null){ %>
		<h4>
			No hay stock para satisfacer su compra. Haga <a href="itemTop.jsp">click</a>
			para continuar
		</h4>
		<% request.getSession().setAttribute("errorStock", null); }
	else if(request.getSession().getAttribute("logout")!=null){
		request.getSession().setAttribute("userSession", null);
		request.getSession().setAttribute("logout", null); 
		response.sendRedirect("itemTop.jsp");} %>
	</div>
</body>
<%
	} else response.sendRedirect("adminInicio.jsp");
	} else response.sendRedirect("login.jsp");
 %>
</html>