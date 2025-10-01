
package Control;
import Entidades.Alumno;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;


public class prueba {
  public static void main(String[] args){

  Alumno a1= new Alumno(4656232,"Agustin","Rosales",LocalDate.of(2004, 9, 06),true);
  Alumno a2= new Alumno(4534213,"Ian","Quimey",LocalDate.of(2004, 02, 03),true);
  Alumno a3= new Alumno(4102934,"Ramiro","Moran",LocalDate.of(2000, 3, 9),true);
  Alumno a4= new Alumno(4032122,"Juan","Villegas",LocalDate.of(1997, 12, 03),true);
  Alumno a5= new Alumno(0000001,"Prueba","Prueba",LocalDate.of(1992, 05, 03),true);

  //Creamos la conexion.
  Conexion conexion = new Conexion("jdbc:mariadb://localhost/gp18universidad","root","");
  //creamos alumnosdata junto con la conexion a la bd
  AlumnosData alumnos = new AlumnosData(conexion);
    /*  
   alumnos.cargarAlumnos(INSERT)................................
    alumnos.cargarAlumno(a1);
    alumnos.cargarAlumno(a2);
    alumnos.cargarAlumno(a3);
    alumnos.cargarAlumno(a4);
    alumnos.cargarAlumno(a5);
  
  
  alumno.darDeBaja alumno.darDeAlta(UPDATE)................................
    alumnos.darDeBaja(0000001);
    alumnos.darDeAlta(0000001);
  
  alumno.borrarAlumno(DELETE)................................
   alumnos.borrarAlumno(0000001);
  
  */
    //alumno.buscar(SELECT)................................
    alumnos.buscarAlumno(2);
  
    //metodo que retorna una lista de todos los alumnos(SELECT *)................................
        System.out.println("Alumnos");
    for(Alumno a : alumnos.mostrarAlumnos()){
            System.out.println(a.mostrarInfo());
    }
        System.out.println(".....................................................................................");
        
  }
   
}
