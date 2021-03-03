package acciones;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dao.ClienteDAO;
import dao.ReparacionDAO;
import dao.VehiculoDAO;
import models.Cliente;
import models.Reparacion;

public class Informes {
	static ClienteDAO cl = new ClienteDAO();
	static VehiculoDAO vh = new VehiculoDAO();
	static ReparacionDAO rp = new ReparacionDAO();
	
	
	public void CliPorEdad() {
		Stream<Cliente> cli = cl.getAll().stream();
		
		cli.sorted(Comparator.comparing(Cliente::getEdad)).forEach(System.out::println);
		
		
		
	}
	
	public void CliMasReparaciones() {
		Stream<Reparacion> sRp = rp.getAll().stream();
		
		Map<Integer, Long> mapa = sRp.collect(Collectors.groupingBy(Reparacion::getCliente, Collectors.counting())); 
		List<Map.Entry<Integer, Long>> rlist = new ArrayList<>(mapa.entrySet());
		rlist.sort(Map.Entry.<Integer, Long>comparingByValue().reversed());

		rlist.stream().limit(10).forEach(r ->{

			System.out.print(cl.get(r.getKey()).toString());
			System.out.println("Reparaciones: " + r.getValue() + "\n");
		});
		
	}
	
	public void RepMaxCosto() {
		
		Stream<Reparacion> srep = rp.getAll().stream();
		Reparacion rep;
		
		rep = srep.max(Comparator.comparing(Reparacion::getTotalReparacion)).get();
		mostrar(rep);
		
	}
	
public void RepMinCosto() {
		
		Stream<Reparacion> srep = rp.getAll().stream();
		Reparacion rep;
		
		rep = srep.min(Comparator.comparing(Reparacion::getTotalReparacion)).get();
		mostrar(rep);
		
	}
	
	
	private void mostrar(Reparacion rep) {
		
		
		System.out.println("Cliente: " + cl.get(rep.getCliente()).getDni() + "\nMatricula: " + vh.get(rep.getVehiculo()).getMatricula()
		+ "\nDescripcion: " + rep.getDescripcion() + "\nFecha: " + rep.getFecha()
		+ "\nTiempo: " + rep.getTiempo() + "\nCosto de reparacion: "
		+ rep.getTotalReparacion() + "\n-----------------------------\n");
	}
}
