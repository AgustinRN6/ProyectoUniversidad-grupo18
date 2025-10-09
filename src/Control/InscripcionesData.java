package Control;

import Entidades.Alumno;
import Entidades.Inscripcion;
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

public class InscripcionesData {

    private Connection con = MiConexion.getConexion();
    private MateriasData md = new MateriasData();
    private AlumnosData ad = new AlumnosData();

    public void cargarInscripion(Inscripcion i) {
        String sqlAD = "INSERT INTO inscripcion(nota, id_alumno , id_materia) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sqlAD, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, i.getNota());
            ps.setInt(2, i.getAlumno().getId());
            ps.setInt(3, i.getMateria().getId_materia());
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Alumno Inscripto");
            }
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                i.setId_inscripcion(rs.getInt(1));
            }
        } catch (java.sql.SQLException error) {
            System.out.println(error.getMessage());
        }
    }

    public List<Inscripcion> mostrarInscripciones() {
        String sqlSL = "SELECT * FROM inscripcion";
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sqlSL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion i = new Inscripcion();
                i.setId_inscripcion(rs.getInt("id_inscripcion"));
                i.setNota(rs.getInt("nota"));
                Alumno alu = ad.buscarAlumno(rs.getInt("id_alumno"));
                Materia mat = md.consultarMateria(rs.getInt("id_materia"));
                i.setAlumno(alu);
                i.setMateria(mat);
                inscripciones.add(i);
            }
        } catch (java.sql.SQLException error) {
            System.out.println(error.getMessage());
        }
        return inscripciones;
    }

    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        try {
            String sql = "DELETE FROM inscripcion WHERE id_alumno = ? AND id_materia = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion Borrada");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
    }

    public List<Materia> obtenerMateriasCursadas(int idAlumno) {
        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT inscripcion.id_materia, nombre, año FROM inscripcion,"
                + "materia WHERE inscripcion.id_materia = materia.id_materia "
                + "AND inscripcion.id_alumno= ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materias.add(materia);

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        return materias;
    }

    public List<Materia> obtenerMateriasNOCursadas(int idAlumno) {
        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia WHERE estado = 1 AND id_materia not in "
                + "(SELECT id_materia FROM inscripcion WHERE id_alumno = ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setId_materia(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materias.add(materia);

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");

        }
        return materias;
    }
    
    public void modificarNota(int id, int nota){
    String sqlUP="UPDATE inscripcion  i SET nota =? WHERE i.id_inscripcion =?";
    try{
        PreparedStatement ps = con.prepareStatement(sqlUP);
        ps.setInt(1, nota);
        ps.setInt(2, id);
        if(ps.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Nota actualizada!!!!");
        }
    }catch(java.sql.SQLException error){
        JOptionPane.showMessageDialog(null, error.getMessage());
    }
    }

}
