package data;

import java.sql.*;
import java.util.*;
import entidades.*;
import utils.*;

public class DataVentaItem {

	public static ArrayList<VentaItem> GetAllItem(int IdUsuario){
		ArrayList<VentaItem> list = new ArrayList<VentaItem>();
		VentaItem vi = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentaItemGetAllForUser(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, IdUsuario);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				vi = new VentaItem();
				vi.setCantidad(rs.getInt("cantidad"));
				vi.setIdItem(rs.getInt("id_item"));
				vi.setIdVenta(rs.getInt("id_venta"));
				
				list.add(vi);
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
	
	public static ArrayList<VentaItem> GetAllVentas(int IdVenta){
		ArrayList<VentaItem> list = new ArrayList<VentaItem>();
		VentaItem vi = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentaItemGetAllForVenta(?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, IdVenta);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				vi = new VentaItem();
				vi.setCantidad(rs.getInt("cantidad"));
				vi.setIdItem(rs.getInt("id_item"));
				vi.setIdVenta(rs.getInt("id_venta"));
				
				list.add(vi);
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
	
	public static VentaItem GetOne(int idItem, int idVenta){
		VentaItem vi = null; ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentaItemGetOne(?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, idItem);
			stmt.setInt(2, idVenta);
			rs = stmt.executeQuery();
			while(rs.next() && rs!=null){
				vi = new VentaItem();
				vi.setCantidad(rs.getInt("cantidad"));
				vi.setIdItem(rs.getInt("id_item"));
				vi.setIdVenta(rs.getInt("id_venta"));
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
		return vi;
	}
	
	
	public static void Insert(VentaItem ventaItem){
		ResultSet rs = null; PreparedStatement stmt = null;
		String sql="{ call VentaItemInsert(?, ?, ?) };";
		try{
			Connection conn = FactoryConexion.getInstancia().getConn();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ventaItem.getCantidad());
			stmt.setInt(2, ventaItem.getIdItem());
			stmt.setInt(3, ventaItem.getIdVenta());
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
