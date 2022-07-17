<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="WEB-INF/pages/comunes/inicioHTML.jsp"/>

<jsp:include page="WEB-INF/pages/comunes/inicioHead.jsp"/>
    <title>Libros</title>
<jsp:include page="WEB-INF/pages/comunes/finHead.jsp"/>

<jsp:include page="WEB-INF/pages/comunes/navbar.jsp"/>

<div class="container-fluid p-5 col-8">
    <h1 class="fw-bold">Gestión de libros</h1>
    
    <p>Aplicación de manejo de libros.</p>
    
    <div>
        <a href="#" class="btn btn-success"
            data-bs-toggle="modal" data-bs-target="#modalAgregarLibro">Agregar libro</a>
    </div>
</div>

<div class="container-fluid mb-3">
    <c:choose>
        <c:when test="${listadoLibros != null && !lista.isEmpty()}">
            <table class="table table-dark table-striped">
                <tr>
                    <td class="col-1">Portada</td>
                    <td class="col-1">Título</td>
                    <td class="col-1">Autor</td>
                    <td class="col-1">Géneros</td>
                    <td class="col-1">Editora</td>
                    <td class="col-6">Descripción</td>
                    <td class="col-1">Editar - Eliminar</td>
                </tr>
                <c:forEach items="${listadoLibros}" var="libro">
                    <tr>
                        <td><img class="card-img-top" src="${libro.portada}" alt="Foto de ${libro.titulo}" /></td>
                        <td>${libro.titulo}</td>
                        <td>${libro.autor}</td>
                        
                        <c:choose>
                            <c:when test="${libro.generos != null && !libro.generos.isEmpty()}">
                                <td>
                                    <c:forEach items="${libro.generos}" var="genero">
                                        ${genero.nombre} <br/>
                                    </c:forEach>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>Sin géneros</td>
                            </c:otherwise>
                        </c:choose>
                                                    
                        <td>${libro.editora}</td>
                        <td>${libro.descripcion}</td>
                        <td>
                            <div class="justify-content-center">
                            <div>
                                <a href="${pageContext.request.contextPath}/libro?accion=editar&id=${libro.id}" class="btn btn-warning btn-block w-100 py-2 my-2">Editar</a>
                            </div>
                            <div>
                                <a href="${pageContext.request.contextPath}/libro?accion=eliminar&id=${libro.id}" class="btn btn-danger btn-block w-100 py-2 my-2">Borrar</a>
                            </div>
                        </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</div>
      
<jsp:include page="WEB-INF/pages/libros/partes/modalAgregarLibro.jsp"/>

<jsp:include page="WEB-INF/pages/comunes/footer.jsp"/>
<script src="scripts/manejoForms.js"></script>  
<jsp:include page="WEB-INF/pages/comunes/finHTML.jsp"/>