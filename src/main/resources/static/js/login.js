document.getElementById('loginButton').addEventListener('click', function (event) {
    event.preventDefault(); // Evita el comportamiento predeterminado del formulario
    const passwordField = document.getElementById('password');
    const errorMessage = document.getElementById('error-message');
    const password = passwordField.value;
    const correctPassword = 'Sc0Ut_2o2*4'; // Cambia esta contraseña por la que desees

    if (password === correctPassword) {
        // Almacenar estado de autenticación en localStorage
        localStorage.setItem('authenticated', 'true');
        // Redirigir a la página deseada después del login exitoso
        window.location.href = 'index.html';
    } else {
        errorMessage.textContent = 'Incorrect password. Please try again.';
    }
});