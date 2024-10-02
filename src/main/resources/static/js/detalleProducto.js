// Obtener el parámetro de la URL (por ejemplo, id del producto)
const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('id');

// Simulación de datos de productos (puedes usar una API o base de datos)
const productos = {
    "4187": {
        "name": "Varsity Camo Cropped Jersey",
        "price": "$36.00",
        "color": "Dark Tree Camo",
        "image": "imagenes/a.png",
        "description": "Meet the Varsity Camo Jersey—a modern take on the classic football style. This cropped jersey is made from breathable 100% polyester mesh."
    },
    "4018": {
        "name": "Essential Cropped Tees",
        "price": "$35.00",
        "color": "Light Grey",
        "image": "imagenes/asd.png",
        "description": "The perfect essential tee for everyday wear."
    }
};

// Cargar la información del producto en la página
if (productos[productId]) {
    document.getElementById('product-name').textContent = productos[productId].name;
    document.getElementById('product-price').textContent = productos[productId].price;
    document.getElementById('product-color').textContent = productos[productId].color;
    document.getElementById('product-description').textContent = productos[productId].description;
    document.getElementById('product-image').src = productos[productId].image;
}

// Actualizar la imagen principal al hacer clic en una miniatura
document.querySelectorAll('.product-thumbnails img').forEach(thumbnail => {
    thumbnail.addEventListener('click', (e) => {
        const mainImage = document.getElementById('product-image');
        mainImage.src = e.target.src;
    });
});

/* Imagenes ------------------------------------------------------------------------------------------------------------------------------------------------*/

// Seleccionar todas las miniaturas
const thumbnails = document.querySelectorAll('.thumbnail-img');

// Seleccionar la imagen principal
const mainImage = document.getElementById('product-image');

// Añadir un evento click a cada miniatura
thumbnails.forEach(thumbnail => {
    thumbnail.addEventListener('click', function() {
        // Cambiar la imagen principal por la de la miniatura seleccionada
        mainImage.src = this.dataset.img;

        // Remover la clase 'active' de todas las miniaturas
        thumbnails.forEach(thumb => thumb.classList.remove('active'));

        // Añadir la clase 'active' a la miniatura seleccionada
        this.classList.add('active');
    });
});