package mp.crud.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeloLibroMySQL implements ModeloLibro {   
    
    private static final String GET_ALL_BOOKS_QUERY = "SELECT libros.*, group_concat(DISTINCT generos.nombre ORDER BY generos.nombre DESC SEPARATOR ', ') "
            + "FROM libros "
            + "LEFT JOIN generos_libros ON libros.id_libro = generos_libros.libro_id "
            + "LEFT JOIN generos ON generos_libros.genero_id = generos.id_genero "
            + "GROUP BY libros.id_libro, libros.titulo, libros.autor";
    
    private static final String ADD_BOOK_QUERY = "INSERT INTO libros VALUES  (null, ?, ?, ?, ?, ?)";

    @Override
    public List<Libro> getLibros() {
        List<Libro> libros = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ALL_BOOKS_QUERY);
                ResultSet rs = ps.executeQuery()) {
            
            while(rs.next()){
               libros.add(rsToLibro(rs));
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        }

        return libros;
    }

    @Override
    public Libro getLibro(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int addLibro(Libro libro) {
        int regsAgregados = 0;
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(ADD_BOOK_QUERY);) {
            fillPreparedStatement(ps, libro);
            regsAgregados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener libros", ex);
        }
        return regsAgregados;
    }

    @Override
    public int updateLibro(Libro libro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeLibro(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    private Libro rsToLibro(ResultSet rs) throws SQLException{
        int id = rs.getInt(1);
        String titulo = rs.getString(2);
        String autor = rs.getString(3);
        String descripcion = rs.getString(4);
        String portada = rs.getString(5);
        String editora = rs.getString(6);
        
        Libro libro = new Libro(id, titulo, autor, descripcion, portada, editora);
        String stringGeneros = rs.getString(7);
        if(stringGeneros != null){
            String[] arrayGeneros = stringGeneros.split(", ");
            for(String nombreGenero : arrayGeneros){
                libro.agregarGenero(new Genero(nombreGenero));
            }
        }        
        
        return libro;
    }

    private void fillPreparedStatement(PreparedStatement ps, Libro libro) throws SQLException {
        ps.setString(1, libro.getTitulo());
        ps.setString(2, libro.getAutor());
        ps.setString(3, libro.getDescripcion());
        ps.setString(4, libro.getPortada());
        ps.setString(5, libro.getEditora());
    }

}
