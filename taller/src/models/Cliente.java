package models;

public class Cliente {
	private int id;
	private String dni;
	private String Nombre;
	private String Apellidos;
	private int edad;
	

	/**
	 * @param dni
	 * @param nombre
	 * @param apellidos
	 * @param edad
	 */
	public Cliente(String dni, String nombre, String apellidos, int edad) {

		this.dni = dni;
		this.Nombre = nombre;
		this.Apellidos = apellidos;
		this.edad = edad;
	}
	

	/**
	 * 
	 */
	public Cliente() {
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
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return Nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return Apellidos;
	}

	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}


	@Override
	public String toString() {
		return "DNI: " + this.dni + "\nNombre: " + this.Nombre + "\nApellido: " + this.Apellidos + "\nEdad: " + this.edad + "\n";
	}
	
	
	
}
