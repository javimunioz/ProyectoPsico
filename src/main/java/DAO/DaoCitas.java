package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

import Modelo.Cita;
import Modelo.Usuario;

public class DaoCitas {
	public static Connection con = null;

	public DaoCitas() throws SQLException {
		this.con = DBconexion.getConexion();

	}
/**
 * metodo para insertar una cita en la base de datos
 * @param c Objeto cita que recibe el método para ejecutar al consulta
 * @throws SQLException
 */
	public void insertarcita(Cita c) throws SQLException {
		String sql = "INSERT INTO cita (fecha,hora,idpsicologo,idcliente) VALUES (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, (Date) c.getFecha());
		ps.setTime(2, c.getHora());
		ps.setInt(3, c.getIdpsicologo());
		ps.setInt(4, c.getIdcliente());
		int filas = ps.executeUpdate();
		ps.close();
	}

	
	/**
	 * metodo para obtener el id de los psicologos a través del idusuario
	 * @param idusuario id del usuario psicologo para buscarlo en la base de datos
	 * @return devuelve un int con el id del psologo
	 * @throws SQLException
	 */
	public int obteneridpsicologo(int idusuario) throws SQLException {
		String sql = "SELECT * FROM psicologo WHERE idusuario=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, idusuario);

		ResultSet rs = ps.executeQuery();
		rs.next();
		int idpsic = rs.getInt("idpsicologo");

		System.out.println(idpsic);
		return idpsic;
	}
	
/**
 * Metodo para obetener el id del cliente a traves del idusuario
 * @param idusuario necesita el parametro idusuario
 * @return devuelve un int con el idcliente
 * @throws SQLException
 */
	public int obteneridcliente(int idusuario) throws SQLException {
		String sql = "SELECT * FROM cliente WHERE idusuario=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idusuario);

		ResultSet rs = ps.executeQuery();
		rs.next();

		int idcli = rs.getInt("idcliente");
		return idcli;
	}

	// Listar las citas de un psicologo
	/**
	 * metodo para listar las citas a traves del idpsicologo
	 * @param idpsicologo necesita el idpsicologo para buscar las citas asignadas a ese id
	 * @return devuelve un array de citas con ese id
	 * @throws SQLException
	 */

	public ArrayList<Cita> listarcitas(int idpsicologo) throws SQLException {

		String sql = "SELECT c.idcita, c.idcliente, c.idpsicologo, c.fecha, c.hora, u.nombre FROM cita c, cliente cl, usuarios u WHERE c.idpsicologo=? AND c.idcliente=cl.idcliente AND cl.idusuario=u.idusuario";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idpsicologo);
		ResultSet rs = ps.executeQuery();

		ArrayList<Cita> lc = new ArrayList<Cita>();
		while (rs.next()) {

			lc.add(new Cita(rs.getInt("idcita"), rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("idpsicologo"),
					rs.getInt("idcliente"), rs.getString("u.nombre")));

			System.out.println(lc);

		}
		return lc;
	}
/**
 * metodo para listar citas a traves del idcliente
 * @param idcliente necesita el idcliente para buscar las citas en la base de datos que tengan ese id asignado
 * @return devuelve un array con citas que contengan ese id pasado
 * @throws SQLException
 */
	public ArrayList<Cita> listacitas(int idcliente) throws SQLException {
		String sql = "SELECT c.idcita, c.fecha, c.hora, c.idpsicologo, c.idcliente, u.nombre FROM cita c, psicologo p, usuarios u WHERE c.idcliente=? AND c.idpsicologo=p.idpsicologo AND p.idusuario=u.idusuario";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idcliente);
		ResultSet rs = ps.executeQuery();
		ArrayList<Cita> lc = new ArrayList<Cita>();
		while (rs.next()) {

			lc.add(new Cita(rs.getInt("idcita"), rs.getDate("fecha"), rs.getTime("hora"), rs.getInt("idpsicologo"),
					rs.getInt("idcliente"), rs.getString("u.nombre")));

			System.out.println(lc);

		}
		return lc;

	}
}
