let resultadoAccionSeleccionado = null;
let jugadorSeleccionado = null;
let zonaSeleccionada = null;
let zonaAtaqueSeleccionada = null;
// Ajusta este valor según el partido actual

// Función para seleccionar el resultado de la acción
function selectResultado(resultado) {
    resultadoAccionSeleccionado = resultado;
    console.log("Resultado de acción seleccionado:", resultadoAccionSeleccionado);
}

// Función para seleccionar el jugador
function selectJugador(jugador) {
    jugadorSeleccionado = jugador;
    console.log("Jugador seleccionado:", jugadorSeleccionado);
}

// Función para seleccionar la zona
function selectZona(zona) {
    zonaSeleccionada = zona;
    console.log("Zona seleccionada:", zonaSeleccionada);
}

// Función para seleccionar la zona de ataque
function selectZonaAtaque(zonaAtaque) {
    zonaAtaqueSeleccionada = zonaAtaque;
    console.log("Zona de ataque seleccionada:", zonaAtaqueSeleccionada);
    submitAccion();
}

// Función para enviar la acción al endpoint
function submitAccion() {
    if (!resultadoAccionSeleccionado || !jugadorSeleccionado || !zonaSeleccionada || !zonaAtaqueSeleccionada) {
        alert("Por favor, selecciona todos los campos.");
        resetVariables();
        return;
    }
    console.log(typeof zonaSeleccionada);

    if (zonaSeleccionada === "10" || zonaSeleccionada === "11" || zonaSeleccionada === "12") {

        const data = {
            idPartido: parseInt(idPartido),
            idJugador: parseInt(jugadorSeleccionado),
            idZona: parseInt(zonaSeleccionada),
            idZonaAtaque: parseInt(zonaAtaqueSeleccionada),
            resultadoAccion: resultadoAccionSeleccionado
        };

        fetch('http://localhost:8080/accion/saque', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Éxito:', data);
                alert("Saque registrado correctamente.");
                resetVariables();
            })
            .catch((error) => {
                console.error('Error:', error);
                alert("Error al registrar el saque.");
                resetVariables();
            });
    } else {

        const data = {
            idPartido: parseInt(idPartido),
            idJugador: parseInt(jugadorSeleccionado),
            idZona: parseInt(zonaSeleccionada),
            idZonaAtaque: parseInt(zonaAtaqueSeleccionada),
            resultadoAccion: resultadoAccionSeleccionado
        };

        fetch('http://localhost:8080/accion/remate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Éxito:', data);
                alert("Acción registrada correctamente.");
                resetVariables();
            })
            .catch((error) => {
                console.error('Error:', error);
                alert("Error al registrar la acción.");
                resetVariables();
            });
    }
}

// Event listeners para los botones de la interfaz
document.querySelectorAll('.remates_rojo, .remates_verde, .remates_amarillo').forEach(button => {
    button.addEventListener('click', () => {
        selectResultado(button.classList[0].split('_')[1]);
    });
});

document.querySelectorAll('.list-group-item').forEach(button => {
    button.addEventListener('click', () => {
        const jugadorId = button.dataset.id; // Obtener el ID del jugador del botón
        selectJugador(jugadorId);
    });
});

document.querySelectorAll('.s5, .s6, .s9, .z1, .z2, .z3, .z4, .z5, .z6, .z7, .z8, .z9').forEach(button => {
    button.addEventListener('click', () => {
        const zonaId = button.dataset.id; // Obtener el ID del jugador del botón
        selectZona(zonaId);
    });
});

document.querySelectorAll('.r1, .r2, .r3, .r4, .r5, .r6, .r7, .r8, .r9').forEach(button => {
    button.addEventListener('click', () => {
        selectZonaAtaque(button.dataset.id);
    });
});




function resetVariables() {
    resultadoAccionSeleccionado = null;
    jugadorSeleccionado = null;
    zonaSeleccionada = null;
    zonaAtaqueSeleccionada = null;
    console.log("Variables reiniciadas.");
}
