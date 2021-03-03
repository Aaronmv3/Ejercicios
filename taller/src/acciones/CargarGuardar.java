package acciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

			fichero = new FileWriter(new File("src/recursos/clientes.txt"));
			pwCli = new PrintWriter(fichero);
			cList.stream().forEach(cli -> pwCli.println(cli.toString()));
			fichero.close();

			fichero = new FileWriter(new File("src/recursos/vehiculos.txt"));
			pwVh = new PrintWriter(fichero);
			vList.stream().forEach(ve -> pwVh.println(ve.toString()));
			fichero.close();

			fichero = new FileWriter(new File("src/recursos/reparaciones.txt"));
			pwRp = new PrintWriter(fichero);
			rList.stream().forEach(rp -> pwRp.println(rp.toString()));

			fichero.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean cargar() {
		Cliente cli = new Cliente();
		Vehiculo vh = new Vehiculo();
		Reparacion rp = new Reparacion();
		
		SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Cliente> cList = new ArrayList<>();
		List<Vehiculo> vList = new ArrayList<>();
		List<Reparacion> rList = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("src/recursos/vehiculos.txt")));
			String linea;
			int i = 0;
			int id = 0;
			while ((linea = br.readLine()) != null) {
				i++;
				++id;
				if (i == 6) {
					vList.add(vh);
					vh = new Vehiculo();
					i = 0;
				} else {
					String datos = linea.split(":")[1].trim();
					switch(i) {
					case 1:
						vh.setId(id);
						vh.setMatricula(datos);
						break;
					case 2:
						vh.setMarca(datos);
						break;
					case 3:
						vh.setModelo(datos);
						break;
					case 4:
						vh.setAno(Integer.parseInt(datos));
						break;
					case 5:
						vh.setColor(datos);
					}
					
				}
			}
			br.close();
			br = new BufferedReader(new FileReader(new File("src/recursos/clientes.txt")));
			
			while ((linea = br.readLine()) != null) {
				i++;
				++id;
				if (i == 5) {
					cList.add(cli);
					cli = new Cliente();
					i = 0;
				} else {
					String datos = linea.split(":")[1].trim();
					switch(i) {
					case 1:
						cli.setId(id);
						cli.setDni(datos);
						break;
					case 2:
						cli.setNombre(datos);
						break;
					case 3:
						cli.setApellidos(datos);
						break;
					case 4:
						cli.setEdad(Integer.parseInt(datos));
						break;
					}
					
				}
			}
			br.close();
			br = new BufferedReader(new FileReader(new File("src/recursos/reparaciones.txt")));
			while ((linea = br.readLine()) != null) {
				i++;
				++id;
				if (i == 7) {
					rList.add(rp);
					rp = new Reparacion();
					i = 0;
				} else {
					String datos = linea.split(":")[1].trim();
					switch(i) {
					case 1:
						rp.setId(id);
						rp.setCliente(Integer.parseInt(datos));
						break;
					case 2:
						rp.setVehiculo(Integer.parseInt(datos));
						break;
					case 3:
						rp.setDescripcion(datos);
						break;
					case 4:
						rp.setFecha(fechaFormat.parse(datos));
						break;
					case 5:
						rp.setTiempo(Integer.parseInt(datos));
						break;
					case 6:
						rp.setTotalReparacion(Integer.parseInt(datos));
					}
					
				}
			}
			br.close();
			

		} catch (IOException | ParseException e) {
			System.out.println("No se ha encontrado el archivo para cargar los datos!!");
			return false;
		}
		return true;
	}

}
