package Modelo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import DAO.DaoCitas;

public class Psicologo extends Usuario {
	private int idpsicologo;
	private String especialidad;

	public Psicologo() {

	}

	public Psicologo(int idusuario, String nombreusuario, String contrasena, String idrol, String dni, String correo,
			String telefono, String nombre, String apellidos, int idpsicologo, String especialidad) {
		super(idusuario, nombreusuario, contrasena, idrol, dni, correo, telefono, nombre, apellidos);
		this.idpsicologo = idpsicologo;
		this.especialidad = especialidad;
	}

	public int getIdpsicologo() {
		return idpsicologo;
	}

	public void setIdpsicologo(int idpsicologo) {
		this.idpsicologo = idpsicologo;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Psicologo [idpsicologo=" + idpsicologo + ", especialidad=" + especialidad + "]";
	}

	/**
	 * Metodo para listas las citasd de un psicologo
	 * @param idusuario
	 * @return 
	 * @throws SQLException
	 */
public String listadecitas (int idusuario) throws SQLException{
	DaoCitas idpsic = new DaoCitas();
	DaoCitas cita = new DaoCitas();
	int idpsicologo = idpsic.obteneridpsicologo(idusuario);
	System.out.println(idpsicologo);
	ArrayList<Cita> listarcitas = cita.listarcitas(idpsicologo);

	String resultados = new Gson().toJson(listarcitas);
	
	System.out.println(resultados);
	
	return resultados;
}
	

}