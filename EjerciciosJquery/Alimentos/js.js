$(document).ready(function() {
    generarImagenes();
    generarDroppables();
    var imagenesBien = 0;


    function generarDroppables() {
        //Los hacemos droppables
        $(".fruta").droppable({ accept: ".fruta", tolerance: "fit", drop: elementoSoltado });
        $(".verdura").droppable({ accept: ".verdura", tolerance: "fit", drop: elementoSoltado });
        $(".carne").droppable({ accept: ".carne", tolerance: "fit", drop: elementoSoltado });


        //Drag
        $(".arrastrable").draggable();
    }


    function generarImagenes() {
        for (let i = 1; i <= 12; i++) {
            let posx = aleatorio2(10, 500);
            let posy = aleatorio2(80, 150);
            let zindex = aleatorio2(1, 4);
            let clase = "";
            if (i < 5) {
                clase = "fruta"
            } else if (i >= 5 && i < 9) {
                clase = "verdura"
            } else {
                clase = "carne"
            }
            var nuevoElemento = $('<img src="imagen/' + i + '.jpg" class="arrastrable ' + clase + '" style="top: ' + posy + 'px; left: ' + posx + 'px; z-index:' + zindex + '">');
            nuevoElemento.draggable();
            $(".imagenes").append(nuevoElemento);
        }
    }

    function elementoSoltado(event, ui) {
        ui.draggable.draggable("option", "containment", "parent");
        ui.draggable.draggable("destroy");
        acabarJuego();

    }

    function acabarJuego() {
        imagenesBien += 1;
        if (imagenesBien == 12) {
            alert("Felicidades usted ha acertado todo");
        }
    }

    function aleatorio2(num1, num2) {
        aleatorio = Math.round(Math.random() * (num2 - num1) + num1);
        return aleatorio;
    }

});