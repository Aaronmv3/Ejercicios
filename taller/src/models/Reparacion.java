package models;

import java.util.Date;

public class Reparacion {
	
	private int id;
	private int cliente;
	private int vehiculo;
	private String descripcion;
	private Date fecha;
	private int tiempo;
	private int totalReparacion;
	
	/**
	 * @param cliente
	 * @param vehiculo
	 * @param descripcion
	 * @param fecha
	 * @param tiempo
	 * @param totalReparacion
	 */
	public Reparacion(int cliente, int vehiculo, String descripcion, Date fecha, int tiempo,
			int totalReparacion) {
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.tiempo = tiempo;
		this.totalReparacion = totalReparacion;
	}

	/**
	 * 
	 */
	public Reparacion() {
	}

	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the cliente
	 */
	public int getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the vehiculo
	 */
	public int getVehiculo() {
		return vehiculo;
	}

	/**
	 * @param vehiculo the vehiculo to set
	 */
	public void setVehiculo(int vehiculo) {
		this.vehiculo = vehiculo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the tiempo
	 */
	public int getTiempo() {
		return tiempo;
	}

	/**
	 * @param tiempo the tiempo to set
	 */
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * @return the totalReparacion
	 */
	public int getTotalReparacion() {
		return totalReparacion;
	}

	/**
	 * @param totalReparacion the totalReparacion to set
	 */
	public void setTotalReparacion(int totalReparacion) {
		this.totalReparacion = totalReparacion;
	}

	@Override
	public String toString() {
		return "ClienteID: " + this.cliente + "\nVehiculoID: " + this.vehiculo + "\nDescripcion: " + this.descripcion + "\nFecha: " + this.fecha + "\nTiempo: " + this.tiempo + "\nTotal: " + this.totalReparacion + "\n";
	}
	
}
