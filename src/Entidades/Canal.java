package Entidades;

import java.util.ArrayList;

public class Canal {

    private int numero;
    private String nombre;
    private String descripcion;    
    private ArrayList<Programa> programas;
    private int idPaquete;

    public Canal() {
        programas=new ArrayList<>();
    }

    public Canal(int numero, String nombre, String descripcion, int idPaquete) {
        this.numero = numero;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idPaquete = idPaquete;
    }

    
    
    public ArrayList<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(ArrayList<Programa> programas) {
        this.programas = programas;
    }
    
    

    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

       @Override
    public String toString() {
        return  numero + " " + nombre ;
    }
}
