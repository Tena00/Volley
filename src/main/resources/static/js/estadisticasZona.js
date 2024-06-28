// Objeto de mapeo de nombres de zona
const zonaMapping = {
    "Zona de saque 1": "12",
    "Zona de saque 5": "10",
    "Zona de saque 6": "11",
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

async function cargarEstadisticas(idPartido) {
    try {
        const response = await fetch(`http://localhost:8080/zonas/estadisticas/${idPartido}`);
        const data = await response.json();

        console.log("Datos recibidos de la API:", data);

        // Calcular el total de remates para todas las estadísticas recibidas
        const totalRemates = data.reduce((sum, estadistica) => sum + estadistica.rematesTotal, 0);
        const totalSaques = data.reduce((sum, estadistica) => sum + estadistica.saquesTotal, 0);

        // Iterar sobre cada estadística recibida
        for (const estadistica of data) {
            const nombreZonaDB = estadistica.idZona.nombreZona;
            const nombreZonaHTML = Object.keys(zonaMapping).find(
                (key) => zonaMapping[key] === nombreZonaDB
            );

            if (!nombreZonaHTML) {
                console.warn(`No se encontró la zona correspondiente para ${nombreZonaDB}`);
                continue; // Saltar al siguiente si no se encuentra el nombre de zona correspondiente
            }

            const tableRows = document.querySelectorAll("tbody tr");

            // Iterar sobre cada fila de la tabla
            for (const row of tableRows) {
                const th = row.querySelector("th[scope='row']");
                if (!th) continue; // Saltar al siguiente si no hay encabezado de fila

                const normalizedThText = th.textContent.trim().toLowerCase();
                const normalizedNombreZonaHTML = nombreZonaHTML.toLowerCase();

                // Verificar si la zona HTML coincide con el encabezado de fila
                if (normalizedThText === normalizedNombreZonaHTML) {
                    // Obtener las celdas de la fila actual
                    const cells = row.querySelectorAll("td");

                    // Calcular remates fallados, bloqueados y puntos
                    const rematesFallados = estadistica.rematesTotal - (estadistica.rematesPuntos + estadistica.rematesBloqueados);
                    const saquesFallados = estadistica.saquesTotal - estadistica.saquesPuntos;
                    // Calcular el porcentaje de remates total
                    const porcentajeRematesTotal = estadistica.rematesTotal ? ((estadistica.rematesPuntos / estadistica.rematesTotal) * 100).toFixed(2) + "%" : "-";
                    const porcentajeSaquesTotal = estadistica.saquesTotal ? ((estadistica.saquesPuntos / estadistica.saquesTotal) * 100).toFixed(2) + "%" : "-";

                    if (nombreZonaHTML.startsWith("Zona de saque")) {

                        cells[0].textContent = saquesFallados|| "-";
                        cells[1].textContent = estadistica.rematesBloqueados || "-";
                        cells[2].textContent = estadistica.saquesPuntos || "-";
                        cells[3].textContent = porcentajeSaquesTotal;

                    }else {

                        // Actualizar las celdas con los datos correspondientes
                        cells[0].textContent = rematesFallados || "-";
                        cells[1].textContent = estadistica.rematesBloqueados || "-";
                        cells[2].textContent = estadistica.rematesPuntos || "-";
                        cells[3].textContent = porcentajeRematesTotal;
                    }

                    // Llamar a la función para actualizar la fila "TOTAL"
                    actualizarFilaTotal(idPartido, totalRemates);
                }
            }
        }
    } catch (error) {
        console.error("Error al cargar las estadísticas:", error);
    }
}


async function actualizarFilaTotal(idPartido) {
    try {
        const totalDataResponse = await fetch(`http://localhost:8080/zonas/estadisticasTotal/${idPartido}`);
        const totalData = await totalDataResponse.json();


        // Obtener los datos específicos
        const rematesPuntos = totalData[1] || 0; // Remates que han sido puntos
        const rematesFallados = totalData[3] || 0; // Remates fallados
        const rematesBloqueados = totalData[2] || 0; // Remates bloqueados
        const totalRemates = totalData[0] || 0; // Total de remates en el partido

        // Calcular el porcentaje de acierto
        const totalRematesCalculado = rematesFallados + rematesBloqueados + rematesPuntos;
        const porcentajeAcierto = totalRemates !== 0 ? ((rematesPuntos / totalRemates) * 100).toFixed(2) + "%" : "-";

        // Buscar la fila de "TOTAL" y actualizar sus celdas
        const totalRow = document.querySelector("tbody tr[data-zona='total']");
        if (totalRow) {
            const cells = totalRow.querySelectorAll("td");
            cells[0].textContent = rematesFallados || "-";
            cells[1].textContent = rematesBloqueados || "-";
            cells[2].textContent = rematesPuntos || "-";
            cells[3].textContent = porcentajeAcierto;
        } else {
            console.warn("No se encontró la fila de TOTAL.");
        }
    } catch (error) {
        console.error("Error al obtener los datos de :", error);
    }
}




window.onload = function () {
    // Obtener los parámetros de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const idPartido = urlParams.get("idPartido");

    if (idPartido) {
        // Llamar a cargarEstadisticas con idPartido si está presente en la URL
        cargarEstadisticas(idPartido);
    } else {
        console.error("No se encontró el parámetro idPartido en la URL.");
    }
};

function estadisticas() {
    const urlParams = new URLSearchParams(window.location.search);
    const idPartido = urlParams.get("idPartido");

    // Carga las estadísticas (asumiendo la lógica existente)
    cargarEstadisticas(idPartido);
}
