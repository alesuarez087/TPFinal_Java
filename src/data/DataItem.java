package data;

import java.sql.*;
import java.util.*;
import entidades.*;
import entidades.Entidad.States;
import entidades.Item.TiposDisco;
import utils.*;

public class DataItem {

	// 1 BlueRay, 2 CD, 3 DVD, 4 Pasta, 5 Vinilo 
	
		public static ArrayList<Item> GetAll(){
			ArrayList<Item> list = new ArrayList<Item>();
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsGetAll };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
					
					list.add(item);
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return list;
		}
		
		public static ArrayList<Item> GetAllHabilitados(){
			ArrayList<Item> list = new ArrayList<Item>();
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsGetAllHabilitado };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
					
					list.add(item);
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return list;
		}
		
		public static ArrayList<Item> ItemsGetAllForArtista(String artista){
			ArrayList<Item> list = new ArrayList<Item>();
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsGetAllForArtista(?) };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, artista);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
					
					list.add(item);
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return list;
		}
		
		public static ArrayList<Item> ItemsGetAllForGenero(String genero){
			ArrayList<Item> list = new ArrayList<Item>();
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsGetAllForGenero(?) };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, genero);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
					
					list.add(item);
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return list;
		}
		
		public static ArrayList<Item> GetTop8(){
			ArrayList<Item> list = new ArrayList<Item>();
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsGetTop8 };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
					
					list.add(item);
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return list;
		}
		
		public static ArrayList<Item> GetTopSemana(){
			ArrayList<Item> list = new ArrayList<Item>();
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsGetTopSemana };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
					
					list.add(item);
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return list;
		}
		
		public static ArrayList<Item> GetTopMes(){
			ArrayList<Item> list = new ArrayList<Item>();
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsGetTopMes };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
					
					list.add(item);
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return list;
		}
		
		public static ArrayList<Item> GetMejorPromedio(){
			ArrayList<Item> list = new ArrayList<Item>();
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsGetMejorPromedio };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
					
					list.add(item);
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return list;
		}
		
		public static ArrayList<Item> ItemsForSearch(String search){
			ArrayList<Item> list = new ArrayList<Item>();
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsBusqueda(?) };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, search);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
					
					list.add(item);
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return list;
		}
		
		public static Item GetOne(int id){
			Item item = null; ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsGetOne(?) };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				while(rs.next() && rs!=null){
					item = new Item();
					item.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
					item.setHabilitado(rs.getBoolean("habilitado"));
					item.setId(rs.getInt("id_item"));
					item.setIdArtista(rs.getInt("id_artista"));
					item.setIdGenero(rs.getInt("id_genero"));
					item.setStock(rs.getInt("stock"));
					item.setTitulo(rs.getString("titulo"));
					item.setUrlPortada(rs.getString("url_portada"));
					switch(rs.getInt("id_tipo_disco")){
						case 1: item.setTipoDisco(TiposDisco.BlueRay); break;
						case 2: item.setTipoDisco(TiposDisco.CD); break;
						case 3: item.setTipoDisco(TiposDisco.DVD); break;
						case 4: item.setTipoDisco(TiposDisco.Pasta); break;
						case 5: item.setTipoDisco(TiposDisco.Vinilo); break;
					}
				}
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
			return item;
		}
		
		public static void Save(Item item){
			if(item.getState()==States.Alta) Insert(item);
			else if(item.getState()==States.Baja) Delete(item);
			else if(item.getState()==States.Modificacion) Update(item);
			else item.setState(States.Consulta);
		}

		private static void Insert(Item item){
			ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsInsert(?, ?, ?, ?, ?, ?, ?, ?) }";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, item.getAnioLanzamiento()); 
				stmt.setBoolean(2, item.isHabilitado());
				stmt.setInt(3, item.getIdArtista());
				stmt.setInt(4, item.getIdGenero());
				stmt.setInt(5, item.getStock());
				stmt.setString(6, item.getTitulo());

				switch(item.getTipoDisco()){
					case BlueRay: stmt.setInt(7, 1); break;
					case CD: stmt.setInt(7, 2); break;
					case DVD: stmt.setInt(7, 3); break;
					case Pasta: stmt.setInt(7, 4); break;
					case Vinilo: stmt.setInt(7, 5); break;
				}
				
				stmt.setString(8, item.getUrlPortada());
				rs = stmt.executeQuery();
				
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
		}

		private static void Update(Item item){
			ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsUpdate(?, ?, ?, ?, ?, ?, ?, ?, ?) };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, item.getAnioLanzamiento()); 
				stmt.setBoolean(2, item.isHabilitado());
				stmt.setInt(3, item.getIdArtista());
				stmt.setInt(4, item.getIdGenero());
				stmt.setInt(5, item.getStock());
				stmt.setString(6, item.getTitulo());
				
				switch(item.getTipoDisco()){
					case BlueRay: stmt.setInt(7, 1); break;
					case CD: stmt.setInt(7, 2); break;
					case DVD: stmt.setInt(7, 3); break;
					case Pasta: stmt.setInt(7, 4); break;
					case Vinilo: stmt.setInt(7, 5); break;
				}
				
				stmt.setInt(8, item.getId());
				stmt.setString(9, item.getUrlPortada());
				rs = stmt.executeQuery();
				
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
		}
		
		private static void Delete(Item item){
			ResultSet rs = null; PreparedStatement stmt = null;
			String sql="{ call ItemsDelete(?) };";
			try{
				Connection conn = FactoryConexion.getInstancia().getConn();
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, item.getId());
				rs = stmt.executeQuery();
				
			} catch(SQLException e){
				e.printStackTrace();
			} catch(ApplicationException e){
				e.printStackTrace();
			}
			finally{
				try {
					FactoryConexion.getInstancia().releaseConn();
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(stmt != null) stmt = null;
				if(rs!=null) rs = null;
			}
		}
}
