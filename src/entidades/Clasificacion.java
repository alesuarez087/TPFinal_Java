package entidades;

import data.*;

public class Clasificacion extends Entidad {

	private int valor;
	private String detalles;
	private int idUsuario;
	private int idItem;
	
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	} 
	
	public Item GetItem(){
		return DataItem.GetOne(idItem);
	}
	public Usuario GetUsuario(){
		return DataUsuario.GetOne(idUsuario);
	}
}
