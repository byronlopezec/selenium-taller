package com.tcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TiendaTest {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            // Paso 1: Acceder a la página SauceDemo
            driver.get("https://www.saucedemo.com");
            driver.manage().window().maximize();  // Maximizar la ventana del navegador

            // Crear WebDriverWait para espera explícita (máximo 10 segundos)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Paso 2: Iniciar sesión
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

            // Enviar las credenciales
            usernameField.sendKeys("standard_user");
            passwordField.sendKeys("secret_sauce");
            loginButton.click();

            // Esperar que la página cargue correctamente después de iniciar sesión
            wait.until(ExpectedConditions.urlContains("inventory.html")); // Esperamos que la URL contenga 'inventory.html'

            // Paso 3: Seleccionar un producto y agregarlo al carrito
            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn_inventory']")));
            addToCartButton.click();

            // Esperamos un poco para asegurarnos de que el producto se ha agregado
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), "1"));

            // Paso 4: Ir al carrito de compras
            WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
            cartIcon.click();

            // Paso 5: Proceder al checkout
            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='checkout_button']")));
            checkoutButton.click();

            // Paso 6: Completar los detalles del checkout
            WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
            WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
            WebElement postalCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='btn_primary cart_button']")));

            firstNameField.sendKeys("Juan");
            lastNameField.sendKeys("Perez");
            postalCodeField.sendKeys("12345");
            continueButton.click();

            // Paso 7: Confirmación de la compra
            WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn_action cart_button']")));
            finishButton.click();

            // Mostrar mensaje de éxito
            System.out.println("Compra completada con éxito!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el navegador después de completar el flujo
            driver.quit();
        }
    }
}
