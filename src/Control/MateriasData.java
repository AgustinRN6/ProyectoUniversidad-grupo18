
package Control;

import Control.MiConexion;
import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MateriasData {
   
    private Connection con = null;

    public MateriasData(){
        this.con = MiConexion.getConexion();
    }

public void cargarMateria(Materia m){
    String sqlAD="INSERT INTO materia(nombre, año, estado) VALUES(?,?,?)";
    try{
        PreparedStatement ps = con.prepareStatement(sqlAD, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, m.getNombre());
        ps.setInt(2, m.getAño());
        ps.setBoolean(3, m.isEstado());
        if(ps.executeUpdate()> 0){
            JOptionPane.showMessageDialog(null, "Materia Agregada");
        }
        
        ResultSet rs = ps.getGeneratedKeys();
        while(rs.next()){
        m.setId_materia(rs.getInt(1));
        }
        
        ps.close();
        
    }catch(java.sql.SQLException error){
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
}
public void darDeBaja(int id){

    String sqlUP="UPDATE materia SET estado='0' WHERE materia.id_materia ='"+id+"';";
    try{
    PreparedStatement ps = con.prepareStatement(sqlUP);
    if(ps.executeUpdate()> 0){
        JOptionPane.showMessageDialog(null, "Materia dada de Baja");
    }
    
    }catch(java.sql.SQLException error){
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
}

public void darDeAlta(int id){
    String sqlUP="UPDATE materia SET estado='1' WHERE materia.id_materia ='"+id+"';";
    try{
    PreparedStatement ps= con.prepareStatement(sqlUP);
    if(ps.executeUpdate() > 0){
        JOptionPane.showMessageDialog(null, "Materia dada de alta");
    }
    }catch(java.sql.SQLException error){
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
}

public void borrarMateria(int id){
    String sqlDL="DELETE FROM materia WHERE materia.id_materia='"+id+"';";
    try{
        PreparedStatement ps = con.prepareStatement(sqlDL);
        if(ps.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Materia Borrada");
        }
        ps.close();
    }catch(java.sql.SQLException error){
       JOptionPane.showMessageDialog(null, error.getMessage());
    }
}

/*public void consultarMateria(int id){
    String sqlSL="SELECT * FROM materia WHERE materia.id_materia ='"+id+"';";
    try{
        PreparedStatement ps = con.prepareStatement(sqlSL);
        ResultSet resultado = ps.executeQuery();
        while(resultado.next()){
            System.out.println("ID: "+ resultado.getInt("id_materia"));
            System.out.println("Materia: "+ resultado.getString("nombre"));
            System.out.println("Año: "+ resultado.getInt("año"));
            System.out.println("Estado: "+ resultado.getBoolean("estado"));
        }
        ps.close();
    }catch(java.sql.SQLException error){
    JOptionPane.showMessageDialog(null, error.getMessage());
    }*/

public Materia consultarMateria(int id){
  String sql ="SELECT nombre, año, estado FROM materia WHERE idMateria = ?";
        Materia materia = null;
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);          
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                materia = new Materia();
                materia.setId_materia(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materia.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null, "No existe una materia con ese ID");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
        }
        return materia;

}

public List<Materia> mostrarMaterias(){
    String sqlSL="Select * FROM materia";
    List<Materia> materias = new ArrayList<>();
    try{
        PreparedStatement ps = con.prepareStatement(sqlSL);
        ResultSet mt = ps.executeQuery();
        
        while(mt.next()){//se va creando da 1 materia 
        //creamos un objeto de tipo Materia vacio
        Materia m= new Materia();
        
        m.setId_materia(mt.getInt("id_materia"));
        m.setNombre(mt.getString("nombre"));
        m.setAño(mt.getInt("año"));
        m.setEstado(mt.getBoolean("estado"));
        //Agregamos el objeto al Arreglo 
        materias.add(m);
        }
        ps.close();
    }catch(java.sql.SQLException error){
        System.out.println(error);
    }
    
    return  materias;//nos retorna la lista completa
}

public void actualizarMateria(Materia m){
    String sqlUP="UPDATE materia SET nombre=?, año=?, estado=? WHERE materia.id_materia=? ;";
    try{
        PreparedStatement ps = con.prepareStatement(sqlUP);
        
        
        ps.setString(1, m.getNombre());
        ps.setInt(2, m.getAño());
        ps.setBoolean(3, m.isEstado());
        ps.setInt(4, m.getId_materia());
        if(ps.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Materia Actualizada");
        }
    }catch(java.sql.SQLException error){
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
}

}
