var color = "color1";
var dibujar = false;
window.addEventListener("load", function() {
    for (let i = 1; i < 7; i++) {
        document.getElementsByClassName("color" + i)[0].addEventListener('click', seleccionar);
    }
    crearTabla();
    document.getElementById("pulsar").addEventListener('click', action);

});

function seleccionar() {

    for (let i = 1; i < 7; i++) {
        var nombrethis = document.getElementsByClassName("color" + i)[0].className;
        var nombre = nombrethis.split(" ")[0] + " seleccionado";
        if (nombrethis == nombre) {
            nombre = nombre.split(" ")[0];
            document.getElementsByClassName("color" + i)[0].className = nombre;
        }
    }
    color = this.className;
    this.className = this.className + " seleccionado";

}

function crearTabla() {
    var tabla = document.createElement('table');
    var clase = document.createAttribute('class');
    var id = document.createAttribute('id');
    id.value = "pulsar";
    clase.value = "tablerodibujo";
    tabla.setAttributeNode(id);
    tabla.setAttributeNode(clase);
    for (let i = 1; i <= 50; i++) {
        var tr = document.createElement('tr');
        for (let j = 1; j <= 50; j++) {
            var td = document.createElement('td');
            var id = document.createAttribute('class');
            id.value = "tablerodibujo cuadrado";
            td.setAttributeNode(id);
            tr.appendChild(td);
        }
        tabla.appendChild(tr);
    }

    document.getElementById("zonadibujo").appendChild(tabla);
}

function action() {
    document.getElementsByClassName("ocultar")[0].style.display = 'none';
    if (dibujar == false) {
        for (let i = 0; i < document.getElementsByClassName("cuadrado").length; i++) {
            document.getElementsByClassName("cuadrado")[i].addEventListener('mousemove', pintar);
        }
        document.getElementById("pincel").innerHTML = "PINCEL ACTIVADO";
        dibujar = true;
    } else {
        for (let i = 0; i < document.getElementsByClassName("cuadrado").length; i++) {
            document.getElementsByClassName("cuadrado")[i].removeEventListener('mousemove', pintar);
            dibujar = false;
        }
        document.getElementById("pincel").innerHTML = "PINCEL DESACTIVADO";
    }
}

function pintar() {

    this.className = this.className.split(" ")[0] + " " + this.className.split(" ")[1] + " " + color;

}