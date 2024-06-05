package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import Modelo.Usuario;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// cuando pulse en login se invalida la sesion anterior
	
		
		String nombreusuario = request.getParameter("nombreusuario");
		String contrasena = request.getParameter("contrasena");
		Usuario u = new Usuario(nombreusuario, contrasena);
	

		try {

			if (u.logeo(contrasena)) {
				sesion = request.getSession();
				sesion.setAttribute("idusuario", u.getIdusuario());
				sesion.setAttribute("idrol", u.getIdrol());
				String respuesta = (String) sesion.getAttribute("idrol");
				System.out.println(respuesta);
				if (respuesta.equals("psicologo")) {
					response.sendRedirect("psicologologueado.html");
					System.out.println("1");
				}
				if (respuesta.equals("cliente")) {
					response.sendRedirect("listacitas.html");
					System.out.println("2");
				}
				if (respuesta.equals("administrador")) {
					response.sendRedirect("adminlogueado.html");
				}
				
			} else {
				response.sendRedirect("login.html");
				System.out.println("no se ha podido loguear");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		}
		
	
}
