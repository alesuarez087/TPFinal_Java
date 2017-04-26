package entidades;

import data.*;

public class Item extends Entidad{

public static enum TiposDisco{ CD, DVD, Vinilo, Pasta, BlueRay}
	
	private int stock;
	private String titulo;
	private String anioLanzamiento;
	private TiposDisco tipoDisco;
	private int idArtista;
	private int idGenero;
	private String urlPortada;
	
	public String getUrlPortada() {
		return urlPortada;
	}
	public void setUrlPortada(String urlPortada) {
		this.urlPortada = urlPortada;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAnioLanzamiento() {
		return anioLanzamiento;
	}
	public void setAnioLanzamiento(String anioLanzamiento) {
		this.anioLanzamiento = anioLanzamiento;
	}
	public TiposDisco getTipoDisco() {
		return tipoDisco;
	}
	public void setTipoDisco(TiposDisco tipoDisco) {
		this.tipoDisco = tipoDisco;
	}
	public int getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	
	
	public Artista GetArtista(){
		return DataArtista.GetOne(idArtista);
	}
	
	public Genero GetGenero(){
		return DataGenero.GetOne(idGenero);
	}
	
	public void quitoStock(int cant){
		stock -= cant;
	}
	public void sumoStock(int cant){
		stock += cant;
	}
	public Precio GetPrecio(){
		return DataPrecio.GetPrecioToday(this.getId());
	}
}
