package Persistencia;
import javax.swing.JOptionPane;

import java.sql.DriverManager;
import java.sql.Connection;

public class Conexion {
    
private static Connection conexion = null;
   
    
private Conexion(){
     
}
public static Connection getConexion(){
    Connection con = null;
    if(conexion == null){
    try{
        
    Class.forName("org.mariadb.jdbc.Driver");
    Connection conexion = DriverManager.getConnection("jdcb:mariadb://localhost:3306/gp18universidad", "root", "");
    
    }catch(java.sql.SQLException error){
    JOptionPane.showMessageDialog(null, "Erro de conexion"+ error.getMessage());
    }catch(java.lang.ClassNotFoundException error){
    JOptionPane.showMessageDialog(null, "Clase no encontrada "+ error.getMessage());
    
    }
    
    }
    
    return conexion;
}
    
}
