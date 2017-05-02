package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.*;
import entidades.Entidad.States;
import entidades.Usuario.TiposUsuario;
import utils.Validate;
import controlador.*;
/**
 * Servlet implementation class srvInicio
 */
@WebServlet("/srvInicio")
public class srvInicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public srvInicio() {
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

		Usuario user = new Usuario();
		Controlador ctrl = new Controlador();
		if(request.getParameter("eventLogin")!=null){
			user = ctrl.login(request.getParameter("userLogin"), request.getParameter("passLogin"));
			if(user!=null){
				request.getSession().setAttribute("userSession", user);
				
				// TENER EN CUENTA COMO PROSEGUIR DEPENDIENDO DE QUE TIPO DE USAURIO SEA 
				if(user.getTipoUsuario() != TiposUsuario.Usuario) request.getRequestDispatcher("adminInicio.jsp").forward(request, response);
				else if(request.getSession().getAttribute("item")!=null) request.getRequestDispatcher("elegido.jsp").forward(request, response);
				else request.getRequestDispatcher("itemTop.jsp").forward(request, response);
				
				
				
			} else{
				request.setAttribute("messageError", "Usuario y/o Contraseña incorrecto");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		
		if(request.getParameter("eventRegisterUser")!=null){
			user = ctrl.getOneUsuarioForName(request.getParameter("userCreate"));
			if(user == null){
				if(Validate.Email(request.getParameter("email"))){
					if(Validate.EsNumerico(request.getParameter("dni"))){
						user = new Usuario();
						user.setDni(request.getParameter("email"));
						user.setNombreUsuario(request.getParameter("userCreate"));
						user.setApellido(request.getParameter("apellido"));
						user.setClave(request.getParameter("passCreate"));
						user.setEmail(request.getParameter("dni"));
						user.setHabilitado(true);
						user.setNombre(request.getParameter("nombre"));
						user.setTipoUsuario(TiposUsuario.Usuario);
						user.setState(States.Alta);
						ctrl.save(user);
						request.setAttribute("messageError2", "Usuario Creado Correctamente");
						request.getRequestDispatcher("login.jsp").forward(request, response);

					}else{
						request.setAttribute("messageError2", "DNI inválido");
						request.getRequestDispatcher("login.jsp").forward(request, response);
					}
				} else{
					 request.setAttribute("messageError2", "Email inválido");
					 request.getRequestDispatcher("login.jsp").forward(request, response);
				} 
			} else{
				request.setAttribute("messageError2", "Ya existe un Usuario con ese nombre");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		
		if(request.getParameter("logout")!=null){
			request.getSession().setAttribute("userSession", null);
			request.getRequestDispatcher("itemTop.jsp").forward(request, response);;
		}
	}

}
