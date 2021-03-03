package taller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.Optional;

import acciones.Eliminar;
import acciones.Informes;
import acciones.Insertar;
import acciones.Modificar;
import acciones.Buscar;
import acciones.CargarGuardar;
import models.Cliente;
import models.Vehiculo;

public class Taller {

	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean exit = false;
		boolean listo;
		new CargarGuardar().cargar();

		do {
			try {
				System.out.println("\nBienvenido al taller");
				System.out.println("Elija lo que quiere hacer: ");
				System.out.println("1.- Gestionar los clientes");
				System.out.println("2.- Gestionar los vehículos");
				System.out.println("3.- Gestionar reparaciones");
				System.out.println("4.- Informes");
				System.out.println("5.- Salir");

				int opcion = Integer.parseInt(br.readLine());

				switch (opcion) {
				case 1:
					System.out.println("");
					System.out.println("--------------------------------------");
					System.out.println("Gestion de clientes: ");
					System.out.println("1.- Insertar cliente");
					System.out.println("2.- Buscar cliente");
					System.out.println("3.- Modificar cliente");
					System.out.println("4.- Eliminar cliente");
					opcion = Integer.parseInt(br.readLine());

					switch (opcion) {
					case 1:
						listo = new Insertar().insertar("cliente");

						if (listo == false) {
							System.out.println("Usuario insertado con exito");
							System.out.println("");
						} else {
							System.out.println("Fallo al insertar usuario");
							System.out.println("");
						}
						break;
					case 2:
						System.out.println("");
						System.out.println("--------------------------------------");
						System.out.println("1.- Buscar por DNI");
						System.out.println("2.- Buscar por nombre y apellidos");
						opcion = Integer.parseInt(br.readLine());
						switch (opcion) {
						case 1:
							String dni;

							System.out.println("--------------------------------------");
							System.out.println("Introduzca dni: ");
							dni = br.readLine();

							Optional<Cliente> cli = new Buscar().busquedaDNI(dni);

							if (cli.isEmpty()) {
								System.out.println("\nEl dni buscado no existe");
							} else{
							System.out.println("---------------------");
							System.out.println(cli.get().toString());
							System.out.println("---------------------");
						}
							;
							break;
						case 2:
							String nombre;
							String apellido;

							System.out.println("--------------------------------------");

							System.out.println("Introduce nombre: ");
							nombre = br.readLine();

							System.out.println("Introduce apellido: ");
							apellido = br.readLine();

							new Buscar().busquedaNombreApellido(nombre, apellido);

							break;
						}
						break;
					case 3:
						listo = new Modificar().actualizar("cliente");

						if (listo == false) {
							System.out.println("Usuario modificado con exito");
							System.out.println("");
						} else {
							System.out.println("Fallo al modificar usuario");
							System.out.println("");
						}
						break;
					case 4:
						listo = new Eliminar().eliminar("cliente");

						if (listo == false) {
							System.out.println("Usuario eliminado con exito");
							System.out.println("");
						} else {
							System.out.println("Fallo al eliminar usuario");
							System.out.println("");
						}
						break;
					}
					break;
				case 2:
					System.out.println("");
					System.out.println("--------------------------------------");
					System.out.println("Gestion de vehículos: ");
					System.out.println("1.- Insertar vehículo");
					System.out.println("2.- Buscar vehículo");
					System.out.println("3.- Modificar vehículo");
					System.out.println("4.- Eliminar vehículo");
					opcion = Integer.parseInt(br.readLine());

					switch (opcion) {
					case 1:
						listo = new Insertar().insertar("vehiculo");

						if (listo == false) {
							System.out.println("Vehiculo insertado con exito");
							System.out.println("");
						} else {
							System.out.println("Fallo al insertar vehiculo");
							System.out.println("");
						}
						break;
					case 2:

						String matricula;
						String modelo;
						String marca;
						int ano;

						System.out.println("");
						System.out.println("--------------------------------------");
						System.out.println("1.- Buscar por matricula");
						System.out.println("2.- Buscar por marca/modelo");
						System.out.println("3.- Buscar por marca/modelo/año");
						opcion = Integer.parseInt(br.readLine());
						switch (opcion) {
						case 1:
							System.out.println("--------------------------------------");

							System.out.println("Introduce matricula a buscar");
							matricula = br.readLine();

							Optional<Vehiculo> vehiculo = new Buscar().busquedaMatricula(matricula);

							if (vehiculo.isEmpty()) {
								System.out.println("\nNo existe ningun vehiculo con esa matricula\n");
							} else {
								System.out.println("\n--------------------------------------");
								System.out.println(vehiculo.get().toString());
							}
							break;
						case 2:
							System.out.println("\n--------------------------------------\n");

							System.out.println("Introduce marca");
							marca = br.readLine();

							System.out.println("\nIntroduce modelo");
							modelo = br.readLine();

							new Buscar().busquedaMarcaModelo(marca, modelo);
							break;
						case 3:
							System.out.println("\n--------------------------------------\n");

							System.out.println("Introduce marca");
							marca = br.readLine();

							System.out.println("\nIntroduce modelo");
							modelo = br.readLine();
							
							System.out.println("\nIntroduce año");
							ano = Integer.parseInt(br.readLine());

							new Buscar().busquedaMarcaModeloAno(marca, modelo, ano);
							break;
						}
						break;
					case 3:
						listo = new Modificar().actualizar("vehiculo");

						if (listo == false) {
							System.out.println("Vehiculo modificado con exito");
							System.out.println("");
						} else {
							System.out.println("Fallo al modificar Vehiculo");
							System.out.println("");
						}
						break;
					case 4:
						listo = new Eliminar().eliminar("vehiculo");

						if (listo == false) {
							System.out.println("Vehiculo eliminado con exito");
							System.out.println("");
						} else {
							System.out.println("Fallo al eliminar vehiculo");
							System.out.println("");
						}
						break;
					}

					break;

				case 3:
					System.out.println("");
					System.out.println("--------------------------------------");
					System.out.println("Gestion de reparaciones: ");
					System.out.println("1.- Insertar reparación");
					System.out.println("2.- Buscar reparación");
					System.out.println("3.- Modificar reparación");
					opcion = Integer.parseInt(br.readLine());

					switch (opcion) {
					case 1:
						listo = new Insertar().insertar("reparacion");

						if (listo == false) {
							System.out.println("Reparacion insertada con exito");
							System.out.println("");
						} else {
							System.out.println("Fallo al insertar reparacion");
							System.out.println("");
						}
						break;
					case 2:					
						System.out.println("");
						System.out.println("--------------------------------------");
						System.out.println("1.- Buscar por cliente");
						System.out.println("2.- Buscar por vehículo");
						System.out.println("3.- Buscar por fecha");
						opcion = Integer.parseInt(br.readLine());
						switch (opcion) {
						case 1:
							
							String cliente;
							System.out.println("\n--------------------------------------\n");
							
							System.out.println("Introduzca DNI del cliente: ");
							cliente = br.readLine();
							
							System.out.println("\n--------------------------------------\n");
							new Buscar().busquedaClienteReparacion(cliente);
							break;
						case 2:
							String matricula;
							System.out.println("\n--------------------------------------\n");
							
							System.out.println("Introduzca matricula del vehiculo: ");
							matricula = br.readLine();
							
							System.out.println("\n--------------------------------------\n");
							new Buscar().busquedaMatriculaReparacion(matricula);
							break;
						case 3:
							Date fecha;
							System.out.println("\n--------------------------------------\n");
							
							System.out.println("Introduzca fecha (formato: yyyy-mm-dd): ");
							fecha = java.sql.Date.valueOf(br.readLine());

							
							System.out.println("\n--------------------------------------\n");
							new Buscar().busquedaFechaReparacion(fecha);
							break;
						}
						break;
					case 3:
						break;
					}

					break;
				case 4:
					System.out.println("--------------------------------------");
					System.out.println("Informes: ");
					System.out.println("1.- Listar clientes por edad");
					System.out.println("2.- 10 clientes con mas reparaciones");
					System.out.println("3.- Reparacion mas costosa");
					System.out.println("4.- Reparacion mas barata");
					opcion = Integer.parseInt(br.readLine());
					
					switch(opcion) {
					case 1:
						System.out.println("\n--------------------------------------");
						new Informes().CliPorEdad();
						break;
					case 2:
						System.out.println("\n--------------------------------------");
						new Informes().CliMasReparaciones();
						break;
					case 3:
						System.out.println("\n--------------------------------------");
						new Informes().RepMaxCosto();
						break;
					case 4:
						System.out.println("\n--------------------------------------");
						new Informes().RepMinCosto();
						break;
					}
					break;
				case 5:
					exit = true;
					new CargarGuardar().guardar();
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println("El valor introducido no es valido por favor intentelo de nuevo");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (exit == false);
	}

}
