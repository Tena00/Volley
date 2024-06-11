let resultadoAccionSeleccionado = null;
let jugadorSeleccionado = null;
let zonaSeleccionada = null;
let zonaAtaqueSeleccionada = null;
const idPartido = 7; // Ajusta este valor según el partido actual

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
}

// Función para enviar la acción al endpoint
function submitAccion() {
    if (!resultadoAccionSeleccionado || !jugadorSeleccionado || !zonaSeleccionada || !zonaAtaqueSeleccionada) {
        alert("Por favor, selecciona todos los campos.");
        return;
    }

    const data = {
        idPartido: idPartido,
        idJugador: jugadorSeleccionado,
        idZona: zonaSeleccionada,
        idZonaAtaque: zonaAtaqueSeleccionada,
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
        })
        .catch((error) => {
            console.error('Error:', error);
            alert("Error al registrar la acción.");
        });
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

document.querySelectorAll('.s5, .s6, .s9, .z1, .z2, .z3, .z4, .z5, .z6, .z7, .z8, .z9, .r1, .r2, .r3, .r4, .r5, .r6, .r7, .r8, .r9').forEach(button => {
    button.addEventListener('click', () => {
        selectZona(button.classList[0].slice(1));
    });
});

document.querySelectorAll('.campo_mitades .mitad2 .mitad2_zonas button').forEach(button => {
    button.addEventListener('click', () => {
        selectZonaAtaque(button.classList[0].slice(1));
    });
});
