package Persistencia;

import Entidades.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AlumnosData{
private Connection con=null;

public AlumnosData(Conexion conexion){
    //establecemos conexion con la base de datos mediante el metodo .buscarConexion()
    this.con = conexion.buscarConexion();
                                     
}

public void cargarAlumno(Alumno a){
    try {
        //Sentencia sql.
        String sql = "INSERT INTO alumno(dni, nombre, apellido, fechaNacimiento, estado)VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, a.getDni());
        ps.setString(2, a.getNombre());
        ps.setString(3, a.getApellido());
        ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
        ps.setBoolean(5, true);
        
        if(ps.executeUpdate()> 0){//devuelve un entero con el numero de filas afectadas
            System.out.println("Alumno agregado");
        }
        
        //le asignamos el id auto_increment al alumno de java
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
    //sentencia sql
    String sqlSL="SELECT * FROM alumno WHERE dni ="+id+";";
    try{
    PreparedStatement ps = con.prepareStatement(sqlSL);
    ResultSet rs = ps.executeQuery();//nos devuelve el resultado de la consulta de tipo Result Set
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

public void darDeBaja(int dni){

    String sqlUP="UPDATE alumno SET estado='0' WHERE alumno.dni =' "+dni+" ';";
    try{
    PreparedStatement ps = con.prepareStatement(sqlUP);
    if(ps.executeUpdate()> 0){
        System.out.println("Alumno dado de baja!!!");
    }
    
    }catch(java.sql.SQLException error){
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
}

public void darDeAlta(int dni){
    String sqlUP="UPDATE alumno SET estado='1' WHERE alumno.dni ='"+dni+"';";
    try{
    PreparedStatement ps= con.prepareStatement(sqlUP);
    if(ps.executeUpdate() > 0){
        System.out.println("Alumno dado de alta!!!!");
    }
    }catch(java.sql.SQLException error){
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
}

public void borrarAlumno(int dni){
    String sqlDL="DELETE FROM alumno WHERE alumno.dni='"+dni+"';";
    try{
        PreparedStatement ps = con.prepareStatement(sqlDL);
        if(ps.executeUpdate()> 0){
            System.out.println("Alumno borrado");
        }
    }catch(java.sql.SQLException error){
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
}

public List<Alumno> mostrarAlumnos(){
    String sqlSL="SELECT * FROM alumno";
    List<Alumno> alumnos = new ArrayList<>(); 
    try{
    PreparedStatement ps = con.prepareStatement(sqlSL);
    ResultSet al = ps.executeQuery();
    while(al.next()){
    Alumno a = new Alumno();
    a.setId(al.getInt("id_alumno"));
    a.setDni(al.getInt("dni"));
    a.setNombre(al.getString("nombre"));
    a.setApellido(al.getString("apellido"));
    //parseamos de Date a String para que luego LocalDate lo parsee devuelta
    String fecha= String.valueOf(al.getDate("fechaNacimiento"));
    a.setFechaNacimiento(LocalDate.parse(fecha));
    a.setEstado(al.getBoolean("estado"));
    
    alumnos.add(a);
    }
    }catch(java.sql.SQLException error){
        System.out.println(error.getMessage());
    }
    return alumnos;
}

}

