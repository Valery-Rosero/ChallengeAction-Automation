package com.choucair.pages;

import com.choucair.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ShoppingPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public ShoppingPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    public void goToDesktops() {
        // Re-buscar el elemento en cada intento para evitar StaleElement
        WebElement computersMenu = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/computers']"))
        );
        actions.moveToElement(computersMenu).perform();

        WebElement desktopsSubMenu = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/desktops']"))
        );
        desktopsSubMenu.click();
    }

    public void selectFirstProduct() {
        WebElement firstProduct = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector(".product-title a"))
        );
        firstProduct.click();
    }

    public void addToCart() {
        WebElement addToCartButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector(".add-to-cart-button"))
        );
        addToCartButton.click();
    }

    public void goToCart() {
        // Esperar que la notificación del carrito aparezca primero
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".bar-notification")
        ));
        WebElement cartLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/cart']"))
        );
        cartLink.click();
    }

    public void acceptTermsAndCheckout() {
        WebElement termsCheckbox = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("termsofservice"))
        );
        termsCheckbox.click();

        WebElement checkoutButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("checkout"))
        );
        checkoutButton.click();
    }
}