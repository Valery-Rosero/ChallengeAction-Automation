package com.choucair.steps;

import com.choucair.pages.CheckoutPage;
import com.choucair.pages.LoginPage;
import com.choucair.pages.ShoppingPage;
import com.choucair.utils.DriverManager;
import io.cucumber.java.es.*;
import org.junit.Assert;

public class CompraSteps {

    private LoginPage loginPage = new LoginPage();
    private ShoppingPage shoppingPage = new ShoppingPage();
    private CheckoutPage checkoutPage = new CheckoutPage();

    @Dado("que el usuario inicia sesión con {string} y {string}")
    public void iniciarSesion(String correo, String contrasena) {
        loginPage.navigateToLogin();
        loginPage.login(correo, contrasena);
    }

    @Cuando("el usuario navega a la categoría Computers y subcategoría Desktops")
    public void navegarDesktops() {
        shoppingPage.goToDesktops();
    }

    @Y("el usuario agrega el primer producto al carrito")
    public void agregarProducto() {
        shoppingPage.selectFirstProduct();
        shoppingPage.addToCart();
    }

    @Y("el usuario va al carrito y procede al checkout")
    public void irAlCarrito() {
        shoppingPage.goToCart();
        shoppingPage.acceptTermsAndCheckout();
    }

    @Y("el usuario completa la dirección de facturación")
    public void completarDireccion() {
        checkoutPage.fillBillingAddress(
            "Jose", "Elver", "joseelver123@gmail.com",
            "Medellin", "calle 14 # 6-51", "12315", "3177777777"
        );
        checkoutPage.continueShipping();
        checkoutPage.continueShippingMethod();
    }

    @Y("el usuario selecciona tarjeta de crédito como método de pago")
    public void seleccionarTarjeta() {
        checkoutPage.selectCreditCard();
    }

    @Y("el usuario completa la información de pago con tarjeta Visa")
    public void completarPago() {
        checkoutPage.fillPaymentInfo(
            "Visa", "Barbara Gordon",
            "4485564059489345", "4", "2039", "123"
        );
    }

    @Y("el usuario confirma la orden")
    public void confirmarOrden() {
        checkoutPage.confirmOrder();
    }

    @Entonces("el usuario debe ver el mensaje de compra exitosa {string}")
    public void verificarConfirmacion(String mensajeEsperado) {
        String mensajeActual = checkoutPage.getOrderConfirmationMessage();
        Assert.assertTrue(mensajeActual.contains(mensajeEsperado));
        DriverManager.quitDriver();
}
}