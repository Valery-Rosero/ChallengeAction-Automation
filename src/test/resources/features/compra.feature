#language: es
Característica: Compra de producto en Demo Web Shop

  Escenario: Compra exitosa de un producto con tarjeta de crédito
    Dado que el usuario inicia sesión con "joseelver123@gmail.com" y "Test1234*"
    Cuando el usuario navega a la categoría Computers y subcategoría Desktops
    Y el usuario agrega el primer producto al carrito
    Y el usuario va al carrito y procede al checkout
    Y el usuario completa la dirección de facturación
    Y el usuario selecciona tarjeta de crédito como método de pago
    Y el usuario completa la información de pago con tarjeta Visa
    Y el usuario confirma la orden
    Entonces el usuario debe ver el mensaje de compra exitosa "Your order has been successfully processed!"