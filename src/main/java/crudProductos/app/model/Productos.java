package crudProductos.app.model;

import javax.persistence.*;

@Entity
@Table(name= "productos")
public class Productos {

    @Id
    @Column(name = "id_producto")
    private long idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private double precio;

    //constructor
    public Productos() {
    }

    //get and set
    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
