package mp.crud.modelo;

import mp.crud.exception.InvalidParameterException;

public class Genero {
    private int id;
    private String nombre;

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
        if(nombre == null || nombre.trim().equals("")){
            throw new InvalidParameterException ("El nombre del genero ingresado es vacio o solo contiene espacios en blanco");
        }
        this.nombre = nombre;
    }

    public Genero() {
    }

    public Genero(int id, String nombre) {
        setId(id);
        setNombre(nombre);
    }
   

    public Genero(String nombre) {
        this.nombre = nombre;
    }
    
    
}
