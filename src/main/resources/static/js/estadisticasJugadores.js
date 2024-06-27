



// Función para obtener el valor de un parámetro de la URL por su nombre
function getUrlParam(name) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    return urlParams.get(name);
}

// Función para obtener datos de la API y crear el acordeón con la tabla
async function fetchAndCreateAccordionAndTable() {
    var modalBody = document.querySelector('#statsJugModal .modal-body');
    var equipoId = getUrlParam('idEquipo');
    var idPartido = getUrlParam('idPartido');

    // Verifica si se ha proporcionado el idEquipo en la URL
    if (!equipoId) {
        console.error('ID de equipo no proporcionado en la URL');
        alert('ID de equipo no proporcionado en la URL.');
        return;
    }

    // Verifica si se ha proporcionado el idPartido en la URL
    if (!idPartido) {
        console.error('ID de partido no proporcionado en la URL');
        alert('ID de partido no proporcionado en la URL.');
        return;
    }

    try {
        // Llamada AJAX usando Fetch API para obtener jugadores del equipo
        const response = await fetch(`https://scoutboard-2c1996d939fa.herokuapp.com/equipos/${equipoId}/jugadores`);
        if (!response.ok) {
            throw new Error('Error al obtener los datos de la API');
        }
        const jugadores = await response.json();

        // Crea el acordeón
        var accordion = document.createElement('div');
        accordion.classList.add('accordion');
        accordion.id = 'accordionPanelsStayOpen';

        // Itera sobre los datos recibidos (jugadores)
        for (const player of jugadores) {
            // Crea el accordion-item para cada jugador
            var accordionItem = document.createElement('div');
            accordionItem.classList.add('accordion-item');

            // Crea el header del accordion-item
            var accordionHeader = document.createElement('h2');
            accordionHeader.classList.add('accordion-header');
            accordionHeader.id = 'nombreJugador' + player.idJugador;

            // Crea el botón del header
            var accordionButton = document.createElement('button');
            accordionButton.classList.add('accordion-button');
            accordionButton.type = 'button';
            accordionButton.setAttribute('data-bs-toggle', 'collapse');
            accordionButton.setAttribute('data-bs-target', '#playerCollapse' + player.idJugador);
            accordionButton.setAttribute('aria-expanded', 'true');
            accordionButton.setAttribute('aria-controls', 'playerCollapse' + player.idJugador);
            accordionButton.textContent = player.nombre; // Ajusta según la estructura de tu objeto Jugador

            // Agrega el botón al header
            accordionHeader.appendChild(accordionButton);

            // Agrega el header al accordion-item
            accordionItem.appendChild(accordionHeader);

            // Crea el div de la collapse
            var accordionCollapse = document.createElement('div');
            accordionCollapse.id = 'playerCollapse' + player.idJugador;
            accordionCollapse.classList.add('accordion-collapse', 'collapse', 'player-collapse'); // Agrega una clase para identificar la collapse

            // Si es el primer jugador, mostrarlo abierto
            if (jugadores.indexOf(player) === 0) {
                accordionCollapse.classList.add('show');
            }

            // Crea el div del body del acordeón
            var accordionBody = document.createElement('div');
            accordionBody.classList.add('accordion-body');

            // Crea la tabla
            var table = document.createElement('table');
            table.classList.add('table');

            // Crea el thead de la tabla
            var thead = document.createElement('thead');
            var trHead = document.createElement('tr');
            var headers = ['', '=', '/', '#', '#%']; // Ajusta según las estadísticas disponibles

            // Agrega los th al tr del thead
            headers.forEach(header => {
                var th = document.createElement('th');
                th.textContent = header;
                trHead.appendChild(th);
            });

            // Agrega el tr del thead a thead
            thead.appendChild(trHead);

            // Crea el tbody de la tabla
            var tbody = document.createElement('tbody');

            // Crea filas de ejemplo con las zonas
            const zonas = [
                "Zona de saque 1", "Zona de saque 5", "Zona de saque 6",
                "Zona 1", "Zona 2", "Zona 3", "Zona 4", "Zona 5",
                "Zona 6", "Zona 7", "Zona 8", "Zona 9", "TOTAL"
            ];

            zonas.forEach(zona => {
                var tr = document.createElement('tr');
                var th = document.createElement('th');
                th.setAttribute('scope', 'row');
                th.setAttribute('data-zona', zona);
                th.textContent = zona;
                tr.appendChild(th);
                tr.innerHTML += `<td>-</td><td>-</td><td>-</td><td>-</td>`;
                tbody.appendChild(tr);
            });

            // Agrega thead y tbody a la tabla
            table.appendChild(thead);
            table.appendChild(tbody);

            // Agrega la tabla al cuerpo del acordeón
            accordionBody.appendChild(table);

            // Agrega el cuerpo del acordeón a la collapse
            accordionCollapse.appendChild(accordionBody);

            // Agrega la collapse al accordion-item
            accordionItem.appendChild(accordionCollapse);

            // Agrega el accordion-item al acordeón
            accordion.appendChild(accordionItem);

            // Llama a cargarEstadisticasJugador con el ID del jugador, del equipo y del partido para actualizar la tabla
            await cargarEstadisticasJugador(player.idJugador, idPartido, accordionCollapse);
        }

        // Limpia el modal-body antes de agregar el nuevo contenido
        modalBody.innerHTML = '';

        // Agrega el accordion al modal-body
        modalBody.appendChild(accordion);

    } catch (error) {
        console.error('Error al obtener datos de la API:', error);
        alert('Error al obtener datos de la API.');
    }
}

// Event listener para llamar a la función al hacer clic en el botón
document.addEventListener('DOMContentLoaded', function () {
    var buttonStatsJugador = document.querySelector('button[data-bs-target="#statsJugModal"]');
    buttonStatsJugador.addEventListener('click', fetchAndCreateAccordionAndTable);
});

// Función para cargar estadísticas de un jugador
async function cargarEstadisticasJugador(jugadorId, idPartido, accordionCollapse) {
    try {
        const response = await fetch(`https://scoutboard-2c1996d939fa.herokuapp.com/zonas/estadisticasJugador/${jugadorId}?idPartido=${idPartido}`);
        if (!response.ok) {
            throw new Error('Error al obtener las estadísticas del jugador');
        }
        const data = await response.json();

        console.log(`Datos de estadísticas para jugador ${jugadorId} en partido ${idPartido}:`, data);

        // Calcular el total de remates para todas las estadísticas recibidas
        const totalRemates = data.reduce((sum, estadistica) => sum + estadistica.rematesTotal, 0);
        const totalSaques = data.reduce((sum, estadistica) => sum + estadistica.saquesTotal, 0);

        // Obtener el acordeón del jugador actual
        const accordionBody = accordionCollapse.querySelector('.accordion-body');
        if (!accordionBody) {
            console.warn(`No se encontró el cuerpo del acordeón para el jugador ${jugadorId}`);
            return;
        }

        // Iterar sobre cada estadística recibida
        for (const estadistica of data) {
            const nombreZonaDB = estadistica.idZona.nombreZona;
            const nombreZonaHTML = Object.keys(zonaMappingJugadores).find(
                (key) => zonaMappingJugadores[key] === nombreZonaDB
            );

            if (!nombreZonaHTML) {
                console.warn(`No se encontró la zona correspondiente para ${nombreZonaDB}`);
                continue; // Saltar al siguiente si no se encuentra el nombre de zona correspondiente
            }

            console.log(`Buscando fila para zona HTML: ${nombreZonaHTML}`);

            // Buscar la fila correspondiente en el acordeón
            const row = accordionBody.querySelector(`tr th[scope='row'][data-zona="${nombreZonaHTML}"]`);
            if (!row) {
                console.warn(`No se encontró la fila correspondiente para ${nombreZonaHTML}`);
                continue; // Saltar al siguiente si no se encuentra la fila correspondiente
            }

            // Verificar si la fila contiene celdas
            const cells = row.parentElement.querySelectorAll('td');

            // Calcular remates fallados
            const rematesFallados = estadistica.rematesTotal - (estadistica.rematesPuntos + estadistica.rematesBloqueados);
            const saquesFallados = estadistica.saquesTotal - estadistica.saquesPuntos;
            // Calcular el porcentaje de remates total
            const porcentajeRematesTotal = totalRemates ? ((estadistica.rematesTotal / totalRemates) * 100).toFixed(2) + "%" : "-";
            const porcentajeSaquesTotal = totalSaques ? ((estadistica.saquesTotal / totalSaques) * 100).toFixed(2) + "%" : "-";

            if (nombreZonaHTML.startsWith("Zona de saque")) {
                cells[0].textContent = saquesFallados || "-";
                cells[1].textContent = estadistica.rematesBloqueados || "-";
                cells[2].textContent = estadistica.saquesPuntos || "-";
                cells[3].textContent = porcentajeSaquesTotal;
            } else {
                // Actualizar las celdas con los datos correspondientes
                cells[0].textContent = rematesFallados || "-";
                cells[1].textContent = estadistica.rematesBloqueados || "-";
                cells[2].textContent = estadistica.rematesPuntos || "-";
                cells[3].textContent = porcentajeRematesTotal;
            }

            // Llamar a la función para actualizar la fila "TOTAL" específica del jugador
            actualizarFilaTotalJugador(idPartido, totalRemates, accordionCollapse);
        }

    } catch (error) {
        console.error(`Error al cargar las estadísticas para el jugador ${jugadorId}:`, error);
    }
}

async function actualizarFilaTotalJugador(idPartido, totalRemates, accordionCollapse) {
    try {
        const totalDataResponse = await fetch(`https://scoutboard-2c1996d939fa.herokuapp.com/zonas/estadisticasTotal/${idPartido}`);
        const totalData = await totalDataResponse.json();

        // Obtener los datos específicos
        const rematesPuntos = totalData[1] || 0; // Remates que han sido puntos
        const rematesFallados = totalData[3] || 0; // Remates fallados
        const rematesBloqueados = totalData[2] || 0; // Remates bloqueados

        // Calcular el porcentaje de acierto
        const totalRematesCalculado = rematesFallados + rematesBloqueados + rematesPuntos;
        const porcentajeAcierto = totalRemates !== 0 ? ((rematesPuntos / totalRemates) * 100).toFixed(2) + "%" : "-";

        // Buscar la fila de "TOTAL" dentro del acordeón específico
        const totalRow = accordionCollapse.querySelector("tbody tr[data-zona='TOTAL']");
        if (totalRow) {
            const cells = totalRow.querySelectorAll("td");
            cells[0].textContent = rematesFallados || "-";
            cells[1].textContent = rematesBloqueados || "-";
            cells[2].textContent = rematesPuntos || "-";
            cells[3].textContent = porcentajeAcierto;
        } else {
            console.warn("No se encontró la fila de TOTAL en el acordeón específico.");
        }
    } catch (error) {
        console.error("Error al obtener los datos de TOTAL:", error);
    }
}


// Objeto de mapeo de nombres de zona
const zonaMappingJugadores = {
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
