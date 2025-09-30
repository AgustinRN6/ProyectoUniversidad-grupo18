
package Persistencia;
import Entidades.Alumno;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;


public class prueba {
  public static void main(String[] args){

  Alumno a1= new Alumno(2,"Gustavo","Montes",LocalDate.of(2000, 3, 01),true);
  Alumno a2= new Alumno(453,"Julieta","Dominguez",LocalDate.of(2004, 9, 06),true);
  //Creamos la conexion.
  Conexion conexion = new Conexion("jdbc:mariadb://localhost/gp18universidad","root","");
  //creamos alumnosdata junto con la conexion a la bd
  AlumnosData alumnos = new AlumnosData(conexion);
  //ejecutamos metodo de cargar alumno
  //alumnos.cargarAlumno(a2);
  alumnos.buscarAlumno(2);
  
  }
   
}
