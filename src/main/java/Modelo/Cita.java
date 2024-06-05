package Modelo;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import DAO.DaoCitas;

public class Cita {
	private int idcita;
	private Date fecha;
	private Time hora;
	private int idpsicologo;
	private int idcliente;
	private String nombrecliente;
	
/**
 * Constructor utilizado para solicitar citas
 * @param idcita
 * @param fecha
 * @param hora
 * @param idpsicologo
 * @param idcliente
 * @param nombrecliente
 */
	public Cita(int idcita, Date fecha, Time hora, int idpsicologo, int idcliente, String nombrecliente) {
		super();
		this.idcita = idcita;
		this.fecha = fecha;
		this.hora = hora;
		this.idpsicologo = idpsicologo;
		this.idcliente = idcliente;
		this.nombrecliente = nombrecliente;
	}
	

	public Cita(int idcita, Date fecha, Time hora, int idpsicologo, int idcliente) {
		super();
		this.idcita = idcita;
		this.fecha = fecha;
		this.hora = hora;
		this.idpsicologo = idpsicologo;
		this.idcliente = idcliente;
	}

	public Cita(Date fecha, Time hora, int idpsicologo, int idcliente) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.idpsicologo = idpsicologo;
		this.idcliente = idcliente;
	}

	public Cita() {

	}

	public int getIdcita() {
		return idcita;
	}

	public void setIdcita(int idcita) {
		this.idcita = idcita;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public int getIdpsicologo() {
		return idpsicologo;
	}

	public void setIdpsicologo(int idpsicologo) {
		this.idpsicologo = idpsicologo;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getNombrecliente(String nombrecliente) {
		return nombrecliente;

	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	@Override
	public String toString() {
		return "Cita [idcita=" + idcita + ", fecha=" + fecha + ", hora=" + hora + ", idpsicologo=" + idpsicologo
				+ ", idcliente=" + idcliente + "]";
	}
/**
 * Metodo para crear una cita
 * @throws SQLException
 */
	public void crearcita() throws SQLException {
		DaoCitas dao = new DaoCitas();
		dao.insertarcita(this);

	}

}
