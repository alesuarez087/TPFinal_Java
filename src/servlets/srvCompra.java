package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.*;
import entidades.*;
import entidades.Entidad.States;
import utils.Validate;

/**
 * Servlet implementation class srvCompra
 */
@WebServlet("/srvCompra")
public class srvCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Controlador ctrl = new Controlador();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvCompra() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("eventSale")!=null){
			Item item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idSelect")));
			request.getSession().setAttribute("item", item);
			if(request.getSession().getAttribute("userSession")!=null){
				request.getRequestDispatcher("elegido.jsp").forward(request, response);
			} else request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		if(request.getParameter("addCarrito")!=null){
			if(Validate.HayStock(((Item)request.getSession().getAttribute("item")).getId(), Integer.parseInt(request.getParameter("cmbCantidad"))))
			{
				ArrayList<VentaItem> carrito;
				if((ArrayList<VentaItem>)request.getSession().getAttribute("carrito")!=null) carrito = (ArrayList<VentaItem>)request.getSession().getAttribute("carrito");
					else carrito = new ArrayList<VentaItem>();
				VentaItem fila = new VentaItem();
				fila.setIdItem(((Item)request.getSession().getAttribute("item")).getId());
				fila.setCantidad(Integer.parseInt(request.getParameter("cmbCantidad")));
				carrito.add(fila);
				request.getSession().setAttribute("carrito", carrito);
				request.getRequestDispatcher("itemTop.jsp").forward(request, response);
			} else{
				request.getSession().setAttribute("errorStock", "No hay stock para satisfacer su compra");
				request.getRequestDispatcher("valid.jsp").forward(request, response);
			}
		}
		
		if(request.getParameter("eventQuitarCarro")!=null){
			ArrayList<VentaItem> carrito = (ArrayList<VentaItem>)request.getSession().getAttribute("carrito");
			ArrayList<VentaItem> carrito_new = new ArrayList<VentaItem>();
			for(VentaItem i : carrito){
				if(i.getIdItem() != Integer.parseInt(request.getParameter("idSelected"))){
					carrito_new.add(i);
				}
			}
			request.getSession().setAttribute("carrito", carrito_new);
			request.getRequestDispatcher("compra.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventConfirmar")!=null){
			
			if(Validate.EsNumerico(request.getParameter("nroCalle"))){
				if(Validate.FormatoNumero(request.getParameter("nroTarjeta"), 16)){
					if(Validate.Texto(request.getParameter("localidad"))){
						if(Validate.Texto(request.getParameter("titTarjeta"))){
							
							Venta venta = new Venta(); Item item;
							Usuario user = (Usuario)request.getSession().getAttribute("userSession");
							ArrayList<VentaItem> ventaItem = (ArrayList<VentaItem>)request.getSession().getAttribute("carrito");
							
							venta.setIdUsuario(user.getId());
							venta.setNroTarjeta(request.getParameter("nroTarjeta"));
							venta.setTitularTarjeta(request.getParameter("titTarjeta"));
							venta.setIdProvincia(ctrl.getOneProvincia(request.getParameter("cmbProvincia")).getId()); 
							venta.setLocalidad(request.getParameter("localidad"));
							venta.setCalle(request.getParameter("calle")); 
							venta.setNroCalle(request.getParameter("nroCalle"));
							if(!request.getParameter("piso").equals("")) venta.setPiso(request.getParameter("piso"));
							if(!request.getParameter("nroDpto").equals("")) venta.setNroDpto(request.getParameter("nroDpto"));
							
							ctrl.save(venta);
							venta.setId(ctrl.ultimaVenta());
							
							for(VentaItem vi : ventaItem){
								vi.setIdVenta(venta.getId());
								item = ctrl.getOneItem(vi.getIdItem());
								item.quitoStock(vi.getCantidad());
								item.setState(States.Modificacion);
								
								ctrl.save(item);
								ctrl.save(vi);
							}
							request.getSession().setAttribute("carrito", null);
							request.getSession().setAttribute("compraValida", "Compra exitosa");
							request.getRequestDispatcher("valid.jsp").forward(request, response);
							
						} else{
							request.getSession().setAttribute("message", "No ingresó Titular de Tarjeta válido");
							request.getRequestDispatcher("carrito.jsp").forward(request, response);
						}						
					} else{
						request.getSession().setAttribute("message", "No ingresó Localidad válida");
						request.getRequestDispatcher("carrito.jsp").forward(request, response);
					}
				} else{
					request.getSession().setAttribute("message", "No ingresó Número de tarjeta válido");
					request.getRequestDispatcher("carrito.jsp").forward(request, response);
				}
			} else {
				request.getSession().setAttribute("message", "No ingresó Número de calle válido");
				request.getRequestDispatcher("carrito.jsp").forward(request, response);
			}
		}
	}

}
