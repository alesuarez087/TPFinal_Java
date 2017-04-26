<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.*" %>
<%@ page import="controlador.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compras</title>

	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/dashboard.css" rel="stylesheet">
	<link href="bootstrap/css/propio.css" rel="stylesheet">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compras</title>
</head>

<%
	Controlador ctrl = new  Controlador();
	Usuario user = (Usuario)request.getSession().getAttribute("userSession");
	if(user != null){ if(user.getTipoUsuario() == Usuario.TiposUsuario.Usuario){ 
%>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
			<ul class="nav navbar-nav">
        	  	<li><a href="adminInicio.jsp">Discos</a></li>
          		<li class="active"><a href="listCompras.jsp">Compras</a></li>  
        	</ul> 
		</div>
        <div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
           	<% 	if(user != null){ int nro=0;
			if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito") != null){ 
           		nro = ((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")).size();
           	} else nro = 0; %>
        	<li><a href="carrito.jsp">
        	<img alt="Brand" src="bootstrap/img/carrito25.png"> Carrito de compras <span clase="badge">(<%=nro %>)</a></li> 

        	<% } %>
        	<li><a href="valid.jsp">Cerrar Sesión</a></li>
          	</ul>
          	<form action="srvItem" method="post" class="navbar-form navbar-right">
            	<input type="text" class="form-control" id="buscar" name="buscar" placeholder="Que estás buscando?">
          	</form>
        </div>
	</div>
</nav>

<div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="listCompras.jsp">Compras<span class="sr-only">(current)</span></a></li>
            <li><a href="itemsComprados.jsp">Clasficaciones</a></li>
            </ul>
        </div>
	</div>
</div>

<div class="col-sm-4 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<div class="container-fluid">
		<h3>Compras</h3>
		<div class="row">
			<table class="table table-hover" style="text-align: left">
       		<thead> 
			<tr> 
				<th>Código</th>
           		<th>Discos</th>
           		<th>Fecha</th>
           		<th>Precio Final</th> 
         	</tr> 
       		</thead>
       		<tbody>
       		<% for(Venta venta : ctrl.getAllVentaForUser(user.getId())){ double monto = 0;  int i =0 ;%>
         	<tr>
           		<td style="vertical-align:middle">
           			<input type="hidden" name="nro" id="nro" value="<%=venta.getId()%>" /><%=venta.getId()%>
           		</td>
           		<td style="vertical-align:middle">
           			<% for(VentaItem ventaItem : ctrl.getAllVentaVentaItem(venta.getId())){ %>
           				<input type="hidden" name="disco" id="disco" value="<%=ventaItem.GetItem().getTitulo()%>,"/><%=ventaItem.GetItem().getTitulo()%> (x<%=ventaItem.getCantidad()%>)<%
						i++; if(i<ctrl.getAllVentaVentaItem(venta.getId()).size()){           			
           			%>,  <% } } %>
           		</td>
           		<td style="vertical-align:middle">
           			<input type="hidden" name="artista" id="artista" value="<%=venta.getFecha()%>"/><%=venta.getFecha()%>
           		</td>
           		<td style="vertical-align:middle">
  					<input type="hidden" name="artista" id="artista" value="$ <%=Math.rint(venta.GetMonto())%>"/><%=Math.rint(venta.GetMonto())%>
           		</td>
			</tr>
         	<% } %>
			</tbody>
			</table>
		</div>
	</div>
</div>
</body>

<%
	} else response.sendRedirect("adminInicio.jsp"); 
	} else response.sendRedirect("login.jsp");
		
 %>
</html>