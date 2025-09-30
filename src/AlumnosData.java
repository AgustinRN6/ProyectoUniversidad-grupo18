package Persistencia;

import Entidades.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

public class AlumnosData{
private Connection con=null;

public AlumnosData(Conexion conexion){
this.con = conexion.buscarConexion();
}

public void cargarAlumno(Alumno a){
    try {
        String sql = "INSERT INTO alumno(dni, nombre, apellido, fechaNacimiento, estado)VALUES(?,?,?,?,?)";//sentencia sql
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, a.getDni());
        ps.setString(2, a.getNombre());
        ps.setString(3, a.getApellido());
        ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
        ps.setBoolean(5, true);
        
        if(ps.executeUpdate()> 0){//modificamos la tabla
            System.out.println("Alumno agregado");
        }
        
        //le asignamos el id auto_increment
        ResultSet rs = ps.getGeneratedKeys();
        while(rs.next()){
        a.setId(rs.getInt(1));
        }
        ps.close();//cerramos el statement
        //con.close();//cerramos la conexion
        
    } catch (java.sql.SQLException error) {
        JOptionPane.showMessageDialog(null, error.getMessage());
    }  
}

public void buscarAlumno(int id){
    String sqlSL="SELECT * FROM alumno WHERE dni ="+id+";";
    try{
    PreparedStatement ps = con.prepareStatement(sqlSL);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
        System.out.println("Alumno: ");
        System.out.println("Dni: "+ rs.getString("dni"));
        System.out.println("Nombre: "+ rs.getString("nombre"));
        System.out.println("Apellido: "+ rs.getString("apellido"));
        System.out.println("Fecha De Naciemiento: "+ rs.getDate("fechaNacimiento"));
        System.out.println("Estado: "+ rs.getBoolean("estado"));
    }
    }catch(java.sql.SQLException error){
       JOptionPane.showMessageDialog(null, error.getMessage());
    }
    
}



}

