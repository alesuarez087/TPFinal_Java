package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Entidad.States;
import controlador.*;
import entidades.*;

/**
 * Servlet implementation class srvCalificar
 */
@WebServlet("/srvCalificar")
public class srvCalificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Controlador ctrl = new Controlador();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvCalificar() {
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

		Usuario user = (Usuario)request.getSession().getAttribute("userSession");
		
		if(request.getParameter("eventCalif")!=null){
			
			Clasificacion clas = (Clasificacion)request.getSession().getAttribute("clas");
			if(clas != null) clas.setState(States.Modificacion);
			else{
				clas = new Clasificacion();
				clas.setState(States.Alta);
			}
			clas.setValor(Integer.parseInt(request.getParameter("estrellas")));
			clas.setDetalles(request.getParameter("messageAdd"));
			ctrl.save(clas);
			request.getSession().setAttribute("clas", null);
			request.getRequestDispatcher("itemsComprados.jsp").forward(request, response);
		}
		
		if(request.getParameter("eventSelect")!=null){
			int idItem = Integer.parseInt(request.getParameter("itemSelect"));
			Clasificacion clas = ctrl.getOneClasificacion(idItem, user.getId());
			if(clas!=null){
				clas.setState(States.Modificacion);
			} else {
				clas = new Clasificacion();
				clas.setIdItem(idItem);
				clas.setIdUsuario(user.getId());
				clas.setState(States.Alta);
			}
			request.getSession().setAttribute("clas", clas);
			request.getRequestDispatcher("calificar.jsp").forward(request, response); 
		}
		
		if(request.getParameter("eventCancel")!=null){
			request.getSession().setAttribute("clas", null);
			request.getRequestDispatcher("itemsComprados.jsp").forward(request, response);
		}
	}
}
