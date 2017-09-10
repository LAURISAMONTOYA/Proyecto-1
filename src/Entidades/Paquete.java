package Entidades;

import java.util.ArrayList;

public class Paquete {

    private int id;
    private String nombre;
    private ArrayList<Canal> canales;
    private double valor;

    public Paquete() {
        canales = new ArrayList<>();
    }

    public Paquete(int id, String nombre, double valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        canales = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Canal> getCanales() {
        return canales;
    }

    public void setCanales(ArrayList<Canal> canales) {
        this.canales = canales;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return  id + " " + nombre ;
    }

    
}
