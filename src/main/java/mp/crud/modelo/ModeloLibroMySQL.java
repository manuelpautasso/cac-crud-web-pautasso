package mp.crud.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeloLibroMySQL implements ModeloLibro {

    private static final String GET_ALL_BOOKS_WITH_GENEROS_QUERY = "SELECT libros.*, group_concat(DISTINCT generos.nombre ORDER BY generos.nombre DESC SEPARATOR ', ') "
            + "FROM libros "
            + "LEFT JOIN generos_libros ON libros.id_libro = generos_libros.libro_id "
            + "LEFT JOIN generos ON generos_libros.genero_id = generos.id_genero "
            + "GROUP BY libros.id_libro, libros.titulo, libros.autor";

    private static final String GET_BY_ID_QUERY = "SELECT libros.*, group_concat(DISTINCT generos.nombre ORDER BY generos.nombre DESC SEPARATOR ', ') "
            + "FROM libros "
            + "LEFT JOIN generos_libros ON libros.id_libro = generos_libros.libro_id "
            + "LEFT JOIN generos ON generos_libros.genero_id = generos.id_genero "
            + "WHERE libros.id_libro = ? "
            + "GROUP BY libros.id_libro, libros.titulo, libros.autor";
    
    
    
    
    private static final String GET_BY_TITULO_QUERY = "SELECT libros.*, group_concat(DISTINCT generos.nombre ORDER BY generos.nombre DESC SEPARATOR ', ') "
            + "FROM libros "
            + "LEFT JOIN generos_libros ON libros.id_libro = generos_libros.libro_id "
            + "LEFT JOIN generos ON generos_libros.genero_id = generos.id_genero "
            + "WHERE libros.titulo = ? "
            + "GROUP BY libros.id_libro, libros.titulo, libros.autor";
    
    

    private static final String GET_BOOKS_BY_GENERO_ID = "SELECT libros.*, group_concat(DISTINCT generos.nombre ORDER BY generos.nombre DESC SEPARATOR ', ') "
            + "FROM libros "
            + "LEFT JOIN generos_libros ON libros.id_libro = generos_libros.libro_id "
            + "LEFT JOIN generos ON generos_libros.genero_id = generos.id_genero "
            + "WHERE generos.id_genero = ? "
            + "GROUP BY libros.id_libro, libros.titulo, libros.autor";

    private static final String ADD_BOOK_QUERY = "INSERT INTO libros VALUES  (null, ?, ?, ?, ?, ?)";

    private static final String UPDATE_LIBRO_QUERY = "UPDATE libros SET titulo = ?, autor = ?, descripcion = ?, "
            + "portada = ?, editora = ? WHERE id_libro = ?";

    private static final String ADD_LIBRO_GENERO_RELATION = "INSERT INTO generos_libros VALUES  (null, ?, ?)";

    private static final String DELETE_LIBRO_QUERY = "DELETE FROM libros WHERE id_libro = ?";
    
    private static final String DELETE_RELATIONS_BY_ID_LIBRO = "DELETE FROM generos_libros "
            + "WHERE libro_id = ?";
    
    private static final String DELETE_RELATION_BY_ID_LIBRO_ID_GENERO = "DELETE FROM generos_libros "
            + "WHERE libro_id = ? AND genero_id = ?";

    @Override
    public List<Libro> getLibrosConGeneros() {
        List<Libro> libros = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ALL_BOOKS_WITH_GENEROS_QUERY);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                libros.add(rsToLibro(rs));
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener libros", ex);
        }

        return libros;
    }

    @Override
    public Libro getLibro(int id) {
        Libro libro;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                libro = rsToLibro(rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener libro", ex);
        }

        return libro;
    }
    
        
    @Override
    public Libro getLibro(String titulo) {
        Libro libro;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BY_TITULO_QUERY);) {
            ps.setString(1, titulo);
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                libro = rsToLibro(rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener libro", ex);
        }

        return libro;
    }


    @Override
    public List<Libro> getLibrosConGenero(int idGenero) {
        List<Libro> libros = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BOOKS_BY_GENERO_ID);) {
            ps.setInt(1, idGenero);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    libros.add(rsToLibro(rs));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener libros", ex);
        }

        return libros;
    }

    @Override
    public int addLibro(Libro libro) {
        int regsAgregados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(ADD_BOOK_QUERY);) {
            fillPreparedStatement(ps, libro);
            regsAgregados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al crear libro", ex);
        }
        return regsAgregados;
    }

    @Override
    public int updateLibro(Libro libro) {
        int regsAgregados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_LIBRO_QUERY);) {
            fillPreparedStatement(ps, libro);
            ps.setInt(6, libro.getId());
            regsAgregados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al actualizar libro", ex);
        }
        return regsAgregados;
    }

    @Override
    public int addGeneroALibro(int idLibro, int idGenero) {
        int regsAgregados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(ADD_LIBRO_GENERO_RELATION);) {
            ps.setInt(1, idLibro);
            ps.setInt(2, idGenero);
            regsAgregados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al crear la relacion libro genero", ex);
        }
        return regsAgregados;
    }

    @Override
    public int removeLibro(int id) {
        int regsBorrados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_LIBRO_QUERY);) {
            ps.setInt(1, id);
            regsBorrados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar libro", ex);
        }
        return regsBorrados;
    }

    @Override
    public int removeRelationsOfLibro(int idLibro) {
        int regsBorrados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_RELATIONS_BY_ID_LIBRO);) {
            ps.setInt(1, idLibro);
            regsBorrados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar las relaciones del libro con generos", ex);
        }
        return regsBorrados;
    }

    @Override
    public int removeRelationLibroGenero(int idLibro, int idGenero) {
        int regsBorrados = 0;
        try (Connection con = Conexion.getConnection(); 
                PreparedStatement ps = con.prepareStatement(DELETE_RELATION_BY_ID_LIBRO_ID_GENERO);) {
            ps.setInt(1, idLibro);
            ps.setInt(2, idGenero);
            regsBorrados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar la relacion del genero con libro", ex);
        }
        return regsBorrados;
    }

    private Libro rsToLibro(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String titulo = rs.getString(2);
        String autor = rs.getString(3);
        String descripcion = rs.getString(4);
        String portada = rs.getString(5);
        String editora = rs.getString(6);

        Libro libro = new Libro(id, titulo, autor, descripcion, portada, editora);
        String stringGeneros = rs.getString(7);
        if (stringGeneros != null) {
            String[] arrayGeneros = stringGeneros.split(", ");
            for (String nombreGenero : arrayGeneros) {
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
