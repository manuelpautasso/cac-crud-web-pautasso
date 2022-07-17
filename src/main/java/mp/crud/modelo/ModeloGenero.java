package mp.crud.modelo;

import java.util.List;

/**
 *
 * @author Manuel Pautasso
 */
public interface ModeloGenero {
    /**
     * Retorna una lista de los generos
     * @return 
     */
    public List<Genero> getGeneros();
    
    /**
     * Retorna un genero por ID
     * @param id el id del genero a retornar
     * @return El genero encontrado por ID o null si no existe
     */
    public Genero getGenero(int id);
    
    /**
     * Retorna lista de generos relacionados con el libro
     * @param idLibro, el id del libro a buscar sus generos
     * @return Lista de generos asociados al libro
     */
    public List<Genero> getGenerosDeLibro(int idLibro);
    
    /**
     * Retorna lista de ids de generos relacionados con el libro
     * @param idLibro, el id del libro a buscar sus generos
     * @return Lista de enteros que representan los ids de generos asociados al libro
     */
    public List<Integer> getGenerosIdDeLibro(int idLibro);
            
    /**
     * Agrega un genero al modelo
     * @param genero, El genero a agregar
     * @return La cantidad de registros modificados
     */
    public int addGenero(Genero genero);
    
    /**
     * Modifica un genero del modelo
     * @param genero, El genero contiene los datos para modificar, pero el Id debe ser el mismo 
     * @return La cantidad de registros modificados
     */
    public int updateGenero(Genero genero);
    
    /**
     * Borra un genero del modelo
     * @param id, El id del genero a eliminar del modelo
     * @return La cantidad de registros modificados
     */
    public int removeGenero(int id);
    
    /**
     * Borra las relaciones entre un libro y generos con el id del genero
     * @param id, El id del genero a eliminar sus relaciones
     * @return La cantidad de registros modificados
     */
    public int removeRelationsOfGenero(int idGenero);
    
    /**
     * Borra la relacion entre un libro y un genero con el id del libro y del genero
     * @param idGenero, @param idLibro, El id del libro y el del genero a eliminar sus relacion mutua
     * @return La cantidad de registros modificados
     */
    public int removeRelationLibroGenero(int idLibro, int idGenero);
}
