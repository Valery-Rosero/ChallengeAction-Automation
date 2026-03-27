package com.choucair.pages;

import com.choucair.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    private void clickContinue(String containerSelector) {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector(containerSelector + " input[value='Continue']")
        ));
        btn.click();
    }

    public void fillBillingAddress(String first, String last, String email,
                                    String city, String address, String zip, String phone) {

        // Esperar que cargue el bloque de billing
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("#checkout-step-billing")));

        // Verificar si hay dropdown de direcciones guardadas
        List<WebElement> dropdown = driver.findElements(By.id("billing-address-select"));

        if (!dropdown.isEmpty()) {
            Select select = new Select(dropdown.get(0));
            String selectedText = select.getFirstSelectedOption().getText();

            // Si no está en "New Address", seleccionarlo
            if (!selectedText.contains("New Address")) {
                try {
                    select.selectByVisibleText("New Address");
                    wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("BillingNewAddress_FirstName")));
                    fillForm(first, last, email, city, address, zip, phone);
                } catch (Exception e) {
                    // Si no existe la opción "New Address", continuar con la dirección existente
                }
            } else {
                // Ya está en New Address, llenar el formulario
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("BillingNewAddress_FirstName")));
                fillForm(first, last, email, city, address, zip, phone);
            }
        } else {
            // No hay dropdown, llenar el formulario directamente
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("BillingNewAddress_FirstName")));
            fillForm(first, last, email, city, address, zip, phone);
        }

        clickContinue("#billing-buttons-container");
    }

    private void fillForm(String first, String last, String email,
                           String city, String address, String zip, String phone) {
        clearAndType(By.id("BillingNewAddress_FirstName"), first);
        clearAndType(By.id("BillingNewAddress_LastName"), last);
        clearAndType(By.id("BillingNewAddress_Email"), email);
        new Select(driver.findElement(By.id("BillingNewAddress_CountryId")))
            .selectByVisibleText("Colombia");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("BillingNewAddress_City")));
        clearAndType(By.id("BillingNewAddress_City"), city);
        clearAndType(By.id("BillingNewAddress_Address1"), address);
        clearAndType(By.id("BillingNewAddress_ZipPostalCode"), zip);
        clearAndType(By.id("BillingNewAddress_PhoneNumber"), phone);
    }

    private void clearAndType(By locator, String value) {
        WebElement el = driver.findElement(locator);
        el.clear();
        el.sendKeys(value);
    }

    public void continueShipping() {
        // Esperar que cargue el paso de shipping address
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("#checkout-step-shipping")));
        clickContinue("#shipping-buttons-container");
    }

    public void continueShippingMethod() {
        // Esperar que cargue el paso de shipping method
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("#checkout-step-shipping-method")));
        clickContinue("#shipping-method-buttons-container");
    }

    public void selectCreditCard() {
        // Esperar que cargue el paso de payment method
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("#checkout-step-payment-method")));

        WebElement creditCard = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@type='radio'][following-sibling::label[normalize-space()='Credit Card']]")
        ));
        creditCard.click();
        clickContinue("#payment-method-buttons-container");
    }

    public void fillPaymentInfo(String type, String holder, String number,
                                 String month, String year, String code) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("#checkout-step-payment-info")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CreditCardType")));
        new Select(driver.findElement(By.id("CreditCardType"))).selectByValue(type);
        driver.findElement(By.id("CardholderName")).sendKeys(holder);
        driver.findElement(By.id("CardNumber")).sendKeys(number);
        new Select(driver.findElement(By.id("ExpireMonth"))).selectByValue(month);
        new Select(driver.findElement(By.id("ExpireYear"))).selectByValue(year);
        driver.findElement(By.id("CardCode")).sendKeys(code);
        clickContinue("#payment-info-buttons-container");
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("#checkout-step-confirm-order")));
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("input[value='Confirm']")
        ));
        confirmBtn.click();
    }

    public String getOrderConfirmationMessage() {
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='title']/strong | //strong[contains(text(),'processed')]")
        ));
        return msg.getText();
    }
}