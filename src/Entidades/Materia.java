
package Entidades;


public class Materia {
    private int id_materia = -1;
    private String nombre;
    private int año;
    private boolean estado;

    public Materia(String nombre, int año, boolean estado){
        this.nombre = nombre;
        this.año = año;
        this.estado = estado;
    }
    public Materia(){
    
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public String getMateria(){
        return'['+"ID: "+ id_materia+" ,Nombre: "+ nombre+" ,Año: "+ año+" ,Estado: "+ estado+']';
    }
    
}
