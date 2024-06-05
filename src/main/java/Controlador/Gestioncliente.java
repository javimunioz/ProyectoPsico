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
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

import DAO.DaoCitas;
import DAO.DaoPsic;
import Modelo.Cita;
import Modelo.Cliente;
import Modelo.Usuario;

/**
 * Servlet implementation class Gestioncliente
 */
public class Gestioncliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Gestioncliente() {
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

		// lista los psicologos
		sesion = request.getSession();
		int idusuario = (int) sesion.getAttribute("idusuario");
		String idrol = (String) sesion.getAttribute("idrol");
		
		if (idusuario != 0 && idrol.equals("cliente")) {
			Cliente cliente = new Cliente();
			ArrayList<Usuario> psic;
			try {
				psic = cliente.getCitas();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				Gson gson = new Gson();
				String json = gson.toJson(psic);
				response.getWriter().write(json);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("no tienes permiso");
			response.sendRedirect("login.html");
		}
	}
// trasladar el numero de psicologo con su eleccion

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		sesion = request.getSession();
		int idusuario = Integer.parseInt(request.getParameter("psicologo"));
		String idrol = (String) sesion.getAttribute("idrol");
		
		//permiso de cliente
		if (idusuario != 0 && idrol.equals("cliente")) {

			try {
				int idlogin = (int) sesion.getAttribute("idusuario");
				System.out.println(idlogin);
				System.out.println(idusuario);
				Date fecha = Date.valueOf(request.getParameter("fecha"));
				Time hora = Time.valueOf(request.getParameter("hora") + ":00");
				Cliente cliente = new Cliente();
				Cita cita = cliente.solicitarCita(idlogin, idusuario, fecha, hora);
				System.out.println(cita);
				response.sendRedirect("listacitas.html");
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
