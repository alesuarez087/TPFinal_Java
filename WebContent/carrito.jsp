<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidades.*"%>
<%@ page import="utils.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="controlador.Controlador"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrito de Compras</title>

<script type="text/javascript">
function valida(){
	if(confirmar.cmbProvincia.value == ''){alert('Ingrese Provincia'); return false;}
	else if(confirmar.localidad.value == ''){alert('Ingrese Localidad'); return false;}
	else if(confirmar.calle.value == ''){alert('Ingrese calle'); return false;}
	else if(confirmar.nroCalle.value == ''){alert('Ingrese número de calle'); return false;}
	else if(confirmar.nroTarjeta.value == ''){alert('Ingrese número de Tarjeta'); return false;}
	else if(confirmar.titTarjeta.value == ''){alert('Ingrese titular de Tarjeta'); return false;}

	else return true
}
</script>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/dashboard.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<%
	Controlador ctrl = new Controlador(); 
	Usuario user = (Usuario)request.getSession().getAttribute("userSession"); 
	if(user != null) { if(user.getTipoUsuario() == Usuario.TiposUsuario.Usuario){
	ArrayList<VentaItem> carrito = (ArrayList<VentaItem>) request.getSession().getAttribute("carrito");
%>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Luzbelito</a>
			<ul class="nav navbar-nav">
				<li><a href="itemTop.jsp">Discos</a></li>
				<li><a href="listCompras.jsp">Compras</a></li>
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
				<li><a href="valid.jsp">Cerrar Sesión</a></li>
			</ul>
			<form action="srvItem" method="post" class="navbar-form navbar-right">
				<input type="text" class="form-control" id="buscar" name="buscar"
					placeholder="Que estás buscando?">
			</form>
		</div>
	</div>
	</nav>

	<div class="col-sm-9 col-md-10">
		<h2 class="page-header">Carrito de Compras</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Título</th>
					<th>Autor</th>
					<th>Año Lanzamiento</th>
					<th>Género</th>
					<th>Precio</th>
					<th>Cantidad</th>
					<th>Quitar del carro</th>
				</tr>
			</thead>
			<tbody>
				<%	double monto = 0;
       		if(carrito != null){
       			for(VentaItem vi : carrito){
       			Item item = ctrl.getOneItem(vi.getIdItem());
       			monto = monto + item.GetPrecio().getValor()*vi.getCantidad();
       %>
				<tr>
					<td style="vertical-align: middle"><%=item.getTitulo()%></td>
					<td style="vertical-align: middle"><%=item.GetArtista().getNombre() %></td>
					<td style="vertical-align: middle"><%=item.getAnioLanzamiento() %></td>
					<td style="vertical-align: middle"><%=item.GetGenero().getDescripcion() %></td>
					<td style="vertical-align: middle">$<%=item.GetPrecio().getValor() %></td>
					<td style="vertical-align: middle"><%= vi.getCantidad() %></td>
					<td style="vertical-align: middle">
						<form role="form" action="srvCompra" method="post" id="eliminar"
							name="eliminar">
							<input type="hidden" name="idSelected" id="idSelected"
								value="<%=item.getId()%>" /> <input
								class="btn btn-danger btn-sm" type="submit" value="Eliminar"
								id="eventQuitarCarro" name="eventQuitarCarro" />
						</form>
					</td>
				</tr>
				<% } } %>
			</tbody>
		</table>

	</div>
	<% if(carrito != null){ %>

	<div class="col-lg-8 col-lg-offset-1" style="background-color: #ccc">
		<div class="container">
			<h3>
				Total: $<%=monto %></h3>
		</div>
		<form role="form" action="srvCompra" method="post" id="confirmar"
			name="confirmar" onsubmit="return valida()">
			<table align="center">
				<tr>
					<td><b>Provincia</b></td>
					<td colspan=5><select class="form-control" id="cmbProvincia"
						name="cmbProvincia">
							<option></option>
							<%
          	 			for(Provincia provincia : ctrl.getAllProvincia()){
           			%>
							<option>
								<%=provincia.getDescripcion()%>
							</option>
							<%} %>
					</select></td>
					<td>
				</tr>
				<tr>
					<td><b>Localidad</b></td>
					<td colspan=5><input type="text" class="form-control"
						id="localidad" name="localidad" size="43"></td>
				</tr>
				<tr>
					<td><b>Calle</b></td>
					<td colspan=5><input type="text" class="form-control"
						id="calle" name="calle" size="43"></td>
				</tr>
				<tr>
					<td><b>Número</b></td>
					<td><input type="text" class="form-control" id="nroCalle"
						name="nroCalle"></td>
					<td><b>Piso</b></td>
					<td><input type="text" class="form-control" id="piso"
						name="piso"></td>
					<td><b>Departamento</b></td>
					<td><input type="text" class="form-control" id="nroDpto"
						name="nroDpto"></td>
				</tr>
				<tr style="height: 30px;"></tr>
				<tr>
					<td><b>Número de Tarjeta</b></td>
					<td colspan=5><input type="text" class="form-control"
						id="nroTarjeta" name="nroTarjeta" size="43" placeholder="XXXXXXXXXXXXXXXX"></td>
				</tr>
				<tr>
					<td><b>Titular Tarjeta</b></td>
					<td colspan=5><input type="text" class="form-control"
						id="titTarjeta" name="titTarjeta"></td>
				</tr>
				<%
				if(request.getSession().getAttribute("message")!=null){
			%>
				<tr>
					<td colspan=5><font color="#FF0000"><%=request.getSession().getAttribute("message") %></font>
					</td>
				</tr>
				<%
				request.getSession().setAttribute("message", null);} 
			%>
				<tr>
					<td><input class="btn btn-success" type="submit"
						value="Confirmar Compra" id="eventConfirmar" name="eventConfirmar" />
					</td>
				</tr>
			</table>



		</form>
		<% } %>
	</div>

</body>
<%
	} else response.sendRedirect("itemTop.jsp");
	} else response.sendRedirect("login.jsp");
 %>
</html>