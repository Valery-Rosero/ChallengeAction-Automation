package com.choucair.steps;

import com.choucair.pages.RegisterPage;
import com.choucair.utils.DriverManager;
import io.cucumber.java.es.*;
import org.junit.Assert;

public class RegistroSteps {

    private RegisterPage registerPage = new RegisterPage();

    @Dado("que el usuario navega a la página de registro")
    public void navegarARegistro() {
        registerPage.navigateToRegister();
    }

    @Cuando("el usuario completa el formulario con {string} {string} {string} y {string}")
    public void completarFormulario(String nombre, String apellido, String correo, String contrasena) {
        registerPage.fillRegisterForm(nombre, apellido, correo, contrasena);
    }

    @Y("el usuario hace clic en el botón de registro")
    public void clickRegistro() {
        registerPage.clickRegister();
    }

    @Entonces("el usuario debe ver el mensaje de registro exitoso {string}")
    public void verificarMensaje(String mensajeEsperado) {
        String mensajeActual = registerPage.getRegisterSuccessMessage();
        Assert.assertTrue(mensajeActual.contains(mensajeEsperado));
        DriverManager.quitDriver();
    }
}