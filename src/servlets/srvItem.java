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
import entidades.Item.TiposDisco;
import utils.Validate;

/**
 * Servlet implementation class srvItem
 */
@WebServlet("/srvItem")
public class srvItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Controlador ctrl = new Controlador();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvItem() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Item item;
		
		if(request.getParameter("saveItem")!=null){
			if(request.getParameter("idItem").equals("")){
				item = new Item();
				item.setState(States.Alta);
				Precio precio = new Precio();
				precio.setIdItem(item.getId());
				precio.setValor(Double.parseDouble(request.getParameter("precioItem")));
				ctrl.save(precio);
			} else{
				request.getSession().setAttribute("FormSession", null);
				item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idItem")));
				item.setState(States.Modificacion);
				if(ctrl.getOnePrecioToday(item.getId()).getValor() != Double.parseDouble(request.getParameter("precioItem"))){
					Precio precio = new Precio();
					precio.setIdItem(item.getId());
					precio.setValor(Double.parseDouble(request.getParameter("precioItem")));
					ctrl.save(precio);
				}
			}
				
			item.setTitulo(request.getParameter("tituloItem"));
			item.setAnioLanzamiento(request.getParameter("anioLanzamiento"));
			item.setHabilitado(true);
			item.setStock(Integer.parseInt(request.getParameter("stock")));
			item.setUrlPortada(request.getParameter("urlPortada"));
			
			Artista art = ctrl.getOneArtista(request.getParameter("cmbArtista"));
			Genero gen = ctrl.getOneGenero(request.getParameter("cmbGenero"));
			TipoItem tipo = ctrl.getOneTipoItem(request.getParameter("cmbTipoDisco"));
					
			item.setIdArtista(art.getId());
			item.setIdGenero(gen.getId());
				
			switch(tipo.getId()){
				case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
				case 2: item.setTipoDisco(TiposDisco.CD); break;
				case 3: item.setTipoDisco(TiposDisco.DVD); break;
				case 4: item.setTipoDisco(TiposDisco.Pasta); break;
				case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
			}
					
			if(item.getState() == States.Alta){
				if(Validate.ArtistaItem(request.getParameter("tituloItem"), request.getParameter("artistaItem"))){
					ctrl.save(item);
				} else request.setAttribute("messageError", "Ya fue creado este disco");
			} else ctrl.save(item);
			request.getRequestDispatcher("adminItem.jsp").forward(request, response);
		}
			
		if(request.getParameter("eventUpdate")!=null){
			item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idSelect")));
		
			request.setAttribute("idItem", item.getId());
			request.setAttribute("tituloItem", item.getTitulo());
			request.setAttribute("anioLanzamiento", item.getAnioLanzamiento());
			request.setAttribute("precioItem", ctrl.getOnePrecioToday(item.getId()).getValor());
			request.setAttribute("stock", item.getStock());
		
			request.setAttribute("artistaItem", item.GetArtista().getNombre());
			request.setAttribute("generoItem", item.GetGenero().getDescripcion());
			request.setAttribute("urlPortada", item.getUrlPortada());
				
			switch(item.getTipoDisco()){
				case BlueRay: request.setAttribute("tipoDisco", "Blue-Ray"); break;
				case CD: request.setAttribute("tipoDisco", "CD"); break;
				case DVD: request.setAttribute("tipoDisco", "DVD"); break;
				case Pasta: request.setAttribute("tipoDisco", "Pasta"); break;
				case Vinilo: request.setAttribute("tipoDisco", "Vinilo"); break;
			}
				
			request.getSession().setAttribute("FormSession", "Modificacion");
			request.getRequestDispatcher("adminItem.jsp").forward(request, response);
		}
			
		if(request.getParameter("eventDelete")!=null){
			item = new Item();
			item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idSelect")));
			if(item!=null){
				item.setState(States.Baja);
				ctrl.save(item);
			}
			request.getSession().setAttribute("FormSession", null);
			request.getRequestDispatcher("adminItem.jsp").forward(request, response);
		}
				
		if(request.getParameter("filtroGenero")!=null){
			request.getSession().setAttribute("listado", null);
			ArrayList<Item> listado = ctrl.getAllItemForGenero(request.getParameter("cmbGenero"));
			request.getSession().setAttribute("listado", listado);
			request.getRequestDispatcher("itemForGenero.jsp").forward(request, response);
		}
				
		if(request.getParameter("buscar") != null){
			request.getSession().setAttribute("listado", null);
			ArrayList<Item> listado = ctrl.getBusqueda(request.getParameter("buscar"));
			request.getSession().setAttribute("listado", listado);
			request.getRequestDispatcher("itemForBusqueda.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventRemarcar") != null){
			item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idSelect")));
			
			request.setAttribute("idItem", item.getId());
			request.setAttribute("tituloItem", item.getTitulo());
			request.setAttribute("precioItem", ctrl.getOnePrecioToday(item.getId()).getValor());
			request.setAttribute("stock", item.getStock());
		
			request.setAttribute("artistaItem", item.GetArtista().getNombre());
				
			switch(item.getTipoDisco()){
				case BlueRay: request.setAttribute("tipoDisco", "Blue-Ray"); break;
				case CD: request.setAttribute("tipoDisco", "CD"); break;
				case DVD: request.setAttribute("tipoDisco", "DVD"); break;
				case Pasta: request.setAttribute("tipoDisco", "Pasta"); break;
				case Vinilo: request.setAttribute("tipoDisco", "Vinilo"); break;
			}
			
			request.getRequestDispatcher("adminStockPrecio.jsp").forward(request, response);
		
		}
		
		if(request.getParameter("saveRemarcar") != null){
			item = ctrl.getOneItem(Integer.parseInt(request.getParameter("idItem")));
			Precio precio = new Precio();
			precio.setIdItem(item.getId());
			precio.setValor(Double.parseDouble(request.getParameter("precioNuevo")));
			item.sumoStock(Integer.parseInt(request.getParameter("stockAgregado")));
			item.setState(States.Modificacion);
			
			ctrl.save(item);
			ctrl.save(precio);
			request.getRequestDispatcher("adminStockPrecio.jsp").forward(request, response);
		}

	}

}
