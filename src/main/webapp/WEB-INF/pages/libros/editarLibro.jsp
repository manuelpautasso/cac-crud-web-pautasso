<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../comunes/inicioHTML.jsp"/>

<jsp:include page="../comunes/inicioHead.jsp"/>
    <title>Libro a editar "${libroAEditar.titulo}"</title>
<jsp:include page="../comunes/finHead.jsp"/>

<jsp:include page="../comunes/navbar.jsp"/>

        <h2 class="text-center">Editar libro "${libroAEditar.titulo}"</h2>

        <div class="container-fluid col-11">
        <form id="formAgregarLibro" action="${pageContext.request.contextPath}/libro?accion=update&id=${libroAEditar.id}"
                  method="post" class="was-validated">
            
            <div class="row">
                <div class="row mb-3">                    
                      <label for="titulo" class="col-sm-2 col-form-label">T�tulo</label>                      
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="titulo" name="titulo" value="${libroAEditar.titulo}" required>
                    </div>
                </div>
                
                <div class="row mb-3">                    
                      <label for="autor" class="col-sm-2 col-form-label">Autor/a</label>                      
                      <div class="col-sm-10">
                      <input type="text" class="form-control" id="autor" name="autor" value="${libroAEditar.autor}" required>
                    </div>
                </div>
                
                 
                <div class="row mb-3">                    
                      <label for="editora" class="col-sm-2 col-form-label">Editora</label>                      
                      <div class="col-sm-10">
                      <input type="text" class="form-control" id="editora" name="editora" value="${libroAEditar.editora}" required>
                    </div>
                </div>
                
                <div class="row mb-3">                    
                    <label for="portada" class="col-sm-2 col-form-label">Portada</label>                      
                    <div class="col-sm-10">
                      <input type="file" class="form-control" id="inputPortada" name="portada">
                      <input type="hidden" id="portadaBase64" name="portadaBase64" value="${libroAEditar.portada}" >
                    </div>
                </div>
                
                
                <div class="row mb-3">                    
                      <label for="descripcion" class="col-sm-2 col-form-label">Descripci�n</label>                      
                      <div class="col-sm-10">
                      <textarea class="form-control" aria-label="With textarea"                             
                         id="descripcion" name="descripcion" value="" required>${libroAEditar.descripcion}</textarea>
                    </div>
                </div>
                
                <div class="btn-group">
                    <div class="form-check dropend px-4 py-3">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuClickableInside" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="true">
                          G�neros
                        </button>
                        <div class="dropdown-menu form-check" aria-labelledby="dropdownMenuClickableInside">
                            <c:forEach items="${listadoGeneros}" var="genero">
                                <c:set var="encontradoEnLibro" value="false" scope="page"/>
                                <div class="dropdown-item mx-3">
                                    <c:forEach items="${generosLibroAEditar}" var="generoLibro">
                                        <c:if test="${encontradoEnLibro == false && generoLibro.id == genero.id}">
                                            <input class="form-check-input genero-checkbox" type="checkbox" value="${genero.id}"
                                                   id="checkbox-${genero.nombre}" checked>
                                            <c:set var="encontradoEnLibro" value="true" scope="page"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${encontradoEnLibro == false}">
                                        <input class="form-check-input genero-checkbox" type="checkbox" value="${genero.id}" 
                                               id="checkbox-${genero.nombre}">
                                    </c:if>
                                    <label class="form-check-label" for="checkbox-${genero.nombre}">
                                        ${genero.nombre}
                                    </label>
                                </div>
                            </c:forEach>   
                            <input type="hidden" id="stringGeneros" name="stringGeneros" value="">
                        </div>
                    </div>
                </div>
            
            <div class="container-fluid p-2 col-8">
                <div class="row text-center">            
                    <div class="col-12">
                        <a href="${pageContext.request.contextPath}/libro" class="btn btn-secondary">Volver</a>
                        <button type="submit" class="btn btn-primary">Guardar cambios</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<jsp:include page="../comunes/footer.jsp"/>
<script src="scripts/manejoForms.js"></script>  

<jsp:include page="../comunes/finHTML.jsp"/>