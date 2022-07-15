
<div class="modal modal-lg" id="modalAgregarGenero"  tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" >
      <div class="modal-header">
        <h5 class="modal-title">Agregar genero</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
        <form id="formAgregarGenero" action="${pageContext.request.contextPath}/genero?accion=add"
                  method="post" class="was-validated">
            
            <div class="modal-body row">
                <div class="row mb-3">                    
                      <label for="titulo" class="col-sm-2 col-form-label">Nombre</label>                      
                      <div class="col-sm-10">
                      <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                </div> 
          
            </div>
            
            <div class="modal-footer">
                <span><button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Save changes</button></span>
            </div>
        </form>
    </div>
  </div>
</div>
