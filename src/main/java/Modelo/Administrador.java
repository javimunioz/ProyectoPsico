package Modelo;

public class Administrador extends Usuario{
private int idadmin;

public Administrador(int idusuario, String nombreusuario, String contrasena, String idrol, String dni, String correo,
		String telefono, String nombre, String apellidos, int idadmin) {
	super(idusuario, nombreusuario, contrasena, idrol, dni, correo, telefono, nombre, apellidos);
	this.idadmin = idadmin;
}

}
