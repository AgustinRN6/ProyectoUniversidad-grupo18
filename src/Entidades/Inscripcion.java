
package Entidades;

import Entidades.Alumno;
import Entidades.Materia;


public class Inscripcion {
    private int id_inscripcion =-1;
    private int nota;
    private Alumno alumno;
    private Materia materia;
    
    public Inscripcion(int nota, Alumno alumno, Materia materia){
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }
    public Inscripcion(){
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

     public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

      public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    public String getInscripcion(){
        return'['+"ID: "+ id_inscripcion+" ,Nota: "+ nota+" ,Id_Alumno"+ alumno+" ,Id_Materia"+ materia+']';
    }
    

}
