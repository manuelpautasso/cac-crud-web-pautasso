package mp.crud.modelo;

import java.util.List;

/**
 *
 * @author Manuel Pautasso
 */
public interface ModeloLibro {
    /**
     * Retorna una lista de los libros
     * @return 
     */
    public List<Libro> getLibros();
    
    /**
     * Retorna un libro por ID
     * @param id el id del libro a retornar
     * @return El libro encontrado por ID o null si no existe
     */
    public Libro getLibro(int id);
    
    /**
     * Agrega un libro al modelo
     * @param libro, El libro a agregar
     * @return La cantidad de registros modificados
     */
    public int addLibro(Libro libro);
    
    /**
     * Modifica un libro del modelo
     * @param libro, El libro contiene los datos para modificar, pero el Id debe ser el mismo 
     * @return La cantidad de registros modificados
     */
    public int updateLibro(Libro libro);
    
    /**
     * Borra un libro del modelo
     * @param id, El id del libro a eliminar del modelo
     * @return La cantidad de registros modificados
     */
    public int removeLibro(int id);
}
