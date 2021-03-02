package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import models.Vehiculo;

public class VehiculoDAO implements DAO<Vehiculo>{
	
	//Se coge un vehiculo por su id
	/**
	 * @param id
	 */
	
	@Override
	public Vehiculo get(int id) {
		Vehiculo c = null;
		Connection con = null;
		Statement st;
		ResultSet rs;

		try {
			con = ConexionBD.obterConexion();
			st = con.createStatement();
			rs = st.executeQuery("SELECT matricula, marca, modelo, ano, color, id FROM vehiculo WHERE id= " + id);

			if (rs.next()) {

				c = new Vehiculo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
				c.setId(rs.getInt(6));

			} else {
				c = null;
			}

			ConexionBD.devolverConexion(con);

		} catch (SQLException e) {
			System.out.println(
					"No se puede conectar con la base de datos. Error al obtener el vehiculo: " + e.getMessage());
		}

		return c;
	}
	//Se cogen todos los Vehiculos de la bd
	@Override
	public List<Vehiculo> getAll() {

		ArrayList<Vehiculo> listaC = new ArrayList<Vehiculo>();

		Connection con = null;
		Statement st;
		ResultSet rs;

		try {

			con = ConexionBD.obterConexion();
			st = con.createStatement();
			rs = st.executeQuery("SELECT matricula, marca, modelo, ano, color, id FROM vehiculo");

			while (rs.next()) {
				Vehiculo c = new Vehiculo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
				c.setId(rs.getInt(6));
				listaC.add(c);
			}

			ConexionBD.devolverConexion(con);

		} catch (SQLException e) {
			System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
		}

		return listaC;

	}
	//Insertar un vehiculo en la bd
	/**
	* @param t
	*/
	@Override
	public boolean save(Vehiculo t) {

		Connection con = null;
		Statement st;
		boolean rs = true;
		
		try {

			con = ConexionBD.obterConexion();
			st = con.createStatement();
			rs = st.execute("INSERT INTO vehiculo (matricula, marca, modelo, ano, color) VALUES ('" + t.getMatricula() + "', '" + t.getMarca() +  "', '" + t.getModelo()+ "', '" + t.getAno() + "', '" + t.getColor() +"');"); 
				
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
	public boolean update(Vehiculo t) {

		Connection con = null;
		Statement st;
		boolean rs = true;
		
		try {
			con = ConexionBD.obterConexion();
			st = con.createStatement();
			
			rs = st.execute("UPDATE vehiculo SET matricula='" + t.getMatricula() + "', marca='" + t.getMarca() + "', modelo='" + t.getModelo() + "', ano="+ t.getAno() + ", color='" + t.getColor() + "' WHERE id=" + t.getId());
			
			ConexionBD.devolverConexion(con);
			
		} catch (SQLException e) {
			System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
		}
		
		return rs;
	}

	//Borrar un vehiculo en la bd
	/**
	 * @param t
	*/
	@Override
	public boolean delete(Vehiculo t) {

		Connection con = null;
		Statement st;
		boolean rs = true;
		
		try {
			
			con = ConexionBD.obterConexion();
			st = con.createStatement();
			
			rs = st.execute("DELETE FROM vehiculo WHERE matricula = '" + t.getMatricula() +"';"); 

			ConexionBD.devolverConexion(con);
			
		} catch (SQLException e) {
			System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
		}
		
		return rs;
		
	}
}
