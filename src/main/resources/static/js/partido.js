let jugadorTitularSeleccionado = null;
let jugadorSuplenteSeleccionado = null;

// Función para obtener los jugadores titulares desde la API
function obtenerJugadoresTitulares() {
    fetch('http://localhost:8080/jugadores/titulares')
        .then(response => {
            if (!response.ok) {
                throw new Error('La respuesta de la API no fue exitosa.');
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos de jugadores titulares:', data); // Verificar los datos recibidos de la API
            actualizarBotonesTitulares(data);
        })
        .catch(error => {
            console.error('Error al obtener los datos de la API:', error);
        });
}

// Función para actualizar los botones con los nombres de los jugadores titulares
function actualizarBotonesTitulares(data) {
    data.forEach((jugador, index) => {
        const boton = document.getElementById(`botonJugador${index + 1}`);
        if (boton) {
            boton.innerText = `${jugador.nombre} ${jugador.dorsal}`;
            boton.dataset.id = jugador.idJugador; // Guardar el ID del jugador en el botón
        }
    });
}

// Función para obtener los jugadores suplentes desde la API
function obtenerJugadoresSuplentes() {
    fetch('http://localhost:8080/jugadores/suplentes')
        .then(response => {
            if (!response.ok) {
                throw new Error('La respuesta de la API no fue exitosa.');
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos de jugadores suplentes:', data); // Verificar los datos recibidos de la API
            actualizarBotonesSuplentes(data);
        })
        .catch(error => {
            console.error('Error al obtener los datos de la API:', error);
        });
}

// Función para actualizar los botones con los nombres de los jugadores suplentes
function actualizarBotonesSuplentes(data) {
    data.forEach((jugador, index) => {
        const boton = document.getElementById(`botonSuplente${index + 1}`);
        if (boton) {
            boton.innerText = `${jugador.nombre} ${jugador.dorsal}`;
            boton.dataset.id = jugador.idJugador; // Guardar el ID del jugador en el botón
        }
    });
}

// Llamar a ambas funciones para obtener los jugadores (titulares y suplentes) cuando se cargue la página
window.addEventListener('load', function() {
    obtenerJugadoresTitulares();
    obtenerJugadoresSuplentes();
});

// Funcionalidad de selección y rotación de jugadores
document.addEventListener('DOMContentLoaded', function () {
    // Selección de jugadores titulares
    const titulares = document.querySelectorAll('.jugadores_seisInicial .list-group-item');
    titulares.forEach(titular => {
        titular.addEventListener('click', function () {
            if (jugadorTitularSeleccionado) {
                jugadorTitularSeleccionado.classList.remove('selected');
            }
            jugadorTitularSeleccionado = titular;
            titular.classList.add('selected');
        });
    });

    // Selección de jugadores suplentes
    const suplentes = document.querySelectorAll('.jugadores_suplentes .list-group-item');
    suplentes.forEach(suplente => {
        suplente.addEventListener('click', function () {
            if (jugadorSuplenteSeleccionado) {
                jugadorSuplenteSeleccionado.classList.remove('selected');
            }
            jugadorSuplenteSeleccionado = suplente;
            suplente.classList.add('selected');
        });
    });

    // Intercambio de jugadores
    const btnRotacion = document.getElementById('rotacion');
    btnRotacion.addEventListener('click', function () {
        if (jugadorTitularSeleccionado && jugadorSuplenteSeleccionado) {
            const titularId = jugadorTitularSeleccionado.dataset.id;
            const suplenteId = jugadorSuplenteSeleccionado.dataset.id;

            // Intercambio en la interfaz
            const titularTexto = jugadorTitularSeleccionado.textContent;
            const suplenteTexto = jugadorSuplenteSeleccionado.textContent;

            jugadorTitularSeleccionado.textContent = suplenteTexto;
            jugadorSuplenteSeleccionado.textContent = titularTexto;

            // Intercambio de dataset.id en la interfaz
            const tempId = jugadorTitularSeleccionado.dataset.id;
            jugadorTitularSeleccionado.dataset.id = jugadorSuplenteSeleccionado.dataset.id;
            jugadorSuplenteSeleccionado.dataset.id = tempId;

            jugadorTitularSeleccionado.classList.remove('selected');
            jugadorSuplenteSeleccionado.classList.remove('selected');

            jugadorTitularSeleccionado = null;
            jugadorSuplenteSeleccionado = null;

            // Actualización en la base de datos
            actualizarEstadoJugador(titularId, false); // Cambiar titular a suplente
            actualizarEstadoJugador(suplenteId, true); // Cambiar suplente a titular
        } else {
            alert('Selecciona un jugador titular y un suplente para hacer la rotación.');
        }
    });
});

function actualizarEstadoJugador(jugadorId, esTitular) {
    console.log(`Actualizando estado del jugador con ID: ${jugadorId} a titular: ${esTitular}`);
    fetch(`http://localhost:8080/jugadores/${jugadorId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ titular: esTitular })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar el estado del jugador.');
            }
            console.log('Estado del jugador actualizado:', response.status);
        })
        .catch(error => {
            console.error('Error al actualizar el estado del jugador:', error);
        });
}
