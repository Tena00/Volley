// Nuevo dominio de la API
var apiUrl = 'https://scoutboard.alvarofs.com';




function navigateTo(page) {
    window.location.href = page;
}
function showAddTeamView() {
    document.getElementById('addTeamView').style.display = 'block';
    document.getElementById('teamListView').style.display = 'none';
}

function viewTeams() {
    fetch('http://localhost:8080/equipos/todos')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            const teamsList = document.getElementById('teamsList');
            teamsList.innerHTML = ''; // Limpiar la lista de equipos

            data.forEach(team => {
                const teamItem = document.createElement('div');
                teamItem.className = 'team-item';
                teamItem.innerHTML = `
                     <div><strong>Nombre:</strong> ${team.nombreEquipo}</div>
                    <button onclick="editTeam(${team.id})"><img src="path/to/edit-icon.png" alt="Editar"></button>
                    <button onclick="deleteTeam(${team.idEquipo})"><img src="path/to/delete-icon.png" alt="Borrar"></button>
                `;
                teamsList.appendChild(teamItem);
            });

            // Muestra la vista de la lista de equipos y oculta la vista de agregar equipo
            document.getElementById('addTeamView').style.display = 'none';
            document.getElementById('teamListView').style.display = 'block';
        })
        .catch(error => console.error('Error:', error));
}

function deleteTeam(id) {
    fetch(`http://localhost:8080/equipos/eliminarEquipo/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Team deleted successfully:', data);
            viewTeams(); // Refresca la lista de equipos después de eliminar uno
        })
        .catch(error => console.error('Error:', error));
}

