// Nuevo dominio de la API
var apiUrl = 'https://scoutboard.alvarofs.com';




document.getElementById('equipoForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío del formulario por defecto

    const nombreEquipo = document.getElementById('nombreEquipo').value;

    fetch('https://scoutboard.alvarofs.com/equipos/addEquipo', {
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
        });location.reload();
});

document.addEventListener('DOMContentLoaded', function() {
    // Llamada a la API para obtener la lista de equipos
    function cargarEquipos() {
        fetch('https://scoutboard.alvarofs.com/equipos/todos')
            .then(response => response.json())
            .then(equipos => {
                const equipoSelect = document.getElementById('equipoSelect');

                // Limpiar contenido actual del ul
                equipoSelect.innerHTML = '';

                // Crear y añadir elementos de lista para cada equipo
                equipos.forEach(equipo => {
                    const li = document.createElement('li');
                    li.className = 'list-group-item d-flex justify-content-start align-items-center';

                    li.innerHTML = `
                        <div class="d-flex gap-3">
                            <span>${equipo.nombreEquipo}</span>
                        </div>
                        <button type="submit" class="btn btn-link text-dark" data-id="${equipo.idEquipo}">
                            <i class="fa-solid fa-trash"></i>
                        </button>
                    `;

                    equipoSelect.appendChild(li);
                });

                // Añadir eventos para cargar jugadores al hacer clic en cada equipo
                equipoSelect.querySelectorAll('.btn-link').forEach(button => {
                    button.addEventListener('click', function (event) {
                        event.preventDefault();
                        const idEquipo = this.getAttribute('data-id');
                        borrarEquipo(idEquipo);
                    });
                });
            })
            .catch(error => console.error('Error al cargar los equipos:', error));
    }

    function borrarEquipo(idEquipo) {
        console.log(idEquipo)
        fetch(`https://scoutboard.alvarofs.com/equipos/eliminarEquipo/${idEquipo}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text) });
                }
                console.log(`Equipo con ID ${idEquipo} borrado`);

            })
            .catch(error => {
                console.error('Error al borrar el jugador:', error.message);
            });
        location.reload();
    }

    // Llama a la función para cargar los equipos cuando se carga la página
    cargarEquipos();
});

