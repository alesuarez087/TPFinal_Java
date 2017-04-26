package controlador;

import java.util.ArrayList;

import data.DataArtista;
import data.DataClasificacion;
import data.DataGenero;
import data.DataItem;
import data.DataPrecio;
import data.DataProvincia;
import data.DataTipoItem;
import data.DataTipoUsuario;
import data.DataUsuario;
import data.DataVenta;
import data.DataVentaItem;
import entidades.Artista;
import entidades.Clasificacion;
import entidades.Genero;
import entidades.Item;
import entidades.Precio;
import entidades.Provincia;
import entidades.TipoItem;
import entidades.TipoUsuario;
import entidades.Usuario;
import entidades.Venta;
import entidades.VentaItem;

public class Controlador {

	//FORMATO: funcion + Clase (+ Hab) 
		//ejemplo: getAllArtista() --> devuelve todos los artistas
		// 		   getAllArtistaHab() --> devuelve todos los artistas que no fueron "eliminados"
		//		   getAllVentaForUsuario() --> devuelve todas las ventas correspondiente a un usuario
		
		//ARTISTAS
		public ArrayList<Artista> getAllArtista(){
			return DataArtista.GetAll();
		}
		
		public ArrayList<Artista> getAllArtistaHab(){
			return DataArtista.GetAllHabilitados();
		}
		
		public Artista getOneArtista(String desc){
			return DataArtista.GetOne(desc);
		}
		
		public Artista getOneArtista(int idArtista){
			return DataArtista.GetOne(idArtista);
		}
		
		public void save(Artista artista){
			DataArtista.Save(artista);
		}
		
		//GENEROS
		public ArrayList<Genero> getAllGenero(){
			return DataGenero.GetAll();
		}
		public ArrayList<Genero> getAllGeneroHab(){
			return DataGenero.GetAllHabilitados();
		}
		public Genero getOneGenero(String desc){
			return DataGenero.GetOne(desc);
		}
		
		public Genero getOneGenero(int idGenero){
			return DataGenero.GetOne(idGenero);
		}
		
		public void save(Genero gen){
			DataGenero.Save(gen);
		}
		
		//ITEMS
		public ArrayList<Item> getAllItem(){
			return DataItem.GetAll();
		}
		public ArrayList<Item> getAllItemHab(){
			return DataItem.GetAllHabilitados();
		}
		public ArrayList<Item> getAllItemForArtista(String artista){
			return DataItem.ItemsGetAllForArtista(artista);
		}
		public ArrayList<Item> getAllItemForGenero(String genero){
			return DataItem.ItemsGetAllForGenero(genero);
		}
		public ArrayList<Item> getTop8(){
			return DataItem.GetTop8();
		}
		public ArrayList<Item> getTopSemana(){
			return DataItem.GetTopSemana();
		}
		public ArrayList<Item> getTopMes(){
			return DataItem.GetTopMes();
		}
		public ArrayList<Item> getMejorPromedio(){
			return DataItem.GetMejorPromedio();
		}
		public ArrayList<Item> getBusqueda(String search){
			return DataItem.ItemsForSearch(search);
		}
		public Item getOneItem(int idItem){
			return DataItem.GetOne(idItem);
		}
		public void save(Item item){
			DataItem.Save(item);
		}
		
		//PRECIO
		public Precio getOnePrecioToday(int idItem){
			return DataPrecio.GetPrecioToday(idItem);
		}
		public Precio getOnePrecioVenta(int idItem, int idVenta){
			return DataPrecio.GetPrecioVenta(idItem, idVenta);
		}
		public void save(Precio precio){
			DataPrecio.Save(precio);
		}
		
		//PROVINCIA
		public ArrayList<Provincia> getAllProvincia(){
			return DataProvincia.ProvinciasGetAll();
		}
		public Provincia getOneProvincia(String nombreProvincia){
			return DataProvincia.GetOne(nombreProvincia);
		}

		//VENTA
		public ArrayList<Venta> getAllVenta(){
			return DataVenta.GetAll();
		}
		public ArrayList<Venta> getAllVentaForUser(int idUsuario){
			return DataVenta.VentasGetAllForUser(idUsuario); 
		}
		public void save(Venta venta){
			DataVenta.Insert(venta);
		}
		public int ultimaVenta(){
			return DataVenta.ultimaVenta();
		}
		
		//VENTAITEM
		public void save(VentaItem ventaItem){
			DataVentaItem.Insert(ventaItem);
		}
		public ArrayList<VentaItem> getAllItemVentaItem(int idUsuario){
			return DataVentaItem.GetAllItem(idUsuario);
		}
		public VentaItem getOneVentaItem(int idItem, int idVenta){
			return DataVentaItem.GetOne(idItem, idVenta);
		}
		public ArrayList<VentaItem> getAllVentaVentaItem(int idVenta){
			return DataVentaItem.GetAllVentas(idVenta);
		}
		
		//CLASIFICACION
		public ArrayList<Clasificacion> getAllClasificacion(int idItem){
			return DataClasificacion.GetAll(idItem);
		}
		public void save(Clasificacion clas){
			DataClasificacion.Save(clas);
		}
		public Clasificacion getOneClasificacion(int idItem, int idUsuario){
			return DataClasificacion.GetOne(idItem, idUsuario);
		}
		public double getPromedio(int idItem){
			return DataClasificacion.GetPromedio(idItem);
		}
		
		//TIPOS DE ITEM
		public ArrayList<TipoItem> getAllTipoItemHab(){
			return DataTipoItem.GetAllHabilitados();
		}
		
		public TipoItem getOneTipoItem(String desc){
			return DataTipoItem.GetOne(desc);
		}
		
		public TipoItem getOneTipoItem(int idTipoItem){
			return DataTipoItem.GetOne(idTipoItem);
		}
		
		//TIPOS DE USUARIO
		public ArrayList<TipoUsuario> getAllTipoUsuario(){
			return DataTipoUsuario.GetAll();
		}
		
		//USUARIO
		public ArrayList<Usuario> getAllUsuario(){
			return DataUsuario.GetAll();
		}
		public Usuario getOneUsuario(int idUsuario){
			return DataUsuario.GetOne(idUsuario);
		}
		public Usuario getOneUsuarioForName(String userName){
			return DataUsuario.GetOne(userName);
		}
		public Usuario login(String user, String pass){
			return DataUsuario.Login(user, pass);
		}
		public void save(Usuario user){
			DataUsuario.Save(user);
		}
}
