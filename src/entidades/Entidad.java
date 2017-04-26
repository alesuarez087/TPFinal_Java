package entidades;

public class Entidad {

public enum States{Alta, Baja, Consulta, Modificacion}
	
	private int id;
	private boolean habilitado;
	private States state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public States getState() {
		return state;
	}
	public void setState(States state) {
		this.state = state;
	}
}
