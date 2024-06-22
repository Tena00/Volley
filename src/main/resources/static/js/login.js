// Nuevo dominio de la API
var apiUrl = 'https://scoutboard.alvarofs.com';




document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const passwordInput = document.getElementById('password');
    const errorMessage = document.getElementById('error-message');

    loginForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Evitar que el formulario se envíe automáticamente

        const password = passwordInput.value;

        // Realizar una solicitud AJAX al backend para verificar la contraseña
        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8080/login', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    window.location.href = `index.html`; // Redirigir a la página de inicio si la autenticación es exitosa
                } else {
                    errorMessage.textContent = 'Contraseña incorrecta'; // Mostrar mensaje de error si la autenticación falla
                }
            }
        };
        xhr.send('password=' + encodeURIComponent(password));
    });
});
