package conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
	
	//Inicio de variables
	 private static String host;
	 private static String port;
	 private static String db;
	 private static String user;
	 private static String password;
	 private static ArrayList<Connection> lista = null;
	 private static final int NUMCON = 5;
	 private static final int NUMCONINC = 2;    
	 private static final Logger LOG = Logger.getLogger("LOG_BD");
	 
	 //Metodos
	 
	 
	    
	 private ConexionBD() {
	        
	        LOG.setLevel(Level.INFO);
	        cargarPropiedades();
	        lista = new ArrayList<Connection>();
	        crearConexions(NUMCON);
	    }
	 
	 //Crea las conexiones a la bd
	 private static void crearConexions(int num) {
	        
	        String cadeaConexion = "jdbc:mysql://" + host + ":" + port + "/" + db +"?serverTimeZone=Europe/Madrid";
	        for (int i = 0; i < num; i++) {
	            try {
	                lista.add(DriverManager.getConnection(cadeaConexion, user, password));
	            } catch (SQLException e) {                
	                LOG.info("Erro creando conexión: " + e.getMessage());
	            }
	        }
	    }
	    
	    public static Connection obterConexion() {
	        
	        if (lista == null) {
	            new ConexionBD();
	        }
	        
	        if (lista.size() == 0) {
	            crearConexions(NUMCONINC);            
	        }   
	        
	        Connection con = null;
	        
	        try {
	            con = lista.remove(0);
	        } catch(IndexOutOfBoundsException e) {
	            LOG.info("Erro voltando conexión: " + e.getMessage());
	        }
	        return con;
	    }
	    
	    //Cierra la conexion con la bd
	    public static void devolverConexion(Connection conn) {
	        lista.add(conn);
	    }
	    
	    
	    
	    //Carga las propiedad de un archivo externo
	    private static void cargarPropiedades() {
	        try {
	            InputStream fr = ConexionBD.class.getResourceAsStream("/recursos/datos.prop");
	            
	            Properties propiedades = new Properties();
	            propiedades.load(fr);
	            user = propiedades.getProperty("user");
	            password = propiedades.getProperty("password");
	            db = propiedades.getProperty("db");
	            port = propiedades.getProperty("port");
	            host = propiedades.getProperty("host");            
	             
	        }catch(IOException | NullPointerException  e) {            
	            LOG.info("Non se pode cargar o ficheiro de propiedades" + e.getMessage());
	        }
	    }
}
