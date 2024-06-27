package pages;

// LogoutPage.java

import objects.LoginObject;
import objects.LogoutObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage {
    WebDriver driver;
    LogoutObject logoutObject;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        this.logoutObject = new LogoutObject();
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(logoutObject.getInputEmail());
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(logoutObject.getInputPassword());
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submitButton = driver.findElement(logoutObject.getSubmitBtn());
        submitButton.click();
    }

    public void clickLogout() {
        WebElement logoutButton = driver.findElement(logoutObject.getLogoutBtn());
        logoutButton.click();
    }
}

