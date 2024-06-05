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

import DAO.DaoUsuario;
import Modelo.Usuario;

/**
 * Servlet implementation class gestionusuarios
 */
public class gestionusuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public gestionusuarios() {
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

		// listar usuarios para admin
		// hay que hacer que
		sesion = request.getSession();
		PrintWriter out = response.getWriter();
		int option = Integer.parseInt(request.getParameter("op"));
		int idusuariosesion = (int) sesion.getAttribute("idusuario");
		String idrol = (String) sesion.getAttribute("idrol");
		System.out.println(option);
		System.out.println(option);
		if (idusuariosesion != 0 && idrol.equals("administrador")) {
			if (option == 1) {
				try {
					DaoUsuario dao = new DaoUsuario();

					String resultados;
					resultados = dao.listarJoson();
					System.out.println(resultados);
					out.print(resultados);
					System.out.println(resultados);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (option == 2) {
				try {
					int idusuario = Integer.parseInt(request.getParameter("idusuario"));
					System.out.println(idusuario);
					DaoUsuario us = new DaoUsuario();
					us.borrar(idusuario);
					out.print(us.listarJoson());

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
		// recoger datos de listarusuarios.html y hacer lista para admin
		sesion = request.getSession();
		String idrol = (String) sesion.getAttribute("idrol");
		System.out.println("justo aqui");
		System.out.println(idrol);
		if (idrol.equals("administrador")) {
			try {
				PrintWriter out = response.getWriter();

				DaoUsuario lista = new DaoUsuario();
				ArrayList<Usuario> listadoUsuario = lista.listar();
				out.print("<ul>");
				for (Usuario u : listadoUsuario) {
					System.out.println(u.getNombre());
					out.print("<li>" + u.getNombre());
				}
				out.print("</ul>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("login.html");

		}

	}

}
