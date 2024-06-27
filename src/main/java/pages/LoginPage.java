package pages;

import objects.LoginObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage{
    WebDriver driver;
    LoginObject loginObject;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.loginObject = new LoginObject();
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(loginObject.getInputEmail());
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(loginObject.getInputPassword());
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submitButton = driver.findElement(loginObject.getSubmitBtn());
        submitButton.click();
    }
}
