package data;

import java.sql.*;
import java.util.*;
import entidades.*;
import entidades.Entidad.States;
import utils.*;

public class DataArtista {

	public static ArrayList<Artista> GetAll(){
		ArrayList<Artista> list = new ArrayList<Artista>();
		Artista art = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ArtistasGetAll };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				art = new Artista();
				art.setId(rs.getInt("id_artista"));
				art.setNombre(rs.getString("nombre_artista"));
				art.setHabilitado(rs.getBoolean("habilitado"));
				
				list.add(art);
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
	
	public static ArrayList<Artista> GetAllHabilitados(){
		ArrayList<Artista> list = new ArrayList<Artista>();
		Artista art = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ArtistasGetAllHabilitado };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				art = new Artista();
				art.setId(rs.getInt("id_artista"));
				art.setNombre(rs.getString("nombre_artista"));
				art.setHabilitado(rs.getBoolean("habilitado"));
				
				list.add(art);
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
	
	public static Artista GetOne(String desc){
		Artista art = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ArtistasGetOne(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, desc);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				art = new Artista();
				art.setId(rs.getInt("id_artista"));
				art.setNombre(rs.getString("nombre_artista"));
				art.setHabilitado(rs.getBoolean("habilitado"));
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
		return art;
	}

	public static Artista GetOne(int id){
		Artista art = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ArtistasGetOneForID(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				art = new Artista();
				art.setId(rs.getInt("id_artista"));
				art.setNombre(rs.getString("nombre_artista"));
				art.setHabilitado(rs.getBoolean("habilitado"));
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
		return art;
	}
	
	public static void Save(Artista art){
		if(art.getState()==States.Alta) Insert(art);
		else if(art.getState()==States.Baja) Delete(art);
		else if(art.getState()==States.Modificacion) Update(art);
		else art.setState(States.Consulta);
	}

	private static void Insert(Artista art){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ArtistasInsert(?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, art.getNombre());
			stmt.setBoolean(2, art.isHabilitado());
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

	private static void Update(Artista art){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ArtistasUpdate(?, ?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, art.getNombre());
			stmt.setBoolean(2, art.isHabilitado());
			stmt.setInt(3, art.getId());
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
	
	private static void Delete(Artista art){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ArtistasDelete(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, art.getId());
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
