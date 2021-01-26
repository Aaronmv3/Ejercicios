$(document).ready(function() {
    var silabas = ["ho", "la", "ca", "zar", "ca", "sa", "puer", "ta", "bal", "con"];
    var palabras = ["hola", "cazar", "casa", "puerta", "balcon", "balsa", "laca", "lacon", "cala", "caca", "puerca", "cata"];

    generarSilabas();
    generarDroppables();

    function generarDroppables() {
        //Droppable
        for (let i = 1; i < 6; i++) {
            $("#palabra" + i).droppable({
                accept: ".arrastrable",
                tolerance: "fit",
                drop: elementoSoltado
            });
        }
        //draggable
        $(".arrastrable").draggable();
    }



    function generarSilabas() {
        for (let i = 0; i < silabas.length; i++) {
            let posx = aleatorio2(10, 500);
            let posy = aleatorio2(80, 150);
            let zindex = aleatorio2(1, 4);

            var nuevoElemento = $('<p class="arrastrable " style="top: ' + posy + 'px; left: ' + posx + 'px; z-index:' + zindex + '">' + silabas[i] + '</p>');
            nuevoElemento.draggable();
            $(".silabas").append(nuevoElemento);
        }
    }

    function elementoSoltado(event, ui) {
        $("p", this).append(ui.draggable.text());
        ui.draggable.remove();
    }

    function aleatorio2(num1, num2) {
        aleatorio = Math.round(Math.random() * (num2 - num1) + num1);
        return aleatorio;
    }

    $("#comprobar").click(function() {
        let palabrasBien = 0;
        let palabra = [];

        for (let i = 1; i < 6; i++) {
            palabra.push($("#palabra" + i + "> p").text())
        }

        for (let i = 0; i < palabras.length; i++) {
            if (palabras.includes(palabra[i])) {
                palabrasBien += 1
            }
        }
        if (palabrasBien == 5) {
            alert("Enhorabuena ha completado correctamente las palabras");
        } else {
            alert("Usted tiene algun error");
        }


    });
});