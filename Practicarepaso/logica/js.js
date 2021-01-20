$(".menu li ul").hide();
$(".titulo").hide();
$(document).ready(function() {
    $('ul li:has(ul)').hover(function(e) {
            $(this).find('ul').show();
        },
        function(e) {
            $(this).find('ul').hide();
        });

    $(".cuadros").mouseenter(function() {
        $(".titulo").text($(this).attr("alt"));
        $(".titulo").show();
        $(".cuadros").mousemove(function(e) {
            $(".titulo").css("left", e.pageX + 10);
            $(".titulo").css("top", e.pageY - 20);
        })
    });

    $(".cuadros").mouseleave(function() {
        $(".titulo").hide();
        $(".titulo").empty();
    });

});

var modal = document.getElementById('emergente');
var modalImg = document.getElementById("img");

$(".cuadros").click(function() {
    modal.style.display = "block";
    modalImg.src = this.src;
    modalImg.alt = this.alt;
});

$(".cerrar").click(function() {
    modal.style.display = "none";
});