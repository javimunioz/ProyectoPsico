package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

import Modelo.Usuario;

public class DaoUsuario {

	public static Connection con = null;

	public DaoUsuario() throws SQLException {
		this.con = DBconexion.getConexion();

	}

//dar de alta usuarios e insertarlo en la base de datos
	/**
	 * metodo para insertar un usuario en la base de datos (aplicado en el metodo
	 * registrar de esta misma clase)y que inserta ese mismo usuario en la tabla
	 * cliente o psicologo en funciond e su idrol
	 * 
	 * @param u se envia un objeto usuario recogido en el servlet
	 * @throws SQLException
	 */
	public void insertar(Usuario u) throws SQLException {
		String sql = "INSERT INTO usuarios (nombreusuario, contrasena,idrol,dni,correo,telefono,nombre,apellidos,foto) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, u.getNombreusuario());
		ps.setString(2, u.getContrasena());
		ps.setString(3, u.getIdrol());
		ps.setString(4, u.getDni());
		ps.setString(5, u.getCorreo());
		ps.setString(6, u.getTelefono());
		ps.setString(7, u.getNombre());
		ps.setString(8, u.getApellidos());
		ps.setString(9, u.getFoto());
		String idrol = u.getIdrol();

		ResultSet generatedkeys = ps.getGeneratedKeys();
		ps.executeUpdate();
// RECOGE EL IDUSUARIO DEL USUARIO INSERTADO Y LO INSERTA EN LA TABLA PSICOLOGO O CLIENTE DEPENDIENDO DEL ROL
		ResultSet rs = ps.getGeneratedKeys();
		int generatedKey = 0;
		if (rs.next()) {
			generatedKey = rs.getInt(1);
		}

		System.out.println("Inserted record's ID: " + generatedKey);

		if (idrol.equals("psicologo")) {
			String sql2 = "INSERT INTO psicologo (idusuario) VALUES (?)";
			PreparedStatement ps1 = con.prepareStatement(sql2);
			ps1.setInt(1, generatedKey);
			ps1.executeUpdate();

		} else if (idrol.equals("cliente")) {
			String sql3 = "INSERT INTO cliente (idusuario) VALUES (?)";
			PreparedStatement ps2 = con.prepareStatement(sql3);
			ps2.setInt(1, generatedKey);
			ps2.executeUpdate();

		}
		ps.close();

	}

	/**
	 * metodo para borrar usuarios
	 * 
	 * @param idusuario necesita el parametro del idusuario y borra en cascada
	 * @throws SQLException
	 */
	public void borrar(int idusuario) throws SQLException {
		// busca el idcliente para borrar sus citas
		DaoCitas dao = new DaoCitas();
		int idcliente = dao.obteneridcliente(idusuario);
		String sql1 = "DELETE FROM cita WHERE idcliente=?";
		PreparedStatement ps1 = con.prepareStatement(sql1);
		ps1.setInt(1, idcliente);

		int filas1 = ps1.executeUpdate();
		ps1.close();
		// borrar su usuario
		String sql = "DELETE FROM usuarios WHERE idusuario=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idusuario);

		int filas = ps.executeUpdate();
		ps.close();
	}

	/**
	 * modificar perfil psicologo, correo y teléfono
	 * 
	 * @param u
	 * @throws SQLException
	 */
	public void modificarperfil(Usuario u) throws SQLException {

		String sql = "UPDATE usuarios SET nombreusuario=?,correo=?,telefono=?,contrasena=? WHERE idusuario=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, u.getCorreo());
		ps.setString(2, u.getTelefono());
		ps.setString(3, u.getNombreusuario());
		ps.setString(4, u.getContrasena());
		ps.setInt(5, u.getIdusuario());

		int filas = ps.executeUpdate();
		ps.close();
	}

	public String listarJoson() throws SQLException {
		String txtJSON = "";
		Gson gson = new Gson();
		txtJSON = gson.toJson(this.listar());
		return txtJSON;

	}

	/**
	 * metodo para registrar que comprueba si hay mas usuarios con ese nombre o
	 * correo y después si no hay ejecuta el insertar de esta misma clase
	 * 
	 * @param u recoge los parametros nombreusuario y correo del objeto usurio
	 *          recibido por el servlet
	 * @throws SQLException
	 */
	public void registrar(Usuario u) throws SQLException {

		System.out.println(u);

		String sql = "SELECT COUNT(*) AS count FROM usuarios WHERE nombreusuario=? AND correo=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, u.getNombreusuario());
		ps.setString(2, u.getCorreo());
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int count = rs.getInt("count");
			if (count > 0) {
				System.out.println("No se ha podido registrar.");

				//

			} else
				insertar(u);

		}

	}

	/**
	 * metodo para el login
	 * 
	 * @param u          recibe un objeto usuario y una contrasena y ejecuta un
	 *                   string con una query para buscarlo en la base de datos, si
	 *                   lo encuentra devuelve todos los campos de ese usuario en la
	 *                   base de datos
	 * @param contrasena
	 * @return devuelve los campos de la base de datos de ese id
	 * @throws SQLException
	 */
	public Usuario login(Usuario u, String contrasena) throws SQLException {

		String sql = "SELECT * FROM usuarios WHERE nombreusuario=? AND contrasena =?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getNombreusuario());
		ps.setString(2, u.getContrasena());
		ResultSet rs = ps.executeQuery();

		rs.next();

		Usuario us = new Usuario(rs.getInt("idusuario"), rs.getString("nombreusuario"), rs.getString("contrasena"),
				rs.getString("idrol"), rs.getString("dni"), rs.getString("correo"), rs.getString("telefono"),
				rs.getString("nombre"), rs.getString("apellidos"));
		System.out.println(us);
		return us;

	}

	/**
	 * metodo para obtener por id un usuario
	 * 
	 * @param idusuario recibe el id del usuario
	 * @return devulve los datos del usuario
	 * @throws SQLException
	 */
	public Usuario obtenerPorID(int idusuario) throws SQLException {

		String sql = "SELECT * FROM usuarios WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idusuario);

		ResultSet rs = ps.executeQuery();

		rs.next();

		Usuario u = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));

		return u;
	}

	/**
	 * metodo para listar todos los usuarios
	 * 
	 * @return devuelve una listad e todos los usuario registrados
	 * @throws SQLException
	 */
	public ArrayList<Usuario> listar() throws SQLException {

		String sql = "SELECT * FROM usuarios ";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		// generar un arraylist donde pasar todos los datos del rs (que es una
		// colección.
		ArrayList<Usuario> ls = new ArrayList<Usuario>();
		while (rs.next()) {

			ls.add(new Usuario(rs.getInt("idusuario"), rs.getString("nombreusuario"), rs.getString("contrasena"),
					rs.getString("idrol"), rs.getString("dni"), rs.getString("correo"), rs.getString("telefono"),
					rs.getString("nombre"), rs.getString("apellidos"), rs.getString("foto")));

		}
		return ls;
	}

	/**
	 * metodo para crear una lista de todos los usuarios segun su idrol
	 * 
	 * @param idrol recibe el idrol para buscar ese mismo idrol en la base de datos
	 * @return devuleve la lista de usuario con ese idrol
	 * @throws SQLException
	 */
	public ArrayList<Usuario> listar(String idrol) throws SQLException {

		String sql = "SELECT * FROM usuarios WHERE idrol=? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		// generar un arraylist donde pasar todos los datos del rs (que es una
		// colección.
		ArrayList<Usuario> ls = new ArrayList<Usuario>();
		while (rs.next()) {

			ls.add(new Usuario(rs.getInt("idusuario"), rs.getString("nombreusuario"), rs.getString("contrasena"),
					rs.getString("idrol"), rs.getString("dni"), rs.getString("correo"), rs.getString("telefono"),
					rs.getString("nombre"), rs.getString("apellidos"), rs.getString("foto")));

		}
		return ls;

	}

}
