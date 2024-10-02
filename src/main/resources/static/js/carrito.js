document.addEventListener('DOMContentLoaded', () => {
    const updateTotalPrice = () => {
        let total = 0;
        document.querySelectorAll('.product-row').forEach(row => {
            const price = parseFloat(row.querySelector('.product-price').getAttribute('data-price'));
            const quantity = parseInt(row.querySelector('.quantity-input').value);
            const subtotal = price * quantity;
            row.querySelector('.subtotal').textContent = `Subtotal: $${subtotal.toFixed(2)}`;
            total += subtotal;
        });
        document.getElementById('totalPrice').textContent = `$${total.toFixed(2)}`;
    };

    document.querySelectorAll('.increment-btn').forEach(button => {
        button.addEventListener('click', () => {
            const quantityInput = button.previousElementSibling;
            quantityInput.value = parseInt(quantityInput.value) + 1;
            updateTotalPrice();
        });
    });

    document.querySelectorAll('.decrement-btn').forEach(button => {
        button.addEventListener('click', () => {
            const quantityInput = button.nextElementSibling;
            const newValue = parseInt(quantityInput.value) - 1;
            quantityInput.value = newValue > 0 ? newValue : 1;
            updateTotalPrice();
        });
    });

    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', () => {
            button.closest('.product-row').remove();
            updateTotalPrice();
        });
    });

    // Inicializar el cálculo total al cargar la página
    updateTotalPrice();
});