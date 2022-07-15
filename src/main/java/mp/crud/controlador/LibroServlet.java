
package mp.crud.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp.crud.modelo.Libro;
import mp.crud.modelo.ModeloFactory;
import mp.crud.modelo.ModeloLibro;
import sun.rmi.server.Dispatcher;

@WebServlet(name = "LibroServlet", urlPatterns = {"/libro"})
public class LibroServlet extends HttpServlet{
    private ModeloLibro modeloLibro;
    private static final String URI_LIST_LIBROS = "listadoLibros.jsp";
    
    
    @Override
    public void init() throws ServletException {
        this.modeloLibro = getModeloLibro();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String accion = request.getParameter("accion");
        accion = accion == null ? "" : accion;
        
        switch (accion){
            case "buscar":
                //buscar
                
            case "editar":
                //editar
            case "eliminar":
                // eliminar
            default:                
                request.setAttribute("listadoLibros", modeloLibro.getLibros());
                request.getRequestDispatcher(URI_LIST_LIBROS).forward(request, response);
        }
                
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Libro libro;
        String accion = request.getParameter("accion");
        accion = accion == null ? "" : accion;
        int id;
        switch (accion) {
            case "add":
                libro = new Libro();
                libroFromRequest(libro, request);  
                System.out.println(libro);
                modeloLibro.addLibro(libro);
                break;
            case "update":
                /*id = Integer.parseInt(request.getParameter("id"));
                alu = model.getAlumno(id);
                cargarAlumnoSegunParams(alu, request);
                model.updateAlumno(alu);*/
                break;
            case "delete":
                /*id = Integer.parseInt(request.getParameter("id"));
                model.removeAlumno(id);*/
                break;
        }
        doGet(request, response);
    }
    
    private Libro libroFromRequest(Libro libro, HttpServletRequest request){
        libro.setTitulo(request.getParameter("titulo"));
        libro.setAutor(request.getParameter("autor"));
        libro.setEditora(request.getParameter("editora"));
        libro.setPortada(request.getParameter("portadaBase64"));
        libro.setDescripcion(request.getParameter("descripcion"));
        
        return libro;
    }
    
    private ModeloLibro getModeloLibro() throws ServletException {
        ModeloLibro m = null;
        try ( InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            props.load(is);
            String tipoModelo = props.getProperty("tipoModelo");
            m = ModeloFactory.getInstance().crearModeloLibro(tipoModelo);
        } catch (IOException ex) {
            throw new ServletException("Error de E/S al al leer 'config' para iniciar el Servlet", ex);
        }
        return m;
    }
}
