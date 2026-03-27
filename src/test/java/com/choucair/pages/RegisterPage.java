package com.choucair.pages;

import com.choucair.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public RegisterPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToRegister() {
        driver.get("https://demowebshop.tricentis.com/register");
    }

    public void fillRegisterForm(String first, String last, String emailVal, String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("gender-male"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))).sendKeys(first);
        driver.findElement(By.id("LastName")).sendKeys(last);
        driver.findElement(By.id("Email")).sendKeys(emailVal);
        driver.findElement(By.id("Password")).sendKeys(pass);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(pass);
    }

    public void clickRegister() {
        driver.findElement(By.id("register-button")).click();
    }

    public String getRegisterSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".result"))).getText();
    }
}