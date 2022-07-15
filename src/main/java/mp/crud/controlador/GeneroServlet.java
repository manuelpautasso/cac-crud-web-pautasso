
package mp.crud.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mp.crud.modelo.Genero;
import mp.crud.modelo.Libro;
import mp.crud.modelo.ModeloFactory;
import mp.crud.modelo.ModeloGenero;
import mp.crud.modelo.ModeloLibro;
import sun.rmi.server.Dispatcher;

@WebServlet(name = "GeneroServlet", urlPatterns = {"/genero"})
public class GeneroServlet extends HttpServlet{
    private ModeloGenero modeloGenero;
    private ModeloLibro modeloLibro;
    private static final String URI_LIST_GENEROS = "listadoGeneros.jsp";
    private static final String URI_DELETE_GENERO = "/WEB-INF/pages/generos/borrarGenero.jsp";
    
    
    @Override
    public void init() throws ServletException {
        this.modeloGenero = getModeloGenero();
        this.modeloLibro = getModeloLibro();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String accion = request.getParameter("accion");
        accion = accion == null ? "" : accion;
        int id;
        switch (accion){            
            case "editar":
                //editar
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                Genero generoAEliminar = modeloGenero.getGenero(id);                
                List<Libro> librosConGenero = modeloLibro.getLibrosConGenero(generoAEliminar.getId());
                request.setAttribute("generoAEliminar", generoAEliminar);
                request.setAttribute("librosConGenero", librosConGenero);
                request.getRequestDispatcher(URI_DELETE_GENERO).forward(request, response);
                break;                
            default:                
                request.setAttribute("listadoGeneros", modeloGenero.getGeneros());
                request.getRequestDispatcher(URI_LIST_GENEROS).forward(request, response);
        }
                
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Genero genero;
        String accion = request.getParameter("accion");
        accion = accion == null ? "" : accion;
        int id;
        switch (accion) {
            case "add":
                genero = new Genero();
                genero.setNombre(request.getParameter("nombre"));                
                modeloGenero.addGenero(genero);
                break;
            case "update":
                id = Integer.parseInt(request.getParameter("id"));
                genero = modeloGenero.getGenero(id);
                genero.setNombre(request.getParameter("nombre"));
                modeloGenero.updateGenero(genero);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                modeloGenero.removeRelationGeneroLibro(id);
                modeloGenero.removeGenero(id);
                break;
        }
        doGet(request, response);
    }
    
    
    private ModeloGenero getModeloGenero() throws ServletException {
        ModeloGenero m = null;
        try ( InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            props.load(is);
            String tipoModelo = props.getProperty("tipoModelo");
            m = ModeloFactory.getInstance().crearModeloGenero(tipoModelo);
        } catch (IOException ex) {
            throw new ServletException("Error de E/S al al leer 'config' para iniciar el Servlet", ex);
        }
        return m;
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
