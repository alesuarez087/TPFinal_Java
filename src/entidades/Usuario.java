package entidades;

public class Usuario extends Entidad {

public static enum TiposUsuario{Administrador, Usuario, Empleado}
	
	private String nombreUsuario;
	private String clave;
	private String nombre;
	private String apellido;
	private String email;
	private String dni;
	private TiposUsuario TipoUsuario;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TiposUsuario getTipoUsuario() {
		return TipoUsuario;
	}
	public void setTipoUsuario(TiposUsuario tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}
}
