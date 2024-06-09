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
            console.log(data); // Verificar los datos recibidos de la API
            // Actualizar el contenido de cada botón con los nombres de los jugadores
            actualizarBotones(data);
        })
        .catch(error => {
            console.error('Error al obtener los datos de la API:', error);
        });
}

// Función para actualizar los botones con los nombres de los jugadores
function actualizarBotones(data) {
    const botones = document.querySelectorAll('.list-group-item');
    data.forEach((jugador, index) => {
        const boton = botones[index];
        if (boton) {
            boton.innerText = `${jugador.nombre} ${jugador.dorsal}`;
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
            console.log(data); // Verificar los datos recibidos de la API
            // Actualizar el contenido de cada botón con los nombres de los jugadores
            actualizarBotones_Suplentes(data);
        })
        .catch(error => {
            console.error('Error al obtener los datos de la API:', error);
        });
}

// Función para actualizar los botones con los nombres de los jugadores
function actualizarBotones_Suplentes(data) {
    data.forEach((jugador, index) => {
        const boton = document.getElementById(`bottonJugador${index+1}`);
        if (boton) {
            boton.innerText = `${jugador.nombre} ${jugador.dorsal}`;
        }
    });
}

// Llamar a ambas funciones para obtener los jugadores (titulares y suplentes) cuando se cargue la página
window.onload = function() {
    obtenerJugadoresTitulares();
    obtenerJugadoresSuplentes();
};

