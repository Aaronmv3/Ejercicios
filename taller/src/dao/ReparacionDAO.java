/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import models.Reparacion;

public class ReparacionDAO implements DAO<Reparacion> {
	
	//Se coge una Reparacion por su id
		/**
		 * @param id
		 */
		
		@Override
		public Reparacion get(int id) {
			Reparacion r = null;
			Connection con = null;
			Statement st;
			ResultSet rs;

			try {
				con = ConexionBD.obterConexion();
				st = con.createStatement();
				rs = st.executeQuery("SELECT cliente, vehiculo, descripcion, fecha, tiempo, totalReparacion, id FROM reparacion WHERE id= " + id);

				if (rs.next()) {

					r = new Reparacion(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getDate(4), rs.getInt(5),rs.getInt(6));
					r.setId(rs.getInt(7));
				} else {
					r = null;
				}

				ConexionBD.devolverConexion(con);

			} catch (SQLException e) {
				System.out.println(
						"No se puede conectar con la base de datos. Error al obtener la reparacion: " + e.getMessage());
			}

			return r;
		}
		//Se cogen todos las reparaciones de la bd
		@Override
		public List<Reparacion> getAll() {

			ArrayList<Reparacion> listaR = new ArrayList<Reparacion>();

			Connection con = null;
			Statement st;
			ResultSet rs;

			try {

				con = ConexionBD.obterConexion();
				st = con.createStatement();
				rs = st.executeQuery("SELECT cliente, vehiculo, descripcion, fecha, tiempo, totalReparacion, id FROM reparacion");

				while (rs.next()) {
					Reparacion r = new Reparacion(rs.getInt(1),rs.getInt(2),rs.getString(3), rs.getDate(4), rs.getInt(5),rs.getInt(6));
					r.setId(rs.getInt(7));
					listaR.add(r);
				}

				ConexionBD.devolverConexion(con);

			} catch (SQLException e) {
				System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
			}

			return listaR;

		}
		//Insertar un vehiculo en la bd
		/**
		* @param t
		*/
		@Override
		public boolean save(Reparacion t) {

			Connection con = null;
			Statement st;
			boolean rs = true;
			Date fechasql;
			
			try {
			
				fechasql = convertirFecha(t.getFecha());
				con = ConexionBD.obterConexion();
				st = con.createStatement();
				rs = st.execute("INSERT INTO reparacion (cliente, vehiculo, descripcion, fecha, tiempo, totalReparacion) VALUES ('" + t.getCliente() + "', '" + t.getVehiculo() +  "', '" + t.getDescripcion()+ "', '" + fechasql + "', '" + t.getTiempo() + "', '" + t.getTotalReparacion() +"');"); 
					
				ConexionBD.devolverConexion(con);
				
			} catch (SQLException e) {
				System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
			}
			
			return rs;
		}
		//Modificar un vehiculo en la bd
		/**
		* @param t
		*/
		@Override
		public boolean update(Reparacion t) {

			Connection con = null;
			Statement st;
			boolean rs = true;
			Date fechasql;
			
			try {
				fechasql = convertirFecha(t.getFecha());
				con = ConexionBD.obterConexion();
				st = con.createStatement();
				
				rs = st.execute("UPDATE reparacion SET cliente='" + t.getCliente() + "', vehiculo='" + t.getVehiculo() + "', descripcion='" + t.getDescripcion() + "', fecha="+ fechasql + ", tiempo='" + t.getTiempo() + ", totalReparacion='" + t.getTotalReparacion() + "' WHERE id=" + t.getId());
				
				ConexionBD.devolverConexion(con);
				
			} catch (SQLException e) {
				System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
			}
			
			return rs;
		}
		
		@Override
		public boolean delete(Reparacion t) {
			return false;		
		}
		
		
		private static java.sql.Date convertirFecha(java.util.Date uDate) {
	        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
	        return sDate;
	    }
}
