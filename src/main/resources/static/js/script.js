// Redirigir solo cuando se hace clic en la imagen del producto
document.querySelectorAll('.product-img').forEach(img => {
    img.addEventListener('click', () => {
        const productId = img.getAttribute('data-id');
        window.location.href = `/detalleProducto`;
    });
});

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
