// Esperamos que toda la página se haya cargado
window.addEventListener("load", function() {
    // Ocultamos la pantalla de carga
    const loadingScreen = document.getElementById("loading-screen");
    
    loadingScreen.style.opacity = "0";  // Desvanecemos la pantalla de carga
    setTimeout(function() {
        loadingScreen.style.display = "none";  // Ocultamos la pantalla completamente después de 0.5s
        
    }, 1000); // Espera el tiempo de la animación
});

// Sidebar - expandir
const hamBurger = document.querySelector(".toggle-btn");

hamBurger.addEventListener("click", function () {
  document.querySelector("#sidebar").classList.toggle("expand");
});