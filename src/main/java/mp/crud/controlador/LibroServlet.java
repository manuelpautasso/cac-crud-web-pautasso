
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


@WebServlet(name = "LibroServlet", urlPatterns = {"/libro"})
public class LibroServlet extends HttpServlet{
    private ModeloLibro modeloLibro;
    private ModeloGenero modeloGenero;
    private static final String URI_LIST_LIBROS = "listadoLibros.jsp";
    private static final String URI_UPDATE_LIBRO = "/WEB-INF/pages/libros/editarLibro.jsp";
    private static final String URI_DELETE_LIBRO = "/WEB-INF/pages/libros/borrarLibro.jsp";
    
    
    @Override
    public void init() throws ServletException {
        this.modeloLibro = getModeloLibro();
        this.modeloGenero = getModeloGenero();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String accion = request.getParameter("accion");
        accion = accion == null ? "" : accion;
        int id;
        switch (accion){            
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                Libro libroAEditar = modeloLibro.getLibro(id);
                request.setAttribute("libroAEditar", libroAEditar);
                request.setAttribute("listadoGeneros", modeloGenero.getGeneros());
                List<Genero> generosDeLibro = modeloGenero.getGenerosDeLibro(id);
                System.out.println(generosDeLibro.toString());
                request.setAttribute("generosLibroAEditar", generosDeLibro);
                request.getRequestDispatcher(URI_UPDATE_LIBRO).forward(request, response);
                break;
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                Libro libroAEliminar = modeloLibro.getLibro(id); 
                request.setAttribute("libroAEliminar", libroAEliminar);
                request.getRequestDispatcher(URI_DELETE_LIBRO).forward(request, response);
                break;
            default:                
                request.setAttribute("listadoLibros", modeloLibro.getLibrosConGeneros());
                request.setAttribute("listadoGeneros", modeloGenero.getGeneros());
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
                modeloLibro.addLibro(libro);
                libro = modeloLibro.getLibro(libro.getTitulo());
                agregarGeneros(libro, request);
                break;
            case "update":
                id = Integer.parseInt(request.getParameter("id"));
                libro = modeloLibro.getLibro(id);
                libroFromRequest(libro, request);
                modeloLibro.updateLibro(libro);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                modeloLibro.removeRelationLibroGenero(id);
                modeloLibro.removeLibro(id);
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
    
    private void agregarGeneros(Libro libro, HttpServletRequest request){
        //request obtener generos
        //foreach modeloLibro.agregarGenero(libro.getId(), genero.getId());
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
}
