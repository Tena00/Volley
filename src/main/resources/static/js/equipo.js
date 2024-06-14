document.getElementById('equipoForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío del formulario por defecto

    const nombreEquipo = document.getElementById('nombreEquipo').value;

    fetch('http://localhost:8080/equipos/addEquipo', {
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
        });
});

document.addEventListener('DOMContentLoaded', function() {
    // Llamada a la API para obtener la lista de equipos
    function cargarEquipos() {
        fetch('http://localhost:8080/equipos/todos')
            .then(response => response.json())
            .then(equipos => {
                const equipoSelect = document.getElementById('equipoSelect');
                equipos.forEach(equipo => {
                    const option = document.createElement('option');
                    option.value = equipo.idEquipo;  // Asume que 'id' es el identificador del equipo
                    option.text = equipo.nombreEquipo;
                    equipoSelect.add(option);
                });
            })
            .catch(error => console.error('Error al cargar los equipos:', error));
    }

    // Llama a la función para cargar los equipos cuando se carga la página
    cargarEquipos();
});

document.addEventListener('DOMContentLoaded', function() {
    // Llamada a la API para obtener la lista de equipos
    function cargarEquipos() {
        fetch('http://localhost:8080/equipos/todos')
            .then(response => response.json())
            .then(equipos => {
                const selectAnalizar = document.querySelector('.select_analizar');

                // Limpiar contenido actual de los botones
                selectAnalizar.innerHTML = '';

                // Crear y añadir botones para cada equipo
                equipos.forEach(equipo => {
                    const button = document.createElement('button');
                    button.type = 'button';
                    button.className = 'list-group-item list-group-item-action';
                    button.textContent = equipo.nombreEquipo;
                    selectAnalizar.appendChild(button);
                });
            })
            .catch(error => console.error('Error al cargar los equipos:', error));
    }

    // Llama a la función para cargar los equipos cuando se carga la página
    cargarEquipos();
});
