/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.tree;

/**
 *
 * @author bestrada
 */
public class Plantilla {
    
  public  String id;
  public String nombre;
  public boolean valor;
  public String descripcion;
 

    public Plantilla(String id, String nombre, boolean valor, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public boolean getValor() {
        return valor;
    }
    

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    
   

    
    @Override
    public String toString(){
     return nombre;
    }
  
  
}