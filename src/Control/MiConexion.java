
package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class MiConexion {
    private static Connection con = null;
    
    private MiConexion(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch(java.lang.ClassNotFoundException error){
            System.out.println(error.getMessage());
        }
    }
    public static Connection getConexion(){
        MiConexion conexion= null; 
        if(con == null){
        
            conexion = new MiConexion();
        try{
            con = DriverManager.getConnection("jdbc:mariadb://localhost/gp18universidad","root","");
        
        }catch(java.sql.SQLException error){
            System.out.println(error.getMessage());
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos, active el servidor");
        }
        }
        return con;
    }
}
