package Modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import DAO.DaoCitas;
import DAO.DaoPsic;
import jakarta.servlet.http.HttpServletResponse;

public class Cliente extends Usuario {

	private int idcliente;

	private Psicologo psico;

	public Cliente() {

	}

	public Cliente(int idusuario, String nombreusuario, String contrasena, String idrol, String dni, String correo,
			String telefono, String nombre, String apellidos, int idcliente, Psicologo psico) {
		super(idusuario, nombreusuario, contrasena, idrol, dni, correo, telefono, nombre, apellidos);
		this.idcliente = idcliente;
		this.psico = psico;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public Psicologo getPsico() {
		return psico;
	}

	public void setPsico(Psicologo psico) {
		this.psico = psico;
	}

	@Override
	public String toString() {
		return "Cliente [idcliente=" + idcliente + ", psico=" + psico + "]";
	}

	/**
	 * Método para listar citas de un cliente, con la fecha, hora y nombre del psicólogo
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Usuario> getCitas() throws SQLException {
		

		System.out.println("entra");
		DaoPsic daoPsic = new DaoPsic();
		ArrayList<Usuario> psic = daoPsic.psicologos();

		return psic;
	}
/**
 * Método para solicitar una cita
 * @param idusuario
 * @param psicologo
 * @param fecha
 * @param hora
 * @return
 * @throws SQLException
 */
	public Cita solicitarCita(int idusuario, int psicologo, Date fecha, Time hora) throws SQLException {
		DaoCitas idpsic;
		DaoCitas idcli;
		int idlogin = idusuario;
		int idus = psicologo;

		idcli = new DaoCitas();
		idpsic = new DaoCitas();
		int idcliente = idcli.obteneridcliente(idlogin);
		int idpsicologo = idpsic.obteneridpsicologo(idus);

		Cita c = new Cita(fecha, hora, idpsicologo, idcliente);
		System.out.println(c);

		c.crearcita();

		return c;
	}
}
