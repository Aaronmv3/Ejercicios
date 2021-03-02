package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.ConexionBD;
import models.Cliente;

public class ClienteDAO implements DAO<Cliente> {

	// Se coge un cliente por su id
	/**
	 * @param id
	 */
	@Override
	public Cliente get(int id) {
		Cliente c = null;
		Connection con = null;
		Statement st;
		ResultSet rs;

		try {
			con = ConexionBD.obterConexion();
			st = con.createStatement();
			rs = st.executeQuery("SELECT dni, nombre, apellidos, edad, id FROM cliente WHERE id= " + id);

			if (rs.next()) {

				c = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				c.setId(rs.getInt(5));

			} else {
				c = null;
			}

			ConexionBD.devolverConexion(con);

		} catch (SQLException e) {
			System.out.println(
					"No se puede conectar con la base de datos. Error al obtener el cliente: " + e.getMessage());
		}

		return c;
	}

	// Se cogen todos los clientes de la bd
	@Override
	public List<Cliente> getAll() {

		ArrayList<Cliente> listaC = new ArrayList<Cliente>();

		Connection con = null;
		Statement st;
		ResultSet rs;

		try {

			con = ConexionBD.obterConexion();
			st = con.createStatement();
			rs = st.executeQuery("SELECT dni, nombre, apellidos, edad, id FROM cliente");

			while (rs.next()) {
				Cliente c = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				c.setId(rs.getInt(5));
				listaC.add(c);
			}

			ConexionBD.devolverConexion(con);

		} catch (SQLException e) {
			System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
		}

		return listaC;

	}

	// Insertar un cliente en la bd
	/**
	 * @param t
	 */
	@Override
	public boolean save(Cliente t) {

		Connection con = null;
		Statement st;
		boolean rs = true;

		try {

			con = ConexionBD.obterConexion();
			st = con.createStatement();
			rs = st.execute("INSERT INTO cliente (dni, nombre, apellidos, edad) VALUES ('" + t.getDni() + "', '"
					+ t.getNombre() + "', '" + t.getApellidos() + "', '" + t.getEdad() + "');");

			ConexionBD.devolverConexion(con);

		} catch (SQLException e) {
			System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
		}

		return rs;
	}

	// Modificar un cliente en la bd
	/**
	 * @param t
	 */
	@Override
	public boolean update(Cliente t) {

		Connection con = null;
		Statement st;
		boolean rs = true;

		try {
			con = ConexionBD.obterConexion();
			st = con.createStatement();

			rs = st.execute("UPDATE cliente SET dni='" + t.getDni() + "', nombre='" + t.getNombre() + "', apellidos='"
					+ t.getApellidos() + "', edad=" + t.getEdad() + " WHERE id=" + t.getId());

			ConexionBD.devolverConexion(con);

		} catch (SQLException e) {
			System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
		}

		return rs;
	}

	// Borrar un cliente en la bd
	/**
	 * @param t
	 */

	@Override
	public boolean delete(Cliente t) {

		Connection con = null;
		Statement st;
		boolean rs = true;

		try {

			con = ConexionBD.obterConexion();
			st = con.createStatement();

			rs = st.execute("DELETE FROM cliente WHERE dni = '" + t.getDni() + "';");

			ConexionBD.devolverConexion(con);

		} catch (SQLException e) {
			System.out.println("No se puede conectar con la base de datos. " + e.getMessage());
		}

		return rs;

	}

}
