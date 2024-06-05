package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import DAO.DaoCitas;
import Modelo.Cita;
import Modelo.Psicologo;
import Modelo.Usuario;

/**
 * Servlet implementation class Gestionpsicologo
 */
public class Gestionpsicologo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Gestionpsicologo() {
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

//listar citas del psicologo

		sesion = request.getSession();
		int idusuario = (int) sesion.getAttribute("idusuario");
		String idrol = (String) sesion.getAttribute("idrol");
		//permiso de psicologo
		if (idusuario != 0 && idrol.equals("psicologo")) {
			try {

				System.out.println(idusuario);
				PrintWriter out = response.getWriter();
				Psicologo citas = new Psicologo();
				String resultados = citas.listadecitas(idusuario);
				out.print(resultados);
				out.flush();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("no tienes permiso");
			response.sendRedirect("login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		sesion = request.getSession();
		int idusuario = (int) sesion.getAttribute("idusuario");
		String idrol = (String) sesion.getAttribute("idrol");
		if (idusuario != 0 && idrol.equals("psicologo")) {
			System.out.println(idusuario);
			String correo = request.getParameter("correo");
			String telefono = request.getParameter("telefono");
			String nombreusuario = request.getParameter("nombreusuario");
			String contrasena = request.getParameter("contrasena");
			System.out.println(correo + " " + telefono);

			try {

				Usuario u = new Usuario(idusuario, correo, telefono, nombreusuario, contrasena);
				System.out.println(u);
				u.modificarperfil();
				response.sendRedirect("psicologologueado.html");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("no tienes permiso");
			response.sendRedirect("login.html");
		}
	}

}
