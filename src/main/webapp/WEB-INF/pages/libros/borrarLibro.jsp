<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../comunes/inicioHTML.jsp"/>

<jsp:include page="../comunes/inicioHead.jsp"/>
    <title>Borrar libro "${libroAEliminar.titulo}"</title>
<jsp:include page="../comunes/finHead.jsp"/>

<jsp:include page="../comunes/navbar.jsp"/>

<div class="container-fluid p-2 col-8">
    <div class="col-3"><img class="card-img-top rounded mx-auto d-block" src="${libroAEliminar.portada}" alt="Foto de ${libroAEliminar.titulo}" /></div>
    <div><p>El libro que está por eliminar es <span class="fw-bold">${libroAEliminar.titulo}</span></p>
    <p>Escrito por <span class="fw-bold">${libroAEliminar.autor}</span></p>     
    <p>Editado por <span class="fw-semibold">${libroAEliminar.editora}</span></p>
    <div class="my-4"><p class="m-1">Sinopsis:</p>
        <p class="m-1">${libroAEliminar.descripcion}</p>
    </div> 
    </div> 
</div>


<div class="container-fluid p-2 col-8">
   <form action="${pageContext.request.contextPath}/libro?accion=delete&id=${libroAEliminar.id}"
                      method="post" class="was-validated p-2 rounded-3">
        <div class="row text-center">
            <div class="col-12 mb-2">
                <p class="lead fw-bold m-0">¿Estás seguro?</p>
            </div>
            <div class="col-12">
                <a href="${pageContext.request.contextPath}/libro" class="btn btn-secondary">Mejor no</a>
                <button type="submit" class="btn btn-danger">Sí, borrar</button>
            </div>
        </div>
    </form>
    

</div>



<jsp:include page="../comunes/footer.jsp"/>

<jsp:include page="../comunes/finHTML.jsp"/>