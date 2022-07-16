package mp.crud.modelo;

import java.util.List;

/**
 *
 * @author Manuel Pautasso
 */
public interface ModeloLibro {
    /**
     * Retorna una lista de los libros con los generos asociados
     * @return 
     */
    public List<Libro> getLibrosConGeneros();
    
    /**
     * Retorna un libro por ID
     * @param id el id del libro a retornar
     * @return El libro encontrado por ID o null si no existe
     */
    public Libro getLibro(int id);
    
    /**
     * Retorna un libro por titulo
     * @param titulo, el titulo del libro a retornar
     * @return El libro encontrado por titulo o null si no existe
     */
    public Libro getLibro(String titulo);
        
     /**
     * Retorna una lista de los libros que tienen relacion con el genero
     * @param idGenero, el id de uno de los generos de los libros a retornar 
     * @return 
     */
    public List<Libro> getLibrosConGenero(int idGenero);
    
    /**
     * Agrega un libro al modelo
     * @param libro, El libro a agregar
     * @return La cantidad de registros modificados
     */
    public int addLibro(Libro libro);
    
    /**
     * Agrega una relacion entre libro y genero
     * @param idLibro, @paramidGenero, los ids del libro y genero a relacionar
     * @return La cantidad de registros modificados
     */
    public int addGeneroALibro(int idLibro, int idGenero);
    
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
    
    /**
     * Borra las relaciones entre un libro y generos con el id del libro
     * @param id, El id del libro a eliminar sus relaciones
     * @return La cantidad de registros modificados
     */
    public int removeRelationLibroGenero(int idLibro);
    
    /**
     * Borra la relacion entre un libro y un genero con el id del libro y del genero
     * @param idLibro, @param idGenero, El id del libro y el del genero a eliminar sus relacion mutua
     * @return La cantidad de registros modificados
     */
    public int removeRelationLibroGenero(int idLibro, int idGenero);
    
}
