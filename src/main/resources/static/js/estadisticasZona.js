// Objeto de mapeo de nombres de zona
const zonaMapping = {
    "Zona de saque 1": "10",
    "Zona de saque 5": "11",
    "Zona de saque 6": "12",
    "Zona 1": "1",
    "Zona 2": "2",
    "Zona 3": "3",
    "Zona 4": "4",
    "Zona 5": "5",
    "Zona 6": "6",
    "Zona 7": "7",
    "Zona 8": "8",
    "Zona 9": "9",
    "TOTAL": "TOTAL"
};

// Función para cargar estadísticas
async function cargarEstadisticas(idPartido) {
    try {
        const response = await fetch(`http://localhost:8080/zonas/estadisticas/${idPartido}`);
        const data = await response.json();

        console.log('Datos recibidos de la API:', data);


        data.forEach(estadistica => {
            const nombreZonaDB = estadistica.idZona.nombreZona; // Nombre de zona según la base de datos
            const nombreZonaHTML = Object.keys(zonaMapping).find(key => zonaMapping[key] === nombreZonaDB);

            if (!nombreZonaHTML) {
                console.warn(`No se encontró la zona correspondiente para ${nombreZonaDB}`);
                return; // Salir del bucle si no se encuentra el nombre de zona correspondiente
            }

            const tableRows = document.querySelectorAll('tbody tr');

            tableRows.forEach(row => {
                const th = row.querySelector('th[scope="row"]');
                if (th && th.textContent.trim() === nombreZonaHTML) {
                    const cells = row.querySelectorAll('td');
                    const equipo = estadistica.partido.equipo.nombreEquipo;

                    cells[0].textContent = estadistica.saquesTotal || '-';
                    cells[1].textContent = estadistica.saquesPuntos || '-';
                    cells[2].textContent = estadistica.rematesTotal || '-';
                    cells[3].textContent = estadistica.rematesBloqueados || '-';
                }
            });
        });
    } catch (error) {
        console.error('Error al cargar las estadísticas:', error);
    }
}


window.onload = function () {
    // Obtener los parámetros de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const idPartido = urlParams.get('idPartido');

    if (idPartido) {
        // Llamar a cargarEstadisticas con idPartido si está presente en la URL
        cargarEstadisticas(idPartido);
    } else {
        console.error('No se encontró el parámetro idPartido en la URL.');
    }
};

function startGame(){
    cargarEstadisticas(idPartido);
}