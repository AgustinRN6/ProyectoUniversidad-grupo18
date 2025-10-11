package Control;

import Entidades.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AlumnosData{
private Connection con=null;


 public AlumnosData(){
        this.con = MiConexion.getConexion();
    }
//public AlumnosData(Conexion conexion){
//    //establecemos conexion con la base de datos mediante el metodo .buscarConexion()
//    this.con = conexion.buscarConexion();
//                                     
//}

public void cargarAlumno(Alumno a){
    try {
        //Sentencia sql.
        String sql = "INSERT INTO alumno(dni, nombre, apellido, fechaNacimiento, estado)VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, a.getDni());
        ps.setString(2, a.getNombre());
        ps.setString(3, a.getApellido());
        ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
        ps.setBoolean(5, a.isEstado());
        
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

public Alumno buscarAlumno(int id){
      
        String sql = "SELECT * FROM alumno WHERE id_alumno = ?";
         Alumno alumno = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                alumno = new Alumno();
                alumno.setId(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe un alumno con ese ID");
            }
            ps.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno");
        }  
        return alumno;
    
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
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
    return alumnos;
}

    public void actualizarAlumno(Alumno alumno) {
        String query = "UPDATE alumno SET dni=?,nombre=?,apellido=?"
                + ",fechaNacimiento=?,estado=? WHERE id_alumno = ?";
        try{
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getId());
            
            int subido = ps.executeUpdate();
            if (subido > 0) {
                System.out.println("Alumno actualizado con exito");
            }
        
        }catch (java.sql.SQLException error) {
            
            System.out.println("Error en actualizacion de alumno: " + error.getMessage());
        }
            
        
    }
}

