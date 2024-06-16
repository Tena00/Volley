async function fetchPlayerData() {
    try {
        const response = await fetch('URL_DE_TU_API');
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error al obtener los datos:', error);
    }
}

function updateTable(playerData) {
    const zonas = ['zona1', 'zona2', 'zona3', 'zona4', 'zona5', 'zona6', 'zona7', 'zona8', 'zona9'];
    zonas.forEach((zona, index) => {
        const row = document.querySelector(`.table tbody tr:nth-child(${index + 1})`);
        if (row) {
            const cells = row.querySelectorAll('td');
            cells[0].textContent = playerData.stats[zona] || '-'; // Ajusta según la estructura de tus datos
        }
    });
}

// Llama a la función fetchPlayerData y luego actualiza el DOM
fetchPlayerData().then(playerData => {
    updateTable(playerData);
});
