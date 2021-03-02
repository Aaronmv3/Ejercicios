package models;

public class Vehiculo {
	
	private int id;
	private String matricula;
	private String marca;
	private String modelo;
	private int ano;
	private String color;
	
	/**
	 * @param matricula
	 * @param marca
	 * @param modelo
	 * @param ano
	 * @param color
	 */
	public Vehiculo(String matricula, String marca, String modelo, int ano, String color) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.color = color;
	}

	/**
	 * 
	 */
	public Vehiculo() {
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
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return the ano
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * @param ano the ano to set
	 */
	public void setAno(int ano) {
		this.ano = ano;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "Matricula: " + this.matricula + "\nMarca: " + this.marca + "\nModelo: " + this.modelo + "\nAño: " + this.ano + "\nColor: " + this.color + "\n";
	}
	
}
