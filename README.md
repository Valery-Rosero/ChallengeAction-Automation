# Choucair Reto Técnico — Automatización Web

Proyecto de automatización web desarrollado con **Java + Selenium + Cucumber + POM (Page Object Model)** sobre la página [Demo Web Shop Tricentis](https://demowebshop.tricentis.com/).

---

## 🛠️ Tecnologías utilizadas

- Java 21
- Maven 3.9.x
- Selenium 4.29.0
- Cucumber 7.22.0
- WebDriverManager 5.9.3
- JUnit 4.13.2
- Google Chrome (último estable)

---

## 📁 Estructura del proyecto

```
choucair-reto/
├── src/
│   └── test/
│       ├── java/com/choucair/
│       │   ├── pages/           ← Page Objects (POM)
│       │   │   ├── RegisterPage.java
│       │   │   ├── LoginPage.java
│       │   │   ├── ShoppingPage.java
│       │   │   └── CheckoutPage.java
│       │   ├── steps/           ← Step Definitions de Cucumber
│       │   │   ├── RegistroSteps.java
│       │   │   └── CompraSteps.java
│       │   ├── runner/          ← Clase que ejecuta los tests
│       │   │   └── TestRunner.java
│       │   └── utils/           ← Utilidades
│       │       ├── DriverManager.java
│       │       └── Hooks.java
│       └── resources/
│           └── features/        ← Escenarios Cucumber en español
│               ├── registro.feature
│               └── compra.feature
├── .gitignore
└── pom.xml
```

---

## 📋 Escenarios automatizados

### Bloque 1 — Registro de usuario
- Navegar a la página de registro
- Completar el formulario con datos válidos
- Verificar el mensaje de registro exitoso: `Your registration completed`

### Bloque 2 — Compra de producto
- Iniciar sesión con el usuario registrado
- Navegar a Computers > Desktops
- Agregar el primer producto al carrito
- Proceder al checkout
- Completar dirección de facturación
- Seleccionar tarjeta de crédito como método de pago
- Ingresar datos de la tarjeta Visa
- Confirmar la orden
- Verificar el mensaje de confirmación: `Your order has been successfully processed!`

---

## ▶️ Cómo ejecutar los tests

### Prerrequisitos
- Java 21 instalado y configurado en variables de entorno
- Maven 3.9.x instalado y configurado en variables de entorno
- Google Chrome instalado

### Comando de ejecución

```bash
mvn clean test
```

---

## 📊 Resultados de la ejecución

Los reportes se generan automáticamente en la carpeta `target` al finalizar la ejecución.

### Reporte HTML (visual)
```
target/cucumber-reports/report.html
```
Ábrelo directamente en el navegador para ver el resultado completo con colores, pasos detallados y tiempos de ejecución.

### Reporte JSON
```
target/cucumber-reports/report.json
```

### Reportes Surefire (por escenario)
```
target/surefire-reports/
```

### Abrir el reporte desde la terminal (Windows)
```bash
start target\cucumber-reports\report.html
```

---

## 👩‍💻 Autora

Valery — Candidata Reto Técnico Choucair
