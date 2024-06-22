// Nuevo dominio de la API
var apiUrl = 'https://scoutboard.alvarofs.com';



document.addEventListener('DOMContentLoaded', function () {
    // Llamada a la API para obtener la lista de equipos
    function cargarEquipos() {
        fetch('https://scoutboard.alvarofs.com/equipos/todos')
            .then(response => response.json())
            .then(equipos => {
                const equipoSelect = document.getElementById('equipoSelect');

                // Limpiar contenido actual del select
                equipoSelect.innerHTML = '<option selected>Selecciona equipo</option>';

                // Crear y añadir opciones para cada equipo
                equipos.forEach(equipo => {
                    const option = document.createElement('option');
                    option.value = equipo.idEquipo;  // Asignar el ID del equipo al valor de la opción
                    option.textContent = equipo.nombreEquipo;
                    equipoSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error al cargar los equipos:', error));
    }

    // Llamada a la API para obtener la lista de jugadores del equipo seleccionado
    function cargarJugadores(idEquipo) {
        console.log(idEquipo);
        fetch(`https://scoutboard.alvarofs.com/equipos/${idEquipo}/jugadores`)
            .then(response => response.json())
            .then(jugadores => {
                const jugadoresList = document.getElementById('jugadoresList');
                console.log(jugadoresList)
                // Limpiar contenido actual de la lista de jugadores
                jugadoresList.innerHTML = '';

                // Crear y añadir elementos de lista para cada jugador
                jugadores.forEach(jugador => {
                    const li = document.createElement('li');
                    li.className = 'list-group-item d-flex justify-content-start align-items-center';

                    li.innerHTML = `
                                <div class="d-flex gap-3">
                                    <span class="text-dark fw-bold">${jugador.dorsal}</span>
                                    <p>${jugador.nombre}</p>
                                </div>
                                <button type="submit" class="btn btn-link text-dark" data-id="${jugador.idJugador}">
                                    <i class="fa-solid fa-trash"></i>
                                </button>
                            `;

                    jugadoresList.appendChild(li);
                });
                // Añadir eventos de borrar a cada botón de la lista
                document.querySelectorAll('.btn-link').forEach(button => {
                    button.addEventListener('click', function (event) {
                        event.preventDefault();
                        const jugadorId = this.getAttribute('data-id');
                        borrarJugador(jugadorId);
                    });
                });
            })
            .catch(error => console.error('Error al cargar los jugadores:', error));
    }

    // Evento para detectar cambio en el select de equipos
    document.getElementById('equipoSelect').addEventListener('change', function() {
        const idEquipo = this.value;
        if (idEquipo !== 'Selecciona equipo') {
            cargarJugadores(idEquipo);
        }
    });

    // Función para borrar un jugador
    function borrarJugador(idJugador) {
        fetch(`https://scoutboard.alvarofs.com/jugadores/eliminarJugador/${idJugador}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text) });
                }
                console.log(`Jugador con ID ${idJugador} borrado`);
                const equipoId = document.getElementById('equipoSelect').value.trim();
                if (equipoId !== 'Selecciona equipo') {
                    cargarJugadores(equipoId);
                }
            })
            .catch(error => {
                console.error('Error al borrar el jugador:', error.message);
            });
    }

// Escucha el clic en los botones de borrar jugador
    document.querySelectorAll('.btn-link').forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();
            const jugadorId = this.getAttribute('data-id');
            borrarJugador(jugadorId);
        });
    });

    // Llama a la función para cargar los equipos cuando se carga la página
    cargarEquipos();
});

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('jugadorForm').addEventListener('submit', function(event) {
        event.preventDefault();

        // Obtener datos del formulario
        const nombreJugadorElement = document.getElementById('nombreJugador');
        const dorsalJugadorElement = document.getElementById('dorsalJugador');
        const equipoSelectElement = document.getElementById('equipoSelect');

        console.log(nombreJugadorElement);
        console.log(dorsalJugadorElement);
        console.log(equipoSelectElement);
        if (!nombreJugadorElement || !dorsalJugadorElement || !equipoSelectElement) {
            console.error("No se encontraron todos los elementos del formulario.");
            return;
        }

        const nombreJugador = nombreJugadorElement.value.trim();
        const dorsalJugador = dorsalJugadorElement.value.trim();
        const equipoId = equipoSelectElement.value;

        console.log(equipoId);

        // Validar los datos
        if (!nombreJugador || !dorsalJugador || isNaN(dorsalJugador) || !equipoId || isNaN(equipoId)) {
            console.error("Todos los campos son obligatorios y deben ser válidos.");
            return;
        }

        // Crear objeto Jugador
        let jugador = {
            nombre: nombreJugador,
            dorsal: parseInt(dorsalJugador),
            equipo: {
                idEquipo: parseInt(equipoId)
            }
        };

        // Enviar solicitud POST
        fetch('https://scoutboard.alvarofs.com/jugadores/addJugador', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jugador)
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text) });
                }
                return response.json();
            })
            .then(data => {
                console.log('Jugador creado:', data);
            })
            .catch(error => {
                console.error('Error al agregar el jugador:', error.message);
            });
        location.reload();
    });
});


