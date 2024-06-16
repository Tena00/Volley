window.addEventListener('load', function() {
    obtenerZonasCampo();
    obtenerZonasAtaque();
});

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
            console.log('Datos de las zonas del campo:', data); // Verificar los datos recibidos de la API
            actualizarBotonesZonasCampo(data);
        })
        .catch(error => {
            console.error('Error al obtener los datos de la API:', error);
        });
}

// Función para actualizar los botones con los nombres de las zonas del campo
function actualizarBotonesZonasCampo(data) {
    data.forEach((zonasCampo, index) => {
        const boton = document.getElementById(`zonaCampo${index + 1}`);
        if (boton) {
            boton.dataset.id = zonasCampo.idZonaCampo;// Guardar el ID de la zona en el botón
        }
    });
}

// Función para obtener los datos de las Zonas del campo desde la API
function obtenerZonasAtaque() {
    fetch('http://localhost:8080/zonasAtaque')
        .then(response => {
            if (!response.ok) {
                throw new Error('La respuesta de la API no fue exitosa.');
            }
            return response.json();
        })
        .then(data => {
            console.log('Datos de las zonas Ataque del campo:', data); // Verificar los datos recibidos de la API
            actualizarBotonesZonasAtaque(data);
        })
        .catch(error => {
            console.error('Error al obtener los datos de la API:', error);
        });
}

// Función para actualizar los botones con los nombres de las zonas del campo
function actualizarBotonesZonasAtaque(data) {
    const numerosCoincidentes = [10, 11, 12];
    data.forEach((zonasAtaque, index) => {
        const boton = document.getElementById(`zonaAtaque${index + 1}`);
        if (boton) {
            boton.dataset.id = zonasAtaque.idZonaAtaque; // Guardar el ID de la zona en el botón
        }
        const out = document.getElementById(`out${index + 1}`);
        console.log(`Elemento out${index + 1}:`, out); // Verificar si el elemento out existe

        if (out) {
            console.log(`ID de zona ataque: ${zonasAtaque.idZonaAtaque}`);
            if (numerosCoincidentes.includes(zonasAtaque.idZonaAtaque)) {
                out.dataset.id = zonasAtaque.idZonaAtaque; // Guardar el ID de la zona en el botón
                console.log(`ID ${zonasAtaque.idZonaAtaque} encontrado y asignado a out${index + 1}`);
            } else {
                console.log(`ID ${zonasAtaque.idZonaAtaque} no está en numerosCoincidentes`);
            }
        }
    });
}