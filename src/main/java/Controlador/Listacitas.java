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
import DAO.DaoPsic;
import DAO.DaoUsuario;
import Modelo.Cita;
import Modelo.Usuario;

/**
 * Servlet implementation class Listacitas
 */
public class Listacitas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Listacitas() {
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
		
		sesion = request.getSession();
	
		int idusuario = (int) sesion.getAttribute("idusuario");
		String idrol = (String) sesion.getAttribute("idrol");
		if (idusuario != 0 && idrol.equals("cliente")) {
			PrintWriter out = response.getWriter();

			int option = Integer.parseInt(request.getParameter("op"));
			System.out.println(option);
			if (option == 1) {
				// listar citas
				try {
					System.out.println("entra");
					DaoCitas idclient = new DaoCitas();
					int idcliente = idclient.obteneridcliente(idusuario);
					System.out.println(idcliente);
					DaoCitas daocitas = new DaoCitas();
					ArrayList<Cita> listacitas = daocitas.listacitas(idcliente);
					Gson gson = new Gson();
					String resultados = gson.toJson(listacitas);
					out.print(resultados);
					out.flush();
					System.out.println(resultados);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}else {
			System.out.println("no tienes permiso");
			
			response.sendRedirect("login.html");
			
		}	
			
			
			/*
		
			 * if (option == 2) { // borrar citas try { int idusuario =
			 * Integer.parseInt(request.getParameter("idusuario"));
			 * System.out.println(idusuario); DaoUsuario us = new DaoUsuario();
			 * us.borrar(idusuario); out.print(us.listarJoson());
			 * 
			 * } catch (SQLException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } }
			 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
