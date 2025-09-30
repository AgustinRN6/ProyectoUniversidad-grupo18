package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
    
    private String url;
    private String user;
    private String psw;
    
    private static Connection conexion = null;
    
    public Conexion(String url, String user, String psw){
        this.url = url;
        this.user = user;
        this.psw = psw;
        
    }
    public Connection buscarConexion(){
    if(conexion == null){// si conexion no esta establecida 
        try{
        //cargamos los drivers de jdbc
        Class.forName("org.mariadb.jdbc.Driver");
        //abrimos la conexion a la base de datos
        conexion = DriverManager.getConnection(url, user, psw);
        
        }catch(java.lang.ClassNotFoundException error){
            System.out.println("Clase no encontrada"+ error.getMessage());
        }catch(java.sql.SQLException error){
            System.out.println("Error de conexion"+ error.getMessage());
        }  
    }
    return conexion;
    }
    
}
