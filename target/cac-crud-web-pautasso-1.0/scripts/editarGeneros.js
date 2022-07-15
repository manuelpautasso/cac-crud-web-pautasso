var generos = document.getElementsByClassName('genero-a-editar');

for(var i =0; i < generos.length; i++){
    agregarListener(generos.item(i));
}


function cambiarVariablesDeEditar(){
    let url = this;
    console.log("url" + url);
    console.log(typeof (url));
    let urlParams = new URLSearchParams(url);
    console.log(typeof (urlParams));
    console.log("urlParams: " + urlParams);
    console.log("id: " + urlParams.get('id'));
    console.log("nombre: " + this);
    let idElementForm = document.getElementById('idGeneroAEditar');
    let nombreElementForm = document.getElementById('nombreGeneroAEditar');
    let tituloElementForm = document.getElementById('tituloGeneroAEditar');
    
    /*idElementForm.value = id;
    nombreElementForm.value = nombre;
    tituloElementForm.textContent = nombre;*/
}

function agregarListener(elementoGenero) {
    console.log(elementoGenero.toString());
    elementoGenero.addEventListener("click", cambiarVariablesDeEditar);
}

