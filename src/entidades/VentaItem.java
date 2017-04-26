package entidades;

import data.*;

public class VentaItem {

	private int idVenta;
	private int idItem;
	private int cantidad;
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Item GetItem(){
		return DataItem.GetOne(idItem);
	}
	public Venta GetVenta(){
		return DataVenta.GetOne(idVenta);
	}
}
