package acciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.stream.Stream;

import models.Cliente;
import models.Reparacion;
import models.Vehiculo;
import dao.ClienteDAO;
import dao.ReparacionDAO;
import dao.VehiculoDAO;

public class Insertar {
	//Creamos objetos estaticos para acceder a los metodos.
	static ClienteDAO cl = new ClienteDAO();
	static VehiculoDAO vh = new VehiculoDAO();
	static ReparacionDAO rp = new ReparacionDAO();

	//Creamos la clase insertar a la que se le pasa un objeto para identificar lo que se va a insertar en la bd
	public boolean insertar(String objeto) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		switch (objeto) {
		case "cliente":

			Cliente c = new Cliente();

			try {
				//El usuario escribe los datos para despues meterlos en un objeto e insertarlos en la bd
				
				System.out.println("Escriba dni del cliente: ");
				c.setDni(br.readLine());
				System.out.println("Nombre: ");
				c.setNombre(br.readLine());
				System.out.println("Apellidos: ");
				c.setApellidos(br.readLine());
				System.out.println("Edad: ");
				c.setEdad(Integer.parseInt(br.readLine()));

			} catch (IOException e) {
				e.printStackTrace();
			}

			return cl.save(c);

		case "vehiculo":
			Vehiculo v = new Vehiculo();

			try {
				//El usuario escribe los datos para despues meterlos en un objeto e insertarlos en la bd
				
				System.out.println("Escriba matricula del vehiculo: ");
				v.setMatricula(br.readLine());
				System.out.println("Marca: ");
				v.setMarca(br.readLine());
				System.out.println("Modelo: ");
				v.setModelo(br.readLine());
				System.out.println("Año: ");
				v.setAno(Integer.parseInt(br.readLine()));
				System.out.println("Color: ");
				v.setColor(br.readLine());

			} catch (IOException e) {
				e.printStackTrace();
			}

			return vh.save(v);
		case "reparacion":
			
			Reparacion r = new Reparacion();
			
			Stream<Cliente> sc = cl.getAll().stream();
			Stream<Vehiculo> sv = vh.getAll().stream();
			
			try {
				//El usuario escribe los datos para despues meterlos en un objeto e insertarlos en la bd
				
				System.out.println("Elija cliente: ");
				sc.forEach(cli -> System.out.println(cli.getId() + ".- " + cli.getDni()));			
				int opcion = Integer.parseInt(br.readLine());
				sc = cl.getAll().stream();
				
				//Se repiten los datos hasta que coja un numero valido
				while(opcion < 1 || opcion > sc.count()) {
					sc = cl.getAll().stream();
					System.out.println("Cliente no valido, vuelva a introducirlo: ");
					sc.forEach(cli -> System.out.println(cli.getId() + ".- " + cli.getDni()));			
					opcion = Integer.parseInt(br.readLine());
					sc = cl.getAll().stream();
				}
				
				r.setCliente(cl.get(opcion).getId());
				
				System.out.println("Elija vehiculo: ");
				
				sv.forEach(vehi -> System.out.println(vehi.getId() + ".- " + vehi.getMatricula()));			
				opcion = Integer.parseInt(br.readLine());
				sv = vh.getAll().stream();
				
				//Se repiten los datos hasta que coja un numero valido
				while(opcion < 1 || opcion > sv.count()) {
					sv = vh.getAll().stream();
					System.out.println("Vehiculo no valido, vuelva a introducirlo: ");
					sv.forEach(vehi -> System.out.println(vehi.getId() + ".- " + vehi.getMatricula()));				
					opcion = Integer.parseInt(br.readLine());
					sv = vh.getAll().stream();
				}
				
				r.setVehiculo(vh.get(opcion).getId());
				
				System.out.println("Descripcion: ");
				r.setDescripcion(br.readLine());
				
				System.out.println("Tiempo a tardar (horas): ");
				r.setTiempo(Integer.parseInt(br.readLine()));
				
				System.out.println("Costo total de la reparación: ");
				r.setTotalReparacion(Integer.parseInt(br.readLine()));
				
				Date d = new Date();
				r.setFecha(d);
				
				

			} catch (IOException e) {
				e.printStackTrace();
			}

			return rp.save(r);
			
		default:
			return true;
		}

	}
}
