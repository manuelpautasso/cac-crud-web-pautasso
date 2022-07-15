package mp.crud.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeloGeneroMySQL implements ModeloGenero {

    private static final String GET_ALL_GENEROS_QUERY = "SELECT * FROM generos";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM generos WHERE id_genero = ?";
    private static final String ADD_GENERO_QUERY = "INSERT INTO generos VALUES  (null, ?)";
    private static final String UPDATE_GENERO_QUERY = "UPDATE generos SET nombre = ? WHERE id_genero = ?";
    private static final String DELETE_GENERO_QUERY = "DELETE FROM generos WHERE id_genero = ?";
    private static final String DELETE_GENERO_LIBROS_RELATIONS_QUERY = "DELETE FROM generos_libros "
            + "WHERE genero_id = ?";
    private static final String DELETE_LIBRO_GENERO_RELATION_QUERY = "DELETE FROM generos_libros "
            + "WHERE libro_id = ?, genero_id = ?";

    @Override
    public List<Genero> getGeneros() {
        List<Genero> generos = new ArrayList<>();
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(GET_ALL_GENEROS_QUERY);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                generos.add(rsToGenero(rs));
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        }

        return generos;
    }

    @Override
    public Genero getGenero(int id) {
        Genero genero;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                rs.next();
                genero = rsToGenero(rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener genero", ex);
        }

        return genero;
    }

    @Override
    public int addGenero(Genero genero) {
        int regsAgregados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(ADD_GENERO_QUERY);) {
            ps.setString(1, genero.getNombre());
            regsAgregados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar genero", ex);
        }
        return regsAgregados;
    }

    @Override
    public int updateGenero(Genero genero) {
        int regsAgregados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE_GENERO_QUERY);) {
            ps.setString(1, genero.getNombre());
            ps.setInt(2, genero.getId());
            regsAgregados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al actualizar genero", ex);
        }
        return regsAgregados;
    }

    @Override
    public int removeGenero(int id) {
        int regsBorrados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_GENERO_QUERY);) {
            ps.setInt(1, id);
            regsBorrados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar genero", ex);
        }
        return regsBorrados;
    }
    
    @Override
    public int removeRelationGeneroLibro(int idGenero) {
        int regsBorrados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_GENERO_LIBROS_RELATIONS_QUERY);) {
            ps.setInt(1, idGenero);
            regsBorrados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar las relaciones del genero con libros", ex);
        }
        return regsBorrados;
    }

    @Override
    public int removeRelationGeneroLibro(int idGenero, int idLibro) {
        int regsBorrados = 0;
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE_LIBRO_GENERO_RELATION_QUERY);) {
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


    private Genero rsToGenero(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String nombre = rs.getString(2);
        return new Genero(id, nombre);
    }


}
