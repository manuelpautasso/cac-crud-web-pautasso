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
}
