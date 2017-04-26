package entidades;

import java.util.Date;
import data.*;

public class Venta extends Entidad{

	private String titularTarjeta;
	private String nroTarjeta;
	private int idUsuario;
	private Date fecha;
	private int idProvincia;
	private String localidad;
	private String calle;
	private String nroCalle;
	private String piso;
	private String nroDpto;
	
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNroCalle() {
		return nroCalle;
	}
	public void setNroCalle(String nroCalle) {
		this.nroCalle = nroCalle;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getNroDpto() {
		return nroDpto;
	}
	public void setNroDpto(String nroDpto) {
		this.nroDpto = nroDpto;
	}
	public String getTitularTarjeta() {
		return titularTarjeta;
	}
	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}
	public String getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public double GetMonto(){
		double monto = 0;
		for(VentaItem vi : DataVentaItem.GetAllVentas(this.getId())){
			Precio precio = DataPrecio.GetPrecioVenta(vi.getIdItem(), this.getId());
			monto = monto + precio.getValor() * vi.getCantidad();
		}
		return monto;
	}
}
