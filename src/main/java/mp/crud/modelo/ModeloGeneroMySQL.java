package mp.crud.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeloGeneroMySQL implements ModeloGenero {

    private static final String GET_ALL_GENEROS_QUERY = "SELECT * FROM generos";

    private static final String ADD_GENERO_QUERY = "INSERT INTO generos VALUES  (null, ?)";

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            throw new RuntimeException("Error al obtener libros", ex);
        }
        return regsAgregados;
    }

    @Override
    public int updateGenero(Genero genero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeGenero(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Genero rsToGenero(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String nombre = rs.getString(2);
        return new Genero(id, nombre);
    }

}
