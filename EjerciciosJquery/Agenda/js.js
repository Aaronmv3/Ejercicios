var contactos = [];
$(document).ready(function() {

    var iniciales = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";

    //var inicial;
    var nombre;
    var email;

    generar();

    function generar() {

        for (let i = 0; i < iniciales.length; i++) {


            let tituloSeccion = document.createElement("h3");
            tituloSeccion.append('#' + iniciales[i]);
            $("#accordion").append(tituloSeccion);

            let seccion = document.createElement("div");
            seccion.setAttribute("id", iniciales[i]);
            seccion.setAttribute("class", "seccion");
            $("#accordion").append(seccion);

            $(function() {
                $("#accordion").accordion({
                    heightStyle: "content"
                });
            });

        }
    }
    $("#guardar").click(function() {


        nombre = $("#nombre").val();
        email = $("#email").val();
        contactos.push({
            "nombre": nombre,
            "email": email,
        });

        let inicial = nombre.charAt(0).toUpperCase();

        var infoContacto = document.createElement("div");

        // nombre del contacto que he agregado
        let nombreContacto = document.createElement("h6");
        nombreContacto.setAttribute("style", "font-weight: bold");
        nombreContacto.append(nombre);
        $(infoContacto).append(nombreContacto);

        // email del contacto
        let emailContacto = document.createElement("p");
        emailContacto.append('Mail: ' + email);
        $(infoContacto).append(emailContacto);

        //Ponemos hr

        let hr = document.createElement("hr");
        $(infoContacto).append(hr)


        $("#" + inicial).append(infoContacto);


        $("#nombre").val("")
        $("#email").val("");
    });
});