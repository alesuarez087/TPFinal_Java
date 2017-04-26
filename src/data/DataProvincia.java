package data;

import java.sql.*;
import java.util.*;
import entidades.*;
import utils.*;

public class DataProvincia {

	public static ArrayList<Provincia> ProvinciasGetAll(){
		ArrayList<Provincia> list = new ArrayList<Provincia>();
		Provincia prov = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ProvinciasGetAll() };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				prov = new Provincia();
				prov.setId(rs.getInt("id_provincia"));
				prov.setDescripcion(rs.getString("desc_provincia"));
				
				list.add(prov);
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
	
	public static Provincia GetOne(String descProvincia){
		Provincia prov = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call ProvinciasGetOne(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, descProvincia);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				prov = new Provincia();
				prov.setId(rs.getInt("id_provincia"));
				prov.setDescripcion(rs.getString("desc_provincia"));
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
		return prov;
	}
}
