window.addEventListener("load", function() {
    generarImagenes();

    var cols = document.querySelectorAll('.columna .column');
    [].forEach.call(cols, function(col) {
        col.addEventListener('dragstart', handleDragStart, false);
        col.addEventListener('dragenter', handleDragEnter, false)
        col.addEventListener('dragover', handleDragOver, false);
        col.addEventListener('dragleave', handleDragLeave, false);
        col.addEventListener('drop', handleDrop, false);
        col.addEventListener('dragend', handleDragEnd, false);
    });

});

var dragSrcEl = null;

function generarImagenes() {
    var numeros = new Array();
    for (let i = 1; i <= 20; i++) {
        aleatorio = Math.round(Math.random() * (20 - 1) + 1);

        if (numeros.includes(aleatorio)) {
            i--;
        } else {
            document.getElementById(i).innerHTML = "<img src='imagen/" + aleatorio + ".jpg' width='100px' height='100px' id='" + aleatorio + "img'>";
            numeros.push(aleatorio);
        }
    }
}

function handleDragStart(e) {
    this.style.opacity = '0.4';

    dragSrcEl = this;
    e.dataTransfer.effectAllowed = 'move';
    e.dataTransfer.setData('text/html', this.innerHTML);
}

function handleDragOver(e) {
    if (e.preventDefault) {
        e.preventDefault();
    }

    e.dataTransfer.dropEffect = 'move';

    return false;
}

function handleDragEnter(e) {
    return true;
}

function handleDragLeave(e) {
    return true;
}

function handleDrop(e) {
    if (e.stopPropagation) {
        e.stopPropagation();
    }
    if (dragSrcEl != this) {
        dragSrcEl.innerHTML = this.innerHTML;
        dragSrcEl.style.opacity = '';
        this.innerHTML = e.dataTransfer.getData('text/html');
    }
    this.style.opacity = '';
    comprobarPuzzle();
    return false;
}

function handleDragEnd(e) {
    e.target.style.opacity = '';
    e.dataTransfer.clearData("Data");
}

function comprobarPuzzle() {
    var completo = 0;

    for (let i = 1; i <= 20; i++) {
        if (document.getElementById(i + 'img').parentNode.id == i) {
            document.getElementById(i + 'img').setAttribute("draggable", "false");
            completo++;
        }
    }
    if (completo == 20) {
        alert("Felicidades, ha completado el puzzle");
    }
}