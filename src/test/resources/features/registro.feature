#language: es
Característica: Registro de usuario en Demo Web Shop

  Esquema del escenario: Registro exitoso de un nuevo usuario
    Dado que el usuario navega a la página de registro
    Cuando el usuario completa el formulario con "<nombre>" "<apellido>" "<correo>" y "<contrasena>"
    Y el usuario hace clic en el botón de registro
    Entonces el usuario debe ver el mensaje de registro exitoso "Your registration completed"

    Ejemplos:
      | nombre | apellido | correo                          | contrasena  |
      | Jose   | Elver    | joseelver_nuevo107@gmail.com     | Test1234*   |