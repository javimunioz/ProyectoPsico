package Modelo;

import java.sql.Date;
import java.sql.SQLException;

import DAO.DaoUsuario;

public class Usuario {

	private int idusuario;
	private String nombreusuario;
	private String contrasena;
	private String idrol;
	private String dni;
	private String correo;
	private String telefono;
	private String nombre;
	private String apellidos;
	private String foto;

	public Usuario() {

	}


	public Usuario(int idusuario,String correo, String telefono, String nombreusuario, String contrasena) {
		super();
		this.idusuario=idusuario;
		this.correo = correo;
		this.telefono = telefono;
		this.nombreusuario = nombreusuario;
		this.contrasena= contrasena;
		
	}

/**
 * Constructor del login
 * @param nombreusuario
 * @param contrasena
 */
	// constructor del login
	public Usuario(String nombreusuario, String contrasena) {
	super ();
		this.nombreusuario = nombreusuario;
		this.contrasena = contrasena;

	}

	public Usuario(String nombreusuario, String contrasena, String idrol, String dni, String correo, String telefono,
			String nombre, String apellidos, String foto) {
		super();
		this.nombreusuario = nombreusuario;
		this.contrasena = contrasena;
		this.idrol = idrol;
		this.dni = dni;
		this.correo = correo;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.foto = foto;
	}

	public Usuario(int idusuario, String nombreusuario, String contrasena, String idrol, String dni, String correo,
			String telefono, String nombre, String apellidos, String foto) {
		super();
		this.idusuario = idusuario;
		this.nombreusuario = nombreusuario;
		this.contrasena = contrasena;
		this.idrol = idrol;
		this.dni = dni;
		this.correo = correo;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	/**
	 * Constructor del modificar perfil
	 * @param nombreusuario
	 * @param contrasena
	 * @param correo
	 * @param telefono
	 * @param nombre
	 * @param apellidos
	 * @param foto
	 */
	// constructor modificar perfil Y PRUEBALISTA

	public Usuario(String nombreusuario, String contrasena, String correo, String telefono, String nombre,
			String apellidos, String foto) {
		super();
		this.nombreusuario = nombreusuario;
		this.contrasena = contrasena;
		this.correo = correo;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.foto = foto;
	}

		/**
		 * Constructor del registro
		 * @param nombreusuario
		 * @param contrasena
		 * @param idrol
		 * @param dni
		 * @param correo
		 * @param telefono
		 * @param nombre
		 * @param apellidos
		 */
	// Contructor del registro

	public Usuario(String nombreusuario, String contrasena, String idrol, String dni, String correo, String telefono,
			String nombre, String apellidos) {
		super();
		this.nombreusuario = nombreusuario;
		this.contrasena = contrasena;
		this.idrol = idrol;
		this.dni = dni;
		this.correo = correo;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	// constructor Daopsic
	public Usuario(int idusuario, String nombre, String apellidos, String correo, String telefono, String foto) {
		this.idusuario = idusuario;
		this.correo = correo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.foto = foto;
	}

	public Usuario(int idusuario, String nombreusuario, String contrasena, String idrol, String dni, String correo,
			String telefono, String nombre, String apellidos) {
		super();
		this.idusuario = idusuario;
		this.nombreusuario = nombreusuario;
		this.contrasena = contrasena;
		this.idrol = idrol;
		this.dni = dni;
		this.correo = correo;
		this.telefono = telefono;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getIdrol() {
		return idrol;
	}

	public void setIdrol(String idrol) {
		this.idrol = idrol;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;

	}
	/**
	 * Metodo parainsertar en la base de datos
	 * @throws SQLException
	 */
	public void insertar() throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.insertar(this);
	}
/**
 * Metodo para modificar el perfil, utilizado en psicologo
 * @throws SQLException
 */
	public void modificarperfil() throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.modificarperfil(this);
	}
/**
 * Metodo que registra al usuario en la web
 * @throws SQLException
 */
	public void registrar() throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.registrar(this);
		
	}
	/**
	 * Metodo para borrar al usuario mediante su id
	 * @param idusuario
	 * @throws SQLException
	 */
	public void borrar (int idusuario) throws SQLException {
		DaoUsuario dao = new DaoUsuario();
		dao.borrar(idusuario);
		
	}
/**
 * Metodo que hace un proceso de login del usuario, comprobando su nombre de usuario y su contraseña en la base de datos.
 * @param contrasena
 * @return
 * @throws SQLException
 */
	public boolean logeo(String contrasena) throws SQLException {

		boolean encontrado = false;
		DaoUsuario dao = new DaoUsuario();
		Usuario us = dao.login(this, contrasena);
		dao.login(this, contrasena);
		if (us != null) {
			encontrado = true;
			this.setNombre(us.getNombre());
			this.setApellidos(us.getApellidos());
			this.setIdusuario(us.getIdusuario());
			this.setNombreusuario(us.getNombreusuario());
			this.setCorreo(us.getCorreo());
			this.setTelefono(us.getTelefono());
			this.setIdrol(us.getIdrol());
			this.setDni(us.getDni());

		}
		return encontrado;

	}
	

	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", nombreusuario=" + nombreusuario + ", contraseña=" + contrasena
				+ ", idrol=" + idrol + ", dni=" + dni + ", correo=" + correo + ", telefono=" + telefono + ", nombre="
				+ nombre + ", apellidos=" + apellidos + "]";
	}

}