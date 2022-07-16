<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../comunes/inicioHTML.jsp"/>

<jsp:include page="../comunes/inicioHead.jsp"/>
    <title>Borrar genero ${generoAEliminar.nombre}</title>
<jsp:include page="../comunes/finHead.jsp"/>

<jsp:include page="../comunes/navbar.jsp"/>

<div class="container-fluid p-2 col-8">
    <h5 class="text-center">El genero a eliminar es:</h4>
    <h2 class="text-center fs-1">${generoAEliminar.nombre}</h2>
</div>

<div class="container-fluid p-2 col-8">
   <form action="${pageContext.request.contextPath}/genero?accion=delete&id=${generoAEliminar.id}"
                      method="post" class="was-validated p-2 rounded-3">
        <div class="row text-center">
            <div class="col-12 mb-2">
                <p class="lead fw-bold m-0">¿Estás seguro?</p>
            </div>
            <div class="col-12">
                <a href="${pageContext.request.contextPath}/genero" class="btn btn-secondary">Mejor no</a>
                <button type="submit" class="btn btn-danger">Sí, borrar</button>
            </div>
        </div>
    </form>
    

</div>

<div class="container-fluid mt-3 mb-3 col-5">
    <c:choose>
        <c:when test="${librosConGenero != null && !librosConGenero.isEmpty()}">
            <p class="text-center fs-4">Los siguientes libros perderan este genero:</p>
            
            <table class="table table-dark table-striped">
                <tr>
                    <td class="col-1">Portada</td>
                    <td class="col-2">Titulo</td>
                    <td class="col-2">Autor</td>                    
                </tr>
                <c:forEach items="${librosConGenero}" var="libro">
                    <tr>
                        <td><img class="card-img-top" src="${libro.portada}" alt="Foto de ${libro.titulo}" /></td>
                        <td>${libro.titulo}</td>
                        <td>${libro.autor}</td> 
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div class="container">
                <h4 class="text-center mb-5">No tiene ningún libro asociado</h4>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="../comunes/footer.jsp"/>

<jsp:include page="../comunes/finHTML.jsp"/>