package Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import DAO.DaoUsuario;
import Modelo.Usuario;

/**
 * Servlet implementation class Registro
 */
@MultipartConfig
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String pathFiles = "E:\\ProyectoWebPsico\\src\\main\\webapp\\fotousuarios";
	private File uploads = new File(pathFiles);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registro() {
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
		String nombreusuario = request.getParameter("nombreusuario");
		String contrasena = request.getParameter("contrasena");
		String dni = request.getParameter("dni");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String idrol = request.getParameter("idrol");

		// metodod específico para leer los datos del archivo
		Part part = request.getPart("foto");
		Path path = Paths.get(part.getSubmittedFileName()); // esto da el nombre del archivo original
		String fileName = path.getFileName().toString();
		// ya está la lectura del archivo
		InputStream input = part.getInputStream();
		File file = new File(uploads, fileName);

		try {
			Files.copy(input, file.toPath());
		} catch (Exception e) {
			System.out.println(e);
			PrintWriter error = response.getWriter();
			error.print("n");
		}

		Usuario u = new Usuario(nombreusuario, contrasena, idrol, dni, correo, telefono, nombre, apellidos, fileName);
		System.out.println(u);

		try {
			u.registrar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} response.sendRedirect("login.html");

	}

}
