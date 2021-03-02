package acciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import dao.ClienteDAO;
import dao.VehiculoDAO;
import models.Cliente;
import models.Vehiculo;

public class Eliminar {
	
	//Creamos objetos estaticos para acceder a los metodos.
	
	static ClienteDAO cl = new ClienteDAO();
	static VehiculoDAO vh = new VehiculoDAO();
	
	//Creamos la clase eliminar a la que se le pasa un objeto para identificar lo que se va a eliminar
	
	public boolean eliminar(String objeto) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		switch(objeto) {
		case "cliente":
			
			Stream<Cliente> sc = cl.getAll().stream();
			
			try {
				System.out.println("Elija cliente a eliminar(numero): \n");
				
				//Mostramos todos los dni disponibles y damos al usuario a elegir mediante el id.
				
				sc.forEach(c -> System.out.println(c.getId() + ".- " + c.getDni()));
				int opcion = Integer.parseInt(br.readLine());
				
				//Usamos el id para coger ese objeto y eliminarlo
				
				return cl.delete(cl.get(opcion));
				
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
				
				//Mostramos todas las  matriculas disponibles y damos al usuario a elegir mediante el id.
				
				sv.forEach(v -> System.out.println(v.getId() + ".- " + v.getMatricula()));
				int opcion = Integer.parseInt(br.readLine());
				
				//Usamos el id para coger ese objeto y eliminarlo
				return vh.delete(vh.get(opcion));
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
			
		default:
			return true;
		}
	}
}
