window.onload = function () {
obtenerZonasCampo();
};

// Función para obtener los datos de las Zonas del campo desde la API
function obtenerZonasCampo() {
    fetch('http://localhost:8080/zonas')
        .then(response => {
            if (!response.ok) {
                throw new Error('La respuesta de la API no fue exitosa.');
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos de las Zonas:', data); // Verificar los datos recibidos de la API
            actualizarZonasCampo(data);
        })
        .catch(error => {
            console.error('Error al obtener los datos de la API:', error);
        });
}

// Función para actualizar los botones con los nombres de los jugadores suplentes
function actualizarZonasCampo(data) {
    data.forEach((zonasCampo, index) => {
        const boton = document.getElementById(`zonaCampo${index + 1}`);
        if (boton) {
            boton.dataset.id = zonasCampo.idZonaCampo; // Guardar el ID del jugador en el botón
        }
    });
}