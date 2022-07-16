var generosMap = [];
var generos = document.getElementsByClassName('genero-a-editar');

for(var i =0; i < generos.length; i++){ 
    
    console.log(generos[i].attributes.name.value); 
    
    console.log(generos[i].attributes.value.value); 
    
    
    let idGenero = generos[i].attributes.value.value;
    let nombreGenero = generos[i].attributes.name.value;
    
    console.log("id = " + idGenero);
    console.log("nombre = " + nombreGenero);
    generos[i].addEventListener('click', cambiarVariablesDeEditar);
}


function cambiarVariablesDeEditar () {
    console.log(this.name);
    console.log("n = " + idGenero);
    let idElementForm = document.getElementById('idGeneroAEditar');
    let nombreElementForm = document.getElementById('nombreGeneroAEditar');
    let tituloElementForm = document.getElementById('tituloGeneroAEditar');
    
    /*idElementForm.value = id;
    nombreElementForm.value = nombre;
    tituloElementForm.textContent = nombre;*/
};
