package mp.crud.modelo;


import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import mp.crud.enums.VariablesGlobales;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static Connection con;
    private static BasicDataSource dataSource;

    private static DataSource getDataSource() {
        if (dataSource == null) {
            try {
                //String url = "jdbc:mysql://root:admin@localhost:3306/cac_crud_biblioteca?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
                String url = VariablesGlobales.BD_URL.getValue();
                dataSource = new BasicDataSource();
                dataSource.setUrl(url);
                dataSource.setUsername(url);
                dataSource.setInitialSize(50);
            } catch (Exception ex) {
                throw new RuntimeException("Error al conectar con la BD", ex);
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}
