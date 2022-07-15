package mp.crud.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Libro {

    private int id;
    private String titulo;
    private String autor;
    private String descripcion;
    private String portada;
    private String editora;

    private List<Genero> generos;

    public Libro() {
    }

    public Libro(String titulo, String autor, String descripcion, String portada, String editora) {
        this(0, titulo, autor, descripcion, portada, editora);
    }

    public Libro(int id, String titulo, String autor, String descripcion, String portada, String editora) {
        setId(id);
        setTitulo(titulo);
        setAutor(autor);
        setDescripcion(descripcion);
        setPortada(portada);
        setEditora(editora);
        this.generos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPortada() {
        return portada;
    }

    public String getEditora() {
        return editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El valor del id no es valido.");
        }
        this.id = id;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().equals("")) {
            throw new IllegalArgumentException("No se ingreso un titulo al libro.");
        }
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPortada(String portada) {
        if (portada == null || portada.trim().isEmpty()) {
            portada = "assets/sin-portada.jpg";
        }
        if (!portada.contains("sin-portada") || this.portada == null || this.portada.contains("sin-portada")) {
            this.portada = portada.trim();
        }
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void agregarGenero(Genero genero) {
        generos.add(genero);
    }

    public void eliminarGenero(Genero genero) {
        generos.remove(genero);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        hash = 41 * hash + Objects.hashCode(this.titulo);
        hash = 41 * hash + Objects.hashCode(this.autor);
        hash = 41 * hash + Objects.hashCode(this.descripcion);
        hash = 41 * hash + Objects.hashCode(this.portada);
        hash = 41 * hash + Objects.hashCode(this.editora);
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
        final Libro other = (Libro) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.portada, other.portada)) {
            return false;
        }
        if (!Objects.equals(this.editora, other.editora)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", descripcion=" + descripcion + ", portada=" + portada + ", editora=" + editora + ", generos=" + generos + '}';
    }

}
