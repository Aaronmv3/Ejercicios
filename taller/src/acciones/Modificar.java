package acciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.stream.Stream;

import dao.ClienteDAO;
import dao.ReparacionDAO;
import dao.VehiculoDAO;
import models.Cliente;
import models.Reparacion;
import models.Vehiculo;

public class Modificar {
	// Creamos objetos estaticos para acceder a los metodos.
	static ClienteDAO cl = new ClienteDAO();
	static VehiculoDAO vh = new VehiculoDAO();
	static ReparacionDAO rp = new ReparacionDAO();

	public boolean actualizar(String objeto) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String result;

		// Creamos la clase modificar a la que se le pasa un objeto para identificar lo
		// que se va a modificar
		switch (objeto) {
		case "cliente":
			Stream<Cliente> sc = cl.getAll().stream();

			try {
				System.out.println("Elija cliente a modificar(numero): \n");

				// Se muestran todos los usuarios disponibles en la bd y el usuario escoge en
				// funcion al id

				sc.forEach(c -> System.out.println(c.getId() + ".- " + c.getDni()));
				int opcion = Integer.parseInt(br.readLine());

				System.out.println("\n--------------------------\n");

				// En funcion del id cogido se muestra toda la informacion acerca del cliente.

				Cliente c = new Cliente();
				c = cl.get(opcion);
				System.out.println(c.toString());

				// Se pide al usuario los nuevos datos

				System.out.println("\nElementos a modificar(Dejar en blanco para no modificar)");

				System.out.println("Nuevo DNI: ");
				result = br.readLine();
				c.setDni((result == "") ? c.getDni() : result);

				System.out.println("Nuevo nombre: ");
				result = br.readLine();
				c.setNombre((result == "") ? c.getNombre() : result);

				System.out.println("Nuevo apellido: ");
				result = br.readLine();
				c.setApellidos((result == "") ? c.getApellidos() : result);

				System.out.println("Nueva edad: ");
				result = br.readLine();
				c.setEdad((result == "") ? c.getEdad() : Integer.parseInt(result));

				return cl.update(c);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;

		case "vehiculo":
			Stream<Vehiculo> sv = vh.getAll().stream();

			try {
				System.out.println("Elija cliente a modificar(numero): \n");
				sv.forEach(v -> System.out.println(v.getId() + ".- " + v.getMatricula()));
				int opcion = Integer.parseInt(br.readLine());

				System.out.println("\n--------------------------\n");
				Vehiculo v = new Vehiculo();
				v = vh.get(opcion);
				System.out.println(v.toString());

				System.out.println("\nElementos a modificar(Dejar en blanco para no modificar)");

				System.out.println("Nueva matricula: ");
				result = br.readLine();
				v.setMatricula((result == "") ? v.getMatricula() : result);

				System.out.println("Nueva marca: ");
				result = br.readLine();
				v.setMarca((result == "") ? v.getMarca() : result);

				System.out.println("Nuevo modelo: ");
				result = br.readLine();
				v.setModelo((result == "") ? v.getModelo() : result);

				System.out.println("Nuevo Año: ");
				result = br.readLine();
				v.setAno((result == "") ? v.getAno() : Integer.parseInt(result));

				System.out.println("Nuevo color: ");
				result = br.readLine();
				v.setColor((result == "") ? v.getColor() : result);

				return vh.update(v);

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;

		case "reparacion":

			Reparacion r = new Reparacion();
			Vehiculo v = new Vehiculo();
			Cliente c = new Cliente();

			Stream<Cliente> scr = cl.getAll().stream();
			Stream<Vehiculo> svr = vh.getAll().stream();
			Stream<Reparacion> sr = rp.getAll().stream();
			
			String mod;
			try {
				// Se muestran las reparaciones
				sr.forEach((rep) -> {
					Cliente cli = cl.get(rep.getCliente());
					Vehiculo vehi = vh.get(rep.getVehiculo());

					System.out.println(
							rep.getId() + ".-\n" + "DNI: " + cli.getDni() + "\nMatricula: " + vehi.getMatricula());
				});
				System.out.println("\n-------------------------------");
				System.out.println("Elija reparación a modificar: ");
				sr = rp.getAll().stream();
				int opcion = Integer.parseInt(br.readLine());

				while (opcion < 1 || opcion > sr.count()) {
					sr = rp.getAll().stream();
					System.out.println("Reparacion no valida, vuelva a introducirlo: ");
					sr.forEach((rep) -> {
						Cliente cli = cl.get(rep.getCliente());
						Vehiculo vehi = vh.get(rep.getVehiculo());

						System.out.println(
								rep.getId() + ".-\n" + "DNI: " + cli.getDni() + "\nMatricula: " + vehi.getMatricula());
					});
					opcion = Integer.parseInt(br.readLine());
					sr = rp.getAll().stream();
				}

				// Se muestra la opcion escogida
				r = rp.get(opcion);
				c = cl.get(r.getCliente());
				v = vh.get(r.getCliente());
				
				System.out.println("-------------------------------");
				System.out.println("Cliente: " + c.getDni() + "\nMatricula: " + v.getMatricula() + "\nDescripcion: " + r.getDescripcion() +
				"\nFecha" + r.getFecha() + "\nTiempo: " + r.getTiempo() + "\nTotal de costo: " + r.getTotalReparacion() );
				
				System.out.println("\nElementos a modificar(Dejar en blanco para no modificar)");

				System.out.println("Elija cliente: ");
				scr.forEach(cli -> System.out.println(cli.getId() + ".- " + cli.getDni()));
				mod =br.readLine();
				scr = cl.getAll().stream();

				// Se repiten los datos hasta que coja un numero valido
				while (opcion < 1 || opcion > scr.count()) {
					scr = cl.getAll().stream();
					System.out.println("Cliente no valido, vuelva a introducirlo: ");
					scr.forEach(cli -> System.out.println(cli.getId() + ".- " + cli.getDni()));
					mod =br.readLine();
					scr = cl.getAll().stream();
				}

				r.setCliente((mod == "")? r.getCliente(): Integer.parseInt(mod));

				System.out.println("Elija vehiculo: ");

				svr.forEach(vehi -> System.out.println(vehi.getId() + ".- " + vehi.getMatricula()));
				mod =br.readLine();
				svr = vh.getAll().stream();

				// Se repiten los datos hasta que coja un numero valido
				while (opcion < 1 || opcion > svr.count()) {
					svr = vh.getAll().stream();
					System.out.println("Vehiculo no valido, vuelva a introducirlo: ");
					svr.forEach(vehi -> System.out.println(vehi.getId() + ".- " + vehi.getMatricula()));
					mod =br.readLine();
					svr = vh.getAll().stream();
				}

				r.setVehiculo((mod == "")? r.getVehiculo(): Integer.parseInt(mod));

				System.out.println("Descripcion: ");
				mod = br.readLine();
				r.setDescripcion((mod == "")? r.getDescripcion(): mod);

				System.out.println("Tiempo a tardar (horas): ");
				mod = br.readLine();
				r.setTiempo((mod == "")? r.getTiempo(): Integer.parseInt(mod));

				System.out.println("Costo total de la reparación: ");
				mod = br.readLine();
				r.setTotalReparacion((mod == "")? r.getTotalReparacion(): Integer.parseInt(mod));

				Date d = new Date();
				r.setFecha(d);

			} catch (IOException e) {
				e.printStackTrace();
			}

			return rp.update(r);
		default:
			return true;
		}

	}
}
