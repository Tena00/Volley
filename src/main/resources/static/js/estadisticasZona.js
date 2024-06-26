
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

async function cargarEstadisticas(idPartido) {
    try {
        const response = await fetch(`https://scoutboard-2c1996d939fa.herokuapp.com/zonas/estadisticas/${idPartido}`);
        const data = await response.json();

        console.log("Datos recibidos de la API:", data);

        // Iterar sobre cada estadística recibida
        for (const estadistica of data) {
            const nombreZonaDB = estadistica.idZona.nombreZona;
            const nombreZonaHTML = Object.keys(zonaMapping).find(
                (key) => zonaMapping[key] === nombreZonaDB
            );

            if (!nombreZonaHTML) {
                console.warn(
                    `No se encontró la zona correspondiente para ${nombreZonaDB}`
                );
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

                    // Calcular remates fallados
                    const rematesFallados =
                        estadistica.rematesTotal -
                        (estadistica.rematesPuntos + estadistica.rematesBloqueados);

                    // Actualizar las celdas con los datos correspondientes
                    cells[0].textContent = rematesFallados || "-";
                    cells[1].textContent = estadistica.rematesBloqueados || "-";
                    cells[2].textContent = estadistica.rematesPuntos || "-";
                    cells[3].textContent = estadistica.rematesTotal || "-";

                    // Verificar si es la zona "TOTAL"
                    if (normalizedThText === "total") {
                        try {
                            // Realizar la llamada para obtener datos adicionales de "TOTAL"
                            const totalDataResponse = await fetch(`https://scoutboard-2c1996d939fa.herokuapp.com/zonas/estadisticasTotal/${idPartido}`);
                            const totalData = await totalDataResponse.json();

                            console.log("Datos de TOTAL recibidos:", totalData);

                            // Actualizar las celdas con los datos de "TOTAL"
                            cells[4].textContent = totalData[0] || "-";
                            cells[5].textContent = totalData[1] || "-";
                            cells[6].textContent = totalData[2] || "-";
                            cells[7].textContent = totalData[3] || "-";
                        } catch (error) {
                            console.error("Error al obtener los datos de TOTAL:", error);
                        }
                    }
                }
            }
        }
    } catch (error) {
        console.error("Error al cargar las estadísticas:", error);
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
