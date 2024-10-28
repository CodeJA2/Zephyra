// Funcionalidad de selección de colores
document.querySelectorAll('.color-option').forEach(color => {
    color.addEventListener('click', () => {
        document.querySelectorAll('.color-option').forEach(c => c.style.outline = "none");
        color.style.outline = "2px solid #000";
        const productCard = color.closest('.product-card');
        const productImage = productCard.querySelector('.product-img');

        const selectedColor = color.getAttribute('data-color');
        switch (selectedColor) {
            case 'orange':
                productImage.src = "/img/Polos/Polo_1.png";
                break;
            case 'green':
                productImage.src = "/img/Polos/Polo_2.png";
                break;
            case 'purple':
                productImage.src = "/img/Polos/Polo_3.png";
                break;
            case 'black':
                productImage.src = "/img/Polos/Polo_4.png";
                break;
            case 'blue':
                productImage.src = "/img/Polos/Polo_5.png";
                break;
            default:
                productImage.src = "/img/Polos/Polo_1.png";
        }
    });
});

// Accion cuando seleccionas el COLOR
document.querySelectorAll('.color-circle').forEach(circle => {
    circle.addEventListener('click', function () {
        document.querySelectorAll('.color-circle').forEach(c => c.classList.remove('selected'));
        this.classList.add('selected');
    });
});
// Accion cuando seleccionas el TALLA
document.querySelectorAll('.size-product-item').forEach(btn => {
    btn.addEventListener('click', function () {
        document.querySelectorAll('.size-product-item').forEach(b => b.classList.remove('selected'));
        this.classList.add('selected');
    });
});

// Funcionalidad de calificación por estrellas
document.querySelectorAll('.star-rating').forEach(rating => {
    let isLocked = false; // Para bloquear la selección de estrellas después del primer clic
    rating.addEventListener('click', function (event) {
        const stars = this.querySelectorAll('i');
        let index = Array.from(stars).indexOf(event.target);

        if (index >= 0 && !isLocked) {
            stars.forEach((star, i) => {
                star.classList.toggle('fas', i <= index); // Rellena hasta la estrella seleccionada
                star.classList.toggle('far', i > index);  // Deja el resto vacías
            });
            isLocked = true; // Bloquea la selección
        } else if (event.target.classList.contains('fas')) {
            stars.forEach(star => {
                star.classList.remove('fas'); // Desbloquea y limpia todas las estrellas
                star.classList.add('far');
            });
            isLocked = false; // Vuelve a habilitar la selección
        }
    });
});

//Esconder el Top-bar cuando se hace scroll
window.addEventListener("scroll", function () {
    var header = document.querySelector("header");
    // Solo cambia la clase si se ha hecho scroll
    if (window.scrollY > 80) {
        header.classList.add("header-scroll");
    } else {
        header.classList.remove("header-scroll");
    }
});

// Busqueda te regirige a la pagina "galeria"
document.getElementById('button-search').addEventListener('click', function () {
    const searchQuery = document.getElementById('search-query').value.trim();

    if (searchQuery !== '') {
        // Redirige a la página de búsqueda con el término introducido
        window.location.href = `/galeria?search=${encodeURIComponent(searchQuery)}`;
    } else {

    }
});

// Carrito de compras
let carrito = []; // Array para almacenar los productos en el carrito
let totalProductos = 0; // Contador de productos en el carrito

function agregarProducto(nombre, precio) {
    const productoExistente = carrito.find(producto => producto.nombre === nombre);

    if (productoExistente) {
        // Si ya existe, aumentar la cantidad
        productoExistente.cantidad += 1;
    } else {
        // Si no existe, agregar nuevo producto al carrito
        carrito.push({ nombre, precio, cantidad: 1 });
    }

    // Actualizar el total de productos en el carrito
    totalProductos += 1; // Incrementa el contador total
    actualizarCarrito();
    actualizarContador(); // Llama a la función para actualizar el contador en la página
}

function actualizarCarrito() {
    const carritoContainer = document.querySelector('.list-group'); // Seleccionar la lista de productos en el carrito
    carritoContainer.innerHTML = ''; // Limpiar la lista actual

    let total = 0; // Variable para calcular el total

    // Recorrer los productos en el carrito
    carrito.forEach(producto => {
        const subtotal = producto.precio * producto.cantidad;
        total += subtotal;

        // Crear el elemento de la lista para el carrito
        const item = document.createElement('li');
        item.classList.add('list-group-item');
        item.innerHTML = `
            <div class="row align-items-center">
                <div class="col-3">
                    <img src="/img/Polos/Polo_1.png" alt="${producto.nombre}" class="img-fluid" style="width: 64px; height: 64px; object-fit: cover;">
                </div>
                <div class="col-5">
                    <h6 class="my-0">${producto.nombre}</h6>
                    <small class="text-muted">Precio unitario: S/. ${producto.precio.toFixed(2)}</small>
                </div>
                <div class="col-2 text-center">
                    <small class="text-muted">${producto.cantidad}</small>
                </div>
                <div class="col-2 text-end">
                    <span class="text-muted">S/. ${subtotal.toFixed(2)}</span>
                </div>
            </div>
        `;
        carritoContainer.appendChild(item);
    });

    // Actualizar el total
    document.querySelector('.d-flex.justify-content-between.mb-3 h5:last-child').innerText = `S/. ${total.toFixed(2)}`;
}

function actualizarContador() {
    document.getElementById("cart-count").textContent = totalProductos; // Actualiza el contador
}
