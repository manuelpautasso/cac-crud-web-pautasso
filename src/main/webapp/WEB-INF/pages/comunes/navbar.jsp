<nav class="navbar navbar-expand-lg fondo-oscuro">
  <div class="container-fluid">
      <a class="navbar-brand fs-3 fw-semibold mx-2 text-white" href="${pageContext.request.contextPath}">
          <img src="assets/codoacodo.png" class="bi me-2" height="64"></img>
          <span>Inicio</span>
      </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active fs-4 fw-semibold ms-3 me-2 text-white" aria-current="page" href="${pageContext.request.contextPath}/libro">Libros</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active fs-4 fw-semibold mx-2 text-white" aria-current="page" href="${pageContext.request.contextPath}/genero">Generos</a>
        </li>        
      </ul>      
    </div>
  </div>
</nav>
