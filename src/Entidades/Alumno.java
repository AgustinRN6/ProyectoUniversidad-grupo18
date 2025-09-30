
package Entidades;

import java.time.LocalDate;


public class Alumno {
    private int id= -1;
    private int dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private boolean estado;
 
    public Alumno(int dni, String nombre, String apellido,LocalDate fechaNacimiento, boolean estado){
    this.dni = dni;
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.estado = estado;
    }
    public Alumno(){
    
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.dni;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alumno other = (Alumno) obj;
        if (this.id != other.id) {
            return false;
        }
        return this.dni == other.dni;
    }
    
    public String mostrarInfo(){
        return'['+"ID: "+id+" ,DNI: "+ dni +" ,Nombre: "+ nombre+" ,Apellido: "+ apellido +" ,Estado: " + estado+']';
    }  
}
