package acciones;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dao.ClienteDAO;
import dao.ReparacionDAO;
import dao.VehiculoDAO;
import models.Cliente;
import models.Reparacion;
import models.Vehiculo;

public class Buscar {

	static ClienteDAO cl = new ClienteDAO();
	static VehiculoDAO vh = new VehiculoDAO();
	static ReparacionDAO rp = new ReparacionDAO();

	public Optional<Cliente> busquedaDNI(String dni) {

		Stream<Cliente> scli = cl.getAll().stream();

		Optional<Cliente> cliente = scli.filter(cli -> cli.getDni().equalsIgnoreCase(dni)).findFirst();

		return cliente;
	}

	public void busquedaNombreApellido(String nombre, String apellido) {
		Stream<Cliente> scli = cl.getAll().stream();
		List<Cliente> cliList;

		cliList = scli.filter(
				cli -> cli.getNombre().equalsIgnoreCase(nombre) && cli.getApellidos().equalsIgnoreCase(apellido))
				.collect(Collectors.toList());

		if (cliList.isEmpty()) {
			System.out.println("\nNo se han encontrado resultados");
		} else {
			cliList.stream().forEach(cliente -> {

				System.out.println("\n----------------------------------");

				System.out.println(cliente.toString());

				System.out.println("----------------------------------");
			});
		}
	}

	public Optional<Vehiculo> busquedaMatricula(String matricula) {
		Stream<Vehiculo> svehi = vh.getAll().stream();

		Optional<Vehiculo> vehiculo = svehi.filter(vehi -> vehi.getMatricula().equalsIgnoreCase(matricula)).findFirst();

		return vehiculo;

	}

	public void busquedaMarcaModelo(String marca, String modelo) {

		Stream<Vehiculo> svehi = vh.getAll().stream();
		List<Vehiculo> vList;

		vList = svehi.filter(vehiculo -> vehiculo.getMarca().equalsIgnoreCase(marca)
				&& vehiculo.getModelo().equalsIgnoreCase(modelo)).collect(Collectors.toList());

		if (vList.isEmpty()) {
			System.out.println("No hay ningun vehiculo con esa marca y modelo");
		} else {
			System.out.println("\n----------------------------------");
			vList.stream().forEach(vehi -> System.out.println(vehi.toString()));
			System.out.println("----------------------------------\n");
		}

	}

	public void busquedaMarcaModeloAno(String marca, String modelo, int ano) {
		Stream<Vehiculo> svehi = vh.getAll().stream();
		List<Vehiculo> vList;

		vList = svehi
				.filter(vehiculo -> vehiculo.getMarca().equalsIgnoreCase(marca)
						&& vehiculo.getModelo().equalsIgnoreCase(modelo) && vehiculo.getAno() == ano)
				.collect(Collectors.toList());
		if (vList.isEmpty()) {
			System.out.println("No hay ningun vehiculo con esa marca, modelo y año");
		} else {
			System.out.println("\n----------------------------------");
			vList.stream().forEach(vehi -> System.out.println(vehi.toString()));
			System.out.println("----------------------------------\n");
		}
	}

	public void busquedaClienteReparacion(String DNI) {
		Stream<Reparacion> sRep = rp.getAll().stream();
		List<Reparacion> rList = null;
		Optional<Cliente> cliente = busquedaDNI(DNI);
		if (cliente.isEmpty()) {
			System.out.println("No existe ningun cliente con ese DNI");
		} else {
			rList = sRep.filter(reparacion -> reparacion.getCliente() == cliente.get().getId())
					.collect(Collectors.toList());
			if (rList.isEmpty()) {
				System.out.println("No hay ninguna reparacion para ese cliente");
			} else {
				mostrarListaRep(rList);
			}
		}

	}
	
	public void busquedaMatriculaReparacion(String matricula) {
		
		Stream<Reparacion> sRep = rp.getAll().stream();
		List<Reparacion> rList = null;
		Optional<Vehiculo> vehiculo = busquedaMatricula(matricula);
		if (vehiculo.isEmpty()) {
			System.out.println("No existe ningun vehiculo con esa matricula");
		} else {
			rList = sRep.filter(reparacion -> reparacion.getVehiculo() == vehiculo.get().getId())
					.collect(Collectors.toList());
			if (rList.isEmpty()) {
				System.out.println("No hay ninguna reparacion para ese vehiculo");
			} else {
				mostrarListaRep(rList);
			}
		}
	}
	
	public void busquedaFechaReparacion(Date fecha) {
		Stream<Reparacion> sRep = rp.getAll().stream();
		List<Reparacion> rList = null;
		
		String fechaS = fecha.toString();
		
		rList = sRep.filter(reparacion -> reparacion.getFecha().toString().equalsIgnoreCase(fechaS)).collect(Collectors.toList());
		
		if(rList.isEmpty()) {
			System.out.println("No existe reparacion con esa fecha");
		}else {
			mostrarListaRep(rList);
		}
	}

	private void mostrarListaRep(List<Reparacion> rList) {
		
		rList.stream().forEach(reparacion -> {

			Vehiculo ve = vh.get(reparacion.getVehiculo());
			Cliente cli = cl.get(reparacion.getId());

			System.out.println("Cliente: " + cli.getDni() + "\nMatricula: " + ve.getMatricula()
					+ "\nDescripcion: " + reparacion.getDescripcion() + "\nFecha: " + reparacion.getFecha()
					+ "\nTiempo: " + reparacion.getTiempo() + "\nCosto de reparacion: "
					+ reparacion.getTotalReparacion() + "\n-----------------------------\n");

		});
	}
}
