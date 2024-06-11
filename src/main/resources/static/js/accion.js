let resultadoAccionSeleccionado = null;
let jugadorSeleccionado = null;
let zonaSeleccionada = null;
let zonaAtaqueSeleccionada = null;
const idPartido = 1; // Ajusta este valor según el partido actual

// Función para seleccionar el resultado de la acción
document.querySelectorAll('.remates_rojo, .remates_amarillo, .remates_verde').forEach(boton => {
    boton.addEventListener('click', function () {
        resultadoAccionSeleccionado = this.classList.contains('remates_rojo') ? 'Rojo' :
            this.classList.contains('remates_amarillo') ? 'Amarillo' : 'Verde';
        console.log(`Resultado seleccionado: ${resultadoAccionSeleccionado}`);
    });
});

// Función para seleccionar el jugador que realiza la acción
document.querySelectorAll('.list-group-item').forEach(boton => {
    boton.addEventListener('click', function () {
        jugadorSeleccionado = this.dataset.id;
        console.log(`Jugador seleccionado: ${jugadorSeleccionado}`);
    });
});

// Función para seleccionar la zona de ataque
document.querySelectorAll('.mitad1 .z5, .mitad1 .z6, .mitad1 .z9, .mitad1 .z4, .mitad1 .z8, .mitad1 .z3, .mitad1 .z1, .mitad1 .z7, .mitad1 .z2').forEach(boton => {
    boton.addEventListener('click', function () {
        zonaSeleccionada = this.classList[0]; // Usamos la clase para identificar la zona
        console.log(`Zona de ataque seleccionada: ${zonaSeleccionada}`);
    });
});

// Función para seleccionar la zona de recepción
document.querySelectorAll('.mitad2 .r2, .mitad2 .r7, .mitad2 .r1, .mitad2 .r3, .mitad2 .r8, .mitad2 .r6, .mitad2 .r4, .mitad2 .r9, .mitad2 .r5').forEach(boton => {
    boton.addEventListener('click', function () {
        zonaAtaqueSeleccionada = this.classList[0]; // Usamos la clase para identificar la zona de recepción
        console.log(`Zona de recepción seleccionada: ${zonaAtaqueSeleccionada}`);
    });
});

// Función para enviar los datos al servidor
function enviarDatosAccion() {
    if (!resultadoAccionSeleccionado || !jugadorSeleccionado || !zonaSeleccionada || !zonaAtaqueSeleccionada) {
        alert('Por favor, selecciona todas las opciones necesarias antes de registrar la acción.');
        return;
    }

    const datos = {
        idPartido: idPartido,
        idJugador: jugadorSeleccionado,
        idZona: zonaSeleccionada,
        idZonaAtaque: zonaAtaqueSeleccionada,
        resultadoAccion: resultadoAccionSeleccionado
    };

    fetch('http://localhost:8080/acciones', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al registrar la acción.');
            }
            console.log('Acción registrada correctamente:', response.status);
        })
        .catch(error => {
            console.error('Error al registrar la acción:', error);
        });
}

// Asignar la función enviarDatosAccion al botón de registro de acción
document.getElementById('rotacion').addEventListener('click', enviarDatosAccion);
