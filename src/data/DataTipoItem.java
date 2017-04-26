package data;

import java.sql.*;
import java.util.*;
import entidades.*;
import utils.*;

public class DataTipoItem {

	public static ArrayList<TipoItem> GetAllHabilitados(){
		ArrayList<TipoItem> retorno = new ArrayList<TipoItem>();
		TipoItem fila = null; ResultSet rs=null ; PreparedStatement stmt = null; 
		String sql = "{ call TiposItemGetAllHabilitados };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs != null){
				fila = new TipoItem();
				fila.setId(rs.getInt("id_tipo_item"));
				fila.setDescripcion(rs.getString("desc_tipo_item"));
				fila.setHabilitado(rs.getBoolean("habilitado"));
				retorno.add(fila);
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
		return retorno;
	}

	public static TipoItem GetOne(String desc){
		TipoItem tipo = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call TiposItemGetOne(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, desc);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				tipo = new TipoItem();
				tipo.setId(rs.getInt("id_tipo_item"));
				tipo.setDescripcion(rs.getString("desc_tipo_item"));
				tipo.setHabilitado(rs.getBoolean("habilitado"));
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
		return tipo;
	}
	
	public static TipoItem GetOne(int id){
		TipoItem tipo = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call TiposItemGetOneForID(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				tipo = new TipoItem();
				tipo.setId(rs.getInt("id_tipo_item"));
				tipo.setDescripcion(rs.getString("desc_tipo_item"));
				tipo.setHabilitado(rs.getBoolean("habilitado"));
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
		return tipo;
	}
}
