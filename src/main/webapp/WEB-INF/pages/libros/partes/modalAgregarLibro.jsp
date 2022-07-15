
<div class="modal modal-lg" id="modalAgregarLibro"  tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" >
      <div class="modal-header">
        <h5 class="modal-title">Agregar libro</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
        <form id="formAgregarLibro" action="${pageContext.request.contextPath}/libro?accion=add"
                  method="post" class="was-validated">
            
            <div class="modal-body row">
                <div class="row mb-3">                    
                      <label for="titulo" class="col-sm-2 col-form-label">Titulo</label>                      
                      <div class="col-sm-10">
                      <input type="text" class="form-control" id="titulo" name="titulo" required>
                    </div>
                </div>
                
                <div class="row mb-3">                    
                      <label for="autor" class="col-sm-2 col-form-label">Autor/a</label>                      
                      <div class="col-sm-10">
                      <input type="text" class="form-control" id="autor" name="autor" required>
                    </div>
                </div>
                
                 
                <div class="row mb-3">                    
                      <label for="editora" class="col-sm-2 col-form-label">Editora</label>                      
                      <div class="col-sm-10">
                      <input type="text" class="form-control" id="editora" name="editora" required>
                    </div>
                </div>
                
                <div class="row mb-3">                    
                    <label for="portada" class="col-sm-2 col-form-label">Portada</label>                      
                    <div class="col-sm-10">
                      <input type="file" class="form-control" id="inputPortada" name="portada">
                      <input type="hidden" id="portadaBase64" name="portadaBase64">
                    </div>
                </div>
                
                
                <div class="row mb-3">                    
                      <label for="descripcion" class="col-sm-2 col-form-label">Descripcion</label>                      
                      <div class="col-sm-10">
                      <textarea class="form-control" aria-label="With textarea"                             
                         id="descripcion" name="descripcion" required></textarea>
                    </div>
                </div>
                
                <div class="btn-group">
                    <div class="form-check dropend px-4 py-3">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuClickableInside" data-bs-toggle="dropdown" data-bs-auto-close="outside" aria-expanded="true">
                      Generos
                    </button>
                    
                        <div class="dropdown-menu form-check" aria-labelledby="dropdownMenuClickableInside">
                          <div class="dropdown-item mx-3">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                <label class="form-check-label" for="flexCheckDefault">
                                    Terror
                                </label>
                          </div>
                          <div class="dropdown-item mx-3">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                <label class="form-check-label" for="flexCheckDefault">
                                    Fantasia
                                </label>
                          </div>
                          <div class="dropdown-item mx-3">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                <label class="form-check-label" for="flexCheckDefault">
                                    Psicologico
                                </label>
                          </div>
                        </div>
                    </div>
                </div>
               
          
            </div>
            
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
        </form>
    </div>
  </div>
</div>
