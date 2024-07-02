



document.getElementById('equipoForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío del formulario por defecto

    const nombreEquipo = document.getElementById('nombreEquipo').value;

    fetch('http://localhost:8080/equipos/addEquipo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ nombreEquipo: nombreEquipo })
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                return response.text().then(text => { throw new Error(text) });
            }
        })
        .then(data => {
            console.log('Success:', data);
            alert("Equipo agregado exitosamente.");
            // Aquí puedes manejar la respuesta de la API, por ejemplo, mostrar un mensaje de éxito
        })
        .catch((error) => {
            console.error('Error:', error);
            alert("Error al agregar el equipo: " + error.message);
            // Aquí puedes manejar los errores de la API, por ejemplo, mostrar un mensaje de error
        });

});

document.addEventListener('DOMContentLoaded', function() {
    // Llamada a la API para obtener la lista de equipos
    function cargarEquipos() {
        fetch('http://localhost:8080/equipos/todos')
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

    // Llama a la función para cargar los equipos cuando se carga la página
    cargarEquipos();
});


document.addEventListener('DOMContentLoaded', function() {
    // Llamada a la API para obtener la lista de equipos
    function cargarEquipos() {
        fetch('http://localhost:8080/equipos/todos')
            .then(response => response.json())
            .then(equipos => {
                const selectAnalizar = document.querySelector('.select_analizar');
                const selectContrario = document.querySelector('.select_contrario');

                // Limpiar contenido actual de los botones
                selectAnalizar.innerHTML = '';
                selectContrario.innerHTML = '';

                // Crear y añadir botones para cada equipo en select_analizar
                equipos.forEach(equipo => {
                    const buttonAnalizar = document.createElement('button');
                    buttonAnalizar.type = 'button';
                    buttonAnalizar.className = 'list-group-item list-group-item-action';
                    buttonAnalizar.textContent = equipo.nombreEquipo;
                    buttonAnalizar.setAttribute('data-id', equipo.idEquipo); // Asignar el ID del equipo como atributo de datos
                    buttonAnalizar.addEventListener('click', function() {
                        // Al hacer clic en el botón, capturar el idEquipo
                        const idEquipo = equipo.idEquipo;
                        console.log('ID del equipo seleccionado:', idEquipo);
                        // Opcional: Puedes resaltar visualmente el botón seleccionado si lo deseas
                        // Reiniciar estilos de botones previamente seleccionados
                        const botones = document.querySelectorAll('.list-group-item');
                        botones.forEach(boton => boton.classList.remove('selected'));
                        // Agregar clase de selección al botón actual
                        buttonAnalizar.classList.add('selected');
                    });
                    selectAnalizar.appendChild(buttonAnalizar);
                });

                // Crear y añadir botones para cada equipo en select_contrario
                equipos.forEach(equipo => {
                    const buttonContrario = document.createElement('button');
                    buttonContrario.type = 'button';
                    buttonContrario.className = 'list-group-item list-group-item-action';
                    buttonContrario.textContent = equipo.nombreEquipo;
                    selectContrario.appendChild(buttonContrario);
                });
            })
            .catch(error => console.error('Error al cargar los equipos:', error));
    }

    // Llama a la función para cargar los equipos cuando se carga la página
    cargarEquipos();
});

document.getElementById('jugadorForm').addEventListener('submit', function(event) {
    event.preventDefault();

    // Obtener datos del formulario
    const nombreJugador = document.getElementById('nombreJugador').value.trim();
    const dorsalJugador = document.getElementById('dorsalJugador').value.trim();
    const equipoId = document.getElementById('equipoSelect').value;

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
    fetch('http://localhost:8080/jugadores/addJugador', {
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
});

// Función para iniciar el partido
function startGame() {
    // Encontrar el botón seleccionado
    const selectedButton = document.querySelector('.list-group-item.selected');
    if (!selectedButton) {
        console.error('Debes seleccionar un equipo antes de iniciar el partido.');
        alert('Debes seleccionar un equipo antes de iniciar el partido.');
        return;
    }
    // Obtener el idEquipo del botón seleccionado
    const idEquipo = selectedButton.getAttribute('data-id');
    console.log('Iniciando partido para el equipo con ID:', idEquipo);

    // Realizar la solicitud POST usando fetch
    fetch(`http://localhost:8080/partidos/iniciarPartido/${idEquipo}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({id: idEquipo})
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al iniciar partido');
            }
            return response.json();
        })
        .then(data => {
            console.log('Partido iniciado:', data);

            // Verificar si el idPartido es null o undefined
            if (data.idPartido == null) {
                throw new Error('El ID del partido no está disponible.');
            }

            // Obtener los IDs del partido y el equipo del objeto JSON devuelto
            const idPartido = data.idPartido;
            const idEquipo = data.idEquipo;

            // Redirigir a la siguiente página después de iniciar el partido
            window.location.href = `partido.html?idPartido=${idPartido}&idEquipo=${idEquipo}`;

        })
        .catch(error => {
            console.error('Error:', error);
            // Manejar el error si el idPartido es null o cualquier otro error
            alert('Error al iniciar el partido: ' + error.message);
        });

}



