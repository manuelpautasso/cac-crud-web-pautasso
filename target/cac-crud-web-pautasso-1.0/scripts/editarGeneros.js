var generos = document.getElementsByClassName('genero-a-editar');

for(var i =0; i < generos.length; i++){ 
    generos[i].addEventListener('click', cambiarVariablesDeEditar);
}


function cambiarVariablesDeEditar () {
    let idElementForm = document.getElementById('idGeneroAEditar');
    let nombreElementForm = document.getElementById('nombreGeneroAEditar');
    let tituloElementForm = document.getElementById('tituloGeneroAEditar');
    
    tituloElementForm.innerText = this.name;
    nombreElementForm.value = this.name;
    idElementForm.value = this.attributes.value.value;
};
