package mp.crud.modelo;

public class ModeloFactory {

    private ModeloFactory() {}
    
    private static ModeloFactory mf = null;

    public static ModeloFactory getInstance() {
        if (mf == null) {
            mf = new ModeloFactory();
        }
        return mf;
    }

    public ModeloLibro crearModeloLibro(String nombreModelo) {
        ModeloLibro m = null;
        String nombreClase = mf.getClass().getPackage().getName() + ".ModeloLibro" + nombreModelo;
        try {
            m = (ModeloLibro) Class.forName(nombreClase).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Ocurrió un error al instanciar un modelo de Libro de tipo " + nombreClase, ex);
        }
        return m;
    }
    
    public ModeloGenero crearModeloGenero(String nombreModelo) {
        ModeloGenero m = null;
        String nombreClase = mf.getClass().getPackage().getName() + ".ModeloGenero" + nombreModelo;
        try {
            m = (ModeloGenero) Class.forName(nombreClase).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Ocurrió un error al instanciar un modelo de Genero de tipo " + nombreClase, ex);
        }
        return m;
    }
    
}

