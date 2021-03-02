package acciones;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.ClienteDAO;
import dao.ReparacionDAO;
import dao.VehiculoDAO;
import models.Cliente;
import models.Reparacion;
import models.Vehiculo;

public class CargarGuardar {
	
	static ClienteDAO cl = new ClienteDAO();
	static VehiculoDAO vh = new VehiculoDAO();
	static ReparacionDAO rp = new ReparacionDAO();

	public boolean guardar() {
		
		FileWriter fichero = null;
		final PrintWriter pwCli;
		final PrintWriter pwVh;
		final PrintWriter pwRp;
		
		
		List<Cliente> cList;
		List<Vehiculo> vList;
		List<Reparacion> rList;
		
		cList = cl.getAll();
		vList = vh.getAll();
		rList = rp.getAll();
		
		try {
			 
			fichero = new FileWriter( new File("src/recursos/clientes.txt"));
			pwCli = new PrintWriter(fichero);
			
			pwCli.println("----------Clientes----------");
			cList.stream().forEach(cli -> pwCli.println(cli.toString()));			
			fichero.close();
			
			fichero = new FileWriter( new File("src/recursos/vehiculos.txt"));
			pwVh = new PrintWriter(fichero);
			
			pwVh.println("----------Vehiculos----------");
			vList.stream().forEach(ve -> pwVh.println(ve.toString()));
			
			fichero.close();
			
			fichero = new FileWriter( new File("src/recursos/reparaciones.txt"));
			pwRp = new PrintWriter(fichero);
			pwRp.println("----------Reparaciones----------");
			rList.stream().forEach(ve -> pwRp.println(ve.toString()));
			
			fichero.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
