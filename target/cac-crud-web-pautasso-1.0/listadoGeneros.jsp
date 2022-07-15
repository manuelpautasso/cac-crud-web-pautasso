<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="WEB-INF/pages/comunes/inicioHTML.jsp"/>

<jsp:include page="WEB-INF/pages/comunes/inicioHead.jsp"/>
    <title>Generos</title>
<jsp:include page="WEB-INF/pages/comunes/finHead.jsp"/>

<jsp:include page="WEB-INF/pages/comunes/navbar.jsp"/>

<div class="container-fluid p-5 col-8">
    <h1 class="fw-bold">Gestion de generos</h1>        
    
    <div>
        <a href="#" class="btn btn-success"
            data-bs-toggle="modal" data-bs-target="#modalAgregarGenero">Agregar genero</a>
    </div>
</div>

<div class="container col-4 mb-3">
    <c:choose>
        <c:when test="${listadoGeneros != null && !lista.isEmpty()}">
            <table class="table table-dark table-striped">
                <tr>
                    <td class="col-1">Id</td>
                    <td class="col-1">Nombre</td>                    
                    <td class="col-1 text-center">Editar</td>
                    <td class="col-1 text-center">Eliminar</td>
                </tr>
                <c:forEach items="${listadoGeneros}" var="genero">
                    <tr>                        
                        <td>${genero.id}</td>
                        <td>${genero.nombre}</td>
                        
                        <td>
                            <div class="justify-content-center">
                                <a href="${pageContext.request.contextPath}/genero?accion=editar&id=${genero.id}" class="btn btn-warning btn-block w-100 py-2 my-2 genero-a-editar">Editar</a>
                            </div>
                        </td>
                        <td>
                            <div class="justify-content-center">
                                <a href="${pageContext.request.contextPath}/genero?accion=eliminar&id=${genero.id}" class="btn btn-danger btn-block w-100 py-2 my-2">Borrar</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
</div>
     
<jsp:include page="WEB-INF/pages/generos/partes/modalAgregarGenero.jsp"/>
<jsp:include page="WEB-INF/pages/generos/partes/modalEditarGenero.jsp"/>

<jsp:include page="WEB-INF/pages/comunes/footer.jsp"/>
<script src="scripts/editarGeneros.js"></script>  
<jsp:include page="WEB-INF/pages/comunes/finHTML.jsp"/>